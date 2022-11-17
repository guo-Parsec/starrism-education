package nom.edu.starrism.data.type;

import lombok.Getter;
import nom.edu.starrism.common.enums.SeEnum;

/**
 * <p>操作类型</p>
 *
 * @author guocq
 * @since 2022/11/16
 **/
public enum OpType {
    /**
     * 公共操作
     */
    COMMON,
    /**
     * 更新操作
     */
    QUERY,
    /**
     * 更新操作
     */
    CREATE,
    /**
     * 更新操作
     */
    UPDATE,
    /**
     * 删除操作
     */
    DELETE,
    ;


}
