package nom.edu.starrism.common.pool;

/**
 * <p>应用常量池</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
public interface AppPool {
    /**
     * 系统管理服务名称
     */
    String APPLICATION_ADMIN_NAME = "starrism-education-admin/admin";

    /**
     * 认证中心服务名称
     */
    String APPLICATION_AUTH_NAME = "starrism-education-auth/auth";

    /**
     * 网关服务名称
     */
    String APPLICATION_GATEWAY_NAME = "starrism-education-gate";
}
