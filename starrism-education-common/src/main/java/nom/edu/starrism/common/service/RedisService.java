package nom.edu.starrism.common.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Redis业务Service</p>
 *
 * @author hedwing
 * @since 2022/8/25
 **/
public interface RedisService {

    /**
     * <p>获取全部key</p>
     *
     * @param pattern pattern
     * @return java.util.Set<java.lang.String>
     * @author guocq
     * @date 2022/11/16 17:03
     */
    Set<String> keys(String pattern);

    /**
     * <p>根据匹配符获取所有value</p>
     *
     * @param pattern 匹配符
     * @return java.util.List<java.lang.Object>
     * @author guocq
     * @date 2022/11/17 15:46
     */
    List<Object> getByPattern(String pattern);

    /**
     * <p>保存属性</p>
     *
     * @param key   key
     * @param value value
     * @param time  time 按秒计算
     * @author hedwing
     * @since 2022/9/17
     */
    void set(String key, Object value, long time);

    /**
     * <p>保存属性 设置分钟数</p>
     *
     * @param key     key
     * @param value   value
     * @param minutes minutes
     * @author hedwing
     * @since 2022/9/17
     */
    void setByMinute(String key, Object value, long minutes);

    /**
     * <p>保存属性 设置小时数</p>
     *
     * @param key   key
     * @param value value
     * @param hours hours
     * @author hedwing
     * @since 2022/9/17
     */
    void setByHour(String key, Object value, long hours);

    /**
     * <p>保存属性</p>
     *
     * @param key   key
     * @param value value
     * @author hedwing
     * @since 2022/9/17
     */
    void set(String key, Object value);

    /**
     * 获取属性
     *
     * @param key key
     * @return Object
     * @author hedwing
     * @since 2022/9/17
     */
    Object get(String key);

    /**
     * <p>删除属性</p>
     *
     * @param key key
     * @return java.lang.Boolean
     * @author hedwing
     * @since 2022/9/17
     */
    Boolean del(String key);

    /**
     * <p>批量删除属性</p>
     *
     * @param keys keys
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long del(List<String> keys);

    /**
     * <p>根据匹配条件删除所有键</p>
     *
     * @param pattern pattern
     * @return java.lang.Long
     * @author guocq
     * @date 2022/11/16 17:05
     */
    Long delByPattern(String pattern);

    /**
     * <p>设置过期时间</p>
     *
     * @param key  key
     * @param time time
     * @return java.lang.Boolean
     * @author hedwing
     * @since 2022/9/17
     */
    Boolean expire(String key, long time);

    /**
     * <p>获取过期时间</p>
     *
     * @param key key
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long getExpire(String key);

    /**
     * <p>判断是否有该属性</p>
     *
     * @param key key
     * @return java.lang.Boolean
     * @author hedwing
     * @since 2022/9/17
     */
    Boolean hasKey(String key);

    /**
     * <p>按delta递增</p>
     *
     * @param key   key
     * @param delta delta
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long incr(String key, long delta);

    /**
     * <p>按delta递减</p>
     *
     * @param key   key
     * @param delta delta
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long decr(String key, long delta);

    /**
     * <p>获取Hash结构中的属性</p>
     *
     * @param key     key
     * @param hashKey hashKey
     * @return java.lang.Object
     * @author hedwing
     * @since 2022/9/17
     */
    Object hGet(String key, String hashKey);

    /**
     * <p>向Hash结构中放入一个属性</p>
     *
     * @param key     key
     * @param hashKey hashKey
     * @param value   value
     * @param time    time
     * @return java.lang.Boolean
     * @author hedwing
     * @since 2022/9/17
     */
    Boolean hSet(String key, String hashKey, Object value, long time);

    /**
     * <p>向Hash结构中放入一个属性</p>
     *
     * @param key     key
     * @param hashKey hashKey
     * @param value   value
     * @author hedwing
     * @since 2022/9/17
     */
    void hSet(String key, String hashKey, Object value);

    /**
     * <p>直接获取整个Hash结构</p>
     *
     * @param key key
     * @return java.util.Map<java.lang.Object, java.lang.Object>
     * @author hedwing
     * @since 2022/9/17
     */
    Map<Object, Object> hGetAll(String key);

    /**
     * <p>直接设置整个Hash结构</p>
     *
     * @param key  key
     * @param map  map
     * @param time time
     * @return java.lang.Boolean
     * @author hedwing
     * @since 2022/9/17
     */
    Boolean hSetAll(String key, Map<String, Object> map, long time);

    /**
     * <p>直接设置整个Hash结构</p>
     *
     * @param key key
     * @param map map
     * @author hedwing
     * @since 2022/9/17
     */
    void hSetAll(String key, Map<String, ?> map);

    /**
     * <p>删除Hash结构中的属性</p>
     *
     * @param key     key
     * @param hashKey hashKey
     * @author hedwing
     * @since 2022/9/17
     */
    void hDel(String key, Object... hashKey);

    /**
     * <p>判断Hash结构中是否有该属性</p>
     *
     * @param key     key
     * @param hashKey hashKey
     * @return java.lang.Boolean
     * @author hedwing
     * @since 2022/9/17
     */
    Boolean hHasKey(String key, String hashKey);

    /**
     * <p>Hash结构中属性递增</p>
     *
     * @param key     key
     * @param hashKey hashKey
     * @param delta   delta
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long hIncr(String key, String hashKey, Long delta);

    /**
     * <p>Hash结构中属性递减</p>
     *
     * @param key     key
     * @param hashKey hashKey
     * @param delta   delta
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long hDecr(String key, String hashKey, Long delta);

    /**
     * <p>获取Set结构</p>
     *
     * @param key key
     * @return java.util.Set<java.lang.Object>
     * @author hedwing
     * @since 2022/9/17
     */
    Set<Object> sMembers(String key);

    /**
     * <p>向Set结构中添加属性</p>
     *
     * @param key    key
     * @param values values
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long sAdd(String key, Object... values);

    /**
     * <p>向Set结构中添加属性</p>
     *
     * @param key    key
     * @param time   time
     * @param values values
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long sAdd(String key, long time, Object... values);

    /**
     * <p>是否为Set中的属性</p>
     *
     * @param key   key
     * @param value value
     * @return java.lang.Boolean
     * @author hedwing
     * @since 2022/9/17
     */
    Boolean sIsMember(String key, Object value);

    /**
     * <p>获取Set结构的长度</p>
     *
     * @param key key
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long sSize(String key);

    /**
     * <p>删除Set结构中的属性</p>
     *
     * @param key    key
     * @param values values
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long sRemove(String key, Object... values);

    /**
     * <p>获取List结构中的属性</p>
     *
     * @param key key
     * @return java.util.List<java.lang.Object>
     * @author guocq
     * @date 2022/11/18 9:58
     */
    List<Object> lRange(String key);

    /**
     * <p>获取List结构中的属性</p>
     *
     * @param key   key
     * @param start start
     * @param end   end
     * @return java.util.List<java.lang.Object>
     * @author hedwing
     * @since 2022/9/17
     */
    List<Object> lRange(String key, long start, long end);

    /**
     * <p>获取List结构的长度</p>
     *
     * @param key key
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long lSize(String key);

    /**
     * <p>根据索引获取List中的属性</p>
     *
     * @param key   key
     * @param index index
     * @return java.lang.Object
     * @author hedwing
     * @since 2022/9/17
     */
    Object lIndex(String key, long index);

    /**
     * <p>向List结构中添加属性</p>
     *
     * @param key   key
     * @param value value
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long lPush(String key, Object value);

    /**
     * <p>向List结构中添加属性</p>
     *
     * @param key   key
     * @param value value
     * @param time  time
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long lPush(String key, Object value, long time);

    /**
     * <p>向List结构中批量添加属性</p>
     *
     * @param key    key
     * @param values values
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long lPushAll(String key, Object... values);

    /**
     * <p>向List结构中批量添加属性</p>
     *
     * @param key    key
     * @param time   time
     * @param values values
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long lPushAll(String key, Long time, Object... values);

    /**
     * <p>从List结构中移除属性</p>
     *
     * @param key   key
     * @param count count
     * @param value value
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/9/17
     */
    Long lRemove(String key, long count, Object value);
}
