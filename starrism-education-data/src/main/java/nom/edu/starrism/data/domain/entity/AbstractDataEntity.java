package nom.edu.starrism.data.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.data.domain.vo.AbstractVo;
import nom.edu.starrism.data.pool.DataPool;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>数据实体抽象类</p>
 *
 * @author guocq
 */
@Setter
@Getter
public abstract class AbstractDataEntity extends AbstractEntity {
    private static final long serialVersionUID = -2490534118894809930L;

    /**
     * 数据状态
     */
    protected Integer dataStatus;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime gmtModify;

    public AbstractDataEntity() {
        super();
    }

    public AbstractDataEntity(Long id) {
        super(id);
    }

    /**
     * <p>数据展示层转换</p>
     * @return {@link AbstractVo}
     * @author hedwing
     * @since 2022/10/22
     */
    public abstract AbstractVo toVo();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AbstractDataEntity that = (AbstractDataEntity) o;
        return Objects.equals(dataStatus, that.dataStatus) && Objects.equals(gmtModify, that.gmtModify) && Objects.equals(gmtCreate, that.gmtCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dataStatus, gmtModify, gmtCreate);
    }

    @Override
    public String toString() {
        return "AbstractDataEntity{" +
                "dataStatus=" + dataStatus +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                ", id=" + id +
                '}';
    }

    @Override
    public AbstractDataEntity clone() {
        return (AbstractDataEntity) super.clone();
    }
}
