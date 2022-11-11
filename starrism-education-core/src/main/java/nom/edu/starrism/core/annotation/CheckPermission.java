package nom.edu.starrism.core.annotation;

import nom.edu.starrism.common.pool.AuthPool;

import java.lang.annotation.*;

/**
 * <p>权限校验注解</p>
 *
 * @author guocq
 * @since 2022/11/11
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckPermission {
    /**
     * 权限码列表
     */
    String[] value() default {AuthPool.DEFAULT_ALL_PERMISSION};
}
