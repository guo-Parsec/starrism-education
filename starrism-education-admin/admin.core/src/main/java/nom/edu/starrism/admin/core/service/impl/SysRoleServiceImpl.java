package nom.edu.starrism.admin.core.service.impl;

import com.google.common.collect.Sets;
import nom.edu.starrism.admin.core.domain.entity.SysRole;
import nom.edu.starrism.admin.core.mapper.SysRoleMapper;
import nom.edu.starrism.admin.core.service.SysRoleService;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.util.CollectionUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/11/7
 **/
@Service(value = "sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SysRoleServiceImpl.class);
    @Resource
    private SysRoleMapper sysRoleMapper;

    /**
     * <p>获取角色码：根据用户id获取</p>
     *
     * @param userId userId
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/11/7
     */
    @Override
    @Cacheable(key = "#userId", cacheNames = "role:code:of:user", unless = "#result == null || #result.isEmpty()")
    public Set<String> findRoleCodesOfUser(Long userId) {
        List<SysRole> roles = sysRoleMapper.findByUserId(userId);
        if (CollectionUtil.isEmpty(roles)) {
            LOGGER.debug("根据[userId={}]查询到的角色为空", userId);
            return Sets.newHashSet();
        }
        return roles.stream().map(SysRole::getRoleCode).collect(Collectors.toSet());
    }
}
