package org.leg.library.type.core;

/**
 * 带边双向连接接口
 */
public interface IEdgedDoubleLink<E, T extends IEdgedDoubleLink<E, T>> extends IDoubleLink<T> {
    /**
     * 获取边对象
     *
     * @return 边对象
     */
    public E edge();

    /**
     * 设置边对象
     *
     * @param edge 边对象
     */
    public void setEdge(E edge);
}
