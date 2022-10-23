package nom.edu.starrism.core.repository;

import nom.edu.starrism.core.domain.entity.SysDictCategory;
import nom.edu.starrism.data.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>针对表【sys_dict_category(系统字典分类表)】的数据库查询操作Repository</p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
@Mapper
@Repository(value = "sysDictCategoryRepository")
public interface SysDictCategoryRepository extends CoreMapper<SysDictCategory> {
    /**
     * <p>根据字典类别码查询字典类别数据</p>
     *
     * @param categoryCode categoryCode
     * @return {@link SysDictCategory}
     * @author hedwing
     * @since 2022/10/23
     */
    SysDictCategory findByCode(@Param("categoryCode") String categoryCode);
}
