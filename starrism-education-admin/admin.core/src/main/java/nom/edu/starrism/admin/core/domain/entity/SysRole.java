package nom.edu.starrism.admin.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.admin.api.domain.vo.SysRoleVo;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;

/**
 * <p>系统角色表</p>
 *
 * @author hedwing
 * @since  2022/11/4
 **/
@Getter
@Setter
public class SysRole extends AbstractDataEntity {
    private static final long serialVersionUID = 5022541757471674565L;
    /**
    * 角色编码
    */
    private String roleCode;

    /**
    * 角色名称
    */
    private String roleName;

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
     * @return {@link SysRoleVo}
     * @author hedwing
     * @since 2022/10/22
     */
    @Override
    public SysRoleVo toVo() {
        SysRoleVo vo = new SysRoleVo();
        vo.setId(this.id);
        vo.setRoleCode(this.roleCode);
        vo.setRoleName(this.roleName);
        vo.setSort(this.sort);
        vo.setRemark(this.remark);
        vo.setDataStatus(this.dataStatus);
        vo.setGmtCreate(this.gmtCreate);
        vo.setGmtModify(this.gmtModify);
        return vo;
    }
}