package nom.edu.starrism.core.service.impl;

import com.alibaba.fastjson2.JSON;
import nom.edu.starrism.common.exception.CoreException;
import nom.edu.starrism.core.context.SecurityContext;
import nom.edu.starrism.core.domain.entity.SysLog;
import nom.edu.starrism.core.domain.vo.AuthenticatedUser;
import nom.edu.starrism.core.repository.SysLogRepository;
import nom.edu.starrism.core.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>日志处理service</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
@Service(value = "logService")
public class LogServiceImpl implements LogService {
    @Resource
    private SysLogRepository sysLogRepository;

    /**
     * <p>日志写入</p>
     *
     * @param sysLog 日志参数
     * @author guocq
     * @date 2022/11/17 11:15
     */
    @Override
    public void write(SysLog sysLog) {
        sysLogRepository.insert(sysLog);
    }

    /**
     * <p>日志构建</p>
     *
     * @param requestPath   请求路径
     * @param method        请求类型
     * @param timeConsuming 耗时(ms)
     * @param args          请求参数
     * @param result        请求结果
     * @param error         错误信息
     * @param app           来源app
     * @param opExplain     操作说明
     * @return {@link SysLog}
     * @author guocq
     * @date 2022/11/17 11:35
     */
    @Override
    public SysLog build(String requestPath, String method, Long timeConsuming, Object[] args, Object result, CoreException error,
                        String app, String opExplain) {
        return build(requestPath, method, timeConsuming, args, result, error, app, opExplain, null);
    }

    /**
     * <p>日志构建</p>
     *
     * @param requestPath       请求路径
     * @param method            请求类型
     * @param timeConsuming     耗时(ms)
     * @param args              请求参数
     * @param result            请求结果
     * @param error             错误信息
     * @param app               来源app
     * @param opExplain         操作说明
     * @param authenticatedUser 认证用户
     * @return {@link SysLog}
     * @author guocq
     * @date 2022/11/17 11:35
     */
    @Override
    public SysLog build(String requestPath, String method, Long timeConsuming, Object[] args, Object result,
                        CoreException error, String app, String opExplain, AuthenticatedUser authenticatedUser) {
        SysLog sysLog = new SysLog();
        sysLog.setRequestPath(requestPath);
        sysLog.setTimeConsuming(timeConsuming);
        sysLog.setRequestMethod(method);
        sysLog.setApp(app);
        sysLog.setOpExplain(opExplain);
        if (args != null && args.length != 0) {
            sysLog.setRequestParam(JSON.toJSONString(args));
        }
        if (result != null) {
            sysLog.setRequestResult(JSON.toJSONString(result));
        }
        if (authenticatedUser == null) {
            authenticatedUser = SecurityContext.findCertificate();
        }
        sysLog.setOpUserId(authenticatedUser.getId());
        sysLog.setOpUserAccount(authenticatedUser.getSubject());
        if (error != null) {
            sysLog.setErrorCode(error.getCode() == null ? error.getRequestEnum().getCode() : error.getCode());
            sysLog.setErrorMessage(error.getMessage());
            sysLog.setSuccess(SysLog.FAILED);
        } else {
            sysLog.setSuccess(SysLog.SUCCESS);
        }
        return sysLog;
    }
}
