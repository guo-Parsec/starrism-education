package nom.edu.starrism.common.config;

import nom.edu.starrism.common.enums.SeRestEnum;
import nom.edu.starrism.common.exception.SeException;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.support.SeResultCarrier;
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
    public SeResultCarrier<Void> validException(MethodArgumentNotValidException exception) {
        ObjectError objectError = exception.getBindingResult().getAllErrors().get(0);
        LOGGER.error("valid Param failed", exception);
        return SeResultCarrier.validateFailed(objectError.getDefaultMessage());
    }

    /**
     * Starrism异常
     *
     * @param exception 异常信息
     * @return 统一处理
     */
    @ExceptionHandler({SeException.class})
    public SeResultCarrier<Void> starrismExceptionHandler(SeException exception) {
        LOGGER.error("The application run starrismException", exception);
        SeRestEnum baseRestEnum = exception.getBaseRestEnum();
        if (baseRestEnum == null) {
            return SeResultCarrier.failed(exception.getMessage(), exception.getCode());
        }
        return SeResultCarrier.failed(exception.getBaseRestEnum(), exception.getMessage());
    }

    /**
     * 异常
     *
     * @param exception 异常信息
     * @return 统一处理
     */
    @ExceptionHandler({Exception.class})
    public SeResultCarrier<Void> defaultExceptionHandler(Exception exception) {
        LOGGER.error("The application run exception", exception);
        return SeResultCarrier.failed();
    }


}
