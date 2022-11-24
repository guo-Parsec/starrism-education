package nom.edu.starrism.admin.core.service.impl;

import com.google.common.collect.Lists;
import nom.edu.starrism.admin.api.domain.vo.SysMenuVo;
import nom.edu.starrism.admin.core.domain.entity.SysMenu;
import nom.edu.starrism.admin.core.mapper.SysMenuMapper;
import nom.edu.starrism.admin.core.mapper.SysRoleMapper;
import nom.edu.starrism.admin.core.service.SysMenuService;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.util.CollectionUtil;
import nom.edu.starrism.core.context.SecurityContext;
import nom.edu.starrism.core.domain.vo.AuthenticatedUser;
import nom.edu.starrism.core.helper.TreeHelper;
import nom.edu.starrism.core.pool.CacheNamesPool;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>系统菜单service实现类</p>
 *
 * @author guocq
 * @since 2022/11/18
 **/
@Service(value = "sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SysMenuServiceImpl.class);
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;

    /**
     * <p>查询当前用户的菜单信息 组建为树形结构</p>
     *
     * @return {@link List < SysMenuVo >}
     * @author guocq
     * @date 2022/11/18 15:46
     */
    @Override
    public List<SysMenuVo> findMenusOfCurrentUser() {
        AuthenticatedUser certificate = SecurityContext.findCertificate();
        List<SysMenuVo> list = Lists.newArrayList();
        CollectionUtil.castCollection(certificate.getMenus(), list, SysMenuVo.class);
        return list;
    }

    /**
     * <p>查询指定用户id的菜单id列表 </p>
     *
     * @param userId 用户id
     * @return {@link List<SysMenuVo>}
     * @author guocq
     * @date 2022/11/18 15:46
     */
    @Override
    public Set<Long> findMenuIds(Long userId) {
        List<SysMenuVo> menusOfUserId = findMenusOfUserId(userId, false);
        return menusOfUserId.stream().map(SysMenuVo::getId).collect(Collectors.toSet());
    }

    /**
     * <p>查询指定用户id的菜单信息 </p>
     *
     * @param userId     用户id
     * @param returnTree 是否返回树形结构 true 返回树形结构 false 返回普通数据
     * @return {@link List<SysMenuVo>}
     * @author guocq
     * @date 2022/11/18 15:46
     */
    @Override
    @Cacheable(cacheNames = CacheNamesPool.CN_INDIVIDUAL_MENU, key = "'userId:'+#userId+':returnTree:'+#returnTree", unless = "#result == null || #result.isEmpty()")
    public List<SysMenuVo> findMenusOfUserId(Long userId, boolean returnTree) {
        Set<Long> roleIds = sysRoleMapper.findRoleIdsByUserId(userId);
        if (CollectionUtil.isEmpty(roleIds)) {
            LOGGER.debug("根据用户id{}查询角色列表为空", roleIds);
            return Lists.newArrayList();
        }
        List<SysMenuVo> menus = findByRoleIds(roleIds);
        if (returnTree) {
            return TreeHelper.generateTree(menus);
        }
        return menus;
    }

    /**
     * <p>查询指定角色id列表的菜单信息</p>
     *
     * @param roleIds 角色id列表
     * @return {@link List<SysMenuVo>}
     * @author guocq
     * @date 2022/11/18 15:46
     */
    @Override
    public List<SysMenuVo> findByRoleIds(Collection<Long> roleIds) {
        List<SysMenu> menus = sysMenuMapper.findByRoleIds(roleIds);
        if (CollectionUtil.isEmpty(menus)) {
            LOGGER.debug("根据角色列表{}查询菜单信息为空", roleIds);
            return Lists.newArrayList();
        }
        return menus.stream().map(SysMenu::toVo).collect(Collectors.toList());
    }

    /**
     * <p>根据指定的菜单id列表查询菜单信息 组建为树形结构</p>
     *
     * @param menusIds menusIds
     * @return {@link List<SysMenuVo>}
     * @author guocq
     * @date 2022/11/18 16:16
     */
    @Override
    public List<SysMenuVo> findByMenuIds(Collection<Long> menusIds) {
        List<SysMenu> menus = sysMenuMapper.findByMenuIds(menusIds);
        if (CollectionUtil.isEmpty(menus)) {
            LOGGER.debug("根据菜单id列表{}查询菜单信息为空", menusIds);
            return Lists.newArrayList();
        }
        List<SysMenuVo> vos = menus.stream().map(SysMenu::toVo).collect(Collectors.toList());
        return TreeHelper.generateTree(vos);
    }
}
