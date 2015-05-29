package org.leg.library.type.core;

/**
 * 树接口
 */
public interface ITree<T extends ITree<T>> extends IGraph<T> {
    /**
     * 获取父节点
     *
     * @return 父节点对象
     */
    public T parent();

    /**
     * 设置父节点
     *
     * @param parent 待设置为父节点的节点对象
     * @return 原父节点，若原父节点不存在则返回null
     */
    public T setParent(T parent);

    /**
     * 获取子节点集合
     *
     * @return 子节点集合
     */
    public ISet<T> children();
}
