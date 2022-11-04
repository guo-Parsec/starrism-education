package nom.edu.starrism.data.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>数据展示层</p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
@Getter
@Setter
public abstract class AbstractDataVo extends AbstractVo {
    private static final long serialVersionUID = 3124038320847724517L;

    @ApiModelProperty(value = "数据状态")
    protected Integer dataStatus;

    @ApiModelProperty(value = "数据状态字典显示名称")
    protected String dataStatusName;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected LocalDateTime gmtModify;

    /**
     * <p>数据状态字典显示名称getter方法</p>
     *
     * @return java.lang.String
     * @author hedwing
     * @since 2022/10/23
     */
    public abstract String getDataStatusName();

    @Override
    public AbstractDataVo clone() {
        return (AbstractDataVo) super.clone();
    }
}
