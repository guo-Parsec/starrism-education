package nom.edu.starrism.common.exception;

import lombok.Getter;
import nom.edu.starrism.common.enums.SeRestEnum;

/**
 * <p>全局异常类</p>
 *
 * @author hedwing
 * @since 2022/10/21
 **/
public class SeException extends RuntimeException {
    private static final long serialVersionUID = -6994937325789796715L;

    @Getter
    private SeRestEnum baseRestEnum;
    @Getter
    private Long code;
    @Getter
    private String message;

    public SeException(SeRestEnum baseRestEnum) {
        super(baseRestEnum.getMessage());
        this.baseRestEnum = baseRestEnum;
        this.message = baseRestEnum.getMessage();
    }

    public SeException(String message, Long code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public SeException(SeRestEnum baseRestEnum, String message) {
        super(message);
        this.baseRestEnum = baseRestEnum;
        this.message = message;
    }

    public SeException(SeRestEnum baseRestEnum, Throwable cause) {
        super(cause);
        this.baseRestEnum = baseRestEnum;
        this.message = baseRestEnum.getMessage();
    }

    public SeException(Throwable cause, SeRestEnum baseRestEnum, String message) {
        super(message, cause);
        this.baseRestEnum = baseRestEnum;
        this.message = message;
    }
}
