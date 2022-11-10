package nom.edu.starrism.common.util;

/**
 * <p>路径相关工具类</p>
 *
 * @author guocq
 * @since 2022/11/9
 **/
public class PathUtil {
    public static String REQ_PATH_SEPARATOR = "/";

    /**
     * <p>自动填充请求根路径</p>
     *
     * @param orgPath 原始路径
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/9 19:28
     */
    public static String autoPopulateRequestRootPath(String orgPath) {
        return orgPath.startsWith(REQ_PATH_SEPARATOR) ? orgPath : REQ_PATH_SEPARATOR + orgPath;
    }
}
