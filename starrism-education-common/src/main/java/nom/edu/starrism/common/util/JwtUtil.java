package nom.edu.starrism.common.util;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;

/**
 * <p> Jwt Token 工具类</p>
 *
 * @author hedwing
 * @since 2022/11/2
 **/
public class JwtUtil {
    public static String create(String subject) {
        // 创建JWS头，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
        return null;
    }
}
