package nom.edu.starrism.auth.core.service.impl;

import nom.edu.starrism.admin.api.feign.SysPermissionClient;
import nom.edu.starrism.admin.api.feign.SysRoleClient;
import nom.edu.starrism.admin.api.feign.SysUserClient;
import nom.edu.starrism.auth.api.domain.param.UserLoginParam;
import nom.edu.starrism.core.domain.vo.AuthenticatedUser;
import nom.edu.starrism.auth.core.enums.SeAuthResultCode;
import nom.edu.starrism.auth.core.exception.AuthException;
import nom.edu.starrism.auth.core.service.LoginService;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.pool.AuthPool;
import nom.edu.starrism.common.pool.CorePool;
import nom.edu.starrism.common.properties.TokenProperties;
import nom.edu.starrism.common.service.RedisService;
import nom.edu.starrism.common.support.SeResultCarrier;
import nom.edu.starrism.common.util.UUIDGeneratorUtil;
import nom.edu.starrism.core.domain.vo.SeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * <p>登录服务接口实现类</p>
 *
 * @author hedwing
 * @since 2022/11/6
 **/
@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(LoginServiceImpl.class);
    @Resource
    private SysUserClient sysUserClient;
    @Resource
    private SysPermissionClient sysPermissionClient;
    @Resource
    private SysRoleClient sysRoleClient;
    private TokenProperties tokenProperties;
    private RedisService redisService;

    @Autowired
    public void setTokenProperties(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    /**
     * <p>登录方法</p>
     *
     * @param param param
     * @return {@link AuthenticatedUser}
     * @author hedwing
     * @since 2022/11/6
     */
    @Override
    public AuthenticatedUser login(UserLoginParam param) {
        String account = param.getAccount();
        String password = param.getPassword();
        LOGGER.debug("用户{}正在尝试登录系统", account);
        SeResultCarrier<SeUser> carrier = sysUserClient.findUserByAccount(account, password);
        SeUser userEntity = SeResultCarrier.getSuccessData(carrier);
        if (userEntity == null) {
            LOGGER.error("无法找到账户为{}的用户", account);
            throw new AuthException(SeAuthResultCode.ACCOUNT_OR_PASSWORD_ERROR);
        }
        if (!userEntity.isAuthenticated()) {
            LOGGER.error("账户{}密码验证失败", account);
            throw new AuthException(SeAuthResultCode.ACCOUNT_OR_PASSWORD_ERROR);
        }
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        authenticatedUser.setId(userEntity.getId());
        authenticatedUser.setUserEntity(userEntity);
        authenticatedUser.setTokenName(AuthPool.TOKEN_REQ_HEAD);
        fillPermissions(authenticatedUser);
        fillRoles(authenticatedUser);
        createToken(authenticatedUser);
        return authenticatedUser;
    }

    /**
     * <p>令牌生成</p>
     *
     * @param authenticatedUser 认证用户
     * @author hedwing
     * @since 2022/11/6
     */
    private void createToken(AuthenticatedUser authenticatedUser) {
        String tokenPlainContent = UUIDGeneratorUtil.uuidRaw();
        // 设置返回给前端的 token 内容
        authenticatedUser.setTokenContent(AuthPool.JWT_TOKEN_PREFIX + tokenPlainContent);
        // 根据 token 内容作为key保存真实的token数据
        String tokenKey = AuthPool.TOKEN_REDIS_KEY + CorePool.REDIS_KEY_SEPARATOR + tokenPlainContent;
        // 根据查询到的数据生成真实的token并保存在redis中 key为返回给前端的token
        redisService.set(tokenKey, authenticatedUser, tokenProperties.expire);
        // 根据查询到的数据生成真实的token并保存在redis中 key为返回给前端的token
        Long userId = authenticatedUser.getUserEntity().getId();
        String permissionsOfUserKey = AuthPool.JWT_TOKEN_HEADER + CorePool.REDIS_KEY_SEPARATOR + userId;
        SeResultCarrier<Set<String>> carrier = sysPermissionClient.findPermissionUrlOfUser(userId);
        Set<String> permissionUrlsOfUser = SeResultCarrier.getSuccessData(carrier);
        redisService.set(permissionsOfUserKey, permissionUrlsOfUser, tokenProperties.expire);
    }

    /**
     * <p>填充权限信息</p>
     *
     * @param authenticatedUser authenticatedUser
     * @author hedwing
     * @since 2022/11/7
     */
    private void fillPermissions(AuthenticatedUser authenticatedUser) {
        Long userId = authenticatedUser.getUserEntity().getId();
        SeResultCarrier<Set<String>> carrier = sysPermissionClient.findPermissionCodeOfUser(userId);
        authenticatedUser.setPermissions(SeResultCarrier.getSuccessData(carrier));
    }

    /**
     * <p>填充角色信息</p>
     *
     * @param authenticatedUser authenticatedUser
     * @author hedwing
     * @since 2022/11/7
     */
    private void fillRoles(AuthenticatedUser authenticatedUser) {
        Long userId = authenticatedUser.getUserEntity().getId();
        SeResultCarrier<Set<String>> carrier = sysRoleClient.findRoleCodesOfUser(userId);
        authenticatedUser.setRoles(SeResultCarrier.getSuccessData(carrier));
    }
}
