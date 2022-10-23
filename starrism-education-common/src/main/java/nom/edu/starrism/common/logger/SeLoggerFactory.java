package nom.edu.starrism.common.logger;

/**
 * <p>>自定义日志生成工厂类</</p>
 *
 * @author hedwing
 * @since 2022/10/21
 **/
public class SeLoggerFactory {
    public static SeLogger getLogger(Class<?> clz) {
        return new SeLogger(clz);
    }

    public static SeLogger getLogger(String name) {
        return new SeLogger(name);
    }
}
