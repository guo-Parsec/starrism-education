package nom.edu.starrism.data.pool;

import com.google.common.collect.Sets;
import nom.edu.starrism.common.pool.AuthPool;

import java.util.Set;

/**
 * <p>权限相关常量池</p>
 *
 * @author hedwing
 * @since 2022/11/4
 **/
public interface PermissionPool {
    /**
     * 权限类别码 - 登录后拥有相关权限后可放行
     */
    String CATEGORY_PERMISSION_AUTH = "authenticated_permission";

    /**
     * 需要认证的分类
     */
    Set<String> CATEGORY_AUTH = Sets.newHashSet(AuthPool.PERMISSION_CATEGORY_AUTHENTICATED_COMMON, CATEGORY_PERMISSION_AUTH);
}
