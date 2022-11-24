package nom.edu.starrism.admin.api.feign;

import nom.edu.starrism.common.pool.AppPool;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.Carrier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * <p>系统角色对外服务接口</p>
 *
 * @author hedwing
 * @since 2022/11/7
 **/
@FeignClient(name = AppPool.APPLICATION_ADMIN_NAME + UrlPool.ADMIN_ROLE_PREFIX, contextId = "sysRoleClient")
public interface SysRoleClient {
    /**
     * <p>获取角色码：根据用户id获取</p>
     *
     * @param userId userId
     * @return {@link Carrier <Set<String>>}
     * @author hedwing
     * @since 2022/11/7
     */
    @GetMapping(value = "/find/code/by/roles")
    Carrier<Set<String>> findRoleCodesOfUser(@RequestParam(value = "userId") Long userId);
}
