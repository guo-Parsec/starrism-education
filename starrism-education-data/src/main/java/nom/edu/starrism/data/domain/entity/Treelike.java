package nom.edu.starrism.data.domain.entity;

import nom.edu.starrism.common.domain.Domainizable;

import java.util.Collection;

/**
 * <p>树形结构</p>
 *
 * @author guocq
 * @since 2022/11/18
 **/
public interface Treelike<T extends Treelike<T>> extends Domainizable {
    /**
     * <p>获取id</p>
     *
     * @return java.lang.Long
     * @author guocq
     * @date 2022/11/18 14:23
     */
    Long getId();

    /**
     * <p>获上级id</p>
     *
     * @return java.lang.Long
     * @author guocq
     * @date 2022/11/18 14:23
     */
    Long getParentId();

    /**
     * <p>是否根节点</p>
     *
     * @return boolean
     * @author guocq
     * @date 2022/11/18 14:24
     */
    boolean isRoot();

    /**
     * <p>设置是否根节点</p>
     *
     * @param root 是否为根节点
     * @author guocq
     * @date 2022/11/18 14:55
     */
    void setRoot(boolean root);

    /**
     * <p>是否叶子节点</p>
     *
     * @return boolean
     * @author guocq
     * @date 2022/11/18 14:24
     */
    boolean isLeaf();

    /**
     * <p>设置是否叶子节点</p>
     *
     * @param leaf 是否为叶子节点
     * @author guocq
     * @date 2022/11/18 14:55
     */
    void setLeaf(boolean leaf);

    /**
     * <p>设置节点的子节点列表</p>
     *
     * @param children 子节点列表
     * @author guocq
     * @date 2022/11/18 14:26
     */
    void setChildren(Collection<T> children);

    /**
     * <p>获取节点的子节点列表</p>
     *
     * @return 子节点列表
     * @author guocq
     * @date 2022/11/18 14:27
     */
    Collection<? extends Treelike<T>> getChildren();

}
