package nom.edu.starrism.core.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.data.domain.vo.AbstractVo;

/**
 * <p>系统字典详情数据展示层</p>
 *
 * @author hedwing
 * @since 2022/10/22
 **/
@Setter
@Getter
@ApiModel(value="系统字典详情数据展示层")
public class SysDictDetailVo extends AbstractVo {
    private static final long serialVersionUID = 5303934024286447061L;

    /**
     * 字典类别表主键
     */
    @ApiModelProperty(value = "字典类别表主键")
    private Long sysDictCategoryId;

    /**
     * 字典码
     */
    @ApiModelProperty(value = "字典码")
    private String dictCode;

    /**
     * 字典值
     */
    @ApiModelProperty(value = "字典值")
    private String dictValue;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 上级字典id
     */
    @ApiModelProperty(value = "上级字典id")
    private Long parentId;
}
