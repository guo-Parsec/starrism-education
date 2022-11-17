package nom.edu.starrism.admin.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.admin.core.service.SysUserService;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.SeResultCarrier;
import nom.edu.starrism.core.context.SecurityContext;
import nom.edu.starrism.core.domain.vo.SeUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>系统用户管理Web</p>
 *
 * @author guocq
 * @since 2022/10/24
 **/
@Api(value = "系统用户管理Web", tags = "系统用户管理接口")
@RestController
@RequestMapping(value = UrlPool.ADMIN_USER_PREFIX)
public class SysUserController {
    private final SysUserService sysUserService;

    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * <p>根据{@code account}查询用户</p>
     *
     * @param account  账户
     * @param password 密码
     * @return {@link SeResultCarrier<SeUser>}
     * @author guocq
     * @date 2022/10/24 15:07
     */
    @ApiOperation(value = "根据账户查询用户")
    @GetMapping(value = "/find/by/account")
    public SeResultCarrier<SeUser> findUserByAccount(@RequestParam("account") String account,
                                                     @RequestParam("password") String password) {
        SeUser seUser = sysUserService.findUserByAccount(account, password);
        return SeResultCarrier.success(seUser);
    }

    @ApiOperation(value = "当前用户是否登录成功")
    @GetMapping(value = "/login")
    public SeResultCarrier<Boolean> isLogin() {
        return SeResultCarrier.success(SecurityContext.isCertificated());
    }
}
