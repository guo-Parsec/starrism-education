package nom.edu.starrism.admin.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import nom.edu.starrism.admin.core.domain.param.SysDictDetailParam;
import nom.edu.starrism.admin.core.mapper.SysDictDetailMapper;
import nom.edu.starrism.admin.core.service.SysDictDetailService;
import nom.edu.starrism.common.enums.BaseRequest;
import nom.edu.starrism.common.helper.CodeHelper;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.core.access.DictAccess;
import nom.edu.starrism.core.annotation.cache.CacheClear;
import nom.edu.starrism.core.annotation.cache.CacheGroup;
import nom.edu.starrism.core.annotation.crud.PageQuery;
import nom.edu.starrism.core.domain.entity.SysDictCategory;
import nom.edu.starrism.core.domain.entity.SysDictDetail;
import nom.edu.starrism.core.domain.vo.SysDictDetailVo;
import nom.edu.starrism.core.pool.CacheNamesPool;
import nom.edu.starrism.core.repository.SysDictCategoryRepository;
import nom.edu.starrism.core.service.AbstractCoreService;
import nom.edu.starrism.core.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>字典类别Service实现类</p>
 *
 * @author hedwing
 * @since 2022/11/11
 **/
@Service("sysDictDetailService")
public class SysDictDetailServiceImpl extends AbstractCoreService<SysDictDetailVo, SysDictDetailParam> implements SysDictDetailService {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SysDictDetailServiceImpl.class);
    @Resource
    private SysDictDetailMapper sysDictDetailMapper;
    @Resource
    SysDictCategoryRepository categoryRepository;
    @Resource
    DictAccess dictAccess;

    /**
     * <p>字典详情数据新增</p>
     *
     * @param param param
     * @author hedwing
     * @since 2022/11/12
     */
    @Override
    @CacheGroup(
            cacheClear = {
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'categoryCode:'+#result.categoryVo.categoryCode", fuzzyMatch = true),
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'categoryId:'+#result.categoryVo.id", fuzzyMatch = true),
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'id:'+#result.id", fuzzyMatch = true)
            }
    )
    public SysDictDetailVo create(SysDictDetailParam param) {
        String categoryCode = param.getCategoryCode();
        SysDictCategory category = checkCategoryCodeExist(categoryCode);
        checkDictCodeExist(categoryCode, param.getDictCode());
        checkDictValueExist(categoryCode, param.getDictValue(), param.getDictCode());
        SysDictDetail detail = param.toEntity(category);
        sysDictDetailMapper.insert(detail);
        detail.setCategory(category);
        return detail.toVo();
    }

    /**
     * <p>字典详情数据修改</p>
     *
     * @param param param
     * @author hedwing
     * @since 2022/11/12
     */
    @Override
    @CacheGroup(
            cacheClear = {
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'categoryCode:'+#result.categoryVo.categoryCode", fuzzyMatch = true),
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'categoryId:'+#result.categoryVo.id", fuzzyMatch = true),
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'id:'+#result.id", fuzzyMatch = true)
            }
    )
    public SysDictDetailVo update(SysDictDetailParam param) {
        String categoryCode = param.getCategoryCode();
        SysDictCategory category = checkCategoryCodeExist(categoryCode);
        checkDictCodeNotExist(categoryCode, param.getDictCode());
        checkDictValueExist(categoryCode, param.getDictValue(), param.getDictCode());
        SysDictDetail detail = param.toEntity(category);
        sysDictDetailMapper.update(detail);
        detail.setCategory(category);
        return detail.toVo();
    }

    /**
     * <p>数据删除</p>
     *
     * @param id id
     * @author hedwing
     * @since 2022/11/12
     */
    @Override
    @CacheGroup(
            cacheClear = {
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'categoryCode:'+#result.categoryVo.categoryCode", fuzzyMatch = true),
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'categoryId:'+#result.categoryVo.id", fuzzyMatch = true),
                    @CacheClear(name = CacheNamesPool.CN_SYS_DICT, key = "'id:'+#result.id", fuzzyMatch = true)
            }
    )
    public SysDictDetailVo delete(Long id) {
        SysDictDetailVo sysDictDetailVo = checkDictIdExist(id);
        sysDictDetailMapper.delete(id);
        return sysDictDetailVo;
    }

    /**
     * <p>分页查询</p>
     *
     * @param param param
     * @return {@link PageInfo<SysDictDetailVo>}
     * @author hedwing
     * @since 2022/11/12
     */
    @PageQuery
    @Override
    public PageInfo<SysDictDetailVo> pageQuery(SysDictDetailParam param) {
        LOGGER.debug("根据参数{}条件查询分页数据", param);
        Page<SysDictDetail> pageData = sysDictDetailMapper.paginationQuery(param);
        List<SysDictDetailVo> sysDictDetailVos = pageData.stream().map(SysDictDetail::toVo).collect(Collectors.toList());
        return PageUtil.toVoPage(sysDictDetailVos, new PageInfo<>(pageData));
    }

    /**
     * <p>查询数据(不分页)</p>
     *
     * @param param param
     * @return {@link List<SysDictDetailVo>}
     * @author guocq
     * @date 2022/11/16 9:45
     */
    @Override
    public List<SysDictDetailVo> listQuery(SysDictDetailParam param) {
        LOGGER.debug("根据参数{}条件查询数据(不进行分页)", param);
        List<SysDictDetail> list = sysDictDetailMapper.listQuery(param);
        return list.stream().map(SysDictDetail::toVo).collect(Collectors.toList());
    }

    /**
     * <p>判断字典id是否存在 若不存在则抛出异常</p>
     *
     * @param id id
     * @author hedwing
     * @since 2022/11/13
     */
    private SysDictDetailVo checkDictIdExist(Long id) {
        SysDictDetailVo detail = dictAccess.findDetail(id);
        if (Objects.isNull(detail)) {
            String errorMessage = "不存在id为{}的字典";
            CodeHelper.throwError(LOGGER, BaseRequest.DATA_NOT_EXIST, errorMessage, id);
        }
        return detail;
    }

    /**
     * <p>判断字典分类码是否存在 若不存在则抛出异常</p>
     *
     * @param categoryCode categoryCode
     * @author hedwing
     * @since 2022/11/12
     */
    private SysDictCategory checkCategoryCodeExist(String categoryCode) {
        SysDictCategory category = categoryRepository.findByCode(categoryCode);
        if (SysDictCategory.isEmpty(category)) {
            String errorMessage = "不存在分类码为{}的字典";
            CodeHelper.throwError(LOGGER, BaseRequest.DATA_NOT_EXIST, errorMessage, categoryCode);
        }
        return category;
    }

    /**
     * <p>判断类别与字典码是否已经被维护 若被维护则抛出异常</p>
     *
     * @param categoryCode categoryCode
     * @param dictCode     dictCode
     * @author hedwing
     * @since 2022/11/12
     */
    private void checkDictCodeExist(String categoryCode, String dictCode) {
        SysDictDetailVo dict = dictAccess.findDictByCategoryCodeAndDictCode(categoryCode, dictCode);
        if (SysDictDetailVo.isNotEmpty(dict)) {
            String errorMessage = "[categoryCode={},dictCode={}]已维护";
            CodeHelper.throwError(LOGGER, BaseRequest.DATA_EXIST, errorMessage, categoryCode, dictCode);
        }
    }

    /**
     * <p>判断类别与字典码是否已经被维护 若被维护则抛出异常</p>
     *
     * @param categoryCode categoryCode
     * @param dictCode     dictCode
     * @author hedwing
     * @since 2022/11/12
     */
    private void checkDictCodeNotExist(String categoryCode, String dictCode) {
        SysDictDetailVo dict = dictAccess.findDictByCategoryCodeAndDictCode(categoryCode, dictCode);
        if (SysDictDetailVo.isEmpty(dict)) {
            String errorMessage = "[categoryCode={},dictCode={}]不存在";
            CodeHelper.throwError(LOGGER, BaseRequest.DATA_NOT_EXIST, errorMessage, categoryCode, dictCode);
        }
    }

    /**
     * <p>判断类别与字典值是否已经被维护 若被维护则抛出异常</p>
     *
     * @param categoryCode categoryCode
     * @param dictValue    dictValue
     * @author hedwing
     * @since 2022/11/12
     */
    private void checkDictValueExist(String categoryCode, String dictValue, String dictCode) {
        SysDictDetailVo dict = dictAccess.findDictByCategoryCodeAndDictValue(categoryCode, dictValue);
        if (SysDictDetailVo.isNotEmpty(dict) && !dict.getDictCode().equals(dictCode)) {
            String errorMessage = "[categoryCode={},dictValue={}]已维护";
            CodeHelper.throwError(LOGGER, BaseRequest.DATA_EXIST, errorMessage, categoryCode, dictValue);
        }
    }
}
