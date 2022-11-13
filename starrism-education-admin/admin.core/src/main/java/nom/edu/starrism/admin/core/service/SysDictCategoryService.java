package nom.edu.starrism.admin.core.service;

import nom.edu.starrism.admin.core.domain.param.SysDictCategoryParam;

/**
 * <p>字典类别Service</p>
 *
 * @author hedwing
 * @since 2022/11/11
 **/
public interface SysDictCategoryService {
    /**
     * <p>字典类别数据新增</p>
     *
     * @param param param
     * @author hedwing
     * @since 2022/11/11
     */
    void addSysDictCategory(SysDictCategoryParam param);
}
