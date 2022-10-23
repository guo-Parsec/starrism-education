package nom.edu.starrism.data.domain.vo;

import nom.edu.starrism.common.domain.Domainizable;
import nom.edu.starrism.data.domain.entity.AbstractEntity;

/**
 * <p>基础数据展示层</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
public abstract class AbstractVo extends AbstractEntity implements Domainizable {
    private static final long serialVersionUID = -1255571960577933055L;

    @Override
    public AbstractVo clone() {
        return (AbstractVo) super.clone();
    }

}