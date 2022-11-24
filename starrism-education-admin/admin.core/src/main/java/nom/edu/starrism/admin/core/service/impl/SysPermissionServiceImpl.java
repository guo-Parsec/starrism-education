package nom.edu.starrism.admin.core.service.impl;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import nom.edu.starrism.admin.core.domain.entity.SysPermissionDetail;
import nom.edu.starrism.admin.core.mapper.SysPermissionDetailMapper;
import nom.edu.starrism.admin.core.mapper.SysRoleMapper;
import nom.edu.starrism.admin.core.service.SysPermissionService;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.service.RedisService;
import nom.edu.starrism.common.util.CollectionUtil;
import nom.edu.starrism.common.util.JsonUtil;
import nom.edu.starrism.core.annotation.cache.CacheClear;
import nom.edu.starrism.core.annotation.cache.CacheGroup;
import nom.edu.starrism.core.helper.ResourceHelper;
import nom.edu.starrism.core.pool.CacheNamesPool;
import nom.edu.starrism.data.pool.PermissionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
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
    private static final String ALL_SIZE_KEY = "总条数";
    private static final String MODIFY_SIZE_KEY = "修改的条数";
    private static final String CREATE_SIZE_KEY = "创建的条数";

    @Resource
    private SysPermissionDetailMapper sysPermissionDetailMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    private RedisService redisService;

    /**
     * <p>获取用户的权限请求url列表</p>
     *
     * @param userId userId
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/11/4
     */
    @Override
    @Cacheable(cacheNames = "permission:action-url:of:user", key = "#userId", unless = "#result == null || #result.isEmpty()")
    public Set<String> findPermissionUrlOfUser(Long userId) {
        Set<Long> roleIds = sysRoleMapper.findRoleIdsByUserId(userId);
        if (CollectionUtil.isEmpty(roleIds)) {
            LOGGER.debug("根据[userId={}]查询到的角色为空", userId);
            return Sets.newHashSet();
        }
        return findPermissionUrlOfRoles(roleIds);
    }

    /**
     * <p>获取用户的权限请求url列表</p>
     *
     * @param roles roles
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/11/4
     */
    @Override
    public Set<String> findPermissionUrlOfRoles(Collection<Long> roles) {
        Set<String> categoryAuth = PermissionPool.CATEGORY_AUTH;
        List<SysPermissionDetail> details = sysPermissionDetailMapper.findByCategoryCodesAndRoleIds(categoryAuth, roles);
        if (CollectionUtil.isEmpty(details)) {
            LOGGER.debug("根据[categoryCodes={}和roles={}]查询到的权限为空", categoryAuth, roles);
            return Sets.newHashSet();
        }
        return details.stream().map(SysPermissionDetail::getRequestActionUrl).collect(Collectors.toSet());
    }

    /**
     * <p>根据用户id查询权限码</p>
     *
     * @param userId userId
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/11/7
     */
    @Override
    @Cacheable(cacheNames = "permission:code:of:user", key = "#userId", unless = "#result == null || #result.isEmpty()")
    public Set<String> findPermissionCodeOfUser(Long userId) {
        Set<Long> roleIds = sysRoleMapper.findRoleIdsByUserId(userId);
        if (CollectionUtil.isEmpty(roleIds)) {
            LOGGER.debug("根据[userId={}]查询到的角色为空", userId);
            return Sets.newHashSet();
        }
        Set<String> emptyAuth = Sets.newHashSet();
        List<SysPermissionDetail> details = sysPermissionDetailMapper.findByCategoryCodesAndRoleIds(emptyAuth, roleIds);
        if (CollectionUtil.isEmpty(details)) {
            LOGGER.debug("根据[userId={}]查询到的权限为空", userId);
            return Sets.newHashSet();
        }
        return details.stream().map(SysPermissionDetail::getPermissionCode).collect(Collectors.toSet());
    }

    /**
     * <p>根据类别查询权限请求url路径</p>
     *
     * @param categoryCode 类别码
     * @return java.util.Set<java.lang.String>
     * @author guocq
     * @date 2022/11/10 14:29
     */
    @Override
    @Cacheable(cacheNames = CacheNamesPool.CN_SYS_PERMISSION, key = "'categoryCode'+':'+#categoryCode", unless = "#result == null || #result.isEmpty()")
    public Set<String> findPermissionUrlByCategory(String categoryCode) {
        List<SysPermissionDetail> details = sysPermissionDetailMapper.findByCategoryCode(categoryCode);
        if (CollectionUtil.isEmpty(details)) {
            LOGGER.debug("根据[categoryCode={}]查询到的权限为空", categoryCode);
            return Sets.newHashSet();
        }
        return details.stream().map(SysPermissionDetail::getRequestActionUrl).collect(Collectors.toSet());
    }

    /**
     * <p>生成权限到数据库</p>
     *
     * @param cacheUuid 缓存uuid
     * @return Map<String, Long> 成功录入的数量
     * @author guocq
     * @date 2022/11/18 9:55
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    @CacheGroup(cacheClear = {@CacheClear(name = CacheNamesPool.CN_SYS_PERMISSION, fuzzyMatch = true)})
    public Map<String, Long> generatePermissionIntoDb(String cacheUuid) {
        String key = ResourceHelper.generateResourcesCacheKey(cacheUuid);
        Map<String, Long> map = Maps.newHashMapWithExpectedSize(3);
        List<Object> resources = redisService.lRange(key);
        if (CollectionUtil.isEmpty(resources)) {
            LOGGER.debug("键是{}的数据查询为空", key);
            map.put(ALL_SIZE_KEY, 0L);
            return map;
        }
        List<SysPermissionDetail> existDetails = sysPermissionDetailMapper.findByCategoryCodesAndRoleIds(Sets.newHashSet(), Sets.newHashSet());
        Set<String> allCachedCode = existDetails.stream().map(SysPermissionDetail::getRequestActionUrl).collect(Collectors.toSet());
        List<SysPermissionDetail> allList = resources.stream()
                .map(resource -> JsonUtil.mapToObject((Map<String, Object>) resource, SysPermissionDetail.class))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        long createIntoDbSize = createIntoDb(allList, allCachedCode);
        long modifyIntoDbSize = modifyIntoDb(allList, allCachedCode, existDetails);
        if (createIntoDbSize + modifyIntoDbSize > 0) {
            redisService.del(key);
        }
        map.put(ALL_SIZE_KEY, (long) allList.size());
        map.put(CREATE_SIZE_KEY, createIntoDbSize);
        map.put(MODIFY_SIZE_KEY, modifyIntoDbSize);
        return map;
    }

    /**
     * <p>新增入db</p>
     *
     * @param allList       全部数据
     * @param allCachedCode 已存的权限码
     * @return long
     * @author guocq
     * @date 2022/11/18 13:58
     */
    private long createIntoDb(List<SysPermissionDetail> allList, Set<String> allCachedCode) {
        if (CollectionUtil.isEmpty(allList)) {
            return 0L;
        }
        AtomicReference<Integer> lastSort = new AtomicReference<>(sysPermissionDetailMapper.findLastSort());
        List<SysPermissionDetail> needCreateList = allList.stream()
                .filter(detail -> !allCachedCode.contains(detail.getRequestActionUrl()))
                .peek(detail -> setDetailSort(detail, lastSort))
                .sorted(Comparator.comparingInt(SysPermissionDetail::getSort))
                .collect(Collectors.toList());
        long size = 0L;
        if (CollectionUtil.isNotEmpty(needCreateList)) {
            size = sysPermissionDetailMapper.insertAll(needCreateList);
        }
        return size;
    }

    /**
     * <p>修改入db</p>
     *
     * @param allList       全部数据
     * @param allCachedCode 已存的权限码
     * @param existDetails  已存权限详情
     * @return long
     * @author guocq
     * @date 2022/11/18 13:58
     */
    private long modifyIntoDb(List<SysPermissionDetail> allList, Set<String> allCachedCode, List<SysPermissionDetail> existDetails) {
        if (CollectionUtil.isEmpty(allList)) {
            return 0L;
        }
        Map<String, SysPermissionDetail> detailMap = existDetails.stream().collect(Collectors.toMap(SysPermissionDetail::getRequestActionUrl, o -> o));
        List<SysPermissionDetail> needModifyList = allList.stream()
                .filter(detail -> allCachedCode.contains(detail.getRequestActionUrl()))
                .peek(detail -> buildPermissionFromDb(detailMap, detail))
                .collect(Collectors.toList());
        long size = 0L;
        if (CollectionUtil.isNotEmpty(needModifyList)) {
            size = sysPermissionDetailMapper.updateList(needModifyList);
        }
        return size;
    }

    /**
     * <p>设置详情的排序</p>
     *
     * @param detail   详情
     * @param lastSort 上次最大排序
     * @author guocq
     * @date 2022/11/18 14:07
     */
    private void setDetailSort(SysPermissionDetail detail, AtomicReference<Integer> lastSort) {
        lastSort.getAndSet(lastSort.get() + 10);
        detail.setSort(lastSort.get());
    }

    /**
     * <p>从数据库中的数据构建权限信息</p>
     *
     * @param fromDbDetailMap 来自数据库中已存在数据列表
     * @param detail          需要针对修改的详情数据
     * @author guocq
     * @date 2022/11/18 14:02
     */
    private void buildPermissionFromDb(Map<String, SysPermissionDetail> fromDbDetailMap, SysPermissionDetail detail) {
        SysPermissionDetail db = fromDbDetailMap.get(detail.getRequestActionUrl());
        detail.setId(db.getId());
        detail.setPermissionCategoryId(db.getPermissionCategoryId());
        detail.setPermissionName(db.getPermissionName());
        detail.setPermissionCode(db.getPermissionCode());
        detail.setSort(detail.getSort() == null ? db.getSort() : detail.getSort());
        detail.setRemark(db.getRemark());

    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }
}
