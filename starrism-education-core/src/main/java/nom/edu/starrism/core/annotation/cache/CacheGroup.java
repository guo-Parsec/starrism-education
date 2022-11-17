package nom.edu.starrism.core.annotation.cache;

import java.lang.annotation.*;

/**
 * <p>缓存组</p>
 *
 * @author guocq
 * @since 2022/11/16
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheGroup {
    CacheClear[] cacheClear() default {};
}
