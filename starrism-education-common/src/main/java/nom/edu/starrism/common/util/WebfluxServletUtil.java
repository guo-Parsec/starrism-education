package nom.edu.starrism.common.util;

import com.alibaba.fastjson2.JSONObject;
import nom.edu.starrism.common.enums.BaseRequest;
import nom.edu.starrism.common.enums.RequestEnum;
import nom.edu.starrism.common.support.Carrier;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

/**
 * <p>响应式web请求处理</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
public class WebfluxServletUtil {
    /**
     * <p>响应式web响应写入</p>
     *
     * @param response 响应
     * @param msg      错误信息
     * @return reactor.core.publisher.Mono<java.lang.Void>
     * @author hedwing
     * @since 2022/8/27
     */
    public static Mono<Void> write(ServerHttpResponse response, String msg) {
        return write(response, msg, BaseRequest.FAILED);
    }

    /**
     * <p>响应式web响应写入</p>
     *
     * @param response 响应
     * @param msg      错误信息
     * @param code     错误码
     * @return reactor.core.publisher.Mono<java.lang.Void>
     * @author hedwing
     * @since 2022/8/27
     */
    public static Mono<Void> write(ServerHttpResponse response, String msg, RequestEnum code) {
        return write(response, HttpStatus.OK, msg, code);
    }

    /**
     * <p>响应式web响应写入</p>
     *
     * @param response 响应
     * @param status   http状态
     * @param msg      错误信息
     * @param code     错误码
     * @return reactor.core.publisher.Mono<java.lang.Void>
     * @author hedwing
     * @since 2022/8/27
     */
    public static Mono<Void> write(ServerHttpResponse response, HttpStatus status, String msg, RequestEnum code) {
        return write(response, MediaType.APPLICATION_JSON_VALUE, status, msg, code);
    }

    /**
     * <p>响应式web响应写入</p>
     *
     * @param response    响应
     * @param contentType HTTP Content-Type标头字段
     * @param status      http状态
     * @param msg         错误信息
     * @param code        错误码
     * @return reactor.core.publisher.Mono<java.lang.Void>
     * @author hedwing
     * @since 2022/8/27
     */
    public static Mono<Void> write(ServerHttpResponse response, String contentType, HttpStatus status, String msg, RequestEnum code) {
        response.setStatusCode(status);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, contentType);
        Carrier<Void> result = Carrier.failed(code, msg);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(result).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}
