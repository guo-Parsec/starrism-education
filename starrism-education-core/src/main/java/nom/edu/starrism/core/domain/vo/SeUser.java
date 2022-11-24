package nom.edu.starrism.core.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.core.context.DictContext;
import nom.edu.starrism.core.pool.DictPool;
import nom.edu.starrism.data.domain.vo.AbstractDataVo;
import nom.edu.starrism.data.pool.DataPool;

import java.time.LocalDate;

/**
 * <p>登录用户凭证</p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
@Setter
@Getter
public class SeUser extends AbstractDataVo {
    private static final long serialVersionUID = 6068601517012372513L;

    @ApiModelProperty(value = "账户信息")
    private String account;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "性别字典显示名称")
    private String sexName;

    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;

    @ApiModelProperty(value = "出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthday;

    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    @ApiModelProperty(value = "用户类型显示名称")
    private String userTypeName;

    @ApiModelProperty(value = "数据状态字典值")
    private Integer dataStatus;

    @ApiModelProperty(value = "数据状态字典显示名称")
    private String dataStatusName;
    @ApiModelProperty(value = "用户认证是否成功", hidden = true)
    private boolean authenticated;

    public String getSexName() {
        return DictContext.convert(DictPool.DICT_CATEGORY_SEX, this.sex);
    }

    public String getUserTypeName() {
        return DictContext.convert(DictPool.DICT_CATEGORY_USER_TYPE, this.sex);
    }

    /**
     * <p>数据状态字典显示名称getter方法</p>
     *
     * @return java.lang.String
     * @author hedwing
     * @since 2022/10/23
     */
    public String getDataStatusName() {
        return DictContext.convert(DictPool.DICT_CATEGORY_DATA_STATUS, this.dataStatus);
    }

    /**
     * <p>获取空用户对象</p>
     *
     * @return {@link SeUser}
     * @author hedwing
     * @since 2022/10/23
     */
    public static SeUser empty() {
        SeUser seUser = new SeUser();
        seUser.dataStatus = DataPool.DISABLE;
        seUser.authenticated = false;
        return seUser;
    }
}
