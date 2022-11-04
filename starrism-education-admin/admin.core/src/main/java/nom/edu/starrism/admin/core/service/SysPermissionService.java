package nom.edu.starrism.admin.core.service;

import java.util.Collection;
import java.util.Set;

/**
 * <p>系统权限服务类接口</p>
 *
 * @author hedwing
 * @since 2022/11/4
 **/
public interface SysPermissionService {
    /**
     * <p>获取用户的权限列表</p>
     *
     * @param userId userId
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/11/4
     */
    Set<String> findPermissionOfUser(Long userId);

    /**
     * <p>获取用户的权限列表</p>
     *
     * @param roles roles
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/11/4
     */
    Set<String> findPermissionOfRoles(Collection<Long> roles);
}
