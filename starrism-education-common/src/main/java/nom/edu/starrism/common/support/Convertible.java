package nom.edu.starrism.common.support;

/**
 * <p>转换器<</p>
 *
 * @author hedwing
 * @since 2022/10/21
 **/
@FunctionalInterface
public interface Convertible<S, T> {
    /**
     * <p>将 source转化为 T类型的数据，当source为null时, 转化为 defaultTarget</p>
     *
     * @param source        原类型
     * @param defaultTarget 默认转化对象
     * @return T
     * @author hedwing
     * @since 2022/8/14
     */
    default T convert(S source, T defaultTarget) {
        if (source == null) {
            return defaultTarget;
        }
        return convert(source);
    }

    /**
     * <p>将 source转化为 T类型的数据，当source为null时, 转化为 null</p>
     *
     * @param source 原类型
     * @return T
     * @author hedwing
     * @since 2022/8/14
     */
    T convert(S source);
}
