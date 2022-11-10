package nom.edu.starrism.common.util;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>对象工具类</p>
 *
 * @author hedwing
 * @since 2022/8/20
 **/
public class ObjectUtil {
    /**
     * 只要有一个对象为空则为空
     *
     * @param objects 对象列表
     * @return
     */
    public static boolean isAnyNull(Object... objects) {
        if (objects == null || objects.length == 0) {
            return true;
        }
        return Stream.of(objects).anyMatch(Objects::isNull);
    }

    /**
     * 只有所有对象为空则为空
     *
     * @param objects 对象列表
     * @return
     */
    public static boolean isAllNull(Object... objects) {
        if (objects == null || objects.length == 0) {
            return true;
        }
        return Stream.of(objects).allMatch(Objects::isNull);
    }
}
