package nom.edu.starrism.core.service;

import com.github.pagehelper.PageInfo;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.data.domain.param.AbstractParam;
import nom.edu.starrism.data.domain.vo.AbstractVo;

import java.util.List;

/**
 * <p>Crud Service</p>
 *
 * @author hedwing
 * @since 2022/11/13
 **/
public abstract class AbstractCoreService<V extends AbstractVo, P extends AbstractParam> implements CoreService<V, P> {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(AbstractCoreService.class);

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

    /**
     * <p>查询数据(不分页)</p>
     *
     * @param param 查询参数
     * @return java.util.List<V>
     * @author guocq
     * @date 2022/11/16 9:41
     */
    @Override
    public List<V> listQuery(P param) {
        LOGGER.warn("当前非分页查询并未实现，将返回null");
        return null;
    }
}
