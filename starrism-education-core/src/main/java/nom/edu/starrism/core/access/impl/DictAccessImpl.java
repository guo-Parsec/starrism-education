package nom.edu.starrism.core.access.impl;

import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.core.access.DictAccess;
import nom.edu.starrism.core.domain.entity.SysDictCategory;
import nom.edu.starrism.core.domain.entity.SysDictDetail;
import nom.edu.starrism.core.domain.vo.SysDictCategoryVo;
import nom.edu.starrism.core.domain.vo.SysDictDetailVo;
import nom.edu.starrism.core.pool.BeanPool;
import nom.edu.starrism.core.pool.CacheNamesPool;
import nom.edu.starrism.core.repository.SysDictCategoryRepository;
import nom.edu.starrism.core.repository.SysDictDetailRepository;
import nom.edu.starrism.data.domain.entity.AbstractEntity;
import nom.edu.starrism.data.support.EntityConverters;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>数据字典访问服务层接口实现类</p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
@Service(BeanPool.DICT_ACCESS_BEAN_NAME)
public class DictAccessImpl implements DictAccess {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(DictAccessImpl.class);
    @Resource
    private SysDictCategoryRepository sysDictCategoryRepository;
    @Resource
    private SysDictDetailRepository sysDictDetailRepository;

    /**
     * <p>查询字典分类</p>
     *
     * @param id 字典分类主键
     * @return {@link SysDictCategoryVo}
     * @author guocq
     * @date 2022/11/15 17:05
     */
    @Override
    @Cacheable(cacheNames = CacheNamesPool.CN_SYS_DICT_CATEGORY, key = "'id:'+#id", unless = "#result == null")
    public SysDictCategoryVo findCategory(Long id) {
        SysDictCategory category = sysDictCategoryRepository.find(id);
        if (AbstractEntity.isEmpty(category)) {
            LOGGER.debug("根据[id={}]查询结果=为空", id);
            return null;
        }
        return category.toVo();
    }

    /**
     * <p>查询字典分类</p>
     *
     * @param categoryCode 字典分类码
     * @return {@link SysDictCategoryVo}
     * @author guocq
     * @date 2022/11/15 17:05
     */
    @Override
    @Cacheable(cacheNames = CacheNamesPool.CN_SYS_DICT_CATEGORY, key = "'categoryCode:'+#categoryCode", unless = "#result == null")
    public SysDictCategoryVo findCategoryByCategoryCode(String categoryCode) {
        SysDictCategory category = sysDictCategoryRepository.findByCode(categoryCode);
        if (AbstractEntity.isEmpty(category)) {
            LOGGER.debug("根据[categoryCode={}]查询结果=为空", categoryCode);
            return null;
        }
        return category.toVo();
    }

    /**
     * <p>根据分类码查询字典</p>
     *
     * @param categoryCode 字典类别码
     * @return {@link List<SysDictDetailVo>}
     * @author hedwing
     * @since 2022/10/23
     */
    @Override
    @Cacheable(cacheNames = CacheNamesPool.CN_SYS_DICT, key = "'categoryCode:'+#categoryCode", unless = "#result == null || #result.isEmpty()")
    public List<SysDictDetailVo> findDictByCategoryCode(String categoryCode) {
        SysDictCategory category = sysDictCategoryRepository.findByCode(categoryCode);
        if (AbstractEntity.isEmpty(category)) {
            LOGGER.debug("根据[categoryCode={}]查询结果=为空", categoryCode);
            return null;
        }
        List<SysDictDetail> details = sysDictDetailRepository.findByCategoryId(category.getId());
        return details.stream().map(SysDictDetail::toVo).collect(Collectors.toList());
    }

    /**
     * <p>根据分类码与字典值查询字典</p>
     *
     * @param categoryCode 字典类别码
     * @param dictValue    字典值
     * @return {@link SysDictDetailVo}
     * @author hedwing
     * @since 2022/10/23
     */
    @Override
    @Cacheable(cacheNames = CacheNamesPool.CN_SYS_DICT, key = "'categoryCode:'+#categoryCode+':dictValue:'+#dictValue", unless = "#result == null")
    public SysDictDetailVo findDictByCategoryCodeAndDictValue(String categoryCode, String dictValue) {
        SysDictCategory category = sysDictCategoryRepository.findByCode(categoryCode);
        if (AbstractEntity.isEmpty(category)) {
            LOGGER.debug("根据[categoryCode={},dictValue={}]查询结果=为空", categoryCode, dictValue);
            return null;
        }
        return EntityConverters.convertToVo(sysDictDetailRepository.findByCategoryCodeAndDictValue(category.getId(), dictValue), SysDictDetailVo.class);
    }

    /**
     * <p>根据分类码与字典码查询字典</p>
     *
     * @param categoryCode 字典类别码
     * @param dictCode     字典码
     * @return {@link SysDictDetailVo}
     * @author hedwing
     * @since 2022/11/12
     */
    @Override
    @Cacheable(cacheNames = CacheNamesPool.CN_SYS_DICT, key = "'categoryCode:'+#categoryCode+':dictCode:'+#dictCode", unless = "#result == null")
    public SysDictDetailVo findDictByCategoryCodeAndDictCode(String categoryCode, String dictCode) {
        SysDictCategory category = sysDictCategoryRepository.findByCode(categoryCode);
        if (AbstractEntity.isEmpty(category)) {
            LOGGER.debug("根据[categoryCode={},dictCode={}]查询结果=为空", categoryCode, dictCode);
            return null;
        }
        return EntityConverters.convertToVo(sysDictDetailRepository.findByCategoryCodeAndDictCode(category.getId(), dictCode), SysDictDetailVo.class);
    }

    /**
     * <p>根据主键id查询详情数据</p>
     *
     * @param id 主键
     * @return {@link SysDictDetailVo}
     * @author guocq
     * @date 2022/11/15 16:13
     */
    @Override
    @Cacheable(cacheNames = CacheNamesPool.CN_SYS_DICT, key = "'id:'+#id", unless = "#result == null")
    public SysDictDetailVo findDetail(Long id) {
        SysDictDetail sysDictDetail = sysDictDetailRepository.find(id);
        if (SysDictDetail.isEmpty(sysDictDetail)) {
            LOGGER.debug("根据[id={}]查询结果=为空", id);
            return null;
        }
        return sysDictDetail.toVo();
    }

}
