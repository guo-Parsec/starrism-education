package nom.edu.starrism.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Jwt Token 属性集成类</p>
 *
 * @author hedwing
 * @since 2022/11/2
 **/
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "starrism.jwt")
public class TokenProperties {
    /**
     * token 返回头部
     */
    public String header;

    /**
     * token 前缀
     */
    public String prefix;

    /**
     * 签名密钥
     */
    public String secret;

    /**
     * 有效期
     */
    public Long expire;
}