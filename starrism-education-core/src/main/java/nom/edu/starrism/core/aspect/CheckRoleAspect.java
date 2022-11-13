package nom.edu.starrism.core.aspect;

import com.google.common.collect.Sets;
import nom.edu.starrism.common.enums.SeCommonResultCode;
import nom.edu.starrism.common.exception.SeException;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.pool.AuthPool;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.core.annotation.security.CheckRole;
import nom.edu.starrism.core.context.AppCoreContext;
import nom.edu.starrism.core.context.AuthContext;
import nom.edu.starrism.core.domain.vo.AuthenticatedUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>角色校验切面</p>
 *
 * @author guocq
 * @since 2022/11/11
 **/
@Component
@Aspect
public class CheckRoleAspect {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(CheckRoleAspect.class);

    @Pointcut("@annotation(nom.edu.starrism.core.annotation.security.CheckRole)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = AuthContext.getHttpServletRequest();
        String requestPath = request.getRequestURI();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        LOGGER.debug("请求[{}]方法[{}]正在校验角色", requestPath, methodName);
        Object[] args = joinPoint.getArgs();
        if (AppCoreContext.isFeign()) {
            LOGGER.debug("请求[{}]方法[{}]来自服务间调用，将不进行权限校验", requestPath, methodName);
            return joinPoint.proceed(args);
        }
        Method method = methodSignature.getMethod();
        CheckRole checkRole = method.getAnnotation(CheckRole.class);
        Set<String> roleCodes = findRoleCodes(checkRole);
        if (StringUtil.matches(AuthPool.DEFAULT_ADMIN, roleCodes)) {
            LOGGER.debug("方法[{}]校验角色{}成功", methodName, AuthPool.DEFAULT_ALL_PERMISSION);
            return joinPoint.proceed(args);
        }
        AuthenticatedUser authenticatedUser = AuthContext.getAuthenticatedUser();
        Set<String> roles = authenticatedUser.getRoles();
        if (StringUtil.matches(roles, roleCodes)) {
            LOGGER.debug("请求[{}]方法[{}]校验角色{}成功", requestPath, methodName, roleCodes);
            return joinPoint.proceed(args);
        }
        LOGGER.error("请求[{}]方法[{}]校验角色{}失败", requestPath, methodName, roleCodes);
        throw new SeException(SeCommonResultCode.FORBIDDEN);
    }

    /**
     * 获取角色码列表
     *
     * @param checkRole 注解
     * @return 角色码列表
     */
    private Set<String> findRoleCodes(CheckRole checkRole) {
        if (checkRole == null) {
            return Sets.newHashSet();
        }
        return Arrays.stream(checkRole.value()).collect(Collectors.toSet());
    }
}
