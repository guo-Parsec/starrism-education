package nom.edu.starrism.common.pool;

/**
 * <p>redis常量池</p>
 *
 * @author guocq
 * @since 2022/11/10
 **/
public interface RedisPool {
    /**
     * redis key的间隔符
     */
    String REDIS_KEY_SEPARATOR = ":";
    /**
     * redis key统一前缀
     */
    String BASE_REDIS_KEY = "se";

    /**
     * 根据categoryCode查询权限缓存的key
     */
    String PERMISSION_QUERY_BY_CATEGORY_CODE_KEY = "permission:categoryCode";
}
