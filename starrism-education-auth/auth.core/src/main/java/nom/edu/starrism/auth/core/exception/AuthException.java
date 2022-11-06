package nom.edu.starrism.auth.core.exception;

import lombok.Getter;
import nom.edu.starrism.auth.core.enums.SeAuthResultCode;
import nom.edu.starrism.common.exception.SeException;

/**
 * <p>认证异常类</p>
 *
 * @author hedwing
 * @since 2022/11/6
 **/
public class AuthException extends SeException {
    private static final long serialVersionUID = 5844827963823153181L;
    @Getter
    private SeAuthResultCode authResultCode;

    public AuthException(SeAuthResultCode authResultCode) {
        super(authResultCode);
        this.authResultCode = authResultCode;
    }

    public AuthException(String message, Long code) {
        super(message,code);
    }

    public AuthException(SeAuthResultCode authResultCode, String message) {
        super(authResultCode, message);
        this.authResultCode = authResultCode;
    }

    public AuthException(SeAuthResultCode authResultCode, Throwable cause) {
        super(authResultCode, cause);
        this.authResultCode = authResultCode;
    }

    public AuthException(Throwable cause, SeAuthResultCode authResultCode, String message) {
        super(cause, authResultCode, message);
        this.authResultCode = authResultCode;
    }
}
