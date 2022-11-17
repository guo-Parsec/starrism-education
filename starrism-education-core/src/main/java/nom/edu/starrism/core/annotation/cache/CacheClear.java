package nom.edu.starrism.core.annotation.cache;

import java.lang.annotation.*;

/**
 * <p>缓存清理</p>
 *
 * @author guocq
 * @since 2022/11/16
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheClear {
    /**
     * 缓存名称
     */
    String name() default "";

    /**
     * 用于动态计算键的Spring表达式语言（SpEL）表达式。
     * 缓存键 = key.isEmpty ? (name) : (name:key)
     */
    String key() default "";

    /**
     * 用于否决方法缓存的Spring表达式语言（SpEL）表达式。
     * 条件成立才进行缓存
     */
    String condition() default "";

    /**
     * 是否支持模糊匹配
     */
    boolean fuzzyMatch() default false;
}
