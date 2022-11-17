package nom.edu.starrism.data.type;

import java.util.Locale;

/**
 * <p>请求类型</p>
 *
 * @author guocq
 * @since 2022/11/16
 **/
public enum RequestType {
    /**
     * GET
     */
    GET,
    /**
     * POST
     */
    POST,
    /**
     * PUT
     */
    PUT,
    /**
     * DELETE
     */
    DELETE;

    public static RequestType findRequestType(String method) {
        return RequestType.valueOf(method.toUpperCase(Locale.ROOT));
    }
}
