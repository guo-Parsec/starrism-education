package nom.edu.starrism.core.annotation.security;

import nom.edu.starrism.common.pool.AuthPool;

import java.lang.annotation.*;

/**
 * <p>角色校验注解</p>
 *
 * @author guocq
 * @since 2022/11/11
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckRole {
    /**
     * 角色码列表
     */
    String[] value() default {AuthPool.DEFAULT_ADMIN};
}
