package nom.edu.starrism.admin.api.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.core.context.SeDictContext;
import nom.edu.starrism.core.pool.DictPool;
import nom.edu.starrism.data.domain.vo.AbstractDataVo;

/**
 * <p>系统角色数据展示层</p>
 *
 * @author hedwing
 * @since 2022/11/4
 **/
@Setter
@Getter
@ApiModel(value = "系统角色数据展示层")
public class SysRoleVo extends AbstractDataVo {
    private static final long serialVersionUID = 6762928445617970223L;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    /**
     * <p>数据状态字典显示名称getter方法</p>
     *
     * @return java.lang.String
     * @author hedwing
     * @since 2022/10/23
     */
    @Override
    public String getDataStatusName() {
        return SeDictContext.convert(DictPool.DICT_CATEGORY_DATA_STATUS, this.dataStatus);
    }
}
