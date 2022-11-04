package nom.edu.starrism.common.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import nom.edu.starrism.common.enums.SeCommonResultCode;
import nom.edu.starrism.common.exception.SeException;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.properties.TokenProperties;
import nom.edu.starrism.common.service.JwtTokenService;
import nom.edu.starrism.common.support.SePayload;
import nom.edu.starrism.common.util.DateTimeUtil;
import nom.edu.starrism.common.util.Md5Util;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;

/**
 * <p>默认jwt token服务实现类</p>
 *
 * @author hedwing
 * @since 2022/11/2
 **/
@Service(value = "defaultJwtTokenService")
public class DefaultJwtTokenServiceImpl implements JwtTokenService {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(DefaultJwtTokenServiceImpl.class);

    private final TokenProperties tokenProperties;

    public DefaultJwtTokenServiceImpl(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }


    /**
     * <p>token生成</p>
     *
     * @param payloadText 有效载荷文本
     * @return java.lang.String
     * @author hedwing
     * @since 2022/11/2
     */
    @Override
    public String generate(String payloadText) {
        try {
            //准备JWS-header
            JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256)
                    .type(JOSEObjectType.JWT).build();
            //将负载信息装载到payload
            Payload payload = new Payload(payloadText);
            //封装header和payload到JWS对象
            JWSObject jwsObject = new JWSObject(jwsHeader, payload);
            //创建HMAC签名器
            JWSSigner jwsSigner = new MACSigner(Md5Util.md5(tokenProperties.secret));
            //签名
            jwsObject.sign(jwsSigner);
            return jwsObject.serialize();
        } catch (JOSEException e) {
            LOGGER.error("[payloadText={}]生成token失败", payloadText, e);
            throw new SeException(SeCommonResultCode.FAILED, "生成token失败");
        }
    }

    /**
     * <p>验证token有效性</p>
     *
     * @param token token
     * @return boolean
     * @author hedwing
     * @since 2022/11/2
     */
    @Override
    public boolean verify(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            //创建HMAC验证器
            JWSVerifier jwsVerifier = new MACVerifier(Md5Util.md5(tokenProperties.secret));
            if (!jwsObject.verify(jwsVerifier)) {
                LOGGER.error("验证失败，原因：秘钥验证失败");
                return false;
            }
            String payloadText = jwsObject.getPayload().toString();
            SePayload sePayload = JSONObject.parseObject(payloadText, SePayload.class);
            if (DateTimeUtil.compareTo(sePayload.getExp(), LocalDateTime.now()) < 0) {
                LOGGER.error("验证失败，原因：已过期，过期时间为{}", DateTimeUtil.format(sePayload.getExp()));
                return false;
            }
        } catch (ParseException | JOSEException e) {
            LOGGER.error("验证失败，原因：", e);
            return false;
        }
        return true;
    }

    /**
     * <p>解析token</p>
     *
     * @param token token
     * @return boolean
     * @author hedwing
     * @since 2022/11/2
     */
    @Override
    public SePayload parse(String token) {
        try {
            if (!verify(token)) {
                throw new SeException(SeCommonResultCode.FAILED, "解析token失败,验证失败");
            }
            JWSObject jwsObject = JWSObject.parse(token);
            String payloadText = jwsObject.getPayload().toString();
            return JSONObject.parseObject(payloadText, SePayload.class);
        } catch (ParseException e) {
            LOGGER.error("解析失败，原因：", e);
            throw new SeException(SeCommonResultCode.FAILED, "解析token失败");
        }
    }
}
