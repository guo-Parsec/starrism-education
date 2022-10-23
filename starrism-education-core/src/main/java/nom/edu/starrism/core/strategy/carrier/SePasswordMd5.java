package nom.edu.starrism.core.strategy.carrier;

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
    /**
     * <p>使用 {@code option} 对 {@code plainText} 进行加密</p>
     *
     * @param plainText 被加密的密文
     * @param option    加密额外操作
     * @return {@link String}
     * @author hedwing
     * @since 2022/10/23
     */
    @Override
    public String encrypt(String plainText, Option... option) {

        return null;
    }

    /**
     * <p>使用 {@code option} 对 {@code plainText} 和 {@code encryptText} 进行匹配</p>
     *
     * @param plainText   被加密的密文
     * @param encryptText 已加密的密文
     * @param option      加密额外操作
     * @return {@link String}
     * @author hedwing
     * @since 2022/10/23
     */
    @Override
    public boolean match(String plainText, String encryptText, Option... option) {
        return false;
    }
}
