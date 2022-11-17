package nom.edu.starrism.core.context;

import com.google.common.collect.Lists;
import nom.edu.starrism.common.enums.SeCommonResultCode;
import nom.edu.starrism.common.exception.SeException;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.pool.AuthPool;
import nom.edu.starrism.common.properties.TokenProperties;
import nom.edu.starrism.common.service.RedisService;
import nom.edu.starrism.common.support.CodeHelper;
import nom.edu.starrism.common.util.CollectionUtil;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.common.util.UUIDGeneratorUtil;
import nom.edu.starrism.core.domain.vo.AuthenticatedUser;
import nom.edu.starrism.core.domain.vo.SeUser;
import nom.edu.starrism.core.pool.SecurityPool;
import nom.edu.starrism.core.support.SecurityHelper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>安全认证上下文</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
public class SecurityContext {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SecurityContext.class);

    /**
     * <p>登录操作</p>
     *
     * @param authenticatedUser 认证后的用户信息
     * @author guocq
     * @date 2022/11/17 14:24
     */
    public static void login(AuthenticatedUser authenticatedUser) {
        String tokenId = UUIDGeneratorUtil.uuidRaw();
        authenticatedUser.setTokenId(SecurityPool.Bearer + tokenId);
        Long userId = authenticatedUser.getId();
        RedisService redisService = SecurityHelper.redisService();
        TokenProperties tokenProperties = SecurityHelper.tokenProperties();
        redisService.set(SecurityHelper.generateSecurityTokenKey(tokenId), authenticatedUser, tokenProperties.expire);
        redisService.set(SecurityHelper.generateSecurityUserKey(userId), tokenId, tokenProperties.expire);
    }

    /**
     * <p>获取请求的登录凭证信息</p>
     *
     * @return {@link AuthenticatedUser}
     * @author guocq
     * @date 2022/11/17 15:00
     */
    public static AuthenticatedUser findCertificate() {
        String tokenText = CodeHelper.getHttpServletRequest().getHeader(SecurityPool.AUTHORIZATION);
        return findCertificate(tokenText);
    }

    /**
     * <p>根据提供的{@code tokenText}获取登录凭证信息</p>
     *
     * @param tokenText 从请求头中获得的令牌文本
     * @return {@link AuthenticatedUser}
     * @author guocq
     * @date 2022/11/17 14:48
     */
    public static AuthenticatedUser findCertificate(String tokenText) {
        String tokenId = SecurityHelper.findEffectiveTokenId(tokenText);
        if (StringUtil.isBlank(tokenId)) {
            LOGGER.error("获取登录凭证信息失败，无法从当前系统请求中获取合法的令牌id");
            throw new SeException(SeCommonResultCode.UNAUTHORIZED, "获取登录凭证信息失败");
        }
        RedisService redisService = SecurityHelper.redisService();
        AuthenticatedUser cert = (AuthenticatedUser) redisService.get(SecurityHelper.generateSecurityTokenKey(tokenId));
        if (AuthenticatedUser.isEmpty(cert)) {
            LOGGER.error("获取登录凭证信息失败，无法获取到登录信息，可能因为令牌已过期");
            throw new SeException(SeCommonResultCode.UNAUTHORIZED, "获取登录凭证信息失败");
        }
        return cert;
    }

    /**
     * <p>根据请求判断用户是否认证过</p>
     *
     * @return boolean
     * @author guocq
     * @date 2022/11/17 15:02
     */
    public static boolean isCertificated() {
        String tokenText = CodeHelper.getHttpServletRequest().getHeader(SecurityPool.AUTHORIZATION);
        return isCertificated(tokenText);
    }

    /**
     * <p>根据提供的{@code tokenText}判断用户是否认证过</p>
     *
     * @param tokenText 从请求头中获得的令牌文本
     * @return boolean
     * @author guocq
     * @date 2022/11/17 15:02
     */
    public static boolean isCertificated(String tokenText) {
        try {
            findCertificate(tokenText);
        } catch (SeException e) {
            return false;
        }
        return true;
    }

    /**
     * <p>登出系统</p>
     *
     * @author guocq
     * @date 2022/11/17 15:36
     */
    public static void logout() {
        AuthenticatedUser cert = null;
        try {
            cert = findCertificate();
            long userId = cert.getId();
            String tokenId = SecurityHelper.findEffectiveTokenId(cert.getTokenId());
            RedisService redisService = SecurityHelper.redisService();
            String tokenKey = SecurityHelper.generateSecurityTokenKey(tokenId);
            String userKey = SecurityHelper.generateSecurityUserKey(userId);
            redisService.del(Lists.newArrayList(tokenKey, userKey));
        } catch (SeException e) {
            LOGGER.warn("当前用户已退出系统");
            return;
        }
    }

    /**
     * <p>踢人下线</p>
     *
     * @param userId 用户id
     * @author guocq
     * @date 2022/11/17 15:39
     */
    public static void kickOut(Long userId) {
        RedisService redisService = SecurityHelper.redisService();
        String userKey = SecurityHelper.generateSecurityUserKey(userId);
        String tokenId = (String) redisService.get(SecurityHelper.generateSecurityUserKey(userId));
        String tokenKey = SecurityHelper.generateSecurityTokenKey(tokenId);
        redisService.del(Lists.newArrayList(tokenKey, userKey));
    }

    /**
     * <p>查询全部在线用户</p>
     *
     * @return java.util.List<nom.edu.starrism.core.domain.vo.SeUser>
     * @author guocq
     * @date 2022/11/17 15:50
     */
    public static List<SeUser> findOnlineUser() {
        RedisService redisService = SecurityHelper.redisService();
        String onlineSecurityTokenKey = SecurityHelper.generateOnlineSecurityToken();
        List<AuthenticatedUser> list = Lists.newArrayList();
        CollectionUtil.castCollection(redisService.getByPattern(onlineSecurityTokenKey), list, AuthenticatedUser.class);
        return list.stream().map(AuthenticatedUser::getUserEntity).collect(Collectors.toList());
    }
}
