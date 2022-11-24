package nom.edu.starrism.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;
import nom.edu.starrism.data.domain.param.AbstractParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>核心mapper</p>
 *
 * @author guocq
 * @since 2022/11/24
 **/
public interface MainMapper<E extends AbstractDataEntity, P extends AbstractParam> extends BaseMapper<E> {
    /**
     * <p>分页查询数据</p>
     *
     * @param queryParam 查询参数
     * @return {@link Page<T>}
     * @author guocq
     * @date 2022/11/24 15:00
     */
    Page<E> paginationQuery(@Param("queryParam") P queryParam);

    /**
     * <p>非分页查询数据</p>
     *
     * @param queryParam 查询参数
     * @return java.util.List<T>
     * @author guocq
     * @date 2022/11/24 15:01
     */
    List<E> listQuery(@Param("queryParam") P queryParam);

    /**
     * <p>删除数据</p>
     *
     * @param id 主键
     * @author guocq
     * @date 2022/11/16 11:00
     */
    void delete(@Param("id") Long id);

    /**
     * <p>根据主键更新数据</p>
     *
     * @param param 参数
     * @author guocq
     * @date 2022/11/16 10:47
     */
    void update(E param);
}
