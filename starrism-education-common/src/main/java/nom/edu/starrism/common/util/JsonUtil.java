package nom.edu.starrism.common.util;

import com.alibaba.fastjson2.JSONObject;

import java.util.Map;

/**
 * <p>JSON工具类</p>
 *
 * @author guocq
 * @since 2022/11/18
 **/
public class JsonUtil {
    /**
     * <p>对象转map</p>
     *
     * @param object 对象
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author guocq
     * @date 2022/11/18 10:13
     */
    public static Map<String, Object> objectToMap(Object object) {
        if (object == null) {
            return null;
        }
        String jsonString = JSONObject.toJSONString(object);
        return JSONObject.parseObject(jsonString);
    }

    /**
     * <p>对象转map</p>
     *
     * @param map    map
     * @param tClass class
     * @return T
     * @author guocq
     * @date 2022/11/18 10:14
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> tClass) {
        if (CollectionUtil.isEmpty(map) || tClass == null) {
            return null;
        }
        return JSONObject.parseObject(JSONObject.toJSONString(map), tClass);
    }

    /**
     * <p>转化为对象</p>
     *
     * @param object 对象
     * @param tClass 类
     * @return T
     * @author guocq
     * @date 2022/11/18 10:18
     */
    public static <T> T toObject(Object object, Class<T> tClass) {
        if (object == null) {
            return null;
        }
        return mapToObject(objectToMap(object), tClass);
    }

}
