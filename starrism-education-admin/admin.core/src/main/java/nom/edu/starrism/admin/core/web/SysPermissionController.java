package nom.edu.starrism.admin.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.admin.core.service.SysPermissionService;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.SeResultCarrier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

/**
 * <p>系统权限管理Web</p>
 *
 * @author hedwing
 * @since 2022/11/6
 **/
@Api(value = "系统权限管理Web", tags = "系统权限管理接口")
@RestController
@RequestMapping(value = UrlPool.ADMIN_PERMISSION_PREFIX)
public class SysPermissionController {
    private final SysPermissionService sysPermissionService;

    public SysPermissionController(SysPermissionService sysPermissionService) {
        this.sysPermissionService = sysPermissionService;
    }

    @ApiOperation(value = "查询用户权限：根据用户id查询权限")
    @GetMapping(value = "/find/by/userId")
    public SeResultCarrier<Set<String>> findPermissionOfUser(@RequestParam(value = "userId") Long userId) {
        return SeResultCarrier.success(sysPermissionService.findPermissionOfUser(userId));
    }

    @ApiOperation(value = "查询角色权限：根据角色列表查询权限")
    @GetMapping(value = "/find/by/roles")
    public SeResultCarrier<Set<String>> findPermissionOfRoles(@RequestParam(value = "roles") Collection<Long> roles) {
        return SeResultCarrier.success(sysPermissionService.findPermissionOfRoles(roles));
    }
}
