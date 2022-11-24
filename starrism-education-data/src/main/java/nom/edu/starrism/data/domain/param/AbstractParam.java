package nom.edu.starrism.data.domain.param;

import nom.edu.starrism.common.domain.Domainizable;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;

/**
 * <p>基础参数实体类</p>
 *
 * @author hedwing
 * @since 2022/10/21
 **/
public abstract class AbstractParam implements Domainizable {
    private static final long serialVersionUID = -884287800185353435L;

    public AbstractDataEntity toEntity() {
        return null;
    }
}
