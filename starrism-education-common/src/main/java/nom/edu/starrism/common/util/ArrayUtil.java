package nom.edu.starrism.common.util;

/**
 * <p>>数组工具类</</p>
 *
 * @author hedwing
 * @since 2022/10/21
 **/
public class ArrayUtil {
    /**
     * <p>判断数组是否为空数组</p>
     *
     * @param array array
     * @return boolean
     * @author hedwing
     * @since 2022/9/17
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * <p>判断数组是否为非空数组</p>
     *
     * @param array array
     * @return boolean
     * @author hedwing
     * @since 2022/9/17
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }
}
