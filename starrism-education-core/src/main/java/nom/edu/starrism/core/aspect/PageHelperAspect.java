package nom.edu.starrism.core.aspect;

import com.github.pagehelper.PageHelper;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.core.context.PageContext;
import nom.edu.starrism.data.domain.param.AbstractPageParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * <p>分页切面</p>
 *
 * @author guocq
 * @since 2022/11/11
 **/
@Component
@Aspect
public class PageHelperAspect {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(CheckLoginAspect.class);

    @Pointcut("@annotation(nom.edu.starrism.core.annotation.crud.PageQuery)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        LOGGER.debug("方法[{}]正在分页查询数据", methodName);
        Object[] args = joinPoint.getArgs();
        // 必须清空page再次开启
        PageHelper.clearPage();
        startPage(args, methodName);
        return joinPoint.proceed(args);
    }

    /**
     * <p>开启分页查询</p>
     *
     * @param args 方法参数
     * @author guocq
     * @date 2022/11/11 16:53
     */
    private void startPage(Object[] args, String methodName) {
        AbstractPageParam param = null;
        for (Object arg : args) {
            if (arg instanceof AbstractPageParam) {
                param = (AbstractPageParam) arg;
                break;
            }
        }
        Integer currPage = param == null || param.getCurrPage() <= 0 ? PageContext.defaultCurrPage : param.getCurrPage();
        Integer pageSize = param == null || param.getPageSize() <= 0 ? PageContext.defaultPageSize : param.getPageSize();
        LOGGER.debug("方法[{}]正在分页查询数据, 当前页为{},页大小为{}", methodName, currPage, pageSize);
        PageHelper.startPage(currPage, pageSize);
    }
}
