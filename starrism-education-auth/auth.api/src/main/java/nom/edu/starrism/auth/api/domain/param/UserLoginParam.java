package nom.edu.starrism.auth.api.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.data.domain.param.AbstractParam;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * <p>用户登录参数</p>
 *
 * @author hedwing
 * @since 2022/11/6
 **/
@Setter
@Getter
@ApiModel(value = "用户登录参数")
public class UserLoginParam extends AbstractParam {
    private static final long serialVersionUID = -4059630880787609201L;
    @ApiModelProperty(value = "用户登录名")
    @NotEmpty(message = "用户登录名不能为空")
    private String account;

    @ApiModelProperty(value = "用户登录密码")
    @NotEmpty(message = "用户登录密码不能为空")
    @Length(min = 6, max = 18, message = "登录密码长度必须位于6到18之间")
    private String password;

    @ApiModelProperty(value = "登录验证码")
    private String verificationCode;
}
