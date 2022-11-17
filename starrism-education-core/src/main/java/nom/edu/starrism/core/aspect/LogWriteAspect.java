package nom.edu.starrism.core.aspect;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.core.annotation.log.LogWrite;
import nom.edu.starrism.core.context.AuthContext;
import nom.edu.starrism.data.type.RequestType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p></p>
 *
 * @author guocq
 * @since 2022/11/16
 **/
@Component
@Aspect
public class LogWriteAspect {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(LogWriteAspect.class);


    @Around(value = "@within(logWrite)", argNames = "joinPoint,logWrite")
    public Object doAround(ProceedingJoinPoint joinPoint, LogWrite logWrite) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String method = AuthContext.getHttpServletRequest().getMethod();
        List<RequestType> requestTypes = Lists.newArrayList(logWrite.allowRequestType());
        Object result = joinPoint.proceed();
        if (requestTypes.contains(RequestType.findRequestType(method))) {
            LOGGER.debug("请求类型为{}", method);
            LOGGER.debug("方法参数为{}", JSON.toJSONString(args));
            LOGGER.debug("方法结果为{}", JSON.toJSONString(result));
        }
        return result;
    }
}
