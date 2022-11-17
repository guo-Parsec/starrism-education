package nom.edu.starrism.core.access;

import nom.edu.starrism.core.domain.vo.SysDictCategoryVo;
import nom.edu.starrism.core.domain.vo.SysDictDetailVo;

import java.util.List;

/**
 * <p>数据字典访问服务层接口</p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
public interface DictAccess {
    /**
     * <p>查询字典分类</p>
     *
     * @param id 字典分类主键
     * @return {@link SysDictCategoryVo}
     * @author guocq
     * @date 2022/11/15 17:05
     */
    SysDictCategoryVo findCategory(Long id);

    /**
     * <p>查询字典分类</p>
     *
     * @param categoryCode 字典分类码
     * @return {@link SysDictCategoryVo}
     * @author guocq
     * @date 2022/11/15 17:05
     */
    SysDictCategoryVo findCategoryByCategoryCode(String categoryCode);

    /**
     * <p>根据分类码查询字典</p>
     *
     * @param categoryCode 字典类别码
     * @return {@link List<SysDictDetailVo>}
     * @author hedwing
     * @since 2022/10/23
     */
    List<SysDictDetailVo> findDictByCategoryCode(String categoryCode);

    /**
     * <p>根据分类码与字典值查询字典</p>
     *
     * @param categoryCode 字典类别码
     * @param dictValue    字典值
     * @return {@link SysDictDetailVo}
     * @author hedwing
     * @since 2022/10/23
     */
    SysDictDetailVo findDictByCategoryCodeAndDictValue(String categoryCode, String dictValue);

    /**
     * <p>根据分类码与字典码查询字典</p>
     *
     * @param categoryCode 字典类别码
     * @param dictCode     字典码
     * @return {@link SysDictDetailVo}
     * @author hedwing
     * @since 2022/11/12
     */
    SysDictDetailVo findDictByCategoryCodeAndDictCode(String categoryCode, String dictCode);

    /**
     * <p>根据主键id查询详情数据</p>
     *
     * @param id 主键
     * @return {@link SysDictDetailVo}
     * @author guocq
     * @date 2022/11/15 16:13
     */
    SysDictDetailVo findDetail(Long id);
}
