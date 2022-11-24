package nom.edu.starrism.common.pool;

/**
 * <p>权限相关常量定义池</p>
 *
 * @author hedwing
 * @since 2022/8/25
 **/
public interface AuthPool {
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
    String DEFAULT_ADMIN = "full-admin";

    /**
     * 服务间调用请求头
     */
    String FEIGN_HEAD = "feign-client";
}
