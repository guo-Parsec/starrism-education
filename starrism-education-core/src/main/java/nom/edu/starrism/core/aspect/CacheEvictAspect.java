package nom.edu.starrism.core.aspect;

import com.google.common.collect.Lists;
import nom.edu.starrism.common.service.RedisService;
import nom.edu.starrism.common.util.ArrayUtil;
import nom.edu.starrism.common.util.ReflectionUtil;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.data.component.SpringBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>缓存切面</p>
 *
 * @author guocq
 * @since 2022/11/16
 **/
@Component
@Aspect
public class CacheEvictAspect {
    @Pointcut("@annotation(org.springframework.cache.annotation.CacheEvict)")
    public void cacheEvictPointCut() {
    }

    @Pointcut("@annotation(org.springframework.cache.annotation.Caching)")
    public void cachingGroupPointCut() {
    }

    @Around("cacheEvictPointCut() || cachingGroupPointCut()")
    public Object doCacheAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        // 得到被切面修饰的方法的参数列表
        Object[] args = joinPoint.getArgs();
        // 得到被代理的方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        CacheEvict cacheEvict = ReflectionUtil.findMethodAnnotation(method, CacheEvict.class);
        Caching caching = ReflectionUtil.findMethodAnnotation(method, Caching.class);
        result = joinPoint.proceed(args);
        if (cacheEvict != null) {
            doCacheEvict(args, method, cacheEvict, result);
        }
        if (caching != null) {
            doCaching(args, method, caching, result);
        }
        return result;
    }

    /**
     * <p>cache 清除</p>
     *
     * @param args       参数
     * @param method     方法
     * @param cacheEvict 清除注解
     * @param result     结果
     * @author guocq
     * @date 2022/11/16 18:45
     */
    private void doCacheEvict(Object[] args, Method method, CacheEvict cacheEvict, Object result) throws Throwable {
        List<String> cacheNames = Lists.newArrayList(cacheEvict.cacheNames());
        String cacheKey = cacheEvict.key();
        List<String> redisKeys = buildRedisKeys(cacheNames, cacheKey, method, args, result);
        redisKeys.forEach(redisKey -> {
            if (cacheEvict.allEntries()) {
                redisKey = StringUtil.redisKeyJoin(redisKey, "*");
            }
            RedisService bean = SpringBean.getBean(RedisService.class);
            bean.delByPattern(redisKey);
        });
    }

    /**
     * <p>构建redis key</p>
     *
     * @param cacheNames 缓存名称列表
     * @param cacheKey   缓存key
     * @param method     方法
     * @param args       参数
     * @param result     结果
     * @return java.util.List<java.lang.String>
     * @author guocq
     * @date 2022/11/16 19:00
     */
    private List<String> buildRedisKeys(List<String> cacheNames, String cacheKey, Method method, Object[] args, Object result) {
        if (StringUtil.isNotBlank(cacheKey)) {
            return cacheNames.stream()
                    .map(cacheName -> StringUtil.redisKeyJoin(cacheName, parseKey(cacheKey, method, args, result)))
                    .collect(Collectors.toList());
        }
        return cacheNames;
    }

    /**
     * <p>caching处理</p>
     *
     * @param args    参数
     * @param method  方法
     * @param caching 分组注解
     * @param result  结果
     * @author guocq
     * @date 2022/11/16 18:45
     */
    private void doCaching(Object[] args, Method method, Caching caching, Object result) throws Throwable {
        CacheEvict[] cacheEvicts = caching.evict();
        if (ArrayUtil.isEmpty(cacheEvicts)) {
            return;
        }
        for (CacheEvict cacheEvict : cacheEvicts) {
            doCacheEvict(args, method, cacheEvict, result);
        }
    }

    /**
     * <p>从spel中解析key</p>
     *
     * @param key    spel的key
     * @param method 方法
     * @param args   参数
     * @param result 结果
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/16 17:16
     */
    private String parseKey(String key, Method method, Object[] args, Object result) {
        if (StringUtil.isBlank(key)) {
            return null;
        }
        // 获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = discoverer.getParameterNames(method);
        if (ArrayUtil.isEmpty(paraNameArr)) {
            return null;
        }
        // 使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        // SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        // 把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        context.setVariable("result", result);
        return parser.parseExpression(key).getValue(context, String.class);
    }
}
