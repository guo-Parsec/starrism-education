package nom.edu.starrism.core.pool;

/**
 * <p>参数常量池</p>
 *
 * @author guocq
 * @since 2022/10/24
 **/
public interface ParamPool {
    /**
     * 参数码 - 默认密码策略器
     */
    String PARAM_CODE_PASSWORD_STRATEGY = "DEFAULT_PASSWORD_STRATEGY";

    /**
     * 默认密码策略器 {@link nom.edu.starrism.core.strategy.carrier.SePasswordMd5}
     */
    String DEFAULT_PASSWORD_STRATEGY = "sePasswordMd5";
}
