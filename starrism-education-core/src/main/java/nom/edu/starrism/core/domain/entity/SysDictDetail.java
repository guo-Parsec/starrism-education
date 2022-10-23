package nom.edu.starrism.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.core.domain.vo.SysDictDetailVo;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;

/**
 * <p>系统字典详情表</p>
 *
 * @author hedwing
 * @since  2022/10/22
 **/
@Getter
@Setter
@TableName(value = "sys_dict_detail")
public class SysDictDetail extends AbstractDataEntity {
    private static final long serialVersionUID = -7089302109692675066L;
    /**
     * 字典类别表主键
     */
    private Long sysDictCategoryId;

    /**
     * 字典码
     */
    private String dictCode;

    /**
     * 字典值
     */
    private String dictValue;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 排序
     */
    @TableField(value = "`sort`")
    private Integer sort;

    /**
     * 上级字典id
     */
    private Long parentId;

    /**
     * <p>数据展示层转换</p>
     *
     * @return {@link SysDictDetailVo}
     * @author hedwing
     * @since 2022/10/22
     */
    public SysDictDetailVo toVo() {
        SysDictDetailVo vo = new SysDictDetailVo();
        vo.setId(this.id);
        vo.setSysDictCategoryId(this.sysDictCategoryId);
        vo.setDictCode(this.dictCode);
        vo.setDictValue(this.dictValue);
        vo.setDictName(this.dictName);
        vo.setSort(this.sort);
        vo.setParentId(this.parentId);
        return vo;
    }

    @Override
    public String toString() {
        return "SysDictDetail{" +
                "sysDictCategoryId=" + sysDictCategoryId +
                ", dictCode='" + dictCode + '\'' +
                ", dictValue='" + dictValue + '\'' +
                ", dictName='" + dictName + '\'' +
                ", sort=" + sort +
                ", parentId=" + parentId +
                ", dataStatus=" + dataStatus +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                ", id=" + id +
                "} " + super.toString();
    }

    @Override
    public SysDictDetail clone() {
        return (SysDictDetail) super.clone();
    }
}