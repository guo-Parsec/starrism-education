package nom.edu.starrism.core.access.impl;

import nom.edu.starrism.core.access.DictAccess;
import nom.edu.starrism.core.domain.entity.SysDictCategory;
import nom.edu.starrism.core.domain.entity.SysDictDetail;
import nom.edu.starrism.core.domain.vo.SysDictDetailVo;
import nom.edu.starrism.core.pool.BeanPool;
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
    @Resource
    private SysDictCategoryRepository sysDictCategoryRepository;
    @Resource
    private SysDictDetailRepository sysDictDetailRepository;

    /**
     * <p>根据分类码查询字典</p>
     *
     * @param categoryCode 字典类别码
     * @return {@link List<SysDictDetailVo>}
     * @author hedwing
     * @since 2022/10/23
     */
    @Override
    @Cacheable(key = "#categoryCode", cacheNames = "dict:categoryCode")
    public List<SysDictDetailVo> findDictByCategoryCode(String categoryCode) {
        SysDictCategory category = sysDictCategoryRepository.findByCode(categoryCode);
        if (AbstractEntity.isEmpty(category)) {
            return null;
        }
        List<SysDictDetail> details = sysDictDetailRepository.findByCategoryId(category.getId());
        return details.stream().map(SysDictDetail::toVo).collect(Collectors.toList());
    }

    /**
     * <p>根据分类码与字典码查询字典</p>
     *
     * @param categoryCode 字典类别码
     * @param dictCode     字典码
     * @return {@link SysDictDetailVo}
     * @author hedwing
     * @since 2022/10/23
     */
    @Override
    @Cacheable(key = "#categoryCode+'-'+#dictCode", cacheNames = "dict:categoryCode")
    public SysDictDetailVo findDictByCodes(String categoryCode, String dictCode) {
        SysDictCategory category = sysDictCategoryRepository.findByCode(categoryCode);
        if (AbstractEntity.isEmpty(category)) {
            return null;
        }
        return EntityConverters.convertToVo(sysDictDetailRepository.findByCodes(category.getId(), dictCode), SysDictDetailVo.class);
    }
}
