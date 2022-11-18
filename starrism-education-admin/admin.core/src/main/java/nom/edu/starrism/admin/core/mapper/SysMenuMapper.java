package nom.edu.starrism.admin.core.mapper;

import nom.edu.starrism.admin.core.domain.entity.SysMenu;
import nom.edu.starrism.data.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * <p>菜单mapper</p>
 *
 * @author guocq
 * @since 2022/11/18
 **/
@Mapper
@Repository(value = "sysMenuMapper")
public interface SysMenuMapper extends CoreMapper<SysMenu> {
    /**
     * <p>根据角色id列表查询</p>
     *
     * @param roleIds 角色id列表
     * @return {@link List<SysMenu>}
     * @author guocq
     * @date 2022/11/18 15:38
     */
    List<SysMenu> findByRoleIds(@Param("roleIds") Collection<Long> roleIds);

    /**
     * <p>根据指定的菜单id列表查询菜单信息</p>
     *
     * @param menuIds 菜单id列表
     * @return {@link List<SysMenu>}
     * @author guocq
     * @date 2022/11/18 15:38
     */
    List<SysMenu> findByMenuIds(@Param("menuIds") Collection<Long> menuIds);
}