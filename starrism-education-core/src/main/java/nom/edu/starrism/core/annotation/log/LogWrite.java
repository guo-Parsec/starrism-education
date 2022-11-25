package nom.edu.starrism.core.annotation.log;

import nom.edu.starrism.core.type.AppTypes;
import nom.edu.starrism.data.type.RequestType;

import java.lang.annotation.*;

/**
 * <p>日志写入</p>
 *
 * @author guocq
 * @since 2022/11/16
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogWrite {
    RequestType[] allowRequestType() default {RequestType.POST, RequestType.PUT, RequestType.DELETE};

    /**
     * 操作说明
     */
    String value() default "";

    /**
     * 应用
     */
    AppTypes app() default AppTypes.ADMIN;

    /**
     * 是否为登录接口
     */
    boolean login() default false;
}
