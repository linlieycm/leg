package org.leg.library.type.core;

/**
 * 多连接树接口
 */
public interface IMultiTree<L, T extends ITree<T>, C extends ICollection<T>> extends ITree<T> {
    /**
     * 获取指定连接的子节点集合
     *
     * @param link 父子连接对象
     * @return 子节点集合
     */
    public C children(L link);
}
