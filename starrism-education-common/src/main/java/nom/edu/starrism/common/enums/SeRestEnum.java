package nom.edu.starrism.common.enums;

/**
 * <p>通用请求状态枚举</p>
 *
 * @author hedwing
 * @since 2022/10/21
 **/
public interface SeRestEnum extends SeEnum<Long, String> {
    /**
     * <p>获取提示信息</p>
     *
     * @return java.lang.String
     * @author hedwing
     * @since 2022/8/13
     */
    String getMessage();

    /**
     * <p>获取值</p>
     *
     * @return C
     * @author hedwing
     * @since 2022/8/13
     */
    default String getValue() {
        return getMessage();
    }
}
