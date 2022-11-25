package nom.edu.starrism.core.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.core.context.DictContext;
import nom.edu.starrism.core.pool.DictPool;
import nom.edu.starrism.data.domain.vo.AbstractTreelikeDataVo;

import java.util.Collection;

/**
 * <p>菜单数据展示层</p>
 *
 * @author guocq
 * @since 2022/11/18
 **/
@Setter
@Getter
@ApiModel(value = "菜单数据展示层")
public class SysMenuVo extends AbstractTreelikeDataVo<SysMenuVo> {
    private static final long serialVersionUID = 4110136909265143324L;

    @ApiModelProperty(value = "子节点元素")
    private Collection<SysMenuVo> children;

    @ApiModelProperty(value = "菜单标识")
    private String menuCode;

    @ApiModelProperty(value = "菜单标题")
    private String menuTitle;

    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;

    @ApiModelProperty(value = "显示状态")
    private Integer visibleStatus;

    @ApiModelProperty(value = "显示状态名称")
    private String visibleStatusName;

    @ApiModelProperty(value = "菜单级别")
    private Integer menuLevel;

    @ApiModelProperty(value = "菜单排序")
    private Integer sort;

    @ApiModelProperty(value = "菜单前端组件")
    private String component;

    @ApiModelProperty(value = "菜单访问url")
    private String menuUrl;

    @ApiModelProperty(value = "菜单描述")
    private String remark;

    public SysMenuVo() {
    }

    public SysMenuVo(Long id, Long parentId, String menuCode) {
        this.id = id;
        this.parentId = parentId;
        this.menuCode = menuCode;
    }

    /**
     * <p>数据状态字典显示名称getter方法</p>
     *
     * @return java.lang.String
     * @author hedwing
     * @since 2022/10/23
     */
    @Override
    public String getDataStatusName() {
        return DictContext.convert(DictPool.DICT_CATEGORY_DATA_STATUS, this.dataStatus);
    }

    public String getVisibleStatusName() {
        return DictContext.convert(DictPool.DICT_CATEGORY_VISIBLE_STATUS, this.visibleStatus);
    }

    @Override
    public String toString() {
        return "SysMenuVo{" +
                "children=" + children +
                ", menuCode='" + menuCode + '\'' +
                ", parentId=" + parentId +
                ", id=" + id +
                '}';
    }
}
