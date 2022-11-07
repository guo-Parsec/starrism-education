package nom.edu.starrism.core.context;

import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.pool.AuthPool;
import nom.edu.starrism.common.pool.CorePool;
import nom.edu.starrism.common.service.RedisService;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.core.domain.vo.AuthenticatedUser;
import nom.edu.starrism.data.component.SpringBean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>认证上下文</p>
 *
 * @author hedwing
 * @since 2022/11/7
 **/
public class AuthContext {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(AuthContext.class);

    /**
     * <p>判断当前操作是否已经登录</p>
     *
     * @return boolean
     * @author hedwing
     * @since 2022/11/7
     */
    public static boolean isLogin() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            LOGGER.error("用户鉴权失败，原因：无法获取当前系统请求");
            return false;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader(AuthPool.TOKEN_REQ_HEAD);
        if (StringUtil.isBlank(token) || !token.startsWith(AuthPool.JWT_TOKEN_PREFIX)) {
            LOGGER.error("用户鉴权失败，原因：无法从当前系统请求中获取合法的token");
            return false;
        }
        String realToken = token.replace(AuthPool.JWT_TOKEN_PREFIX, StringUtil.EMPTY);
        RedisService redisService = SpringBean.getBean(RedisService.class);
        AuthenticatedUser loginUser = (AuthenticatedUser) redisService.get(AuthPool.TOKEN_REDIS_KEY + CorePool.REDIS_KEY_SEPARATOR + realToken);
        if (AuthenticatedUser.isEmpty(loginUser)) {
            LOGGER.error("用户鉴权失败，原因：无法获取到登录信息，可能因为token已过期");
            return false;
        }
        return true;
    }

}
