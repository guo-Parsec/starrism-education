package nom.edu.starrism.core.service;

import com.github.pagehelper.PageInfo;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;
import nom.edu.starrism.data.domain.param.AbstractParam;
import nom.edu.starrism.data.domain.vo.AbstractVo;
import nom.edu.starrism.data.mapper.MainMapper;

import java.util.List;

/**
 * <p>核心Service</p>
 *
 * @author guocq
 * @since 2022/11/24
 **/
public interface MainService<E extends AbstractDataEntity, V extends AbstractVo, P extends AbstractParam, M extends MainMapper<E,P>> {
    /**
     * <p>数据新增</p>
     *
     * @param param param
     * @return 新增后的数据
     * @author hedwing
     * @since 2022/11/12
     */
    V create(P param);

    /**
     * <p>数据编辑</p>
     *
     * @param param param
     * @return V 结果数据
     * @author hedwing
     * @since 2022/11/12
     */
    V update(P param);

    /**
     * <p>数据删除</p>
     *
     * @param id 主键
     * @return V 结果数据
     * @author hedwing
     * @since 2022/11/12
     */
    V delete(Long id);

    /**
     * <p>分页查询</p>
     *
     * @param param param
     * @return {@link PageInfo <V>}
     * @author hedwing
     * @since 2022/11/12
     */
    PageInfo<V> pageQuery(P param);

    /**
     * <p>查询数据(不分页)</p>
     *
     * @param param 查询参数
     * @return java.util.List<V>
     * @author guocq
     * @date 2022/11/16 9:41
     */
    List<V> listQuery(P param);
}
