package nom.edu.starrism.auth.core.exception;

import lombok.Getter;
import nom.edu.starrism.auth.core.enums.AuthRequest;
import nom.edu.starrism.common.exception.CoreException;

/**
 * <p>认证异常类</p>
 *
 * @author hedwing
 * @since 2022/11/6
 **/
public class AuthException extends CoreException {
    private static final long serialVersionUID = 5844827963823153181L;
    @Getter
    private AuthRequest authResultCode;

    public AuthException(AuthRequest authResultCode) {
        super(authResultCode);
        this.authResultCode = authResultCode;
    }

    public AuthException(String message, Long code) {
        super(message,code);
    }

    public AuthException(AuthRequest authResultCode, String message) {
        super(authResultCode, message);
        this.authResultCode = authResultCode;
    }

    public AuthException(AuthRequest authResultCode, Throwable cause) {
        super(authResultCode, cause);
        this.authResultCode = authResultCode;
    }

    public AuthException(Throwable cause, AuthRequest authResultCode, String message) {
        super(cause, authResultCode, message);
        this.authResultCode = authResultCode;
    }
}
