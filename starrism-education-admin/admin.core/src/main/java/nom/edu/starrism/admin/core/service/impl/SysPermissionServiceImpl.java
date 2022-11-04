package nom.edu.starrism.admin.core.service.impl;

import com.google.common.collect.Sets;
import nom.edu.starrism.admin.core.domain.entity.SysPermissionDetail;
import nom.edu.starrism.admin.core.mapper.SysPermissionDetailMapper;
import nom.edu.starrism.admin.core.mapper.SysRoleMapper;
import nom.edu.starrism.admin.core.service.SysPermissionService;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.util.CollectionUtil;
import nom.edu.starrism.data.pool.PermissionPool;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>系统权限服务类接口实现类</p>
 *
 * @author hedwing
 * @since 2022/11/4
 **/
@Service(value = "sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SysPermissionServiceImpl.class);
    @Resource
    private SysPermissionDetailMapper sysPermissionDetailMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;

    /**
     * <p>获取用户的权限列表</p>
     *
     * @param userId userId
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/11/4
     */
    @Override
    @Cacheable(key = "#userId", cacheNames = "permission:action-url:of:user", unless = "#result == null || #result.isEmpty()")
    public Set<String> findPermissionOfUser(Long userId) {
        Set<Long> roleIds = sysRoleMapper.findRoleIdsByUserId(userId);
        if (CollectionUtil.isEmpty(roleIds)) {
            LOGGER.warn("根据[userId={}]查询到的角色为空", userId);
            return Sets.newHashSet();
        }
        return findPermissionOfRoles(roleIds);
    }

    /**
     * <p>获取用户的权限列表</p>
     *
     * @param roles roles
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/11/4
     */
    @Override
    public Set<String> findPermissionOfRoles(Collection<Long> roles) {
        Set<String> categoryAuth = PermissionPool.CATEGORY_AUTH;
        List<SysPermissionDetail> details = sysPermissionDetailMapper.findByCategoryCodesAndRoleIds(categoryAuth, roles);
        if (CollectionUtil.isEmpty(details)) {
            LOGGER.warn("根据[categoryCodes={}和roles={}]查询到的权限为空", categoryAuth, roles);
            return Sets.newHashSet();
        }
        return details.stream().map(SysPermissionDetail::getRequestActionUrl).collect(Collectors.toSet());
    }
}
