package nom.edu.starrism.core.aspect;

import nom.edu.starrism.common.helper.ReflectHelper;
import nom.edu.starrism.common.service.RedisService;
import nom.edu.starrism.common.util.ArrayUtil;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.core.annotation.cache.CacheClear;
import nom.edu.starrism.core.annotation.cache.CacheGroup;
import nom.edu.starrism.data.component.SpringBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>缓存切面</p>
 *
 * @author guocq
 * @since 2022/11/16
 **/
@Component
@Aspect
public class CacheAspect {
    @Pointcut("@annotation(nom.edu.starrism.core.annotation.cache.CacheClear)")
    public void cacheClearPointCut() {
    }

    @Pointcut("@annotation(nom.edu.starrism.core.annotation.cache.CacheGroup)")
    public void cacheGroupPointCut() {
    }

    @Around("cacheClearPointCut() || cacheGroupPointCut()")
    public Object doCacheAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        // 得到被切面修饰的方法的参数列表
        Object[] args = joinPoint.getArgs();
        // 得到被代理的方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        CacheClear cacheClear = ReflectHelper.findMethodAnnotation(method, CacheClear.class);
        CacheGroup cacheGroup = ReflectHelper.findMethodAnnotation(method, CacheGroup.class);
        result = joinPoint.proceed(args);
        if (cacheClear != null) {
            doCacheClear(args, method, cacheClear, result);
        }
        if (cacheGroup != null) {
            doCacheGroup(args, method, cacheGroup, result);
        }
        return result;
    }

    /**
     * <p>cache 清除</p>
     *
     * @param args       参数
     * @param method     方法
     * @param cacheClear 清除注解
     * @param result     结果
     * @author guocq
     * @date 2022/11/16 18:45
     */
    private void doCacheClear(Object[] args, Method method, CacheClear cacheClear, Object result) throws Throwable {
        String redisKey = StringUtil.redisKeyJoin(cacheClear.name(), parseKey(cacheClear.key(), method, args, result));
        if (cacheClear.fuzzyMatch()) {
            redisKey = StringUtil.redisKeyJoin(redisKey, "*");
        }
        RedisService bean = SpringBean.getBean(RedisService.class);
        bean.delByPattern(redisKey);
    }

    /**
     * <p>cache 分组处理</p>
     *
     * @param args       参数
     * @param method     方法
     * @param cacheGroup 分组注解
     * @param result     结果
     * @author guocq
     * @date 2022/11/16 18:45
     */
    private void doCacheGroup(Object[] args, Method method, CacheGroup cacheGroup, Object result) throws Throwable {
        CacheClear[] cacheClears = cacheGroup.cacheClear();
        if (ArrayUtil.isEmpty(cacheClears)) {
            return;
        }
        for (CacheClear cacheClear : cacheClears) {
            doCacheClear(args, method, cacheClear, result);
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
