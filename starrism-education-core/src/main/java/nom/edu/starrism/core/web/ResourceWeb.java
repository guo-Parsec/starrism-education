package nom.edu.starrism.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.common.pool.AuthPool;
import nom.edu.starrism.common.support.Carrier;
import nom.edu.starrism.core.annotation.security.CheckRole;
import nom.edu.starrism.core.helper.ResourceHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>资源访问</p>
 *
 * @author guocq
 * @since 2022/11/18
 **/
@Api(value = "资源访问Web", tags = "资源访问接口")
@RestController
public class ResourceWeb {
    @ApiOperation(value = "查询用户权限：根据用户id查询权限url")
    @PostMapping(value = "/all/resource/generate.do")
    @CheckRole(AuthPool.DEFAULT_ADMIN)
    public Carrier<Map<String, Object>> generateAllResource() {
        return Carrier.success(ResourceHelper.findResources());
    }
}
