package nom.edu.starrism.admin.api.feign;

import nom.edu.starrism.common.pool.AppPool;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.SeResultCarrier;
import nom.edu.starrism.core.domain.vo.SeUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>用户对外服务接口</p>
 *
 * @author guocq
 * @since 2022/10/24
 **/
@FeignClient(name = AppPool.APPLICATION_ADMIN_NAME + UrlPool.ADMIN_USER_PREFIX, contextId = "sysUserClient")
public interface SysUserClient {
    /**
     * <p>根据{@code account}查询用户</p>
     *
     * @param account  账户
     * @param password 密码
     * @return {@link SeResultCarrier<SeUser>}
     * @author guocq
     * @date 2022/10/24 15:07
     */
    @GetMapping(value = "/find/by/account")
    SeResultCarrier<SeUser> findUserByAccount(@RequestParam("account") String account,
                                              @RequestParam("password") String password);
}
