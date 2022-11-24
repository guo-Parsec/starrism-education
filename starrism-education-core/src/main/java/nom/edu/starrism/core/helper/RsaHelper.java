package nom.edu.starrism.core.helper;

import com.google.common.collect.Maps;
import nom.edu.starrism.common.enums.BaseRequest;
import nom.edu.starrism.common.exception.CoreException;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.pool.RedisPool;
import nom.edu.starrism.common.util.StringUtil;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

/**
 * <p>RSA加解密辅助类</p>
 *
 * @author guocq
 * @since 2022/11/21
 **/
public class RsaHelper {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(RsaHelper.class);

    /**
     * 算法常量
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 公钥存储key
     */
    public static final String PUBLIC_KEY = "publicKey";

    /**
     * 私钥存储key
     */
    public static final String PRIVATE_KEY = "privateKey";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    public static KeyPair keyPair = null;

    /**
     * <p>获取公钥文本</p>
     *
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/21 16:00
     */
    public static String getPublicKeyStr() {
        return getPublicKeyStr(keyPair.getPublic());
    }

    /**
     * <p>获取公钥字符串</p>
     *
     * @param publicKey 公钥
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/21 14:31
     */
    public static String getPublicKeyStr(PublicKey publicKey) {
        try {
            return encryptBase64(publicKey.getEncoded());
        } catch (Exception e) {
            LOGGER.error("publicKey{}获取公钥字符串失败", publicKey, e);
            throw new CoreException(e, BaseRequest.FAILED, "获取公钥字符串失败");
        }
    }

    /**
     * <p>获取私钥字符串</p>
     *
     * @param privateKey 私钥
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/21 14:32
     */
    public static String getPrivateKeyStr(PrivateKey privateKey) {
        try {
            return encryptBase64(privateKey.getEncoded());
        } catch (Exception e) {
            LOGGER.error("privateKey{}获取私钥字符串失败", privateKey, e);
            throw new CoreException(e, BaseRequest.FAILED, "获取私钥字符串失败");
        }
    }

    /**
     * <p>获取公钥</p>
     *
     * @param key 公钥字符串
     * @return java.security.PublicKey
     * @author guocq
     * @date 2022/11/21 14:36
     */
    public static PublicKey getPublicKey(String key) {
        try {
            byte[] keyBytes = decryptBase64(key);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePublic(spec);
        } catch (Exception e) {
            LOGGER.error("key{}中获取公钥失败", key, e);
            throw new CoreException(e, BaseRequest.FAILED, "获取公钥失败");
        }
    }

    /**
     * <p>获取私钥</p>
     *
     * @param key 私钥字符串
     * @return java.security.PrivateKey
     * @author guocq
     * @date 2022/11/21 14:38
     */
    public static PrivateKey getPrivateKey(String key) {
        try {
            byte[] keyBytes = decryptBase64(key);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePrivate(spec);
        } catch (Exception e) {
            LOGGER.error("key{}中获取私钥失败", key, e);
            throw new CoreException(e, BaseRequest.FAILED, "获取私钥失败");
        }
    }

    /**
     * <p>签名</p>
     *
     * @param data          待签名的数据字节数组
     * @param privateKeyStr 私钥字符串
     * @return byte[]
     * @author guocq
     * @date 2022/11/21 14:39
     */
    public static byte[] sign(byte[] data, String privateKeyStr) {
        PrivateKey privateKey = null;
        try {
            privateKey = getPrivateKey(privateKeyStr);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("签名算法{}获取失败", SIGNATURE_ALGORITHM, e);
            throw new CoreException(e, BaseRequest.FAILED, "获取算法失败");
        } catch (InvalidKeyException e) {
            LOGGER.error("使用私钥{}初始化签名失败", privateKey, e);
            throw new CoreException(e, BaseRequest.FAILED, "无效密钥");
        } catch (SignatureException e) {
            LOGGER.error("使用指定的字节数组{}更新数据失败", data, e);
            throw new CoreException(e, BaseRequest.FAILED, "签名失败");
        }
    }

    /**
     * <p>校验</p>
     *
     * @param data         待签名的数据字节数组
     * @param sign         签名字节数组
     * @param publicKeyStr 公钥字符串
     * @return byte[]
     * @author guocq
     * @date 2022/11/21 14:39
     */
    public static boolean verify(byte[] data, byte[] sign, String publicKeyStr) {
        PublicKey publicKey = null;
        try {
            publicKey = getPublicKey(publicKeyStr);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(sign);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("签名算法{}获取失败", SIGNATURE_ALGORITHM, e);
            throw new CoreException(e, BaseRequest.FAILED, "获取算法失败");
        } catch (InvalidKeyException e) {
            LOGGER.error("使用公钥{}初始化验证失败", publicKey, e);
            throw new CoreException(e, BaseRequest.FAILED, "无效密钥");
        } catch (SignatureException e) {
            LOGGER.error("使用指定的字节数组{}更新数据失败", data, e);
            throw new CoreException(e, BaseRequest.FAILED, "验证失败");
        }
    }

    /**
     * <p>使用公钥加密</p>
     *
     * @param plainText 原始文本
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/21 15:57
     */
    public static String encrypt(String plainText) {
        return encrypt(plainText.getBytes());
    }

    /**
     * <p>使用公钥加密</p>
     *
     * @param plainText 原始文本
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/21 14:51
     */
    private static String encrypt(byte[] plainText) {
        PublicKey publicKey = keyPair.getPublic();
        try {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            int plainTextLength = plainText.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offset = 0;
            int index = 0;
            byte[] cache;
            while (plainTextLength - offset > 0) {
                if (plainTextLength - offset > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(plainText, offset, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(plainText, offset, plainTextLength - offset);
                }
                out.write(cache, 0, cache.length);
                index++;
                offset = index * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptText = out.toByteArray();
            out.close();
            return Base64Utils.encodeToString(encryptText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOGGER.error("算法{}获取失败", KEY_ALGORITHM, e);
            throw new CoreException(e, BaseRequest.FAILED, "获取算法失败");
        } catch (InvalidKeyException e) {
            LOGGER.error("公钥{}无效", publicKey, e);
            throw new CoreException(e, BaseRequest.FAILED, "公钥无效");
        } catch (IllegalBlockSizeException e) {
            LOGGER.error("块大小超出异常", e);
            throw new CoreException(e, BaseRequest.FAILED, "块大小超出异常");
        } catch (BadPaddingException e) {
            LOGGER.error("加密失败", e);
            throw new CoreException(e, BaseRequest.FAILED, "加密失败");
        } catch (IOException e) {
            LOGGER.error("输出流关闭失败", e);
            throw new CoreException(e, BaseRequest.FAILED, "输出流关闭失败");
        }
    }

    /**
     * <p>使用私钥解密</p>
     *
     * @param encryptedText 加密密文
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/21 15:15
     */
    public static String decrypt(String encryptedText) {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] encryptText = Base64Utils.decodeFromString(encryptedText);
        try {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            int inputLen = encryptText.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offset = 0;
            int index = 0;
            byte[] cache;
            // 对数据分段解密
            while (inputLen - offset > 0) {
                if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptText, offset, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptText, offset, inputLen - offset);
                }
                out.write(cache, 0, cache.length);
                index++;
                offset = index * MAX_DECRYPT_BLOCK;
            }
            byte[] plainText = out.toByteArray();
            out.close();
            return new String(plainText, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOGGER.error("算法{}获取失败", KEY_ALGORITHM, e);
            throw new CoreException(e, BaseRequest.FAILED, "获取算法失败");
        } catch (InvalidKeyException e) {
            LOGGER.error("私钥{}无效", privateKey, e);
            throw new CoreException(e, BaseRequest.FAILED, "私钥无效");
        } catch (IllegalBlockSizeException e) {
            LOGGER.error("块大小超出异常", e);
            throw new CoreException(e, BaseRequest.FAILED, "块大小超出异常");
        } catch (BadPaddingException e) {
            LOGGER.error("加密失败", e);
            throw new CoreException(e, BaseRequest.FAILED, "加密失败");
        } catch (IOException e) {
            LOGGER.error("输出流关闭失败", e);
            throw new CoreException(e, BaseRequest.FAILED, "输出流关闭失败");
        }
    }

    /**
     * <p>生成缓存的key</p>
     *
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/21 15:42
     */
    public static String generateCacheKey() {
        return StringUtil.redisKeyJoin(RedisPool.BASE_REDIS_KEY, RedisPool.CRYPT_KEY, RedisPool.RSA_KEY);
    }

    /**
     * <p>初始化秘钥信息</p>
     *
     * @return java.util.Map<java.lang.String, java.security.Key>
     * @author guocq
     * @date 2022/11/21 15:31
     */
    public static Map<String, String> initKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            Map<String, String> keyMap = Maps.newHashMapWithExpectedSize(2);
            keyMap.put(PUBLIC_KEY, getPublicKeyStr(publicKey));
            keyMap.put(PRIVATE_KEY, getPrivateKeyStr(privateKey));
            return keyMap;
        } catch (Exception e) {
            LOGGER.error("初始化公私钥失败");
            throw new CoreException(e, BaseRequest.FAILED, "初始化公私钥失败");
        }
    }

    /**
     * <p>解码返回字节数组</p>
     *
     * @param keyStr 密钥字符串
     * @return byte[]
     * @author guocq
     * @date 2022/11/21 14:27
     */
    public static byte[] decryptBase64(String keyStr) throws Exception {
        return new BASE64Decoder().decodeBuffer(keyStr);
    }

    /**
     * <p>编码返回字符串</p>
     *
     * @param keyByteArray 密钥字节数组
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/21 14:28
     */
    public static String encryptBase64(byte[] keyByteArray) throws Exception {
        return new BASE64Encoder().encodeBuffer(keyByteArray);
    }
}
