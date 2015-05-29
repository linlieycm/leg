package org.leg.library.type;

import org.leg.library.type.core.IEdgedDoubleLink;

/**
 * 带边双向连接
 */
public class EdgedDoubleLink<E, T extends EdgedDoubleLink<E, T>> extends DoubleLink<T> implements IEdgedDoubleLink<E, T> {
    /**
     * 边对象
     */
    public E edge;


    /**
     * 获取边对象
     *
     * @return 边对象
     */
    @Override
    public E edge() {
        return edge;
    }

    /**
     * 设置边对象
     *
     * @param edge 边对象
     */
    @Override
    public void setEdge(E edge) {
        this.edge = edge;
    }
}
