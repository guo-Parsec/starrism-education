package nom.edu.starrism.auth.core.service.impl;

import nom.edu.starrism.admin.api.domain.vo.SysMenuVo;
import nom.edu.starrism.admin.api.feign.SysMenuClient;
import nom.edu.starrism.admin.api.feign.SysPermissionClient;
import nom.edu.starrism.admin.api.feign.SysRoleClient;
import nom.edu.starrism.admin.api.feign.SysUserClient;
import nom.edu.starrism.auth.api.domain.param.UserLoginParam;
import nom.edu.starrism.auth.core.enums.SeAuthResultCode;
import nom.edu.starrism.auth.core.exception.AuthException;
import nom.edu.starrism.auth.core.service.LoginService;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.properties.TokenProperties;
import nom.edu.starrism.common.service.RedisService;
import nom.edu.starrism.common.support.SeResultCarrier;
import nom.edu.starrism.core.context.SecurityContext;
import nom.edu.starrism.core.domain.vo.AuthenticatedUser;
import nom.edu.starrism.core.domain.vo.SeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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
    @Resource
    private SysMenuClient sysMenuClient;
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
        AuthenticatedUser authenticatedUser = new AuthenticatedUser(userEntity);
        fillPermissions(authenticatedUser);
        fillRoles(authenticatedUser);
        fillUrls(authenticatedUser);
        fillMenuIds(authenticatedUser);
        fillMenus(authenticatedUser);
        SecurityContext.login(authenticatedUser);
        return authenticatedUser;
    }

    /**
     * <p>填充权限信息</p>
     *
     * @param authenticatedUser authenticatedUser
     * @author hedwing
     * @since 2022/11/7
     */
    private void fillPermissions(AuthenticatedUser authenticatedUser) {
        Long userId = authenticatedUser.getId();
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
        Long userId = authenticatedUser.getId();
        SeResultCarrier<Set<String>> carrier = sysRoleClient.findRoleCodesOfUser(userId);
        authenticatedUser.setRoles(SeResultCarrier.getSuccessData(carrier));
    }

    /**
     * <p>填充urls信息</p>
     *
     * @param authenticatedUser 认证用户
     * @author guocq
     * @date 2022/11/17 14:42
     */
    private void fillUrls(AuthenticatedUser authenticatedUser) {
        Long userId = authenticatedUser.getId();
        SeResultCarrier<Set<String>> carrier = sysPermissionClient.findPermissionUrlOfUser(userId);
        authenticatedUser.setUrls(SeResultCarrier.getSuccessData(carrier));
    }

    /**
     * <p>填充menuIds信息</p>
     *
     * @param authenticatedUser 认证用户
     * @author guocq
     * @date 2022/11/18 16:32
     */
    private void fillMenuIds(AuthenticatedUser authenticatedUser) {
        Long userId = authenticatedUser.getId();
        SeResultCarrier<Set<Long>> carrier = sysMenuClient.findMenuIdsOfUser(userId);
        authenticatedUser.setMenuIds(SeResultCarrier.getSuccessData(carrier));
    }

    /**
     * <p>填充menuIds信息</p>
     *
     * @param authenticatedUser 认证用户
     * @author guocq
     * @date 2022/11/18 16:32
     */
    private void fillMenus(AuthenticatedUser authenticatedUser) {
        Long userId = authenticatedUser.getId();
        SeResultCarrier<List<SysMenuVo>> carrier = sysMenuClient.findMenuTreesOfUser(userId);
        authenticatedUser.setMenus(SeResultCarrier.getSuccessData(carrier));
    }
}
