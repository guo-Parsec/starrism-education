package nom.edu.starrism.core.repository;

import nom.edu.starrism.core.domain.entity.SysParam;
import nom.edu.starrism.data.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>针对表【sys_param(系统参数表)】的数据库查询操作Repository</p>
 *
 * @author hedwing
 * @since 2022/10/22
 **/
@Mapper
@Repository(value = "sysParamRepository")
public interface SysParamRepository extends CoreMapper<SysParam> {
    /**
     * <p>根据主键id查询参数</p>
     *
     * @param id 主键id
     * @return {@link SysParam}
     * @author guocq
     * @date 2022/11/15 17:45
     */
    SysParam find(Long id);

    /**
     * <p>根据参数码获取参数信息</p>
     *
     * @param paramCode {@link String}
     * @return {@link SysParam}
     * @author hedwing
     * @since 2022/10/22
     */
    SysParam findByParamCode(@Param("paramCode") String paramCode);

    /**
     * <p>根据组别和参数码(可选)获取参数信息</p>
     *
     * @param groupCode {@link String}
     * @param paramCode {@link String}
     * @return {@link List<SysParam>}
     * @author hedwing
     * @since 2022/10/22
     */
    List<SysParam> findByGroupCode(@Param("groupCode") String groupCode, @Param("paramCode") String paramCode);
}
