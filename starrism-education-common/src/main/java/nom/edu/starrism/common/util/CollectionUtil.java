package nom.edu.starrism.common.util;

import com.google.common.collect.Lists;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;

import java.util.*;

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
     *
     * @param object          对象
     * @param emptyCollection 待转换的空集合
     * @param tClass          集合元素class对象
     * @param <T>             泛型
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

    /**
     * <p>获取集合第一个元素</p>
     *
     * @param collection 集合
     * @return T
     * @author guocq
     * @date 2022/11/18 9:19
     */
    public static <T> T findFirst(Collection<T> collection) {
        if (CollectionUtil.isEmpty(collection)) {
            return null;
        }
        Optional<T> first = collection.stream().findFirst();
        return first.orElse(null);
    }

    /**
     * <p>将{@code list}按截取{@code subNum}分组的新list切片，返回一个二维列表</p>
     *
     * @param list   被截取的列表
     * @param subNum 每个切片的最大大小
     * @return java.util.List<java.util.List < T>>
     * @author guocq
     * @date 2022/11/23 10:14
     */
    public static <T> List<List<T>> subList(List<T> list, int subNum) {
        int size = list.size();
        List<List<T>> result = Lists.newArrayList();
        if (size <= subNum) {
            result.add(list);
            return result;
        }
        int sliceNum = size % subNum == 0 ? size / subNum : (size / subNum) + 1;
        for (int i = 0; i < sliceNum; i++) {
            result.add(subList(list, i * subNum, subNum));
        }
        return result;
    }

    /**
     * <p>将{@code list}从索引为{@code startIndex}截取数量为{@code subNum}的新list</p>
     *
     * @param list       被截取的列表
     * @param startIndex 被截取开始的索引位置
     * @param subNum     需要截取的元素数量
     * @return java.util.List<T>
     * @author guocq
     * @date 2022/11/23 9:53
     */
    public static <T> List<T> subList(List<T> list, int startIndex, int subNum) {
        int size = list.size();
        if (size <= subNum || startIndex + subNum >= size) {
            return new ArrayList<>(list.subList(startIndex, size));
        }
        int toIndex = startIndex + subNum;
        return new ArrayList<>(list.subList(startIndex, toIndex));
    }
}
