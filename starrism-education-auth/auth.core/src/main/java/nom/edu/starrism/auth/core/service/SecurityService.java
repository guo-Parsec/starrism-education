package nom.edu.starrism.auth.core.service;

import nom.edu.starrism.core.domain.vo.SeUser;

import java.util.List;

/**
 * <p>安全认证Service</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
public interface SecurityService {
    /**
     * <p>查询全部在线用户</p>
     *
     * @return java.util.List<nom.edu.starrism.core.domain.vo.SeUser>
     * @author guocq
     * @date 2022/11/17 16:29
     */
    List<SeUser> findAllOnlineUser();
}
