package nom.edu.starrism.core.strategy;

/**
 * <p>密码策略器/p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
public interface SePasswordStrategy {

    /**
     * <p>判断option是否为空</p>
     *
     * @param options 选项
     * @return boolean
     * @author guocq
     * @date 2022/10/24 14:25
     */
    default boolean isOptionEmpty(SePasswordStrategyOption... options) {
        return options == null || options.length == 0 || !options[0].isExist();
    }

    /**
     * <p>获取单个option</p>
     *
     * @param options 选项
     * @return {@link SePasswordStrategyOption}
     * @author guocq
     * @date 2022/10/24 14:27
     */
    default SePasswordStrategyOption getSingleOption(SePasswordStrategyOption... options) {
        if (isOptionEmpty(options)) {
            return null;
        }
        return options[0];
    }

    /**
     * <p>使用 {@code option} 对 {@code plainText} 进行加密</p>
     *
     * @param plainText                被加密的密文
     * @param sePasswordStrategyOption 加密额外操作
     * @return {@link String}
     * @author hedwing
     * @since 2022/10/23
     */
    String encrypt(String plainText, SePasswordStrategyOption... sePasswordStrategyOption);

    /**
     * <p>使用 {@code option} 对 {@code plainText} 和 {@code encryptText} 进行匹配</p>
     *
     * @param plainText                被加密的密文
     * @param encryptText              已加密的密文
     * @param sePasswordStrategyOption 加密额外操作
     * @return {@link String}
     * @author hedwing
     * @since 2022/10/23
     */
    boolean match(String plainText, String encryptText, SePasswordStrategyOption... sePasswordStrategyOption);
}

