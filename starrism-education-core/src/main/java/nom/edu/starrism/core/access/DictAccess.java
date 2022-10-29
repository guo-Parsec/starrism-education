package nom.edu.starrism.core.access;

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
     * <p>根据分类码查询字典</p>
     *
     * @param categoryCode 字典类别码
     * @return {@link List<SysDictDetailVo>}
     * @author hedwing
     * @since 2022/10/23
     */
    List<SysDictDetailVo> findDictByCategoryCode(String categoryCode);

    /**
     * <p>根据分类码与字典码查询字典</p>
     *
     * @param categoryCode 字典类别码
     * @param dictValue     字典值
     * @return {@link SysDictDetailVo}
     * @author hedwing
     * @since 2022/10/23
     */
    SysDictDetailVo findDictByCodes(String categoryCode, String dictValue);
}
