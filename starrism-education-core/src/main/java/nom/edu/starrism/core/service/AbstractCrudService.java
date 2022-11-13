package nom.edu.starrism.core.service;

import com.github.pagehelper.PageInfo;
import nom.edu.starrism.common.enums.SeCommonResultCode;
import nom.edu.starrism.common.exception.SeException;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;
import nom.edu.starrism.data.domain.param.AbstractParam;
import nom.edu.starrism.data.domain.vo.AbstractVo;

/**
 * <p>Crud Service</p>
 *
 * @author hedwing
 * @since 2022/11/13
 **/
public abstract class AbstractCrudService<E extends AbstractDataEntity, V extends AbstractVo, P extends AbstractParam>
        implements CrudService<E, V, P> {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(AbstractCrudService.class);

    /**
     * <p>数据新增</p>
     *
     * @param param param
     * @author hedwing
     * @since 2022/11/12
     */
    @Override
    public void create(P param) {
        E entity = createAndReturn(param);
        if (AbstractDataEntity.isEmpty(entity)) {
            LOGGER.error("新增数据失败");
            throw new SeException(SeCommonResultCode.ADD_FAILED);
        }
    }

    /**
     * <p>分页查询</p>
     *
     * @param param param
     * @return {@link PageInfo<V>}
     * @author hedwing
     * @since 2022/11/12
     */
    @Override
    public PageInfo<V> pageQuery(P param) {
        LOGGER.warn("当前分页查询并未实现，将返回null");
        return null;
    }
}
