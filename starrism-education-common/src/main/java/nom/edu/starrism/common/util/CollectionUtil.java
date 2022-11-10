package nom.edu.starrism.common.util;

import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;

import java.util.Collection;
import java.util.Map;

/**
 * <p>集合工具类</p>
 *
 * @author hedwing
 * @since 2022/8/20
 **/
public class CollectionUtil {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(CollectionUtil.class);

    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断map是否为空
     *
     * @param map 集合
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || isEmpty(map.keySet());
    }

    /**
     * 判断集合是否不为空
     *
     * @param collection 集合
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断集合是否不为空
     *
     * @param map 集合
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 对象转集合
     * @param object 对象
     * @param emptyCollection 待转换的空集合
     * @param tClass 集合元素class对象
     * @param <T> 泛型
     */
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
