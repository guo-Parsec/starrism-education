package nom.edu.starrism.common.support;

import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.common.enums.BaseRequest;
import nom.edu.starrism.common.enums.RequestEnum;
import nom.edu.starrism.common.exception.CoreException;
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
public class Carrier<T> {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(Carrier.class);

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

    protected Carrier() {

    }

    Carrier(long code) {
        this.code = code;
    }

    Carrier(long code, T data) {
        this.code = code;
        this.data = data;
    }

    Carrier(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Carrier(RequestEnum baseRestEnum) {
        this.code = baseRestEnum.getCode();
        this.message = baseRestEnum.getMessage();
    }

    public Carrier(RequestEnum baseRestEnum, T data) {
        this.code = baseRestEnum.getCode();
        this.message = baseRestEnum.getMessage();
        this.data = data;
    }

    public Carrier(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     */
    public static <T> Carrier<T> success() {
        return new Carrier<T>(BaseRequest.SUCCESS);
    }

    /**
     * 空返回结果
     */
    public static <T> Carrier<T> empty() {
        return new Carrier<T>(BaseRequest.EMPTY);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> Carrier<T> success(T data) {
        return new Carrier<T>(BaseRequest.SUCCESS, data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> Carrier<T> success(T data, String message) {
        return new Carrier<T>(BaseRequest.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> Carrier<T> failed() {
        return new Carrier<T>(BaseRequest.FAILED);
    }

    /**
     * 失败返回结果
     *
     * @param baseRestEnum 错误码
     */
    public static <T> Carrier<T> failed(RequestEnum baseRestEnum) {
        return new Carrier<T>(baseRestEnum);
    }

    /**
     * 失败返回结果
     *
     * @param baseRestEnum 错误码
     * @param message      错误信息
     */
    public static <T> Carrier<T> failed(RequestEnum baseRestEnum, String message) {
        return new Carrier<T>(baseRestEnum.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param code    错误码
     * @param message 错误信息
     */
    public static <T> Carrier<T> failed(String message, Long code) {
        return new Carrier<T>(code, message, null);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> Carrier<T> validateFailed() {
        return new Carrier<T>(BaseRequest.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> Carrier<T> validateFailed(String message) {
        return new Carrier<T>(BaseRequest.VALIDATE_FAILED.getCode(), message);
    }

    /**
     * 未登录返回结果
     */
    public static <T> Carrier<T> unauthorized(T data) {
        return new Carrier<T>(BaseRequest.UNAUTHORIZED, data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> Carrier<T> forbidden(T data) {
        return new Carrier<T>(BaseRequest.FORBIDDEN, data);
    }

    /**
     * <p>获取成功请求数据</p>
     *
     * @param commonResult 统一响应格式
     * @return T
     * @author hedwing
     * @since 2022/8/27
     */
    public static <T> T getSuccessData(Carrier<T> commonResult) {
        if (commonResult == null) {
            LOGGER.error("请求结果集为空，可能为请求错误");
            throw new RuntimeException("The request result set is empty, possibly a request error");
        }
        long resultCode = commonResult.getCode();
        if (!BaseRequest.SUCCESS.getCode().equals(resultCode)) {
            LOGGER.error("请求信息码为{}，可能为请求失败", resultCode);
            throw new CoreException(commonResult.getMessage(), resultCode);
        }
        return commonResult.getData();
    }

    public static <T> boolean isSuccess(Carrier<T> commonResult) {
        try {
            getSuccessData(commonResult);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
