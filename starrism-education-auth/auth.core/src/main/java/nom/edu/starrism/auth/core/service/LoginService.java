package nom.edu.starrism.auth.core.service;

import nom.edu.starrism.auth.api.domain.param.UserLoginParam;
import nom.edu.starrism.auth.api.domain.vo.AuthenticatedUser;

/**
 * <p>登录服务接口</p>
 *
 * @author hedwing
 * @since 2022/11/6
 **/
public interface LoginService {
    /**
     * <p>登录方法</p>
     *
     * @param param param
     * @return {@link AuthenticatedUser}
     * @author hedwing
     * @since 2022/11/6
     */
    AuthenticatedUser login(UserLoginParam param);
}
