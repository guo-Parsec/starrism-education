package nom.edu.starrism.common.util;

import nom.edu.starrism.common.enums.BaseRequest;
import nom.edu.starrism.common.exception.CoreException;

import java.security.MessageDigest;

/**
 * <p>Md5工具类</p>
 *
 * @author guocq
 * @since 2022/10/24
 **/
public class Md5Util {
    /**
     * <p>md5加密</p>
     *
     * @param plainText 指定字符串
     * @return 加密后的字符串
     * @author guocq
     * @date 2022/10/24 14:19
     */
    public static String md5(String plainText) {
        plainText = plainText == null ? StringUtil.EMPTY : plainText;
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = plainText.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] strA = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                strA[k++] = hexDigits[byte0 >>> 4 & 0xf];
                strA[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(strA);
        } catch (Exception e) {
            throw new CoreException(BaseRequest.FAILED, "MD5加密失败");
        }
    }

    /**
     * <p>md5加盐加密: md5(md5(str) + md5(salt)) </p>
     *
     * @param plainText 字符串
     * @param salt      盐
     * @return 加密后的字符串
     * @author guocq
     * @date 2022/10/24 14:20
     */
    public static String md5BySalt(String plainText, String salt) {
        return md5(md5(plainText) + md5(salt));
    }
}
