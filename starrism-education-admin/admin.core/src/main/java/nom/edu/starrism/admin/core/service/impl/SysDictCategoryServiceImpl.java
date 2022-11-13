package nom.edu.starrism.admin.core.service.impl;

import nom.edu.starrism.admin.core.domain.param.SysDictCategoryParam;
import nom.edu.starrism.admin.core.mapper.SysDictCategoryMapper;
import nom.edu.starrism.admin.core.service.SysDictCategoryService;
import nom.edu.starrism.common.enums.SeCommonResultCode;
import nom.edu.starrism.common.exception.SeException;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.util.TextFormat;
import nom.edu.starrism.core.domain.entity.SysDictCategory;
import nom.edu.starrism.core.pool.CachePool;
import nom.edu.starrism.core.repository.SysDictCategoryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>字典类别Service实现类</p>
 *
 * @author hedwing
 * @since 2022/11/11
 **/
@Service(value = "sysDictCategoryService")
public class SysDictCategoryServiceImpl implements SysDictCategoryService {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SysDictCategoryServiceImpl.class);
    @Resource
    private SysDictCategoryMapper sysDictCategoryMapper;
    @Resource
    SysDictCategoryRepository categoryRepository;

    /**
     * <p>字典类别数据新增</p>
     *
     * @param param param
     * @author hedwing
     * @since 2022/11/11
     */
    @Override
    @CacheEvict(cacheNames = CachePool.CN_SYS_DICT, key = "#param.categoryCode")
    public void addSysDictCategory(SysDictCategoryParam param) {
        LOGGER.debug("字典类别数据{}新增", param);
        String categoryCode = param.getCategoryCode();
        SysDictCategory category = categoryRepository.findByCode(categoryCode);
        if (SysDictCategory.isNotEmpty(category)) {
            LOGGER.error("分类码为{}的字典类别已存在", categoryCode);
            throw new SeException(SeCommonResultCode.DATA_EXIST, TextFormat.format("分类码为{categoryCode}的字典类别已存在", categoryCode));
        }
        SysDictCategory sysDictCategory = param.toEntity();
        sysDictCategoryMapper.insert(sysDictCategory);
    }
}
