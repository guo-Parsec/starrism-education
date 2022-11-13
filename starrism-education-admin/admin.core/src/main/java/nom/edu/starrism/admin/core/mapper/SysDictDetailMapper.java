package nom.edu.starrism.admin.core.mapper;

import nom.edu.starrism.core.domain.entity.SysDictDetail;
import nom.edu.starrism.data.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>字典详情mapper</p>
 *
 * @author hedwing
 * @since 2022/11/11
 **/
@Mapper
@Repository(value = "sysDictDetailMapper")
public interface SysDictDetailMapper extends CoreMapper<SysDictDetail> {
}
