package nom.edu.starrism.admin.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import nom.edu.starrism.admin.core.domain.param.SysDictDetailParam;
import nom.edu.starrism.admin.core.mapper.SysDictDetailMapper;
import nom.edu.starrism.admin.core.service.SysDictDetailService;
import nom.edu.starrism.common.enums.SeCommonResultCode;
import nom.edu.starrism.common.exception.SeException;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.util.TextFormat;
import nom.edu.starrism.core.access.DictAccess;
import nom.edu.starrism.core.domain.entity.SysDictCategory;
import nom.edu.starrism.core.domain.entity.SysDictDetail;
import nom.edu.starrism.core.domain.vo.SysDictDetailVo;
import nom.edu.starrism.core.pool.CachePool;
import nom.edu.starrism.core.repository.SysDictCategoryRepository;
import nom.edu.starrism.core.service.AbstractCrudService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>字典类别Service实现类</p>
 *
 * @author hedwing
 * @since 2022/11/11
 **/
@Service("sysDictDetailService")
public class SysDictDetailServiceImpl extends AbstractCrudService<SysDictDetail, SysDictDetailVo, SysDictDetailParam> implements SysDictDetailService {
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
    @CacheEvict(cacheNames = CachePool.CN_SYS_DICT, allEntries = true)
    public SysDictDetail createAndReturn(SysDictDetailParam param) {
        String categoryCode = param.getCategoryCode();
        SysDictCategory category = checkCategoryCodeExist(categoryCode);
        checkDictCodeExist(categoryCode, param.getDictCode());
        checkDictValueExist(categoryCode, param.getDictValue(), null);
        SysDictDetail detail = param.toEntity(category);
        sysDictDetailMapper.insert(detail);
        return detail;
    }

    /**
     * <p>字典详情数据修改</p>
     *
     * @param param param
     * @author hedwing
     * @since 2022/11/12
     */
    @CacheEvict(cacheNames = CachePool.CN_SYS_DICT, allEntries = true)
    @Override
    public void update(SysDictDetailParam param) {
        String categoryCode = param.getCategoryCode();
        SysDictCategory category = checkCategoryCodeExist(categoryCode);
        checkDictCodeExist(categoryCode, param.getDictCode());
        checkDictValueExist(categoryCode, param.getDictValue(), param.getDictCode());
        SysDictDetail detail = param.toEntity(category);
        sysDictDetailMapper.updateById(detail);
    }

    /**
     * <p>数据删除</p>
     *
     * @param param param
     * @author hedwing
     * @since 2022/11/12
     */
    @Override
    @CacheEvict(cacheNames = CachePool.CN_SYS_DICT, allEntries = true)
    public void delete(SysDictDetailParam param) {
        checkDictIdExist(param.getId());

    }

    /**
     * <p>判断字典id是否存在 若不存在则抛出异常</p>
     *
     * @param id id
     * @author hedwing
     * @since 2022/11/13
     */
    private void checkDictIdExist(Long id) {
        LambdaQueryWrapper<SysDictDetail> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDictDetail::getId, id);
        boolean exists = sysDictDetailMapper.exists(wrapper);
        if (!exists) {
            LOGGER.error("不存在id为{}的字典", id);
            throw new SeException(SeCommonResultCode.DATA_NOT_EXIST, String.format("不存在id为%s的字典", id));
        }
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
            LOGGER.error("不存在分类码为{}的字典", categoryCode);
            throw new SeException(SeCommonResultCode.DATA_NOT_EXIST, TextFormat.format("不存在分类码为{categoryCode}的字典", categoryCode));
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
            LOGGER.error("[categoryCode={},dictCode={}]已维护", categoryCode, dictCode);
            throw new SeException(SeCommonResultCode.DATA_EXIST, String.format("[categoryCode=%s,dictValue=%s]已维护", categoryCode, dictCode));
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
            LOGGER.error("[categoryCode={},dictValue={}]已维护", categoryCode, dictValue);
            throw new SeException(SeCommonResultCode.DATA_EXIST, String.format("[categoryCode=%s,dictValue=%s]已维护", categoryCode, dictValue));
        }
    }
}
