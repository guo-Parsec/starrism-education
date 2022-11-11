package nom.edu.starrism.core.annotation;

import java.lang.annotation.*;

/**
 * <p>检查是否登录</p>
 *
 * @author guocq
 * @since 2022/11/11
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckLogin {
}
