package org.leg.library.type.core;

/**
 * 无向图接口
 */
public interface IGraph<T extends IGraph<T>>{
    /**
     * 获取邻居节点集合
     *
     * @return 邻居节点集合
     */
    public ISet<T> neighbor();
}
