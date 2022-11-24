package nom.edu.starrism.core.strategy.carrier;

import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.core.helper.RsaHelper;
import nom.edu.starrism.core.strategy.SePasswordStrategy;
import nom.edu.starrism.core.strategy.SePasswordStrategyOption;
import org.springframework.stereotype.Component;

/**
 * <p>RSA密码生成</p>
 *
 * @author guocq
 * @since 2022/11/21
 **/
@Component(value = "sePasswordRsa")
public class SePasswordRsa implements SePasswordStrategy {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SePasswordRsa.class);

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
        LOGGER.debug("使用RSA加密");
        return RsaHelper.encrypt(plainText);
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
        String decrypt = RsaHelper.decrypt(encryptText);
        return plainText.equals(decrypt);
    }
}
