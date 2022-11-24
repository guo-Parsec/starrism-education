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

    /**
     * 参数组别码 - 分页参数分组
     */
    String PARAM_GROUP_CODE_PAGE = "PAGE_PARAM_GROUP";

    /**
     * 参数码 - 默认页大小
     */
    String PARAM_CODE_PAGE_SIZE = "DEFAULT_PAGE_SIZE";

    /**
     * 参数码 - 默认当前页
     */
    String PARAM_CODE_CURR_PAGE = "DEFAULT_CURR_PAGE";

    /**
     * 参数码 - 默认令牌过期时间
     */
    String PARAM_CODE_TOKEN_EXPIRE = "DEFAULT_TOKEN_EXPIRE";
}
