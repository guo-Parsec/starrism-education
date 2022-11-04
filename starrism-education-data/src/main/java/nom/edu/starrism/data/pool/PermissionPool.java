package nom.edu.starrism.data.pool;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * <p>权限相关常量池</p>
 *
 * @author hedwing
 * @since 2022/11/4
 **/
public interface PermissionPool {
    /**
     * 权限类别码 - 无需认证直接登录
     */
    String CATEGORY_NONE = "none";

    /**
     * 权限类别码 - 登录认证后即放行
     */
    String CATEGORY_LOGIN_AUTH = "login_auth";

    /**
     * 权限类别码 - 登录后拥有相关权限后可放行
     */
    String CATEGORY_PERMISSION_AUTH = "permission_auth";

    /**
     * 需要认证的分类
     */
    Set<String> CATEGORY_AUTH = Sets.newHashSet(CATEGORY_LOGIN_AUTH, CATEGORY_PERMISSION_AUTH);
}
