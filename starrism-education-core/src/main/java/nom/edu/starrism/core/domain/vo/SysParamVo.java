package nom.edu.starrism.core.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.data.domain.vo.AbstractVo;

/**
 * <p>系统参数数据展示层</p>
 *
 * @author hedwing
 * @since 2022/10/22
 **/
@Setter
@Getter
@ApiModel(value="系统参数数据展示层")
public class SysParamVo extends AbstractVo {
    private static final long serialVersionUID = 3739691611480839521L;

    /**
     * 组别码
     */
    @ApiModelProperty(value = "组别码")
    private String groupCode;

    /**
     * 组别名称
     */
    @ApiModelProperty(value = "组别名称")
    private String groupName;

    /**
     * 参数码
     */
    @ApiModelProperty(value = "参数码")
    private String paramCode;

    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值")
    private String paramValue;

    @Override
    public String toString() {
        return "SysParamVo{" +
                "groupCode='" + groupCode + '\'' +
                ", groupName='" + groupName + '\'' +
                ", paramCode='" + paramCode + '\'' +
                ", paramValue='" + paramValue + '\'' +
                ", id=" + id +
                "} " + super.toString();
    }
}
