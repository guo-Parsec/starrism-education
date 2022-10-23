package nom.edu.starrism.common.support;

/**
 * <p>通用转换器</p>
 *
 * @author hedwing
 * @since 2022/10/21
 **/
public class CommonConverts {
    /**
     * <p>String类转换器</p>
     *
     * @return {@link Convertible}
     * @author hedwing
     * @since 2022/9/17
     */
    public static <S> Convertible<S, String> toStr() {
        return (source) -> {
            if (source instanceof String) {
                return (String) source;
            }
            return source.toString();
        };
    }

    /**
     * <p>String类转Integer转换器</p>
     *
     * @return {@link Convertible}
     * @author hedwing
     * @since 2022/9/17
     */
    public static Convertible<String, Integer> strToInt() {
        return Integer::valueOf;
    }

    /**
     * <p>转Integer转换器</p>
     *
     * @return {@link Convertible}
     * @author hedwing
     * @since 2022/9/17
     */
    public static <S> Convertible<S, Integer> toInt() {
        return (source) -> {
            if (source instanceof String) {
                return Integer.valueOf((String) source);
            }
            if (source instanceof Integer) {
                return (Integer) source;
            }
            return Integer.valueOf(source.toString());
        };
    }
}
