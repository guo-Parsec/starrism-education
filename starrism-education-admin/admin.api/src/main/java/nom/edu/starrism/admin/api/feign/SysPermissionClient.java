package nom.edu.starrism.admin.api.feign;

import nom.edu.starrism.common.pool.AppPool;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.SeResultCarrier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Set;

/**
 * <p>系统权限对外服务接口</p>
 *
 * @author hedwing
 * @since 2022/11/6
 **/
@FeignClient(name = AppPool.APPLICATION_ADMIN_NAME + UrlPool.ADMIN_PERMISSION_PREFIX, contextId = "sysPermissionClient")
public interface SysPermissionClient {
    /**
     * <p>查询用户权限：根据用户id查询权限</p>
     *
     * @param userId userId
     * @return @return {@link SeResultCarrier<Set<String>>}
     * @author hedwing
     * @since 2022/11/6
     */
    @GetMapping(value = "/find/by/userId")
    SeResultCarrier<Set<String>> findPermissionOfUser(@RequestParam(value = "userId") Long userId);

    /**
     * <p>查询角色权限：根据角色列表查询权限</p>
     *
     * @param roles roles
     * @return {@link SeResultCarrier<Set<String>>}
     * @author hedwing
     * @since 2022/11/6
     */
    @GetMapping(value = "/find/by/roles")
    SeResultCarrier<Set<String>> findPermissionOfRoles(@RequestParam(value = "roles") Collection<Long> roles);
}
