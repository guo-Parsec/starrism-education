package nom.edu.starrism.common.util;

import java.util.UUID;

/**
 * <p>uuid生成器</p>
 *
 * @author hedwing
 * @since 2022/11/6
 **/
public class UUIDGeneratorUtil {

    /**
     * <p>原生uuid生成 - 携带连接符</p>
     *
     * @return java.lang.String
     * @author hedwing
     * @since 2022/11/6
     */
    public static String uuidRaw() {
        return UUID.randomUUID().toString();
    }

    /**
     * <p>原生uuid生成 - 不携带连接符</p>
     *
     * @return java.lang.String
     * @author hedwing
     * @since 2022/11/6
     */
    public static String uuid() {
        return uuidRaw().replace(StringUtil.CONNECTOR, StringUtil.EMPTY);
    }

}
