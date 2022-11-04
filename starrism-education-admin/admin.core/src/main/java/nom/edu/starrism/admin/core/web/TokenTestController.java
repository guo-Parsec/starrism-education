package nom.edu.starrism.admin.core.web;

import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.properties.TokenProperties;
import nom.edu.starrism.common.service.JwtTokenService;
import nom.edu.starrism.common.support.SePayload;
import nom.edu.starrism.common.support.SeResultCarrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>Token校验Web</p>
 *
 * @author hedwing
 * @since 2022/11/2
 **/
@Api(value = "Token校验Web", tags = "Token校验接口")
@RestController(value = UrlPool.AUTH_TOKEN_PREFIX)
public class TokenTestController {

    private JwtTokenService jwtTokenService;

    private TokenProperties tokenProperties;


    @Autowired
    @Qualifier(value = "defaultJwtTokenService")
    public void setJwtTokenService(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Autowired
    public void setTokenProperties(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    private String defaultDataHandler(String account, Long expireSecond) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expire = now.plusSeconds(expireSecond == null || expireSecond.equals(0L) ? tokenProperties.expire : expireSecond);
        Set<String> authorities = Sets.newHashSet();
        authorities.add("ADMIN");
        SePayload sePayload = SePayload.builder().subject(account)
                .iat(now)
                .exp(expire)
                .account(account)
                .authorities(authorities)
                .build();
        return JSONObject.toJSONString(sePayload);
    }

    @ApiOperation(value = "生成token")
    @PostMapping(value = "/create.do")
    public SeResultCarrier<String> doCreate(@RequestParam String account,@RequestParam(required = false) Long expireSecond) {
        return SeResultCarrier.success(jwtTokenService.generate(defaultDataHandler(account, expireSecond)));
    }

    @ApiOperation(value = "验证token")
    @PostMapping(value = "/verify.do")
    public SeResultCarrier<Boolean> doVerify(String token) {
        return SeResultCarrier.success(jwtTokenService.verify(token));
    }

    @ApiOperation(value = "解析token")
    @PostMapping(value = "/parse.do")
    public SeResultCarrier<SePayload> doParse(String token) {
        return SeResultCarrier.success(jwtTokenService.parse(token));
    }

}
