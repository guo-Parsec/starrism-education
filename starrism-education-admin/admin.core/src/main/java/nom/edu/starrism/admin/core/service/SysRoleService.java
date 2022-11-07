package nom.edu.starrism.admin.core.service;

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
     * @param userId userId
     * @return java.util.Set<java.lang.String>
     * @author hedwing
     * @since 2022/11/7
     */
    Set<String> findRoleCodesOfUser(Long userId);
}
