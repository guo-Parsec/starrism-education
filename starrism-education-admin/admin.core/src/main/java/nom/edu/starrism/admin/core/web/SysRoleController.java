package nom.edu.starrism.admin.core.web;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.admin.api.domain.param.SysRolePageParam;
import nom.edu.starrism.admin.api.domain.vo.SysRoleVo;
import nom.edu.starrism.admin.core.service.SysRoleService;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.Carrier;
import nom.edu.starrism.core.annotation.api.ApiResource;
import nom.edu.starrism.core.type.AppTypes;
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
    @ApiResource(value = "admin:self-role:query", name = "获取角色码：根据用户id获取", app = AppTypes.ADMIN)
    public Carrier<Set<String>> findRoleCodesOfUser(@RequestParam(value = "userId") Long userId) {
        return Carrier.success(sysRoleService.findRoleCodesOfUser(userId));
    }

    @ApiOperation(value = "角色分页查询")
    @GetMapping(value = "/query")
    @ApiResource(value = "admin:role:query-page", name = "角色分页查询", app = AppTypes.ADMIN)
    public Carrier<PageInfo<SysRoleVo>> sysRolePageQuery(SysRolePageParam param) {
        return Carrier.success(sysRoleService.sysRolePageQuery(param));
    }
}
