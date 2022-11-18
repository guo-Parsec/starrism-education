package nom.edu.starrism.core.type;

import lombok.Getter;
import nom.edu.starrism.common.pool.AppPool;
import nom.edu.starrism.common.util.StringUtil;

/**
 * <p>应用类型</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
public enum AppTypes {
    /**
     * 系统管理服务
     */
    ADMIN("系统管理服务", AppPool.APPLICATION_ADMIN_NAME, "/admin"),

    /**
     * 认证中心服务
     */
    AUTH("认证中心服务", AppPool.APPLICATION_AUTH_NAME, "/auth"),

    /**
     * 网关服务
     */
    GATE("网关服务", AppPool.APPLICATION_GATEWAY_NAME, StringUtil.EMPTY),

    ;

    @Getter
    private final String name;
    @Getter
    private final String urlPrefix;
    @Getter
    private final String code;

    AppTypes(String name, String code, String urlPrefix) {
        this.name = name;
        this.urlPrefix = urlPrefix;
        this.code = code;
    }
}
