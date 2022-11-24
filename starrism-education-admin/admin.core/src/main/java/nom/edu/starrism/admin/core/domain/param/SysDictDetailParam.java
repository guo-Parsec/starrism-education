package nom.edu.starrism.admin.core.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.core.domain.entity.SysDictCategory;
import nom.edu.starrism.core.domain.entity.SysDictDetail;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;
import nom.edu.starrism.data.domain.param.AbstractPageParam;
import nom.edu.starrism.data.valid.CrudValidGroup;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>系统字典详情新增参数</p>
 *
 * @author hedwing
 * @since 2022/11/12
 **/
@Getter
@Setter
@ApiModel(value = "系统字典详情新增参数")
public class SysDictDetailParam extends AbstractPageParam {

    private static final long serialVersionUID = 4707764530402055701L;

    @ApiModelProperty(value = "主键id", required = false)
    @NotNull(message = "id不能为空", groups = {CrudValidGroup.Update.class, CrudValidGroup.Delete.class})
    private Long id;

    @ApiModelProperty(value = "字典类别主键", required = false)
    @NotNull(message = "字典类别主键不能为空", groups = CrudValidGroup.PageQuery.class)
    private Long dictCategoryId;

    @NotEmpty(message = "字典类别码不能为空", groups = {CrudValidGroup.Create.class, CrudValidGroup.Update.class, CrudValidGroup.Delete.class})
    @ApiModelProperty(value = "字典类别码")
    private String categoryCode;

    @ApiModelProperty(value = "字典类别名称")
    private String categoryName;

    @NotEmpty(message = "字典码不能为空", groups = {CrudValidGroup.Create.class, CrudValidGroup.Update.class, CrudValidGroup.Delete.class})
    @ApiModelProperty(value = "字典码")
    private String dictCode;

    @NotEmpty(message = "字典值不能为空", groups = {CrudValidGroup.Create.class, CrudValidGroup.Update.class})
    @ApiModelProperty(value = "字典值")
    private String dictValue;

    @NotEmpty(message = "字典名称不能为空", groups = {CrudValidGroup.Create.class, CrudValidGroup.Update.class})
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @Min(value = 0, message = "排序值不能小于0", groups = {CrudValidGroup.Create.class, CrudValidGroup.Update.class})
    @Max(value = 99999, message = "排序值不能大于于99999", groups = {CrudValidGroup.Create.class, CrudValidGroup.Update.class})
    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "上级字典id")
    private Long parentId;


    @Override
    public AbstractDataEntity toEntity() {
        return toEntity(null);
    }

    public SysDictDetail toEntity(SysDictCategory category) {
        SysDictDetail detail = new SysDictDetail();
        detail.setId(this.id);
        if (category != null) {
            detail.setSysDictCategoryId(category.getId());
        } else {
            detail.setSysDictCategoryId(this.dictCategoryId);
        }
        detail.setDictCode(this.dictCode);
        detail.setDictValue(this.dictValue);
        detail.setDictName(this.dictName);
        detail.setSort(this.sort);
        detail.setParentId(this.parentId);
        return detail;
    }

    @Override
    public String toString() {
        return "SysDictDetailParam{" +
                "id=" + id +
                ", categoryCode='" + categoryCode + '\'' +
                ", dictCode='" + dictCode + '\'' +
                ", dictValue='" + dictValue + '\'' +
                ", dictName='" + dictName + '\'' +
                ", sort=" + sort +
                ", parentId=" + parentId +
                "} ";
    }
}