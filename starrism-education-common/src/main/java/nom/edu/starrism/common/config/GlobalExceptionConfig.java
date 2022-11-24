package nom.edu.starrism.common.config;

import nom.edu.starrism.common.enums.RequestEnum;
import nom.edu.starrism.common.exception.CoreException;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.support.Carrier;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>全局异常配置</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionConfig {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(GlobalExceptionConfig.class);

    /**
     * 参数校验失败异常
     *
     * @param exception 异常信息
     * @return 统一处理
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Carrier<Void> validException(MethodArgumentNotValidException exception) {
        ObjectError objectError = exception.getBindingResult().getAllErrors().get(0);
        LOGGER.error("参数校验失败", exception);
        return Carrier.validateFailed(objectError.getDefaultMessage());
    }

    /**
     * Starrism异常
     *
     * @param exception 异常信息
     * @return 统一处理
     */
    @ExceptionHandler({CoreException.class})
    public Carrier<Void> starrismExceptionHandler(CoreException exception) {
        LOGGER.error("The application run starrismException", exception);
        RequestEnum baseRestEnum = exception.getRequestEnum();
        if (baseRestEnum == null) {
            return Carrier.failed(exception.getMessage(), exception.getCode());
        }
        return Carrier.failed(exception.getRequestEnum(), exception.getMessage());
    }

    /**
     * 异常
     *
     * @param exception 异常信息
     * @return 统一处理
     */
    @ExceptionHandler({Exception.class})
    public Carrier<Void> defaultExceptionHandler(Exception exception) {
        LOGGER.error("The application run exception", exception);
        return Carrier.failed();
    }
}
