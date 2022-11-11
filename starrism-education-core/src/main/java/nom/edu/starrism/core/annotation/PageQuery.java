package nom.edu.starrism.core.annotation;

import java.lang.annotation.*;

/**
 * <p>分页查询</p>
 *
 * @author guocq
 * @since 2022/11/11
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageQuery {
    /**
     * 页大小 用于每页查询的数量
     */
    long pageSize() default 10L;

    /**
     * 当前页
     */
    long currPage() default 1L;
}
