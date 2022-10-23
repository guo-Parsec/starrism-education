package nom.edu.starrism.data.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.common.domain.Domainizable;

import java.util.Objects;

/**
 * <p>基础实体抽象类</p>
 *
 * @author guocq
 */
@Setter
@Getter
public abstract class AbstractEntity implements Domainizable {
    private static final long serialVersionUID = 1425584601845122904L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    protected Long id;

    public AbstractEntity() {
    }

    public AbstractEntity(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }

    @Override
    public AbstractEntity clone() {
        try {
            return (AbstractEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * <p>判断实体是否为空 如果entity为null或id为null或者为id为0时即为空</p>
     *
     * @param entity 实体
     * @return boolean
     * @author guocq
     * @date 2022/10/21 16:26
     */
    public static boolean isEmpty(AbstractEntity entity) {
        return entity == null || entity.getId() == null || entity.getId().equals(0L);
    }

    /**
     * <p>判断实体是否为非空 如果entity不为null并且id不为null并且id不为0时即为非空</p>
     *
     * @param entity 实体
     * @return boolean
     * @author guocq
     * @date 2022/10/21 16:27
     */
    public static boolean isNotEmpty(AbstractEntity entity) {
        return !isEmpty(entity);
    }

}
