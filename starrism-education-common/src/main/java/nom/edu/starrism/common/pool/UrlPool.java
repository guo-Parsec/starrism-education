package nom.edu.starrism.common.pool;

/**
 * <p>url常量池</p>
 *
 * @author guocq
 * @since 2022/10/24
 **/
public interface UrlPool {
    /**
     * 系统用户管理url前缀
     */
    String ADMIN_USER_PREFIX = "/sys/user";

    /**
     * 系统权限管理url前缀
     */
    String ADMIN_PERMISSION_PREFIX = "/sys/permission";

    /**
     * 全局统一路由
     */
    String GLOBAL_URL_PREFIX = "/se/v1";

    /**
     * token管理url前缀
     */
    String AUTH_TOKEN_PREFIX = "/token";

    /**
     * token管理url前缀
     */
    String AUTH_LOGIN_PREFIX = "/login";
}
