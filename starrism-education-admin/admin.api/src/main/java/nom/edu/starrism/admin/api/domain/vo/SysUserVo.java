package nom.edu.starrism.admin.api.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.core.context.DictContext;
import nom.edu.starrism.core.pool.DictPool;
import nom.edu.starrism.data.domain.vo.AbstractDataVo;

import java.time.LocalDate;

/**
 * <p>系统用户数据展示层</p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
@Setter
@Getter
@ApiModel(value = "系统用户数据展示层")
public class SysUserVo extends AbstractDataVo {
    private static final long serialVersionUID = 3398797726461590531L;

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

    @ApiModelProperty(value = "数据状态字典显示名称")
    private String dataStatusName;

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
}
