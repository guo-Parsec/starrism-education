package nom.edu.starrism.admin.core.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.core.domain.entity.SysDictCategory;
import nom.edu.starrism.data.domain.param.AbstractPageParam;
import nom.edu.starrism.data.valid.CrudValidGroup;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>系统字典类别新增参数</p>
 *
 * @author hedwing
 * @since 2022/11/12
 **/
@Getter
@Setter
@ApiModel(value = "系统字典类别新增参数")
public class SysDictCategoryParam extends AbstractPageParam {
    private static final long serialVersionUID = 153638764143637498L;

    @ApiModelProperty(value = "主键id")
    @NotNull(message = "id不能为空", groups = {CrudValidGroup.Update.class, CrudValidGroup.Delete.class})
    private Long id;

    @ApiModelProperty(value = "类别码")
    @NotEmpty(message = "类别码不能为空", groups = {CrudValidGroup.Create.class, CrudValidGroup.Update.class})
    private String categoryCode;

    @ApiModelProperty(value = "类别名称")
    @NotEmpty(message = "类别名称不能为空", groups = {CrudValidGroup.Create.class, CrudValidGroup.Update.class})
    private String categoryName;

    @ApiModelProperty(value = "排序")
    @Min(value = 0, message = "排序值不能小于0", groups = {CrudValidGroup.Create.class, CrudValidGroup.Update.class})
    @Max(value = 99999, message = "排序值不能大于于99999", groups = {CrudValidGroup.Create.class, CrudValidGroup.Update.class})
    private Integer sort;


    public SysDictCategory toEntity() {
        SysDictCategory sysDictCategory = new SysDictCategory();
        sysDictCategory.setId(this.id);
        sysDictCategory.setCategoryCode(this.categoryCode);
        sysDictCategory.setCategoryName(this.categoryName);
        sysDictCategory.setSort(this.sort);
        return sysDictCategory;
    }

    @Override
    public String toString() {
        return "SysDictCategoryParam{" +
                "id=" + id +
                ", categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", sort=" + sort +
                "} ";
    }
}