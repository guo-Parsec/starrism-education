package nom.edu.starrism.core.pool;

import nom.edu.starrism.common.pool.RedisPool;

/**
 * <p>缓存名称常量池</p>
 *
 * @author hedwing
 * @since 2022/11/12
 **/
public interface CacheNamesPool {
    /**
     * 缓存名称 - 字典
     */
    String CN_SYS_DICT = "sys:dict";

    /**
     * 缓存名称 - 字典类别
     */
    String CN_SYS_DICT_CATEGORY = CN_SYS_DICT + RedisPool.REDIS_KEY_SEPARATOR + "category";

    /**
     * 缓存名称 - 权限
     */
    String CN_SYS_PERMISSION = "sys:permission";

    /**
     * 缓存名称 - 查询个人菜单
     */
    String CN_INDIVIDUAL_MENU = "individual:menu";

    /**
     * 缓存名称 - 菜单
     */
    String CN_SYS_MENU = "sys:menu";

    /**
     * 缓存名称 - 参数
     */
    String CN_PARAM = "sys:param";
}
