package nom.edu.starrism.core.strategy;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>密码策略器/p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
public interface SePasswordStrategy {

    /**
     * <p>使用 {@code option} 对 {@code plainText} 进行加密</p>
     *
     * @param plainText 被加密的密文
     * @param option    加密额外操作
     * @return {@link String}
     * @author hedwing
     * @since 2022/10/23
     */
    String encrypt(String plainText, Option... option);

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
    boolean match(String plainText, String encryptText, Option... option);

    @Getter
    @Setter
    class Option {
        /**
         * 是否必须
         */
        private boolean exist;

        /**
         * 盐值
         */
        private String salt;

        /**
         * 私钥
         */
        private String privateKey;

        /**
         * 公钥
         */
        private String publicKey;
    }
}

