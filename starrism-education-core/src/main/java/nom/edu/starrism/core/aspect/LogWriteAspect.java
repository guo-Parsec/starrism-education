package nom.edu.starrism.core.aspect;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import nom.edu.starrism.common.enums.BaseRequest;
import nom.edu.starrism.common.exception.CoreException;
import nom.edu.starrism.common.helper.CodeHelper;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.support.Carrier;
import nom.edu.starrism.core.annotation.log.LogWrite;
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
        CoreException error = null;
        try {
            result = joinPoint.proceed();
        } catch (CoreException exception) {
            error = exception;
            result = Carrier.failed(error.getRequestEnum(), error.getMessage());
        } catch (Throwable exception) {
            error = new CoreException(BaseRequest.FAILED, exception.getMessage());
            result = Carrier.failed(error.getRequestEnum(), error.getMessage());
        }
        String url = request.getRequestURI();
        long endMillis = System.currentTimeMillis();
        if (Sets.newHashSet(requestTypes).contains(RequestType.findRequestType(method))) {
            logService.write(url, method, endMillis - startMillis, args, result, error);
        }
        return result;
    }

    @Autowired
    public void setLogService(LogService logService) {
        this.logService = logService;
    }
}
