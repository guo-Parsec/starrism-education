package nom.edu.starrism.core.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.data.domain.vo.AbstractVo;

import java.util.Set;

/**
 * <p>认证通过的用户</p>
 *
 * @author hedwing
 * @since 2022/11/6
 **/
@Setter
@Getter
@ApiModel(value = "认证通过的用户")
public class AuthenticatedUser extends AbstractVo {
    private static final long serialVersionUID = -5158261219041690402L;

    @ApiModelProperty(value = "核心用户")
    private SeUser userEntity;

    @ApiModelProperty(value = "用户所拥有的角色")
    private Set<String> roles;

    @ApiModelProperty(value = "用户所拥有的权限")
    private Set<String> permissions;

    @ApiModelProperty(value = "令牌名称")
    private String tokenName;

    @ApiModelProperty(value = "令牌内容")
    private String tokenContent;

    /**
     * <p>判断是否为空</p>
     *
     * @param user user
     * @return boolean
     * @author hedwing
     * @since 2022/11/7
     */
    public static boolean isEmpty(AuthenticatedUser user) {
        return user == null || SeUser.isEmpty(user.getUserEntity());
    }

    /**
     * <p>判断是否不为空</p>
     *
     * @param user user
     * @return boolean
     * @author hedwing
     * @since 2022/11/7
     */
    public static boolean isNotEmpty(AuthenticatedUser user) {
        return !isEmpty(user);
    }
}