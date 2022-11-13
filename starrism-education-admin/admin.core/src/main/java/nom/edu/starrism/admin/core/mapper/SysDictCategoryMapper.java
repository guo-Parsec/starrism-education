package nom.edu.starrism.admin.core.mapper;

import nom.edu.starrism.core.domain.entity.SysDictCategory;
import nom.edu.starrism.data.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
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
     * <p>新增字典类别</p>
     *
     * @param sysDictCategory sysDictCategory
     * @author hedwing
     * @since 2022/11/11
     */
    void addDictCategory(SysDictCategory sysDictCategory);
}
