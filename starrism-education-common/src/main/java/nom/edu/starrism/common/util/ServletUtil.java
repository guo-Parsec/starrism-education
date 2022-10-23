package nom.edu.starrism.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
public class ServletUtil {
    /**
     * 内容编码
     *
     * @param str 内容
     * @return 编码后的内容
     */
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return StringUtil.EMPTY;
        }
    }
}
