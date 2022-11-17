package nom.edu.starrism.core.aspect;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import nom.edu.starrism.common.enums.SeCommonResultCode;
import nom.edu.starrism.common.exception.SeException;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.support.CodeHelper;
import nom.edu.starrism.core.annotation.log.LogWrite;
import nom.edu.starrism.core.context.AuthContext;
import nom.edu.starrism.core.domain.entity.SysLog;
import nom.edu.starrism.core.domain.vo.AuthenticatedUser;
import nom.edu.starrism.core.service.LogService;
import nom.edu.starrism.data.type.RequestType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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

    private LogService logService;

    @Around(value = "@annotation(logWrite)", argNames = "joinPoint,logWrite")
    public Object doAround(ProceedingJoinPoint joinPoint, LogWrite logWrite) throws Throwable {
        long startMillis = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = CodeHelper.getHttpServletRequest();
        String method = request.getMethod();
        List<RequestType> requestTypes = Lists.newArrayList(logWrite.allowRequestType());
        Object result = null;
        SeException error = null;
        try {
            result = joinPoint.proceed();
        } catch (SeException exception) {
            error = exception;
        } catch (Throwable exception) {
            error = new SeException(SeCommonResultCode.FAILED, exception.getMessage());
        }
        String url = request.getRequestURL().toString();
        long endMillis = System.currentTimeMillis();
        if (requestTypes.contains(RequestType.findRequestType(method))) {
            logService.write(url, method, endMillis - startMillis, args, result, error);
        }
        return result;
    }

    @Autowired
    public void setLogService(LogService logService) {
        this.logService = logService;
    }
}
