package nom.edu.starrism.core.aspect;

import com.github.pagehelper.PageHelper;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * <p>数据更改切面</p>
 *
 * @author guocq
 * @since 2022/11/16
 **/
@Component
@Aspect
public class DataChangeAspect {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(DataChangeAspect.class);

    @Pointcut("@annotation(nom.edu.starrism.core.annotation.crud.Modify)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed(joinPoint.getArgs());
    }
}
