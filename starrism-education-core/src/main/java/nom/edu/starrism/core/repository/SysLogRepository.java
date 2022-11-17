package nom.edu.starrism.core.repository;

import nom.edu.starrism.core.domain.entity.SysLog;
import nom.edu.starrism.data.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>日志持久层</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
@Mapper
@Repository(value = "sysLogRepository")
public interface SysLogRepository extends CoreMapper<SysLog> {
}
