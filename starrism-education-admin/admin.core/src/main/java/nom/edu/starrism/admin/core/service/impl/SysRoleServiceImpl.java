package nom.edu.starrism.admin.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Sets;
import nom.edu.starrism.admin.api.domain.param.SysRolePageParam;
import nom.edu.starrism.admin.api.domain.vo.SysRoleVo;
import nom.edu.starrism.admin.core.domain.entity.SysRole;
import nom.edu.starrism.admin.core.mapper.SysRoleMapper;
import nom.edu.starrism.admin.core.service.SysRoleService;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.util.CollectionUtil;
import nom.edu.starrism.core.annotation.PageQuery;
import nom.edu.starrism.core.util.PageUtil;
import nom.edu.starrism.data.domain.vo.Pageable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collector;
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

    /**
     * <p>角色分页查询</p>
     *
     * @param param 分页查询参数
     * @return {@link Page<SysRoleVo>}
     * @author guocq
     * @date 2022/11/11 14:44
     */
    @Override
    @PageQuery
    public PageInfo<SysRoleVo> sysRolePageQuery(SysRolePageParam param) {
        Page<SysRole> pageData = sysRoleMapper.paginationQuery(param);
        List<SysRoleVo> sysRoleVos = pageData.stream().map(SysRole::toVo).collect(Collectors.toList());
        return PageUtil.toVoPage(sysRoleVos, new PageInfo<>(pageData));
    }

}
