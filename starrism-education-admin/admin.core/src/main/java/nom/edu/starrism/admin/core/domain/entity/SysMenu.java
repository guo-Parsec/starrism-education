package nom.edu.starrism.admin.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.core.domain.vo.SysMenuVo;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;

/**
 * <p>系统菜单表</p>
 *
 * @author guocq
 * @since 2022/11/18
 **/
@Getter
@Setter
@TableName(value = "sys_menu")
public class SysMenu extends AbstractDataEntity {
    private static final long serialVersionUID = -1375383446297092774L;
    /**
     * 上级菜单id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 层级路径
     */
    @TableField(value = "hierarchical_path")
    private String hierarchicalPath;

    /**
     * 菜单标识
     */
    @TableField(value = "menu_code")
    private String menuCode;

    /**
     * 菜单标题
     */
    @TableField(value = "menu_title")
    private String menuTitle;

    /**
     * 菜单图标
     */
    @TableField(value = "menu_icon")
    private String menuIcon;

    /**
     * 显示状态(数据字典 0-显示 1-隐藏)
     */
    @TableField(value = "visible_status")
    private Integer visibleStatus;

    /**
     * 菜单级别
     */
    @TableField(value = "menu_level")
    private Integer menuLevel;

    /**
     * 排序
     */
    @TableField(value = "`sort`")
    private Integer sort;

    /**
     * 前端组件
     */
    @TableField(value = "component")
    private String component;

    /**
     * 菜单访问url
     */
    @TableField(value = "menu_url")
    private String menuUrl;

    /**
     * 菜单描述
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * <p>数据展示层转换</p>
     *
     * @return {@link SysMenuVo}
     * @author hedwing
     * @since 2022/10/22
     */
    @Override
    public SysMenuVo toVo() {
        SysMenuVo vo = new SysMenuVo();
        vo.setId(this.id);
        vo.setDataStatus(this.dataStatus);
        vo.setGmtCreate(this.gmtCreate);
        vo.setGmtModify(this.gmtModify);
        vo.setParentId(this.parentId);
        vo.setRoot(Boolean.FALSE);
        vo.setLeaf(Boolean.TRUE);
        vo.setMenuCode(this.menuCode);
        vo.setMenuTitle(this.menuTitle);
        vo.setMenuIcon(this.menuIcon);
        vo.setVisibleStatus(this.visibleStatus);
        vo.setMenuLevel(this.menuLevel);
        vo.setSort(this.sort);
        vo.setComponent(this.component);
        vo.setMenuUrl(this.menuUrl);
        vo.setRemark(this.remark);
        return vo;
    }
}