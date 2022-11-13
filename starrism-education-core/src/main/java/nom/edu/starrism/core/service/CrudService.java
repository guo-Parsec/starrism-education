package nom.edu.starrism.core.service;

import com.github.pagehelper.PageInfo;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;
import nom.edu.starrism.data.domain.param.AbstractParam;
import nom.edu.starrism.data.domain.vo.AbstractVo;

/**
 * <p>增删改查基础功能Service</p>
 *
 * @author hedwing
 * @since 2022/11/12
 **/
public interface CrudService<E extends AbstractDataEntity, V extends AbstractVo, P extends AbstractParam> {
    SeLogger LOGGER = SeLoggerFactory.getLogger(CrudService.class);

    /**
     * <p>数据新增</p>
     *
     * @param param param
     * @author hedwing
     * @since 2022/11/12
     */
    void create(P param);

    /**
     * <p>数据新增</p>
     *
     * @param param param
     * @return 新增后的数据
     * @author hedwing
     * @since 2022/11/12
     */
    E createAndReturn(P param);

    /**
     * <p>数据编辑</p>
     *
     * @param param param
     * @author hedwing
     * @since 2022/11/12
     */
    void update(P param);

    /**
     * <p>数据删除</p>
     *
     * @param param param
     * @author hedwing
     * @since 2022/11/12
     */
    void delete(P param);

    /**
     * <p>分页查询</p>
     *
     * @param param param
     * @return {@link PageInfo<V>}
     * @author hedwing
     * @since 2022/11/12
     */
    PageInfo<V> pageQuery(P param);
}
