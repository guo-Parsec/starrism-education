package nom.edu.starrism.core.context;

import nom.edu.starrism.common.helper.CodeHelper;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.pool.AuthPool;
import nom.edu.starrism.common.util.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>应用核心上下文</p>
 *
 * @author hedwing
 * @since 2022/11/11
 **/
public class AppCoreContext {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(AppCoreContext.class);
    public static String feignSecret;

    /**
     * 是否为feign客户端
     *
     * @return boolean
     */
    public static boolean isFeign() {
        HttpServletRequest request = CodeHelper.getHttpServletRequest();
        String feignHead = request.getHeader(AuthPool.FEIGN_HEAD);
        if (StringUtil.isNotBlank(feignHead) && feignSecret.equals(feignHead)) {
            LOGGER.debug("当前请求来自客户端");
            return true;
        }
        return false;
    }
}
