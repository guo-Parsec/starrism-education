package nom.edu.starrism.auth.core.service.impl;

import nom.edu.starrism.auth.core.service.SecurityService;
import nom.edu.starrism.core.context.SecurityContext;
import nom.edu.starrism.core.domain.vo.SeUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
@Service(value = "securityService")
public class SecurityServiceImpl implements SecurityService {
    /**
     * <p>查询全部在线用户</p>
     *
     * @return java.util.List<nom.edu.starrism.core.domain.vo.SeUser>
     * @author guocq
     * @date 2022/11/17 16:29
     */
    @Override
    public List<SeUser> findAllOnlineUser() {
        return SecurityContext.findOnlineUser();
    }
}
