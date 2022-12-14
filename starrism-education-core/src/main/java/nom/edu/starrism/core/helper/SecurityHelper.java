package nom.edu.starrism.core.helper;

import nom.edu.starrism.common.helper.TextHelper;
import nom.edu.starrism.common.pool.RedisPool;
import nom.edu.starrism.common.service.RedisService;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.data.pool.SecurityPool;
import nom.edu.starrism.data.component.SpringBean;

/**
 * <p>安全认证辅助类</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
public class SecurityHelper {
    /**
     * <p>生成全部在线的token存储的key</p>
     *
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/17 15:44
     */
    public static String generateOnlineSecurityToken() {
        return generateSecurityTokenKey(RedisPool.ALL_MATCHER);
    }

    /**
     * <p>根据tokenId生成token存储的key</p>
     * <p>e.g. se:security:token:{token}</p>
     *
     * @param tokenId 生成的唯一的uuid
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/17 14:05
     */
    public static String generateSecurityTokenKey(String tokenId) {
        String tokenKeyTemplate = StringUtil.redisKeyJoin(RedisPool.BASE_REDIS_KEY, SecurityPool.SECURITY,
                SecurityPool.TOKEN, TextHelper.EMPTY_VAR_PLACEHOLDER);
        return TextHelper.build().format(tokenKeyTemplate, tokenId);
    }

    /**
     * <p>根据用户id生成指定userId的用户认证通过的用户信息存储的key</p>
     * <p>e.g. se:security:user:{userId}</p>
     *
     * @param userId 用户id
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/17 14:19
     */
    public static String generateSecurityUserKey(Long userId) {
        String tokenKeyTemplate = StringUtil.redisKeyJoin(RedisPool.BASE_REDIS_KEY, SecurityPool.SECURITY,
                SecurityPool.USER, TextHelper.EMPTY_VAR_PLACEHOLDER);
        return TextHelper.build().format(tokenKeyTemplate, userId);
    }

    /**
     * <p>根据用户id生成指定userId的用户拥有的菜单存储的key</p>
     * <p>e.g. se:security:user:{userId}:menus</p>
     *
     * @param userId 用户id
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/17 14:19
     */
    public static String generateMenusOfUser(Long userId) {
        return StringUtil.redisKeyJoin(generateSecurityUserKey(userId), SecurityPool.MENUS);
    }

    /**
     * <p>从字符串中获取有效的令牌id</p>
     *
     * @param text 字符串
     * @return 有效的令牌id
     */
    public static String findEffectiveTokenId(String text) {
        if (StringUtil.isBlank(text) || !text.startsWith(SecurityPool.BEARER)) {
            return StringUtil.EMPTY;
        }
        return text.replace(SecurityPool.BEARER, StringUtil.EMPTY);
    }

    /**
     * <p>获取redisService</p>
     *
     * @return {@link RedisService}
     * @author guocq
     * @date 2022/11/17 14:37
     */
    public static RedisService redisService() {
        return SpringBean.getBean(RedisService.class);
    }
}
