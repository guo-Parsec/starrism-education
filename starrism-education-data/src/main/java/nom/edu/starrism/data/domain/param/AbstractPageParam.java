package nom.edu.starrism.data.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.data.valid.CrudValidGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>数据分页查询参数</p>
 *
 * @author hedwing
 * @since 2022/10/21
 **/
@Setter
@Getter
public abstract class AbstractPageParam extends AbstractParam {
    private static final long serialVersionUID = -3184860793423065260L;

    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空", groups = CrudValidGroup.PageQuery.class)
    @Min(value = 0, message = "当前页不能小于0", groups = CrudValidGroup.PageQuery.class)
    @ApiModelProperty(value = "当前页")
    protected Integer currPage;

    /**
     * 页大小
     */
    @NotNull(message = "页大小不能为空", groups = CrudValidGroup.PageQuery.class)
    @Min(value = 0, message = "页大小不能小于0", groups = CrudValidGroup.PageQuery.class)
    @ApiModelProperty(value = "页大小")
    protected Integer pageSize;
}
