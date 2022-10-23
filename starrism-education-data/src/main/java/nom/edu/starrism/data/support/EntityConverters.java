package nom.edu.starrism.data.support;

import nom.edu.starrism.data.domain.entity.AbstractDataEntity;
import nom.edu.starrism.data.domain.vo.AbstractVo;

import java.util.Optional;

/**
 * <p>实体类转换器</p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
public class EntityConverters {
    public static <S extends AbstractDataEntity, T extends AbstractVo> T convertToVo(S dataEntity, Class<T> tClass) {
        return tClass.cast(Optional.ofNullable(dataEntity).map(AbstractDataEntity::toVo).orElse(null));
    }
}
