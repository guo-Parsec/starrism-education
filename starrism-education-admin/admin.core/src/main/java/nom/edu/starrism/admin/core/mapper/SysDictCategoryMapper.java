package nom.edu.starrism.admin.core.mapper;

import nom.edu.starrism.core.domain.entity.SysDictCategory;
import nom.edu.starrism.data.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>字典类别mapper</p>
 *
 * @author hedwing
 * @since 2022/11/11
 **/
@Mapper
@Repository(value = "sysDictCategoryMapper")
public interface SysDictCategoryMapper extends CoreMapper<SysDictCategory> {
    /**
     * <p>更新数据</p>
     *
     * @param param param
     * @author guocq
     * @date 2022/11/16 11:03
     */
    void update(SysDictCategory param);

    /**
     * <p>删除数据</p>
     *
     * @param id id
     * @author guocq
     * @date 2022/11/16 11:03
     */
    void delete(@Param("id") Long id);
}
