package nom.edu.starrism.admin.core.mapper;

import nom.edu.starrism.admin.core.domain.entity.SysPermissionCategory;
import nom.edu.starrism.data.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>系统权限类别表mapper</p>
 *
 * @author hedwing
 * @since 2022/11/4
 **/
@Mapper
@Repository(value = "sysPermissionCategoryMapper")
public interface SysPermissionCategoryMapper extends CoreMapper<SysPermissionCategory> {
    /**
     * <p>根据分类码查询</p>
     *
     * @param categoryCode categoryCode
     * @return nom.edu.starrism.admin.core.domain.entity.SysPermissionCategory
     * @author hedwing
     * @since 2022/11/4
     */
    SysPermissionCategory findByCode(@Param("categoryCode") String categoryCode);
}
