package nom.edu.starrism.gate.filter;

import nom.edu.starrism.common.enums.SeCommonResultCode;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.pool.AuthPool;
import nom.edu.starrism.common.pool.RedisPool;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.service.RedisService;
import nom.edu.starrism.common.util.CollectionUtil;
import nom.edu.starrism.common.util.PathUtil;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.common.util.WebfluxServletUtil;
import nom.edu.starrism.core.context.SecurityContext;
import nom.edu.starrism.core.domain.vo.AuthenticatedUser;
import nom.edu.starrism.gate.properties.IgnoreUrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>认证核心过滤器</p>
 *
 * @author guocq
 * @since 2022/11/9
 **/
@Component
public class AuthCoreFilter implements GlobalFilter, Ordered {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(AuthCoreFilter.class);
    private final IgnoreUrlProperties ignoreUrlProperties;
    private RedisService redisService;

    public AuthCoreFilter(IgnoreUrlProperties ignoreUrlProperties) {
        this.ignoreUrlProperties = ignoreUrlProperties;
    }


    /**
     * 匿名认证放行
     *
     * @param requestActionUrl 请求url
     * @return 是否放行 true 放行 false 不放行
     */
    private boolean anonRelease(String requestActionUrl) {
        Set<String> anonymousUrlSet = new HashSet<>(ignoreUrlProperties.getUrls());
        String anonCacheKey = StringUtil.redisKeyJoin(RedisPool.BASE_REDIS_KEY,
                RedisPool.PERMISSION_QUERY_BY_CATEGORY_CODE_KEY, AuthPool.PERMISSION_CATEGORY_ANONYMOUS);
        CollectionUtil.castCollection(redisService.get(anonCacheKey), anonymousUrlSet, String.class);
        return StringUtil.matches(PathUtil.autoPopulateRequestRootPath(requestActionUrl), anonymousUrlSet);
    }

    /**
     * 权限认证放行
     *
     * @param requestActionUrl 请求url
     * @param token            令牌
     * @return boolean 是否放行 true 放行 false 不放行
     */
    private boolean authRelease(String requestActionUrl, String token) {
        AuthenticatedUser certificate = SecurityContext.findCertificate(token);
        Set<String> releaseUrlOfUser = certificate.getUrls();
        return StringUtil.matches(PathUtil.autoPopulateRequestRootPath(requestActionUrl), releaseUrlOfUser);
    }

    /**
     * <p>是否登录校验</p>
     *
     * @param requestActionUrl 请求url
     * @param token            令牌
     * @param response         响应
     * @param exchange         当前请求上下文
     * @param chain            提供委托给下一个筛选器的方法
     * @return {@link Mono<Void>}
     * @author guocq
     * @date 2022/11/10 15:56
     */
    private Mono<Void> checkPermission(String requestActionUrl, String token, ServerHttpResponse response, ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!SecurityContext.isCertificated(token)) {
            return WebfluxServletUtil.write(response, SeCommonResultCode.UNAUTHORIZED.getMessage(), SeCommonResultCode.UNAUTHORIZED);
        }
        if (authRelease(requestActionUrl, token)) {
            LOGGER.debug("当前请求[{}]已经匹配为认证过滤路径，可直接放行", requestActionUrl);
            return chain.filter(exchange);
        }
        return WebfluxServletUtil.write(response, SeCommonResultCode.FORBIDDEN.getMessage(), SeCommonResultCode.FORBIDDEN);
    }

    /**
     * <p>核心过滤器处理</p>
     *
     * @param exchange 当前请求上下文
     * @param chain    提供委托给下一个筛选器的方法
     * @return {@link Mono<Void>}
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String requestPath = request.getPath().toString();
        String requestActionUrl = requestPath.replace(UrlPool.GLOBAL_URL_PREFIX, StringUtil.EMPTY);
        if (anonRelease(requestActionUrl)) {
            LOGGER.debug("当前请求[{}]已经匹配为认证过滤路径，可直接放行", requestActionUrl);
            return chain.filter(exchange);
        }
        return checkPermission(requestActionUrl, request.getHeaders().getFirst(AuthPool.TOKEN_REQ_HEAD), response, exchange, chain);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }
}
