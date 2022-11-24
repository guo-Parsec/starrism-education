package nom.edu.starrism.core.aspect;

import com.google.common.collect.Sets;
import nom.edu.starrism.common.enums.BaseRequest;
import nom.edu.starrism.common.exception.CoreException;
import nom.edu.starrism.common.helper.CodeHelper;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.pool.AuthPool;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.core.annotation.security.CheckLogin;
import nom.edu.starrism.core.annotation.security.CheckPermission;
import nom.edu.starrism.core.annotation.security.CheckRole;
import nom.edu.starrism.core.context.AppCoreContext;
import nom.edu.starrism.core.context.SecurityContext;
import nom.edu.starrism.core.domain.vo.AuthenticatedUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>安全认证切面支持</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
@Component
@Aspect
public class SecurityAspectSupport {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SecurityAspectSupport.class);

    /**
     * <p>校验是否登录环绕方法</p>
     *
     * @param joinPoint  切入点
     * @param checkLogin 是否登录注解
     * @return java.lang.Object
     * @author guocq
     * @date 2022/11/17 10:43
     */
    @Around(value = "@annotation(checkLogin)", argNames = "joinPoint,checkLogin")
    public Object checkLoginAround(ProceedingJoinPoint joinPoint, CheckLogin checkLogin) throws Throwable {
        HttpServletRequest request = CodeHelper.getHttpServletRequest();
        String requestPath = request.getRequestURI();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        LOGGER.debug("请求[{}]方法[{}]正在校验权限", requestPath, methodName);
        Object[] args = joinPoint.getArgs();
        if (AppCoreContext.isFeign()) {
            LOGGER.debug("请求[{}]方法[{}]来自服务间调用，将不进行权限校验", requestPath, methodName);
            return joinPoint.proceed(args);
        }
        if (SecurityContext.isCertificated()) {
            LOGGER.debug("请求[{}]方法[{}]校验登录成功", requestPath, methodName);
            return joinPoint.proceed(args);
        }
        LOGGER.error("请求[{}]方法[{}]校验登录失败", requestPath, methodName);
        throw new CoreException(BaseRequest.FORBIDDEN);
    }

    /**
     * <p>校验是否拥有权限环绕方法</p>
     *
     * @param joinPoint       切入点
     * @param checkPermission 是否拥有权限注解
     * @return java.lang.Object
     * @author guocq
     * @date 2022/11/17 10:44
     */
    @Around(value = "@annotation(checkPermission)", argNames = "joinPoint,checkPermission")
    public Object checkPermissionAround(ProceedingJoinPoint joinPoint, CheckPermission checkPermission) throws Throwable {
        HttpServletRequest request = CodeHelper.getHttpServletRequest();
        String requestPath = request.getRequestURI();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        LOGGER.debug("请求[{}]方法[{}]正在校验权限", requestPath, methodName);
        Object[] args = joinPoint.getArgs();
        if (AppCoreContext.isFeign()) {
            LOGGER.debug("请求[{}]方法[{}]来自服务间调用，将不进行权限校验", requestPath, methodName);
            return joinPoint.proceed(args);
        }
        Set<String> permissionCodes = findPermissionCodes(checkPermission);
        if (StringUtil.matches(AuthPool.DEFAULT_ALL_PERMISSION, permissionCodes)) {
            LOGGER.debug("方法[{}]校验权限{}成功", methodName, AuthPool.DEFAULT_ALL_PERMISSION);
            return joinPoint.proceed(args);
        }
        AuthenticatedUser authenticatedUser = SecurityContext.findCertificate();
        Set<String> permissions = authenticatedUser.getPermissions();
        if (StringUtil.matches(permissions, permissionCodes)) {
            LOGGER.debug("请求[{}]方法[{}]校验权限{}成功", requestPath, methodName, permissionCodes);
            return joinPoint.proceed(args);
        }
        LOGGER.error("请求[{}]方法[{}]校验权限{}失败", requestPath, methodName, permissionCodes);
        throw new CoreException(BaseRequest.FORBIDDEN);
    }

    /**
     * <p>校验是否拥有角色环绕方法</p>
     *
     * @param joinPoint 切入点
     * @param checkRole 是否拥有角色注解
     * @return java.lang.Object
     * @author guocq
     * @date 2022/11/17 10:45
     */
    @Around(value = "@annotation(checkRole)", argNames = "joinPoint,checkRole")
    public Object doCheckRoleAround(ProceedingJoinPoint joinPoint, CheckRole checkRole) throws Throwable {
        HttpServletRequest request = CodeHelper.getHttpServletRequest();
        String requestPath = request.getRequestURI();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        LOGGER.debug("请求[{}]方法[{}]正在校验角色", requestPath, methodName);
        Object[] args = joinPoint.getArgs();
        if (AppCoreContext.isFeign()) {
            LOGGER.debug("请求[{}]方法[{}]来自服务间调用，将不进行权限校验", requestPath, methodName);
            return joinPoint.proceed(args);
        }
        Set<String> roleCodes = findRoleCodes(checkRole);
        AuthenticatedUser authenticatedUser = SecurityContext.findCertificate();
        Set<String> roles = authenticatedUser.getRoles();
        if (StringUtil.matches(roles, roleCodes)) {
            LOGGER.debug("请求[{}]方法[{}]校验角色{}成功", requestPath, methodName, roleCodes);
            return joinPoint.proceed(args);
        }
        LOGGER.error("请求[{}]方法[{}]校验角色{}失败", requestPath, methodName, roleCodes);
        throw new CoreException(BaseRequest.FORBIDDEN);
    }

    /**
     * 获取权限码列表
     *
     * @param checkPermission 注解
     * @return 权限码列表
     */
    private Set<String> findPermissionCodes(CheckPermission checkPermission) {
        if (checkPermission == null) {
            return Sets.newHashSet();
        }
        return Arrays.stream(checkPermission.value()).collect(Collectors.toSet());
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
