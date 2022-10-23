package nom.edu.starrism.data.domain.vo;

import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.data.domain.param.AbstractPageParam;

import java.util.List;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/21
 **/
@Setter
@Getter
public class PageVoData<E> implements Pageable<E> {
    private static final long serialVersionUID = 6112787676546516862L;

    public static final Integer DEFAULT_PAGE_SIZE = 30;

    public static final Integer DEFAULT_CURR_PAGE = 1;

    private Integer pageSize;

    private Integer currPage;

    private Long total;

    private List<E> records;

    private PageVoData(List<E> records, Long total) {
        this.currPage = DEFAULT_CURR_PAGE;
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.records = records;
        this.total = total;
    }

    public PageVoData(Integer pageSize, Integer currPage, Long total, List<E> records) {
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.total = total;
        this.records = records;
    }

    /**
     * <p>根据records和总条数组装分页数据</p>
     *
     * @param records 当前页所有记录
     * @param total   总条数
     * @return org.starrism.mall.data.domain.vo.Pageable<E>
     * @author hedwing
     * @since 2022/8/21
     */
    public static <E> PageVoData<E> of(List<E> records, Long total, AbstractPageParam param) {
        return new PageVoData<>(param.getPageSize(), param.getCurrPage(), total, records);
    }

    /**
     * <p>根据records和总条数组装分页数据</p>
     *
     * @param records 当前页所有记录
     * @param total   总条数
     * @return org.starrism.mall.data.domain.vo.Pageable<E>
     * @author hedwing
     * @since 2022/8/21
     */
    public static <E> PageVoData<E> of(List<E> records, Long total) {
        return new PageVoData<>(records, total);
    }
}
