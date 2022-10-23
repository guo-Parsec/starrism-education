package nom.edu.starrism.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.core.domain.vo.SysParamVo;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;

/**
 * <p>系统参数表</p>
 *
 * @author hedwing
 * @since 2022/10/22
 **/
@Getter
@Setter
@TableName("sys_param")
public class SysParam extends AbstractDataEntity {
    private static final long serialVersionUID = -1936280787890026203L;
    /**
     * 组别码
     */
    private String groupCode;

    /**
     * 组别名称
     */
    private String groupName;

    /**
     * 参数码
     */
    private String paramCode;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * <p>数据展示层转换</p>
     *
     * @return {@link SysParamVo}
     * @author hedwing
     * @since 2022/10/22
     */
    public SysParamVo toVo() {
        SysParamVo vo = new SysParamVo();
        vo.setId(this.id);
        vo.setGroupCode(this.groupCode);
        vo.setGroupName(this.groupName);
        vo.setParamCode(this.paramCode);
        vo.setParamValue(this.paramValue);
        return vo;
    }

    @Override
    public String toString() {
        return "SysParam{" +
                "groupCode='" + groupCode + '\'' +
                ", groupName='" + groupName + '\'' +
                ", paramCode='" + paramCode + '\'' +
                ", paramValue='" + paramValue + '\'' +
                ", dataStatus=" + dataStatus +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                ", id=" + id +
                "} " + super.toString();
    }

    @Override
    public SysParam clone() {
        return (SysParam) super.clone();
    }
}