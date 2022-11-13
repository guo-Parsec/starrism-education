package nom.edu.starrism.core.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.pool.AuthPool;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.core.context.AppCoreContext;
import org.springframework.context.annotation.Configuration;

/**
 * <p>feign请求拦截器</p>
 * <p>所有用feign发出的请求的拦截器，注意是feign作为客户端发出请求的，而不是服务端</p>
 *
 * @author hedwing
 * @since 2022/11/11
 **/
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(FeignRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate template) {
        LOGGER.debug("feign请求拦截器注册");
        if (StringUtil.isNotBlank(AppCoreContext.feignSecret)) {
            LOGGER.debug("feign请求拦截器设置服务间访问秘钥为{{}:{}}", AuthPool.FEIGN_HEAD, AppCoreContext.feignSecret);
            template.header(AuthPool.FEIGN_HEAD, AppCoreContext.feignSecret);
        }
    }
}
