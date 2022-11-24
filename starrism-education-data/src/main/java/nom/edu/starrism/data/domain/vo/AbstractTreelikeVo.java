package nom.edu.starrism.data.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.data.domain.entity.Treelike;

/**
 * <p>树形结构抽象实体</p>
 *
 * @author guocq
 * @since 2022/11/18
 **/
@Setter
@Getter
public abstract class AbstractTreelikeVo<T extends AbstractTreelikeVo<T>> extends AbstractVo implements Treelike<T> {
    private static final long serialVersionUID = -2111650017270415208L;

    /**
     * 父级元素id
     */
    @ApiModelProperty(value = "父级元素id")
    protected Long parentId;

    /**
     * 是否为根元素
     */
    @ApiModelProperty(value = "是否为根元素")
    protected boolean root;

    /**
     * 是否为叶子节点
     */
    @ApiModelProperty(value = "是否为叶子节点")
    protected boolean leaf;
}
