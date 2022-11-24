package nom.edu.starrism.common.enums;

/**
 * <p>通用枚举</p>
 *
 * @author hedwing
 * @since 2022/10/21
 **/
public interface BaseEnum<C, V> {
    /**
     * <p>获取码值</p>
     *
     * @return C
     * @author hedwing
     * @since 2022/8/13
     */
    C getCode();

    /**
     * <p>获取值</p>
     *
     * @return C
     * @author hedwing
     * @since 2022/8/13
     */
    V getValue();
}
