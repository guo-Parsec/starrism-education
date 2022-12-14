package nom.edu.starrism.admin.core.mapper;

import com.github.pagehelper.Page;
import nom.edu.starrism.admin.api.domain.param.SysRolePageParam;
import nom.edu.starrism.admin.core.domain.entity.SysRole;
import nom.edu.starrism.data.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * <p>角色mapper</p>
 *
 * @author hedwing
 * @since 2022/11/4
 **/
@Mapper
@Repository(value = "sysRoleMapper")
public interface SysRoleMapper extends CoreMapper<SysRole> {
    /**
     * <p>根据用户id查询</p>
     *
     * @param userId userId
     * @return java.util.List<nom.edu.starrism.admin.core.domain.entity.SysRole>
     * @author hedwing
     * @since 2022/11/4
     */
    List<SysRole> findByUserId(@Param("userId") Long userId);

    /**
     * <p>根据用户id查询roleId列表</p>
     *
     * @param userId userId
     * @return java.util.Set<java.lang.Long>
     * @author hedwing
     * @since 2022/11/4
     */
    Set<Long> findRoleIdsByUserId(@Param("userId") Long userId);

    /**
     * <p>分页查询角色</p>
     *
     * @param queryParam 查询参数
     * @return {@link List<SysRole>}
     * @author guocq
     * @date 2022/11/11 14:18
     */
    Page<SysRole> paginationQuery(@Param("queryParam") SysRolePageParam queryParam);
}
