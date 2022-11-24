package nom.edu.starrism.auth.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.auth.core.service.SecurityService;
import nom.edu.starrism.common.pool.AuthPool;
import nom.edu.starrism.common.support.Carrier;
import nom.edu.starrism.core.annotation.api.ApiResource;
import nom.edu.starrism.core.annotation.security.CheckRole;
import nom.edu.starrism.core.domain.vo.SeUser;
import nom.edu.starrism.core.type.AppTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>安全认证Web</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
@Api(value = "安全认证Web", tags = "安全认证Web接口")
@RestController
public class SecurityController {
    private final SecurityService service;

    public SecurityController(SecurityService service) {
        this.service = service;
    }

    @CheckRole(AuthPool.DEFAULT_ADMIN)
    @ApiOperation(value = "查询全部在线用户")
    @GetMapping(value = "/find/all/online/user")
    @ApiResource(value = "admin:online-user:query", name = "查询全部在线用户", app = AppTypes.AUTH)
    public Carrier<List<SeUser>> findAllOnlineUser() {
        return Carrier.success(service.findAllOnlineUser());
    }
}
