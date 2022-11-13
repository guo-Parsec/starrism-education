package nom.edu.starrism.core.aspect;

import nom.edu.starrism.common.enums.SeCommonResultCode;
import nom.edu.starrism.common.exception.SeException;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.core.annotation.security.CheckLogin;
import nom.edu.starrism.core.context.AppCoreContext;
import nom.edu.starrism.core.context.AuthContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * <p>登录校验切面</p>
 *
 * @author guocq
 * @since 2022/11/11
 **/
@Component
@Aspect
public class CheckLoginAspect {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(CheckLoginAspect.class);

    @Pointcut("@annotation(nom.edu.starrism.core.annotation.security.CheckLogin)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = AuthContext.getHttpServletRequest();
        String requestPath = request.getRequestURI();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        LOGGER.debug("请求[{}]方法[{}]正在校验权限", requestPath, methodName);
        Object[] args = joinPoint.getArgs();
        if (AppCoreContext.isFeign()) {
            LOGGER.debug("请求[{}]方法[{}]来自服务间调用，将不进行权限校验", requestPath, methodName);
            return joinPoint.proceed(args);
        }
        Method method = methodSignature.getMethod();
        CheckLogin checkLogin = method.getAnnotation(CheckLogin.class);
        if (checkLogin != null) {
            LOGGER.debug("请求[{}]方法[{}]校验登录成功", requestPath, methodName);
            return joinPoint.proceed(args);
        }
        LOGGER.error("请求[{}]方法[{}]校验登录失败", requestPath, methodName);
        throw new SeException(SeCommonResultCode.FORBIDDEN);
    }
}
