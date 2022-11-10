package nom.edu.starrism.common.util;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/20
 **/
public class ObjectUtil {
    public static boolean isAnyNull(Object... objects) {
        if (objects == null || objects.length == 0) {
            return true;
        }
        return Stream.of(objects).anyMatch(Objects::isNull);
    }

    public static boolean isAllNull(Object... objects) {
        if (objects == null || objects.length == 0) {
            return true;
        }
        return Stream.of(objects).allMatch(Objects::isNull);
    }
}
