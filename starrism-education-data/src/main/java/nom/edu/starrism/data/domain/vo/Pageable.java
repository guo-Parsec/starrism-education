package nom.edu.starrism.data.domain.vo;

import nom.edu.starrism.common.domain.Domainizable;

import java.util.List;

/**
 * <p>可分页</p>
 *
 * @author hedwing
 * @since 2022/8/21
 **/
public interface Pageable<E> extends Domainizable {
    /**
     * <p>获取总条数</p>
     *
     * @return java.lang.Long
     * @author hedwing
     * @since 2022/8/21
     */
    Long getTotal();

    /**
     * <p>获取页大小</p>
     *
     * @return java.lang.Integer
     * @author hedwing
     * @since 2022/8/21
     */
    Integer getPageSize();

    /**
     * <p>获取当前页</p>
     *
     * @return java.lang.Integer
     * @author hedwing
     * @since 2022/8/21
     */
    Integer getCurrPage();

    /**
     * <p>获取当前页所有记录</p>
     *
     * @return java.util.List<E>
     * @author hedwing
     * @since 2022/8/21
     */
    List<E> getRecords();

}
