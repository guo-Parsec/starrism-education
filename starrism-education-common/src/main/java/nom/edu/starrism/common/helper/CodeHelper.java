package nom.edu.starrism.common.helper;

import nom.edu.starrism.common.enums.BaseRequest;
import nom.edu.starrism.common.exception.CoreException;
import nom.edu.starrism.common.logger.SeLogger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>代码辅助类</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
public class CodeHelper {
    /**
     * <p>错误信息抛出</p>
     *
     * @param logger  日志
     * @param message 错误信息提示
     * @param arg     参数
     * @author guocq
     * @date 2022/11/17 10:13
     */
    public static void throwError(final SeLogger logger, BaseRequest code, String message, Object arg) {
        logger.error(message, arg);
        throw new CoreException(code, TextHelper.build().format(message, arg));
    }

    /**
     * <p>错误信息抛出</p>
     *
     * @param logger  日志
     * @param message 错误信息提示
     * @param arg     参数
     * @author guocq
     * @date 2022/11/17 10:13
     */
    public static void throwError(final SeLogger logger, BaseRequest code, String message, Object... arg) {
        logger.error(message, arg);
        throw new CoreException(code, TextHelper.build().format(message, arg));
    }

    /**
     * <p>错误信息抛出 使用默认错误码</p>
     *
     * @param logger  日志
     * @param message 错误信息提示
     * @param arg     参数
     * @author guocq
     * @date 2022/11/17 10:23
     */
    public static void throwError(final SeLogger logger, String message, Object... arg) {
        throwError(logger, BaseRequest.FAILED, message, arg);
    }

    /**
     * <p>错误信息抛出 使用默认错误信息</p>
     *
     * @param logger 日志
     * @param code   错误码
     * @param arg    参数
     * @author guocq
     * @date 2022/11/17 10:24
     */
    public static void throwError(final SeLogger logger, BaseRequest code, Object... arg) {
        throwError(logger, code, code.getMessage(), arg);
    }

    /**
     * <p>错误信息抛出 使用默认错误信息</p>
     *
     * @param logger 日志
     * @param arg    参数
     * @author guocq
     * @date 2022/11/17 10:24
     */
    public static void throwError(final SeLogger logger, Object... arg) {
        throwError(logger, BaseRequest.FAILED, arg);
    }

    /**
     * 获取当前请求
     * @return 请求
     */
    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        return requestAttributes.getRequest();
    }
}
