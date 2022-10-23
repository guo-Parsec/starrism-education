package nom.edu.starrism.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;

import java.util.List;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
public class StringUtil extends StringUtils {
    /**
     * 查找指定字符串是否匹配指定字符串列表中的任意一个字符串
     *
     * @param str 指定字符串
     * @param strList 需要检查的字符串数组
     * @return boolean 是否匹配
     * @author guochengqiang
     */
    public static boolean matches(String str, List<String> strList) {
        if (isEmpty(str) || CollectionUtil.isEmpty(strList)) {
            return false;
        }
        for (String pattern : strList) {
            if (isMatch(pattern, str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断url是否与规则配置:
     * ? 表示单个字符;
     * * 表示一层路径内的任意字符串，不可跨层级;
     * ** 表示任意层路径;
     *
     * @param pattern 匹配规则
     * @param url     需要匹配的url
     * @return 是否匹配
     */
    public static boolean isMatch(String pattern, String url) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, url);
    }
}
