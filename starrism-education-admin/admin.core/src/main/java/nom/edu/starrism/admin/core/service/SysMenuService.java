package nom.edu.starrism.admin.core.service;

import nom.edu.starrism.admin.api.domain.vo.SysMenuVo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>系统菜单service</p>
 *
 * @author guocq
 * @since 2022/11/18
 **/
public interface SysMenuService {
    /**
     * <p>查询当前用户的菜单信息 组建为树形结构</p>
     *
     * @return {@link List<SysMenuVo>}
     * @author guocq
     * @date 2022/11/18 15:46
     */
    List<SysMenuVo> findMenusOfCurrentUser();

    /**
     * <p>查询指定用户id的菜单id列表 </p>
     *
     * @param userId 用户id
     * @return {@link List<SysMenuVo>}
     * @author guocq
     * @date 2022/11/18 15:46
     */
    Set<Long> findMenuIds(Long userId);

    /**
     * <p>查询指定用户id的菜单信息 </p>
     *
     * @param userId     用户id
     * @param returnTree 是否返回树形结构 true 返回树形结构 false 返回普通数据
     * @return {@link List<SysMenuVo>}
     * @author guocq
     * @date 2022/11/18 15:46
     */
    List<SysMenuVo> findMenusOfUserId(Long userId, boolean returnTree);

    /**
     * <p>查询指定角色id列表的菜单信息 组建为树形结构</p>
     *
     * @param roleIds 角色id列表
     * @return {@link List<SysMenuVo>}
     * @author guocq
     * @date 2022/11/18 15:46
     */
    List<SysMenuVo> findByRoleIds(Collection<Long> roleIds);

    /**
     * <p>根据指定的菜单id列表查询菜单信息 组建为树形结构</p>
     *
     * @param menusIds menusIds
     * @return {@link List<SysMenuVo>}
     * @author guocq
     * @date 2022/11/18 16:16
     */
    List<SysMenuVo> findByMenuIds(Collection<Long> menusIds);
}
