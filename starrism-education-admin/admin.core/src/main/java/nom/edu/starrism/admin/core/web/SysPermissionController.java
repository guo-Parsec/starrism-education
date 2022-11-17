package nom.edu.starrism.admin.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.admin.core.service.SysPermissionService;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.SeResultCarrier;
import nom.edu.starrism.core.annotation.log.LogWrite;
import nom.edu.starrism.core.annotation.security.CheckPermission;
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

    @ApiOperation(value = "查询用户权限：根据用户id查询权限url")
    @GetMapping(value = "/find/url/by/userId")
    @CheckPermission("admin:self-permission:query")
    public SeResultCarrier<Set<String>> findPermissionUrlOfUser(@RequestParam(value = "userId") Long userId) {
        return SeResultCarrier.success(sysPermissionService.findPermissionUrlOfUser(userId));
    }

    @ApiOperation(value = "查询角色权限：根据角色列表查询权限url")
    @GetMapping(value = "/find/url/by/roles")
    public SeResultCarrier<Set<String>> findPermissionUrlOfRoles(@RequestParam(value = "roles") Collection<Long> roles) {
        return SeResultCarrier.success(sysPermissionService.findPermissionUrlOfRoles(roles));
    }

    @ApiOperation(value = "查询角色权限：根据用户id查询权限码")
    @GetMapping(value = "/find/code/by/userId")
    public SeResultCarrier<Set<String>> findPermissionCodeOfUser(@RequestParam(value = "userId") Long userId) {
        return SeResultCarrier.success(sysPermissionService.findPermissionCodeOfUser(userId));
    }
}
