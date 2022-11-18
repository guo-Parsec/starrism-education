package nom.edu.starrism.core.type;

import lombok.Getter;

/**
 * <p></p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
public enum PermissionCategory {
    /**
     * 匿名认证
     */
    anonymous(1, "anonymous", "匿名认证"),

    /**
     * 匿名认证
     */
    authenticated_common(2, "authenticated_common", "登录认证"),

    /**
     * 匿名认证
     */
    authenticated_permission(3, "authenticated_permission", "权限认证"),

    ;

    @Getter
    private final Long key;
    @Getter
    private final String code;
    @Getter
    private final String name;

    PermissionCategory(Long key, String code, String name) {
        this.key = key;
        this.code = code;
        this.name = name;
    }

    PermissionCategory(Integer key, String code, String name) {
        this.key = Long.valueOf(key);
        this.code = code;
        this.name = name;
    }
}
