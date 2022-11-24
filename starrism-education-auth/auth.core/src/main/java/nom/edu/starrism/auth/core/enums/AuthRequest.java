package nom.edu.starrism.auth.core.enums;

import lombok.Getter;
import nom.edu.starrism.common.enums.RequestEnum;

/**
 * <p>认证服务数据码枚举</p>
 *
 * @author hedwing
 * @since 2022/11/6
 **/
public enum AuthRequest implements RequestEnum {
    /**
     * 客户端认证失败
     */
    AUTHENTICATION_FAILED(1000, "认证失败"),
    /**
     * 账户或密码错误
     */
    ACCOUNT_OR_PASSWORD_ERROR(1001, "账户或密码错误"),
    /**
     * 用户已被锁定
     */
    ACCOUNT_LOCKED(1002, "用户已被锁定"),

    /**
     * 无效的token
     */
    INVALID_TOKEN(1003, "无效的token"),

    /**
     * 无权限访问
     */
    NO_PERMISSION(1004, "无权限访问");;
    @Getter
    private final Long code;
    @Getter
    private final String message;

    AuthRequest(Long code, String value) {
        this.code = code;
        this.message = value;
    }

    AuthRequest(Integer code, String value) {
        this.code = Long.valueOf(code);
        this.message = value;
    }
}
