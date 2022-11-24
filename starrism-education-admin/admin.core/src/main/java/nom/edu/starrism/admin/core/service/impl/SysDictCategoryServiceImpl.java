package nom.edu.starrism.admin.core.service.impl;

import nom.edu.starrism.admin.core.domain.param.SysDictCategoryParam;
import nom.edu.starrism.admin.core.mapper.SysDictCategoryMapper;
import nom.edu.starrism.admin.core.mapper.SysDictDetailMapper;
import nom.edu.starrism.admin.core.service.SysDictCategoryService;
import nom.edu.starrism.common.enums.BaseRequest;
import nom.edu.starrism.common.helper.CodeHelper;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.core.access.DictAccess;
import nom.edu.starrism.core.annotation.cache.CacheClear;
import nom.edu.starrism.core.annotation.cache.CacheGroup;
import nom.edu.starrism.core.domain.entity.SysDictCategory;
import nom.edu.starrism.core.domain.vo.SysDictCategoryVo;
import nom.edu.starrism.core.pool.CacheNamesPool;
import nom.edu.starrism.core.service.AbstractCoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>字典类别Service实现类</p>
 *
 * @author hedwing
 * @since 2022/11/11
 **/
@Service(value = "sysDictCategoryService")
public class SysDictCategoryServiceImpl extends AbstractCoreService<SysDictCategoryVo, SysDictCategoryParam> implements SysDictCategoryService {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SysDictCategoryServiceImpl.class);
    @Resource
    private SysDictCategoryMapper sysDictCategoryMapper;
    @Resource
    private SysDictDetailMapper sysDictDetailMapper;
    @Resource
    DictAccess dictAccess;

    /**
     * <p>数据新增</p>
     *
     * @param param param
     * @return 新增后的数据
     * @author hedwing
     * @since 2022/11/12
     */
    @Override
    @CacheGroup(
            cacheClear = {
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'categoryCode:'+#result.categoryCode", fuzzyMatch = true),
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'categoryId:'+#result.id", fuzzyMatch = true),
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT_CATEGORY, key = "'categoryCode:'+#result.categoryCode", fuzzyMatch = true)
            }
    )
    public SysDictCategoryVo create(SysDictCategoryParam param) {
        LOGGER.debug("字典类别数据{}新增", param);
        checkCategoryIsExist(param.getCategoryCode(), Boolean.FALSE);
        SysDictCategory sysDictCategory = param.toEntity();
        sysDictCategoryMapper.insert(sysDictCategory);
        return sysDictCategory.toVo();
    }

    /**
     * <p>数据编辑</p>
     *
     * @param param param
     * @return V 结果数据
     * @author hedwing
     * @since 2022/11/12
     */
    @CacheGroup(
            cacheClear = {
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'categoryCode:'+#result.categoryCode", fuzzyMatch = true),
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'categoryId:'+#result.id", fuzzyMatch = true),
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT_CATEGORY, key = "'categoryCode:'+#result.categoryCode", fuzzyMatch = true)
            }
    )
    @Override
    public SysDictCategoryVo update(SysDictCategoryParam param) {
        LOGGER.debug("字典类别数据{}修改", param);
        checkCategoryIsExist(param.getCategoryCode(), Boolean.TRUE);
        SysDictCategory category = param.toEntity();
        sysDictCategoryMapper.update(category);
        return category.toVo();
    }

    /**
     * <p>数据删除</p>
     *
     * @param id 主键
     * @return V 结果数据
     * @author hedwing
     * @since 2022/11/12
     */
    @CacheGroup(
            cacheClear = {
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'categoryCode:'+#result.categoryCode", fuzzyMatch = true),
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'categoryId:'+#result.id", fuzzyMatch = true),
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT_CATEGORY, key = "'categoryCode:'+#result.categoryCode", fuzzyMatch = true)
            }
    )
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysDictCategoryVo delete(Long id) {
        SysDictCategoryVo category = dictAccess.findCategory(id);
        if (category == null) {
            String errorMessage = "id为{}的字典类别不存在";
            CodeHelper.throwError(LOGGER, BaseRequest.DATA_EXIST, errorMessage, id);
        }
        sysDictCategoryMapper.delete(id);
        sysDictDetailMapper.deleteByCategoryId(id);
        return category;
    }

    /**
     * <p>检查分类是否存在</p>
     *
     * @param categoryCode 分类码
     * @param emptyError   emptyError为true则当查询结果为空时抛出异常，emptyError为false则当查询结果不为空时抛出异常
     * @author guocq
     * @date 2022/11/15 16:39
     */
    private void checkCategoryIsExist(String categoryCode, boolean emptyError) {
        SysDictCategoryVo category = dictAccess.findCategoryByCategoryCode(categoryCode);
        if (SysDictCategory.isNotEmpty(category) && !emptyError) {
            String errorMessage = "分类码为{}的字典类别已存在";
            CodeHelper.throwError(LOGGER, BaseRequest.DATA_EXIST, errorMessage, categoryCode);
        }
    }
}
