package nom.edu.starrism.admin.api.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.core.context.DictContext;
import nom.edu.starrism.core.pool.DictPool;
import nom.edu.starrism.data.domain.vo.AbstractDataVo;

/**
 * <p>系统权限类别展示层</p>
 *
 * @author hedwing
 * @since 2022/11/4
 **/
@Setter
@Getter
@ApiModel(value = "系统权限类别展示层")
public class SysPermissionCategoryVo extends AbstractDataVo {

    private static final long serialVersionUID = 9022903736888793453L;

    @ApiModelProperty(value = "权限编码")
    private String categoryCode;

    @ApiModelProperty(value = "权限名称")
    private String categoryName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    public SysPermissionCategoryVo() {
    }

    public SysPermissionCategoryVo(Long id) {
        this.id = id;
    }

    /**
     * <p>数据状态字典显示名称getter方法</p>
     *
     * @return java.lang.String
     * @author hedwing
     * @since 2022/10/23
     */
    public String getDataStatusName() {
        return DictContext.convert(DictPool.DICT_CATEGORY_DATA_STATUS, this.dataStatus);
    }
}
