package nom.edu.starrism.core.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.data.domain.vo.AbstractVo;

/**
 * <p>系统字典分类数据展示层</p>
 *
 * @author hedwing
 * @since 2022/10/22
 **/
@Setter
@Getter
@ApiModel(value="系统字典分类数据展示层")
public class SysDictCategoryVo extends AbstractVo {
    private static final long serialVersionUID = -7201667741565247332L;

    /**
     * 类别码
     */
    @ApiModelProperty(value = "类别码")
    private String categoryCode;

    /**
     * 类别名称
     */
    @ApiModelProperty(value = "类别名称")
    private String categoryName;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
}
