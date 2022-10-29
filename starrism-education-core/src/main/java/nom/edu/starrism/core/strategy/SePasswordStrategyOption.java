package nom.edu.starrism.core.strategy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SePasswordStrategyOption {
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

    public SePasswordStrategyOption() {
    }

    public SePasswordStrategyOption(boolean exist, String salt, String privateKey, String publicKey) {
        this.exist = exist;
        this.salt = salt;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public static OptionBuilder builder() {
        return new OptionBuilder();
    }

    public static class OptionBuilder {
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

        OptionBuilder() {

        }

        public SePasswordStrategyOption.OptionBuilder exist(boolean exist) {
            this.exist = exist;
            return this;
        }

        public SePasswordStrategyOption.OptionBuilder salt(String salt) {
            this.salt = salt;
            return this;
        }

        public SePasswordStrategyOption.OptionBuilder privateKey(String privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        public SePasswordStrategyOption.OptionBuilder publicKey(String publicKey) {
            this.publicKey = publicKey;
            return this;
        }

        public SePasswordStrategyOption build() {
            return new SePasswordStrategyOption(exist, salt, privateKey, publicKey);
        }
    }
}
