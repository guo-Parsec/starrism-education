package nom.edu.starrism.core.annotation.crud;

import java.lang.annotation.*;

/**
 * <p>修改</p>
 *
 * @author guocq
 * @since 2022/11/16
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Modify {
}
