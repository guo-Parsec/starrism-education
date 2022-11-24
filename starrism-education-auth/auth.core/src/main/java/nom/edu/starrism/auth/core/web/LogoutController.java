package nom.edu.starrism.auth.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.auth.core.service.LogoutService;
import nom.edu.starrism.common.support.Carrier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>登出Web</p>
 *
 * @author guocq
 * @since 2022/11/10
 **/
@Api(value = "登出Web", tags = "登出Web接口")
@RestController
public class LogoutController {
    private final LogoutService logoutService;

    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    /**
     * <p>根据用户id登出系统</p>
     *
     * @param id 用户id
     * @return boolean
     * @author guocq
     * @date 2022/11/10 16:45
     */
    @ApiOperation(value = "登出系统")
    @PostMapping(value = "/logout.do")
    public Carrier<Boolean> logout(@RequestParam(required = false) Long id) {
        return Carrier.success(id == null ? logoutService.logout() : logoutService.logout(id));
    }
}
