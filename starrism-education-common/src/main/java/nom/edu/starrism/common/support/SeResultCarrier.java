package nom.edu.starrism.common.support;

import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.common.enums.SeCommonResultCode;
import nom.edu.starrism.common.enums.SeRestEnum;
import nom.edu.starrism.common.exception.SeException;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;

/**
 * <p>结果载体</p>
 *
 * @author hedwing
 * @since 2022/10/21
 **/
@Setter
@Getter
public class SeResultCarrier<T> {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SeResultCarrier.class);

    /**
     * 信息码
     */
    private long code;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据载体
     */
    private T data;

    protected SeResultCarrier() {

    }

    SeResultCarrier(long code) {
        this.code = code;
    }

    SeResultCarrier(long code, T data) {
        this.code = code;
        this.data = data;
    }

    SeResultCarrier(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public SeResultCarrier(SeRestEnum baseRestEnum) {
        this.code = baseRestEnum.getCode();
        this.message = baseRestEnum.getMessage();
    }

    public SeResultCarrier(SeRestEnum baseRestEnum, T data) {
        this.code = baseRestEnum.getCode();
        this.message = baseRestEnum.getMessage();
        this.data = data;
    }

    public SeResultCarrier(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     */
    public static <T> SeResultCarrier<T> success() {
        return new SeResultCarrier<T>(SeCommonResultCode.SUCCESS);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> SeResultCarrier<T> success(T data) {
        return new SeResultCarrier<T>(SeCommonResultCode.SUCCESS, data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> SeResultCarrier<T> success(T data, String message) {
        return new SeResultCarrier<T>(SeCommonResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> SeResultCarrier<T> failed() {
        return new SeResultCarrier<T>(SeCommonResultCode.FAILED);
    }

    /**
     * 失败返回结果
     *
     * @param baseRestEnum 错误码
     */
    public static <T> SeResultCarrier<T> failed(SeRestEnum baseRestEnum) {
        return new SeResultCarrier<T>(baseRestEnum);
    }

    /**
     * 失败返回结果
     *
     * @param baseRestEnum 错误码
     * @param message      错误信息
     */
    public static <T> SeResultCarrier<T> failed(SeRestEnum baseRestEnum, String message) {
        return new SeResultCarrier<T>(baseRestEnum.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param code    错误码
     * @param message 错误信息
     */
    public static <T> SeResultCarrier<T> failed(String message, Long code) {
        return new SeResultCarrier<T>(code, message, null);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> SeResultCarrier<T> validateFailed() {
        return new SeResultCarrier<T>(SeCommonResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> SeResultCarrier<T> validateFailed(String message) {
        return new SeResultCarrier<T>(SeCommonResultCode.VALIDATE_FAILED.getCode(), message);
    }

    /**
     * 未登录返回结果
     */
    public static <T> SeResultCarrier<T> unauthorized(T data) {
        return new SeResultCarrier<T>(SeCommonResultCode.UNAUTHORIZED, data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> SeResultCarrier<T> forbidden(T data) {
        return new SeResultCarrier<T>(SeCommonResultCode.FORBIDDEN, data);
    }

    /**
     * <p>获取成功请求数据</p>
     *
     * @param commonResult 统一响应格式
     * @return T
     * @author hedwing
     * @since 2022/8/27
     */
    public static <T> T getSuccessData(SeResultCarrier<T> commonResult) {
        isSuccess(commonResult);
        return commonResult.getData();
    }

    public static <T> boolean isSuccess(SeResultCarrier<T> commonResult) {
        if (commonResult == null) {
            LOGGER.error("请求结果集为空，可能为请求错误");
            throw new RuntimeException("The request result set is empty, possibly a request error");
        }
        long resultCode = commonResult.getCode();
        if (!SeCommonResultCode.SUCCESS.getCode().equals(resultCode)) {
            LOGGER.error("请求信息码为{}，可能为请求失败", resultCode);
            throw new SeException(commonResult.getMessage(), resultCode);
        }
        return true;
    }

}
