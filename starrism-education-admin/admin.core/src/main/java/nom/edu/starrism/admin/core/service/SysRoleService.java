package nom.edu.starrism.admin.core.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import nom.edu.starrism.admin.api.domain.param.SysRolePageParam;
import nom.edu.starrism.admin.api.domain.vo.SysRoleVo;

import java.util.Set;

/**
 * <p>系统角色服务接口</p>
 *
 * @author hedwing
 * @since 2022/11/7
 **/
public interface SysRoleService {
    /**
     * <p>获取角色码：根据用户id获取</p>
     *
     * @param userId userId
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/11/7
     */
    Set<String> findRoleCodesOfUser(Long userId);

    /**
     * <p>角色分页查询</p>
     *
     * @param param 分页查询参数
     * @return {@link Page<SysRoleVo>}
     * @author guocq
     * @date 2022/11/11 14:44
     */
    PageInfo<SysRoleVo> sysRolePageQuery(SysRolePageParam param);
}
