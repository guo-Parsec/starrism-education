package nom.edu.starrism.core.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.data.domain.vo.AbstractVo;

/**
 * <p>系统字典数据展示层</p>
 *
 * @author hedwing
 * @since 2022/10/22
 **/
@Setter
@Getter
@ApiModel(value = "系统字典数据展示层")
public class SysDictVo extends AbstractVo {
    private static final long serialVersionUID = -1833433249513551794L;

    @ApiModelProperty(value = "系统字典分类数据")
    private SysDictCategoryVo sysDictCategoryVo;

    @ApiModelProperty(value = "系统字典详情数据")
    private SysDictDetailVo sysDictDetailVo;
}
