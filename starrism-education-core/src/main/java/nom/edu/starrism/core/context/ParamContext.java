package nom.edu.starrism.core.context;

import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.core.access.ParamAccess;
import nom.edu.starrism.core.domain.vo.SysParamVo;
import nom.edu.starrism.data.component.SpringBean;

import java.util.Optional;

/**
 * <p>参数上下文</p>
 *
 * @author guocq
 * @since 2022/11/24
 **/
public class ParamContext {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(ParamContext.class);

    @SuppressWarnings("unchecked")
    public static <T> T findParam(String paramCode, T defaultVal) {
        if (StringUtil.isBlank(paramCode)) {
            LOGGER.error("paramCode不能为空");
            return defaultVal;
        }
        ParamAccess paramAccess = SpringBean.getBean(ParamAccess.class);
        Optional<String> optional = Optional.ofNullable(paramAccess.findByParamCode(paramCode)).map(SysParamVo::getParamValue);
        return optional.map(s -> (T) s).orElse(defaultVal);
    }
}
