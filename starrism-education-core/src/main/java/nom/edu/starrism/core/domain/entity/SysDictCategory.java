package nom.edu.starrism.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.core.domain.vo.SysDictCategoryVo;
import nom.edu.starrism.core.domain.vo.SysDictDetailVo;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;

/**
 * <p>系统字典类别表</p>
 *
 * @author hedwing
 * @since 2022/10/22
 **/
@Getter
@Setter
@TableName("sys_dict_category")
public class SysDictCategory extends AbstractDataEntity {
    private static final long serialVersionUID = 7946181784083973795L;
    /**
     * 类别码
     */
    private String categoryCode;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 排序
     */
    @TableField(value = "`sort`")
    private Integer sort;

    /**
     * <p>数据展示层转换</p>
     *
     * @return {@link SysDictDetailVo}
     * @author hedwing
     * @since 2022/10/22
     */
    public SysDictCategoryVo toVo() {
        SysDictCategoryVo vo = new SysDictCategoryVo();
        vo.setId(this.id);
        vo.setCategoryCode(this.categoryCode);
        vo.setCategoryName(this.categoryName);
        vo.setSort(this.sort);
        return vo;
    }

    @Override
    public String toString() {
        return "SysDictCategory{" +
                "categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", sort=" + sort +
                ", dataStatus=" + dataStatus +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                ", id=" + id +
                "} " + super.toString();
    }

    @Override
    public SysDictCategory clone() {
        return (SysDictCategory) super.clone();
    }
}