package nom.edu.starrism.admin.api.feign;

import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.admin.api.domain.vo.SysMenuVo;
import nom.edu.starrism.common.pool.AppPool;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.SeResultCarrier;
import nom.edu.starrism.core.annotation.api.ApiResource;
import nom.edu.starrism.core.type.AppTypes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * <p>系统菜单对外服务接口</p>
 *
 * @author guocq
 * @since 2022/11/18
 **/
@FeignClient(name = AppPool.APPLICATION_ADMIN_NAME + UrlPool.ADMIN_MENU_PREFIX, contextId = "sysMenuClient")
public interface SysMenuClient {
    /**
     * <p>查询用户的菜单id列表</p>
     *
     * @param userId 用户id
     * @return 菜单id列表
     * @author guocq
     * @date 2022/11/18 16:30
     */
    @GetMapping(value = "/find/menu-ids/of/user-id")
    SeResultCarrier<Set<Long>> findMenuIdsOfUser(@RequestParam("userId") Long userId);

    /**
    * <p>查询用户的菜单树形结构列表</p>
    * @param userId 用户id
    * @return 用户的菜单树形结构列表
    * @author guocq
    * @date 2022/11/18 17:25
    */
    @GetMapping(value = "/find/menu-tree/of/user-id")
    SeResultCarrier<List<SysMenuVo>> findMenuTreesOfUser(@RequestParam(value = "userId", required = false) Long userId);
}
