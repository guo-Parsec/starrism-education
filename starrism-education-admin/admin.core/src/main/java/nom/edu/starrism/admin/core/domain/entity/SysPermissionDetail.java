package nom.edu.starrism.admin.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.admin.api.domain.vo.SysPermissionCategoryVo;
import nom.edu.starrism.admin.api.domain.vo.SysPermissionDetailVo;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;

/**
 * <p>系统权限详情表</p>
 *
 * @author hedwing
 * @since  2022/11/4
 **/
@Getter
@Setter
public class SysPermissionDetail extends AbstractDataEntity {
    private static final long serialVersionUID = -1285781808092642604L;
    /**
    * 权限分类id
    */
    private Long permissionCategoryId;

    /**
     * 权限分类
     */
    @TableField(exist = false)
    private SysPermissionCategory sysPermissionCategory;

    /**
    * 权限编码
    */
    private String permissionCode;

    /**
    * 权限名称
    */
    private String permissionName;

    /**
    * 请求操作url
    */
    private String requestActionUrl;

    /**
    * 排序
    */
    @TableField(value = "`sort`")
    private Integer sort;

    /**
    * 备注信息
    */
    private String remark;


    /**
     * <p>数据展示层转换</p>
     *
     * @return {@link SysPermissionDetailVo}
     * @author hedwing
     * @since 2022/10/22
     */
    @Override
    public SysPermissionDetailVo toVo() {
        SysPermissionDetailVo vo = new SysPermissionDetailVo();
        if (AbstractDataEntity.isNotEmpty(this.sysPermissionCategory)) {
            vo.setSysPermissionCategoryVo(this.sysPermissionCategory.toVo());
        } else {
            vo.setSysPermissionCategoryVo(new SysPermissionCategoryVo(this.permissionCategoryId));
        }
        vo.setPermissionCode(this.permissionCode);
        vo.setPermissionName(this.permissionName);
        vo.setRequestActionUrl(this.requestActionUrl);
        vo.setSort(this.sort);
        vo.setRemark(this.remark);
        return vo;
    }
}