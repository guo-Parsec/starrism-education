package nom.edu.starrism.core.context;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import nom.edu.starrism.common.enums.SeCommonResultCode;
import nom.edu.starrism.common.exception.SeException;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.pool.AuthPool;
import nom.edu.starrism.common.service.RedisService;
import nom.edu.starrism.common.util.CollectionUtil;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.common.util.UUIDGeneratorUtil;
import nom.edu.starrism.core.domain.vo.AuthenticatedUser;
import nom.edu.starrism.data.component.SpringBean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * <p>认证上下文</p>
 *
 * @author hedwing
 * @since 2022/11/7
 **/
public class AuthContext {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(AuthContext.class);

    /**
     * <p>判断当前操作是否已经登录</p>
     *
     * @return boolean
     * @author hedwing
     * @since 2022/11/7
     */
    public static boolean isLogin() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.error("用户鉴权失败，原因：无法获取当前系统请求");
            return false;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        return isLogin(request.getHeader(AuthPool.TOKEN_REQ_HEAD));
    }

    /**
     * 获取用户可放行的url
     *
     * @param token 令牌
     * @return 可放行的url
     */
    public static Set<String> getReleaseUrlOfUser(String token) {
        RedisService redisService = SpringBean.getBean(RedisService.class);
        AuthenticatedUser authenticatedUser = getAuthenticatedUser(token);
        Long id = authenticatedUser.getUserEntity().getId();
        String key = StringUtil.redisKeyJoin(AuthPool.JWT_TOKEN_HEADER, id);
        Set<String> result = Sets.newHashSet();
        CollectionUtil.castCollection(redisService.get(key), result, String.class);
        return result;
    }

    /**
     * 获取真实token
     *
     * @param token 原始token
     * @return 真实token
     */
    private static String getRealToken(String token) {
        if (StringUtil.isBlank(token) || !token.startsWith(AuthPool.JWT_TOKEN_PREFIX)) {
            return StringUtil.EMPTY;
        }
        return token.replace(AuthPool.JWT_TOKEN_PREFIX, StringUtil.EMPTY);
    }


    /**
     * 根据token查询用户是否登录
     *
     * @param token 令牌
     * @return 是否登录 true 已登录 false 未登录
     */
    public static boolean isLogin(String token) {
        try {
            getAuthenticatedUser(token);
        } catch (SeException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.error("用户鉴权失败，原因：无法获取当前系统请求");
            throw new SeException(SeCommonResultCode.UNAUTHORIZED, "用户鉴权失败，原因：无法获取当前系统请求");
        }
        return requestAttributes.getRequest();
    }

    /**
     * 获取认证用户信息
     *
     * @return 认证用户信息
     */
    public static AuthenticatedUser getAuthenticatedUser() {
        return getAuthenticatedUser(getHttpServletRequest().getHeader(AuthPool.TOKEN_REQ_HEAD));
    }

    /**
     * 获取认证用户信息
     *
     * @param token 令牌
     * @return 认证用户信息
     */
    public static AuthenticatedUser getAuthenticatedUser(String token) {
        String realToken = getRealToken(token);
        if (StringUtil.isBlank(realToken)) {
            LOGGER.error("用户鉴权失败，原因：无法从当前系统请求中获取合法的token");
            throw new SeException(SeCommonResultCode.UNAUTHORIZED, "用户鉴权失败，原因：无法从当前系统请求中获取合法的token");
        }
        RedisService redisService = SpringBean.getBean(RedisService.class);
        AuthenticatedUser loginUser = (AuthenticatedUser) redisService.get(getTokenKey(realToken));
        if (AuthenticatedUser.isEmpty(loginUser)) {
            LOGGER.error("用户鉴权失败，原因：无法获取到登录信息，可能因为token已过期");
            throw new SeException(SeCommonResultCode.UNAUTHORIZED, "用户鉴权失败，原因：无法获取到登录信息，可能因为token已过期");
        }
        return loginUser;
    }

    /**
     * <p>登出系统</p>
     *
     * @author guocq
     * @date 2022/11/10 16:58
     */
    public static void logout() {
        AuthenticatedUser authenticatedUser = null;
        try {
            authenticatedUser = getAuthenticatedUser();
        } catch (SeException e) {
            LOGGER.warn("当前用户已退出系统");
            return;
        }
        Long cacheId = authenticatedUser.getId();
        String realToken = getRealToken(authenticatedUser.getTokenContent());
        RedisService redisService = SpringBean.getBean(RedisService.class);
        String tokenKey = StringUtil.redisKeyJoin(AuthPool.TOKEN_REDIS_KEY, realToken);
        String urlsKey = StringUtil.redisKeyJoin(AuthPool.JWT_TOKEN_HEADER, cacheId);
        redisService.del(Lists.newArrayList(tokenKey, urlsKey));
    }

    /**
     * 登出系统
     *
     * @param id 用户id
     */
    public static void logout(Long id) {
        AuthenticatedUser authenticatedUser = null;
        try {
            authenticatedUser = getAuthenticatedUser();
        } catch (SeException e) {
            LOGGER.warn("当前用户[id={}]已退出系统", id);
            return;
        }
        Long cacheId = authenticatedUser.getId();
        String account = authenticatedUser.getUserEntity().getAccount();
        String realToken = getRealToken(authenticatedUser.getTokenContent());
        if (!cacheId.equals(id)) {
            LOGGER.warn("当前用户[id={},account={}]已退出系统", cacheId, account);
            return;
        }
        RedisService redisService = SpringBean.getBean(RedisService.class);
        redisService.del(Lists.newArrayList(getTokenKey(realToken), getUserUrlsKey(cacheId)));
    }

    /**
     * 获取 token 存储的redis key
     *
     * @param tokenContent token内容 同返回给前端的tokenContent
     * @return token 存储的redis key
     */
    public static String getTokenKey(String tokenContent) {
        return StringUtil.redisKeyJoin(AuthPool.TOKEN_REDIS_KEY, tokenContent);
    }

    /**
     * 获取 用户权限url 存储的redis key
     *
     * @param userId 用户id
     * @return 用户权限url 存储的redis key
     */
    public static String getUserUrlsKey(Long userId) {
        return StringUtil.redisKeyJoin(AuthPool.JWT_TOKEN_HEADER, userId);
    }

}
