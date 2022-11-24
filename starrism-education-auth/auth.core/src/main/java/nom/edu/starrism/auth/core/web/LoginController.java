package nom.edu.starrism.auth.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.auth.api.domain.param.UserLoginParam;
import nom.edu.starrism.auth.core.service.LoginService;
import nom.edu.starrism.common.support.Carrier;
import nom.edu.starrism.core.domain.vo.AuthenticatedUser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>登录Web</p>
 *
 * @author hedwing
 * @since 2022/11/6
 **/
@Api(value = "登录Web", tags = "登录Web接口")
@RestController
public class LoginController {
    private final LoginService loginService;


    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ApiOperation(value = "登录认证")
    @PostMapping(value = "/login.do")
    public Carrier<AuthenticatedUser> doLogin(@Validated @RequestBody UserLoginParam param) {
        return Carrier.success(loginService.login(param));
    }
}
