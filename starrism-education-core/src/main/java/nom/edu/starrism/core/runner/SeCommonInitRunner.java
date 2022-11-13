package nom.edu.starrism.core.runner;

import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.pool.AuthPool;
import nom.edu.starrism.common.pool.RedisPool;
import nom.edu.starrism.common.service.RedisService;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.common.util.UUIDGeneratorUtil;
import nom.edu.starrism.core.access.ParamAccess;
import nom.edu.starrism.core.context.AppCoreContext;
import nom.edu.starrism.core.context.PageContext;
import nom.edu.starrism.core.domain.vo.SysParamVo;
import nom.edu.starrism.core.pool.ParamPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * <p>通用初始化启动器</p>
 *
 * @author guocq
 * @since 2022/11/11
 **/
@Component
@Order(value = -1)
public class SeCommonInitRunner implements ApplicationRunner {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SeCommonInitRunner.class);
    @Resource
    private ParamAccess paramAccess;
    private RedisService redisService;

    private static final Map<String, Consumer<SysParamVo>> C_MAP = new HashMap<>(16);

    /**
     * 初始化分页参数
     */
    private void initPageProperties() {
        LOGGER.debug("初始化分页参数");
        List<SysParamVo> pageGroupParam = paramAccess.findByGroupCode(ParamPool.PARAM_GROUP_CODE_PAGE);
        pageGroupParam.forEach(param -> {
            String paramCode = param.getParamCode();
            if (C_MAP.containsKey(paramCode)) {
                C_MAP.get(paramCode).accept(param);
            }
        });
    }

    /**
     * 默认当前页填充
     *
     * @param paramVo 参数
     */
    private void fillDefaultCurrPage(SysParamVo paramVo) {
        PageContext.defaultCurrPage = Integer.valueOf(paramVo.getParamValue());
    }

    /**
     * 默认页大小填充
     *
     * @param paramVo 参数
     */
    private void fillDefaultPageSize(SysParamVo paramVo) {
        PageContext.defaultPageSize = Integer.valueOf(paramVo.getParamValue());
    }

    private void cMapInit() {
        C_MAP.put(ParamPool.PARAM_CODE_CURR_PAGE, this::fillDefaultCurrPage);
        C_MAP.put(ParamPool.PARAM_CODE_PAGE_SIZE, this::fillDefaultPageSize);
    }

    /**
     * 初始化feign秘钥
     */
    private void initFeignSecret() {
        LOGGER.debug("初始化feign秘钥");
        String key = StringUtil.redisKeyJoin(RedisPool.BASE_REDIS_KEY, AuthPool.FEIGN_HEAD);
        String value = (String) redisService.get(key);
        if (StringUtil.isNotBlank(value)) {
            AppCoreContext.feignSecret = value;
            LOGGER.debug("已读取到feign秘钥为{}", value);
            return;
        }
        AppCoreContext.feignSecret = UUIDGeneratorUtil.uuid();
        redisService.set(key, AppCoreContext.feignSecret);
        LOGGER.debug("第一次初始化feign秘钥为{}", AppCoreContext.feignSecret);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initFeignSecret();
        cMapInit();
        initPageProperties();
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }
}
