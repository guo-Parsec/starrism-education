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
     * <p>获取用户的权限请求url列表</p>
     *
     * @param userId userId
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/11/4
     */
    Set<String> findPermissionUrlOfUser(Long userId);

    /**
     * <p>获取用户的权限请求url列表</p>
     *
     * @param roles roles
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/11/4
     */
    Set<String> findPermissionUrlOfRoles(Collection<Long> roles);

    /**
     * <p>根据用户id查询权限码</p>
     *
     * @param userId userId
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/11/7
     */
    Set<String> findPermissionCodeOfUser(Long userId);

    /**
     * <p>根据类别查询权限请求url路径</p>
     *
     * @param categoryCode 类别码
     * @return java.util.Set<java.lang.String>
     * @author guocq
     * @date 2022/11/10 14:29
     */
    Set<String> findPermissionUrlByCategory(String categoryCode);
}
