package nom.edu.starrism.core.service;

import nom.edu.starrism.common.exception.SeException;
import nom.edu.starrism.core.domain.entity.SysLog;

/**
 * <p>日志操作service</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
public interface LogService {
    /**
     * <p>日志写入</p>
     *
     * @param sysLog 日志参数
     * @author guocq
     * @date 2022/11/17 11:15
     */
    void write(SysLog sysLog);

    /**
     * <p>日志写入</p>
     *
     * @param requestPath   请求路径
     * @param method        请求类型
     * @param timeConsuming 耗时(ms)
     * @param args          请求参数
     * @param result        请求结果
     * @param error         错误信息
     * @author guocq
     * @date 2022/11/17 11:36
     */
    default void write(String requestPath, String method, Long timeConsuming, Object[] args, Object result, SeException error) {
        write(build(requestPath, method, timeConsuming, args, result, error));
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
     * @return {@link SysLog}
     * @author guocq
     * @date 2022/11/17 11:35
     */
    SysLog build(String requestPath, String method, Long timeConsuming, Object[] args, Object result, SeException error);
}
