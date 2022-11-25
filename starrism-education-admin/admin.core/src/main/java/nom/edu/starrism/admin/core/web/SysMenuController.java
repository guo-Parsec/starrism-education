package nom.edu.starrism.admin.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.core.domain.vo.SysMenuVo;
import nom.edu.starrism.admin.core.service.SysMenuService;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.Carrier;
import nom.edu.starrism.core.annotation.api.ApiResource;
import nom.edu.starrism.core.type.AppTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>系统菜单管理Web</p>
 *
 * @author guocq
 * @since 2022/11/18
 **/
@Api(value = "系统菜单管理Web", tags = "系统菜单管理接口")
@RestController
@RequestMapping(value = UrlPool.ADMIN_MENU_PREFIX)
public class SysMenuController {
    private final SysMenuService service;

    public SysMenuController(SysMenuService service) {
        this.service = service;
    }

    @ApiOperation(value = "查询用户的菜单id列表")
    @ApiResource(value = "admin:self-menu:query", name = "查询用户的菜单id列表", app = AppTypes.ADMIN)
    @GetMapping(value = "/find/menu-ids/of/user-id")
    public Carrier<Set<Long>> findMenuIdsOfUser(@RequestParam("userId") Long userId) {
        return Carrier.success(service.findMenuIds(userId));
    }

    @ApiOperation(value = "查询用户的菜单树形结构列表")
    @ApiResource(value = "admin:self-menu:query", name = "查询用户的菜单树形结构列表", app = AppTypes.ADMIN)
    @GetMapping(value = "/find/menu-tree/of/user-id")
    public Carrier<List<SysMenuVo>> findMenuTreesOfUser(@RequestParam(value = "userId", required = false) Long userId) {
        if (userId == null) {
            return Carrier.success(service.findMenusOfCurrentUser());
        }
        return Carrier.success(service.findMenusOfUserId(userId, Boolean.TRUE));
    }
}
