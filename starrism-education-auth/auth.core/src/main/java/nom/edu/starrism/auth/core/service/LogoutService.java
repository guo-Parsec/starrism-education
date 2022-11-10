package nom.edu.starrism.auth.core.service;

import org.springframework.stereotype.Service;

/**
 * <p>登出Service</p>
 *
 * @author guocq
 * @since 2022/11/10
 **/

public interface LogoutService {
    /**
     * <p>根据用户id登出系统</p>
     *
     * @param id 用户id
     * @return boolean
     * @author guocq
     * @date 2022/11/10 16:45
     */
    boolean logout(Long id);

    /**
     * <p>当前用户登出系统</p>
     *
     * @return boolean
     * @author guocq
     * @date 2022/11/10 16:45
     */
    boolean logout();
}
