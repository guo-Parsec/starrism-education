package nom.edu.starrism.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/20
 **/
public class CollectionUtil {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || isEmpty(map.keySet());
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }
}
