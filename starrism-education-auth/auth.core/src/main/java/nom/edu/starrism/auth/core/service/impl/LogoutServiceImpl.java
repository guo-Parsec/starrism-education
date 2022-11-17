package nom.edu.starrism.auth.core.service.impl;

import nom.edu.starrism.auth.core.service.LogoutService;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.core.context.SecurityContext;
import org.springframework.stereotype.Service;

/**
 * <p>登出系统服务类</p>
 *
 * @author guocq
 * @since 2022/11/10
 **/
@Service(value = "logoutService")
public class LogoutServiceImpl implements LogoutService {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(LogoutServiceImpl.class);

    /**
     * <p>根据用户id登出系统</p>
     *
     * @param id 用户id
     * @return boolean
     * @author guocq
     * @date 2022/11/10 16:45
     */
    @Override
    public boolean logout(Long id) {
        try {
            SecurityContext.kickOut(id);
        } catch (Exception e) {
            LOGGER.error("登出系统失败", e);
            return false;
        }
        return true;
    }

    /**
     * <p>当前用户登出系统</p>
     *
     * @return boolean
     * @author guocq
     * @date 2022/11/10 16:45
     */
    @Override
    public boolean logout() {
        try {
            SecurityContext.logout();
        } catch (Exception e) {
            LOGGER.error("登出系统失败", e);
            return false;
        }
        return true;
    }
}
