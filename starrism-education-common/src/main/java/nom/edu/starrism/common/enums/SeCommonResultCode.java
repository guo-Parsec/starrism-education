package nom.edu.starrism.common.enums;

import lombok.Getter;

/**
 * <p>通用数据码枚举</p>
 *
 * @author hedwing
 * @since 2022/10/21
 **/
public enum SeCommonResultCode implements SeRestEnum {
    /**
     * 信息码
     */
    SUCCESS(200, "操作成功"),
    /**
     * 操作失败
     */
    FAILED(500, "操作失败"),
    /**
     * 参数检验失败
     */
    VALIDATE_FAILED(400, "参数检验失败"),
    /**
     * 暂未登录或token已经过期
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /**
     * 没有相关权限
     */
    FORBIDDEN(403, "没有相关权限");

    @Getter
    private final Long code;
    @Getter
    private final String message;

    SeCommonResultCode(Long code, String value) {
        this.code = code;
        this.message = value;
    }

    SeCommonResultCode(Integer code, String value) {
        this.code = Long.valueOf(code);
        this.message = value;
    }
}
