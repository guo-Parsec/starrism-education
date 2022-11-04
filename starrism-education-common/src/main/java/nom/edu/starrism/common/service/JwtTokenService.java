package nom.edu.starrism.common.service;


import nom.edu.starrism.common.support.SePayload;

/**
 * <p>JwtToken服务类</p>
 *
 * @author hedwing
 * @since 2022/11/2
 **/
public interface JwtTokenService {
    /**
     * <p>token生成</p>
     *
     * @param payloadText 有效载荷文本
     * @return java.lang.String
     * @author hedwing
     * @since 2022/11/2
     */
    String generate(String payloadText);

    /**
     * <p>验证token有效性</p>
     *
     * @param token token
     * @return boolean
     * @author hedwing
     * @since 2022/11/2
     */
    boolean verify(String token);

    /**
     * <p>解析token</p>
     *
     * @param token token
     * @return boolean
     * @author hedwing
     * @since 2022/11/2
     */
    SePayload parse(String token);
}
