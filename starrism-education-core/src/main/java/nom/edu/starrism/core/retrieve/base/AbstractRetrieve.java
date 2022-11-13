package nom.edu.starrism.core.retrieve.base;

import nom.edu.starrism.data.domain.entity.AbstractDataEntity;
import nom.edu.starrism.data.domain.param.AbstractParam;

/**
 * <p>检索抽象类</p>
 *
 * @author hedwing
 * @since 2022/11/13
 **/
public abstract class AbstractRetrieve<E extends AbstractDataEntity, P extends AbstractParam> implements Retrievable<E, P> {

}
