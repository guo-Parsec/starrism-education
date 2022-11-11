package nom.edu.starrism.admin.api.domain.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.data.domain.param.AbstractPageParam;

/**
 * <p>角色分页查询参数</p>
 *
 * @author guocq
 * @since 2022/11/11
 **/
@Setter
@Getter
public class SysRolePageParam extends AbstractPageParam {
    private static final long serialVersionUID = 1818929592110956313L;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    private String roleName;
}
