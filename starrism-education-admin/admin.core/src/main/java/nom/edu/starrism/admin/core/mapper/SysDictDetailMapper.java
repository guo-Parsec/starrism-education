package nom.edu.starrism.admin.core.mapper;

import com.github.pagehelper.Page;
import nom.edu.starrism.admin.core.domain.param.SysDictDetailParam;
import nom.edu.starrism.core.domain.entity.SysDictDetail;
import nom.edu.starrism.data.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>字典详情mapper</p>
 *
 * @author hedwing
 * @since 2022/11/11
 **/
@Mapper
@Repository(value = "sysDictDetailMapper")
public interface SysDictDetailMapper extends CoreMapper<SysDictDetail> {
    /**
     * <p>分页查询字典详情数据</p>
     *
     * @param queryParam 查询参数
     * @return {@link Page<SysDictDetail>}
     * @author guocq
     * @date 2022/11/16 9:17
     */
    Page<SysDictDetail> paginationQuery(@Param("queryParam") SysDictDetailParam queryParam);

    /**
     * <p>非分页查询字典详情数据</p>
     *
     * @param queryParam 查询参数
     * @return {@link List<SysDictDetail>}
     * @author guocq
     * @date 2022/11/16 9:50
     */
    List<SysDictDetail> listQuery(@Param("queryParam") SysDictDetailParam queryParam);

    /**
     * <p>根据categoryId删除字典详情</p>
     *
     * @param categoryId categoryId
     * @author guocq
     * @date 2022/11/16 9:33
     */
    void deleteByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * <p>根据主键更新数据</p>
     *
     * @param param 参数
     * @return int
     * @author guocq
     * @date 2022/11/16 10:47
     */
    void update(SysDictDetail param);

    /**
     * <p>删除数据</p>
     *
     * @param id 主键
     * @author guocq
     * @date 2022/11/16 11:00
     */
    void delete(@Param("id") Long id);
}
