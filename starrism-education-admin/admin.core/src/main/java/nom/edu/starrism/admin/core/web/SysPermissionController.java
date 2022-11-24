package nom.edu.starrism.admin.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.admin.core.service.SysPermissionService;
import nom.edu.starrism.common.pool.AuthPool;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.Carrier;
import nom.edu.starrism.core.annotation.api.ApiResource;
import nom.edu.starrism.core.annotation.security.CheckPermission;
import nom.edu.starrism.core.annotation.security.CheckRole;
import nom.edu.starrism.core.type.AppTypes;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
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

    @ApiOperation(value = "生成权限到数据库")
    @PostMapping(value = "/generate/into-db.do")
    @CheckRole(AuthPool.DEFAULT_ADMIN)
    public Carrier<Map<String, Long>> generatePermissionIntoDb(@RequestBody String cacheUuid) {
        return Carrier.success(sysPermissionService.generatePermissionIntoDb(cacheUuid));
    }

    @ApiOperation(value = "查询用户权限：根据用户id查询权限url")
    @GetMapping(value = "/find/url/by/userId")
    @CheckPermission("admin:self-permission:query")
    @ApiResource(value = "admin:self-permission:query", name = "字典类别数据更新", app = AppTypes.ADMIN)
    public Carrier<Set<String>> findPermissionUrlOfUser(@RequestParam(value = "userId") Long userId) {
        return Carrier.success(sysPermissionService.findPermissionUrlOfUser(userId));
    }

    @ApiOperation(value = "查询角色权限：根据角色列表查询权限url")
    @GetMapping(value = "/find/url/by/roles")
    @ApiResource(value = "admin:self-permission:query", name = "字典类别数据更新", app = AppTypes.ADMIN)
    public Carrier<Set<String>> findPermissionUrlOfRoles(@RequestParam(value = "roles") Collection<Long> roles) {
        return Carrier.success(sysPermissionService.findPermissionUrlOfRoles(roles));
    }

    @ApiOperation(value = "查询角色权限：根据用户id查询权限码")
    @GetMapping(value = "/find/code/by/userId")
    @ApiResource(value = "admin:self-permission:query", name = "查询角色权限：根据用户id查询权限码", app = AppTypes.ADMIN)
    public Carrier<Set<String>> findPermissionCodeOfUser(@RequestParam(value = "userId") Long userId) {
        return Carrier.success(sysPermissionService.findPermissionCodeOfUser(userId));
    }
}
