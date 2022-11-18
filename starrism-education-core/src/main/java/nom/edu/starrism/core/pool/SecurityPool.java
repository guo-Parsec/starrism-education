package nom.edu.starrism.core.pool;

/**
 * <p>安全认证常量池</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
public interface SecurityPool {
    /**
     * AUTHORIZATION
     */
    String AUTHORIZATION = "Authorization";

    /**
     * SECURITY
     */
    String SECURITY = "security";

    /**
     * TOKEN
     */
    String TOKEN = "token";

    /**
     * USER
     */
    String USER = "user";

    /**
     * 菜单
     */
    String MENUS = "menus";

    /**
     * URLS
     */
    String URLS = "urls";

    /**
     * JWT令牌前缀
     */
    String Bearer = "Bearer ";
}
