package nom.edu.starrism.common.exception;

import lombok.Getter;
import nom.edu.starrism.common.enums.RequestEnum;

/**
 * <p>全局异常类</p>
 *
 * @author hedwing
 * @since 2022/10/21
 **/
public class CoreException extends RuntimeException {
    private static final long serialVersionUID = -6994937325789796715L;

    @Getter
    private RequestEnum requestEnum;
    @Getter
    private Long code;
    @Getter
    private final String message;

    public CoreException(RequestEnum requestEnum) {
        super(requestEnum.getMessage());
        this.requestEnum = requestEnum;
        this.message = requestEnum.getMessage();
    }

    public CoreException(String message, Long code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CoreException(RequestEnum requestEnum, String message) {
        super(message);
        this.requestEnum = requestEnum;
        this.message = message;
    }

    public CoreException(RequestEnum requestEnum, Throwable cause) {
        super(cause);
        this.requestEnum = requestEnum;
        this.message = requestEnum.getMessage();
    }

    public CoreException(Throwable cause, RequestEnum requestEnum, String message) {
        super(message, cause);
        this.requestEnum = requestEnum;
        this.message = message;
    }
}
