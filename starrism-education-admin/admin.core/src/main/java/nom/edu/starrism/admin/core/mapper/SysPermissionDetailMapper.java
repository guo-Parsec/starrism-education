package nom.edu.starrism.admin.core.mapper;

import nom.edu.starrism.admin.core.domain.entity.SysPermissionDetail;
import nom.edu.starrism.data.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * <p>系统权限详情表mapper</p>
 *
 * @author hedwing
 * @since 2022/11/4
 **/
@Mapper
@Repository(value = "sysPermissionDetailMapper")
public interface SysPermissionDetailMapper extends CoreMapper<SysPermissionDetail> {
    /**
     * <p>根据id查询</p>
     *
     * @param id id
     * @return nom.edu.starrism.admin.core.domain.entity.SysPermissionDetail
     * @author hedwing
     * @since 2022/11/4
     */
    SysPermissionDetail find(@Param("id") Long id);

    /**
     * <p>根据类别查询详情</p>
     *
     * @param categoryCode categoryCode
     * @return java.util.List<nom.edu.starrism.admin.core.domain.entity.SysPermissionDetail>
     * @author hedwing
     * @since 2022/11/4
     */
    List<SysPermissionDetail> findByCategoryCode(@Param("categoryCode") String categoryCode);

    /**
     * <p>根据类别列表和用户id查询详情</p>
     *
     * @param categoryCodes categoryCodes
     * @param roleIds       roleIds
     * @return java.util.List<nom.edu.starrism.admin.core.domain.entity.SysPermissionDetail>
     * @author hedwing
     * @since 2022/11/4
     */
    List<SysPermissionDetail> findByCategoryCodesAndRoleIds(@Param("categoryCodes") Collection<String> categoryCodes,
                                                            @Param("roleIds") Collection<Long> roleIds);
}
