package nom.edu.starrism.core.repository;

import nom.edu.starrism.core.domain.entity.SysDictDetail;
import nom.edu.starrism.data.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>针对表【sys_dict_detail(系统字典详情表)】的数据库查询操作Repository</p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
@Mapper
@Repository(value = "sysDictDetailRepository")
public interface SysDictDetailRepository extends CoreMapper<SysDictDetail> {
    /**
     * <p>根据类别Id查询字典详情</p>
     *
     * @param sysDictCategoryId 类别id
     * @return {@link List<SysDictDetail>}
     * @author hedwing
     * @since 2022/10/23
     */
    List<SysDictDetail> findByCategoryId(@Param("sysDictCategoryId") Long sysDictCategoryId);

    /**
     * <p>根据类别Id以及字典值查询字典详情</p>
     *
     * @param sysDictCategoryId 类别id
     * @param dictValue         字典值
     * @return {@link SysDictDetail}
     * @author hedwing
     * @since 2022/10/23
     */
    SysDictDetail findByCategoryCodeAndDictValue(@Param("sysDictCategoryId") Long sysDictCategoryId,
                              @Param("dictValue") String dictValue);

    /**
     * <p>根据类别Id以及字典码查询字典详情</p>
     *
     * @param sysDictCategoryId 类别id
     * @param dictCode         字典码
     * @return {@link SysDictDetail}
     * @author hedwing
     * @since 2022/10/23
     */
    SysDictDetail findByCategoryCodeAndDictCode(@Param("sysDictCategoryId") Long sysDictCategoryId,
                                                 @Param("dictCode") String dictCode);
}
