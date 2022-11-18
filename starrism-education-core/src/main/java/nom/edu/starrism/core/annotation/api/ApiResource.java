package nom.edu.starrism.core.annotation.api;

import nom.edu.starrism.core.type.AppTypes;
import nom.edu.starrism.core.type.PermissionCategory;

import java.lang.annotation.*;

/**
 * <p>api资源标识</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiResource {
    /**
     * 权限码
     */
    String value() default "";

    /**
     * 应用名
     */
    AppTypes app() default AppTypes.GATE;

    /**
     * 权限名称
     */
    String name() default "";

    /**
     * 权限分类
     */
    PermissionCategory category() default PermissionCategory.authenticated_permission;

    /**
     * 排序
     */
    int sort() default 0;

    /**
     * 备注信息
     */
    String remark() default "";
}
