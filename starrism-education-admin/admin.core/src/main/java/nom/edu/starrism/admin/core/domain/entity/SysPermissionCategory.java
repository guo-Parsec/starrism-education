package nom.edu.starrism.admin.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.admin.api.domain.vo.SysPermissionCategoryVo;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;

/**
 * <p>系统权限类别表</p>
 *
 * @author hedwing
 * @since  2022/11/4
 **/
@Getter
@Setter
public class SysPermissionCategory extends AbstractDataEntity {
    private static final long serialVersionUID = 620745569970975855L;
    /**
    * 权限编码
    */
    private String categoryCode;

    /**
    * 权限名称
    */
    private String categoryName;

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
     * @return {@link SysPermissionCategoryVo}
     * @author hedwing
     * @since 2022/10/22
     */
    @Override
    public SysPermissionCategoryVo toVo() {
        SysPermissionCategoryVo vo = new SysPermissionCategoryVo();
        vo.setCategoryCode(this.categoryCode);
        vo.setCategoryName(this.categoryName);
        vo.setSort(this.sort);
        vo.setRemark(this.remark);
        return vo;
    }
}