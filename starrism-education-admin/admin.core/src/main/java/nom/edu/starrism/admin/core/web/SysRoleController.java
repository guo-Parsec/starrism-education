package nom.edu.starrism.admin.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.admin.core.service.SysRoleService;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.SeResultCarrier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * <p>系统角色管理Web</p>
 *
 * @author hedwing
 * @since 2022/11/7
 **/
@Api(value = "系统角色管理Web", tags = "系统角色管理接口")
@RestController
@RequestMapping(value = UrlPool.ADMIN_ROLE_PREFIX)
public class SysRoleController {
    private final SysRoleService sysRoleService;

    public SysRoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @ApiOperation(value = "获取角色码：根据用户id获取")
    @GetMapping(value = "/find/code/by/roles")
    public SeResultCarrier<Set<String>> findRoleCodesOfUser(@RequestParam(value = "userId") Long userId) {
        return SeResultCarrier.success(sysRoleService.findRoleCodesOfUser(userId));
    }
}
