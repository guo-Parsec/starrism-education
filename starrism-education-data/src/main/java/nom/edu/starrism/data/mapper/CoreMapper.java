package nom.edu.starrism.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nom.edu.starrism.data.domain.entity.AbstractEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>核心Mapper</p>
 *
 * @author hedwing
 * @since 2022/10/21
 **/
public interface CoreMapper <T extends AbstractEntity> extends BaseMapper<T> {
    /**
     * <p>批量插入</p>
     *
     * @param list 实体list
     * @return int
     * @author hedwing
     * @since 2022/8/25
     */
    int insertAll(@Param("list") List<T> list);
}

