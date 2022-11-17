package nom.edu.starrism.core.util;

import com.github.pagehelper.PageInfo;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;
import nom.edu.starrism.data.domain.vo.AbstractVo;

import java.util.List;

/**
 * <p>分页工具类</p>
 *
 * @author guocq
 * @since 2022/11/11
 **/
public class PageUtil {
    /**
     * 转换为vo分页
     *
     * @param data      vo数据 list
     * @param ePageInfo 原始分页
     */
    public static <V extends AbstractVo, E extends AbstractDataEntity> PageInfo<V> toVoPage(List<V> data, PageInfo<E> ePageInfo) {
        PageInfo<V> pageInfo = PageInfo.emptyPageInfo();
        pageInfo.setPages(ePageInfo.getPages());
        pageInfo.setList(data);
        pageInfo.setPageSize(ePageInfo.getPageSize());
        pageInfo.setPageNum(ePageInfo.getPageNum());
        pageInfo.setTotal(ePageInfo.getTotal());
        pageInfo.setSize(ePageInfo.getSize());
        pageInfo.setStartRow(ePageInfo.getStartRow());
        pageInfo.setEndRow(ePageInfo.getEndRow());
        pageInfo.setPages(ePageInfo.getPages());
        pageInfo.setPrePage(ePageInfo.getPrePage());
        pageInfo.setNextPage(ePageInfo.getNextPage());
        pageInfo.setIsFirstPage(ePageInfo.isIsFirstPage());
        pageInfo.setIsLastPage(ePageInfo.isIsLastPage());
        pageInfo.setHasNextPage(ePageInfo.isHasNextPage());
        pageInfo.setHasPreviousPage(ePageInfo.isHasPreviousPage());
        pageInfo.setNavigatePages(ePageInfo.getNavigatePages());
        pageInfo.setNavigatepageNums(ePageInfo.getNavigatepageNums());
        pageInfo.setNavigateFirstPage(ePageInfo.getNavigateFirstPage());
        pageInfo.setNavigateLastPage(ePageInfo.getNavigateLastPage());
        return pageInfo;
    }
}
