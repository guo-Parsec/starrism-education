package nom.edu.starrism.core.strategy.carrier;

import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.util.Md5Util;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.core.strategy.SePasswordStrategyOption;
import nom.edu.starrism.core.strategy.SePasswordStrategy;
import org.springframework.stereotype.Component;

/**
 * <p>MD5密码生成</p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
@Component(value = "sePasswordMd5")
public class SePasswordMd5 implements SePasswordStrategy {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SePasswordMd5.class);

    /**
     * <p>判断option是否为空</p>
     *
     * @param options 选项
     * @return boolean
     * @author guocq
     * @date 2022/10/24 14:25
     */
    @Override
    public boolean isOptionEmpty(SePasswordStrategyOption... options) {
        return SePasswordStrategy.super.isOptionEmpty(options) || StringUtil.isBlank(options[0].getSalt());
    }

    /**
     * <p>获取单个option</p>
     *
     * @param options 选项
     * @return {@link SePasswordStrategyOption}
     * @author guocq
     * @date 2022/10/24 14:27
     */
    @Override
    public SePasswordStrategyOption getSingleOption(SePasswordStrategyOption... options) {
        return SePasswordStrategy.super.getSingleOption(options);
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
    @Override
    public String encrypt(String plainText, SePasswordStrategyOption... sePasswordStrategyOption) {
        if (isOptionEmpty(sePasswordStrategyOption)) {
            LOGGER.debug("使用md5[不加盐]加密");
            return Md5Util.md5(plainText);
        }
        LOGGER.debug("使用md5[加盐]加密");
        return Md5Util.md5BySalt(plainText, getSingleOption(sePasswordStrategyOption).getSalt());
    }

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
    @Override
    public boolean match(String plainText, String encryptText, SePasswordStrategyOption... sePasswordStrategyOption) {
        return encrypt(plainText, sePasswordStrategyOption).equals(encryptText);
    }
}
