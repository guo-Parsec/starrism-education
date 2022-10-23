package nom.edu.starrism.data.pool;

import org.apache.ibatis.type.Alias;

/**
 * <p>数据常量池</p>
 *
 * @author hedwing
 * @since 2022/10/22
 **/
@Alias("DataPool")
public interface DataPool {
    /**
     * 启用状态
     */
    Integer ENABLE = 0;

    /**
     * 禁用状态
     */
    Integer DISABLE = 1;

    /**
     * 删除状态
     */
    Integer DELETE = 2;
}
