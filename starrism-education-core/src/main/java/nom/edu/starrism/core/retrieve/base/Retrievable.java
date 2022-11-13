package nom.edu.starrism.core.retrieve.base;

import nom.edu.starrism.data.domain.entity.AbstractDataEntity;
import nom.edu.starrism.data.domain.param.AbstractParam;

import java.util.List;

/**
 * <p>可检索的</p>
 *
 * @author hedwing
 * @since 2022/11/13
 **/
public interface Retrievable<E extends AbstractDataEntity, P extends AbstractParam> {
    /**
     * <p>根据主键检索数据</p>
     *
     * @param id 主键
     * @return E
     * @author hedwing
     * @since 2022/11/13
     */
    E find(Long id);

    /**
     * <p>根据参数动态检索数据</p>
     *
     * @param param 参数
     * @return {@link List<E>}
     * @author hedwing
     * @since 2022/11/13
     */
    List<E> findByParam(P param);
}
