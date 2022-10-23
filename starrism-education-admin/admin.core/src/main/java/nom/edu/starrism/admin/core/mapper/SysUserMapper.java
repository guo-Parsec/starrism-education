package nom.edu.starrism.admin.core.mapper;

import nom.edu.starrism.admin.core.domain.entity.SysUser;
import nom.edu.starrism.data.mapper.CoreMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>用户操作mapper</p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
@Mapper
@Repository(value = "sysUserMapper")
public interface SysUserMapper extends CoreMapper<SysUser> {
    /**
     * <p>根据账户{@link SysUser#getAccount()}查询用户信息</p>
     *
     * @param account {@link SysUser#getAccount()}
     * @return {@link SysUser}
     * @author hedwing
     * @since 2022/10/23
     */
    SysUser findUserByAccount(@Param("account") String account);

    /**
     * <p>根据id{@link SysUser#getId()}查询用户信息</p>
     *
     * @param id {@link SysUser#getId()}
     * @return {@link SysUser}
     * @author hedwing
     * @since 2022/10/23
     */
    SysUser findUserById(@Param("id") Long id);
}