package nom.edu.starrism.common.util;

import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/20
 **/
public class CollectionUtil {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(CollectionUtil.class);

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

    public static <T> void castCollection(Object object, Collection<T> emptyCollection, Class<T> tClass) {
        if (emptyCollection == null) {
            LOGGER.error("emptyCollection不能为null");
            throw new IllegalArgumentException("emptyCollection不能为null");
        }
        if (object instanceof Collection<?>) {
            ((Collection<?>) object).forEach(ele -> emptyCollection.add(tClass.cast(ele)));
        }
    }
}
