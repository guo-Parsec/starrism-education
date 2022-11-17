package nom.edu.starrism.data.config;

import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;


/**
 * <p></p>
 *
 * @author guocq
 * @since 2022/11/16
 **/
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MybatisInterceptor implements Interceptor, InitializingBean {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(MybatisInterceptor.class);
    private static final Map<SqlCommandType, Consumer<AbstractDataEntity>> map = new HashMap<>(16);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        AbstractDataEntity data = null;
        if (args[1] instanceof AbstractDataEntity) {
            data = (AbstractDataEntity) args[1];
            map.get(sqlCommandType).accept(data);
        }
        return invocation.proceed();
    }

    private void doUpdate(AbstractDataEntity arg) {
        LOGGER.debug("参数{}正在执行update", arg);
        arg.setGmtModify(LocalDateTime.now());
    }

    private void doInsert(AbstractDataEntity arg) {
        LOGGER.debug("参数{}正在执行insert", arg);
        LocalDateTime now = LocalDateTime.now();
        arg.setGmtCreate(now);
        arg.setGmtModify(now);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        map.put(SqlCommandType.UPDATE, this::doUpdate);
        map.put(SqlCommandType.INSERT, this::doInsert);
    }
}
