package nom.edu.starrism.admin.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.common.pool.AuthPool;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.Carrier;
import nom.edu.starrism.core.annotation.api.ApiResource;
import nom.edu.starrism.core.annotation.security.CheckRole;
import nom.edu.starrism.core.helper.RsaHelper;
import nom.edu.starrism.core.type.AppTypes;
import nom.edu.starrism.core.type.PermissionCategory;
import org.springframework.web.bind.annotation.*;

/**
 * <p>系统RSA加解密管理Web</p>
 *
 * @author guocq
 * @since 2022/11/21
 **/
@Api(value = "系统RSA加解密管理Web", tags = "系统RSA加解密管理接口")
@RestController
@RequestMapping(value = UrlPool.ADMIN_RSA_PREFIX)
public class RsaWeb {
    @ApiOperation(value = "RSA数据加密")
    @PostMapping(value = "/encrypt.do")
    @CheckRole(value = AuthPool.DEFAULT_ADMIN)
    public Carrier<String> encrypt(@RequestBody String plainText) {
        return Carrier.success(RsaHelper.encrypt(plainText));
    }

    @ApiOperation(value = "RSA数据解密")
    @PostMapping(value = "/decrypt.do")
    @CheckRole(value = AuthPool.DEFAULT_ADMIN)
    public Carrier<String> decrypt(@RequestBody String encryptText) {
        return Carrier.success(RsaHelper.decrypt(encryptText));
    }

    @ApiOperation(value = "RSA公钥获取")
    @GetMapping(value = "/pub-key/query")
    @ApiResource(value = "admin:rsa-pub-key:query", name = "RSA公钥获取", app = AppTypes.ADMIN, category = PermissionCategory.authenticated_common)
    public Carrier<String> getPublicKey() {
        return Carrier.success(RsaHelper.getPublicKeyStr());
    }
}
