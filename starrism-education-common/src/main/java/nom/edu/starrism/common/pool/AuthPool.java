package nom.edu.starrism.common.pool;

import nom.edu.starrism.common.util.StringUtil;

/**
 * <p>权限相关常量定义池</p>
 *
 * @author hedwing
 * @since 2022/8/25
 **/
public interface AuthPool {
    String TOKEN_REQ_HEAD = "Authorization";

    /**
     * 认证信息Http请求头
     */
    String JWT_TOKEN_HEADER = StringUtil.redisKeyJoin(RedisPool.BASE_REDIS_KEY, TOKEN_REQ_HEAD);

    /**
     * 用户标识
     */
    String USER_TOKEN_KEY = "token";

    /**
     * token redis key
     */
    String TOKEN_REDIS_KEY = StringUtil.redisKeyJoin(JWT_TOKEN_HEADER, USER_TOKEN_KEY);

    /**
     * JWT令牌前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 匿名认证
     */
    String PERMISSION_CATEGORY_ANONYMOUS = "anonymous";

    /**
     * 登录认证
     */
    String PERMISSION_CATEGORY_AUTHENTICATED_COMMON = "authenticated_common";

    /**
     * 默认全权限
     */
    String DEFAULT_ALL_PERMISSION = "*:*:*";

    /**
     * 默认管理员
     */
    String DEFAULT_ADMIN = "admin";
}
