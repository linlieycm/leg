package org.leg.library.type.core;

/**
 * 有向图接口
 */
public interface IEdgedDigraph<T extends IEdgedDigraph<T, L>, L extends ILink<T, T>> {
    /**
     * 获取连接集合
     *
     * @return 邻居节点集合
     */
    public ISet<L> links();
}
