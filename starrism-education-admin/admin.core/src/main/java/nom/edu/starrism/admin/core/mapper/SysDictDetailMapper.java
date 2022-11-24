package nom.edu.starrism.admin.core.mapper;

import nom.edu.starrism.admin.core.domain.param.SysDictDetailParam;
import nom.edu.starrism.core.domain.entity.SysDictDetail;
import nom.edu.starrism.data.mapper.MainMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>字典详情mapper</p>
 *
 * @author hedwing
 * @since 2022/11/11
 **/
@Mapper
@Repository(value = "sysDictDetailMapper")
public interface SysDictDetailMapper extends MainMapper<SysDictDetail, SysDictDetailParam> {
    /**
     * <p>根据categoryId删除字典详情</p>
     *
     * @param categoryId categoryId
     * @author guocq
     * @date 2022/11/16 9:33
     */
    void deleteByCategoryId(@Param("categoryId") Long categoryId);
}
