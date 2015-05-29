package org.leg.library.type;

import org.leg.library.type.core.IPointer;

/**
 * 指向类
 */
public class Pointer<E, T> implements IPointer<E, T> {
    /**
     * 边
     */
    private E edge;
    /**
     * 目标
     */
    private T target;


    /**
     * 获取边
     *
     * @return 边
     */
    @Override
    public E edge() {
        return edge;
    }

    /**
     * 设置边
     *
     * @param edge 边
     */
    @Override
    public void setEdge(E edge) {
        this.edge = edge;
    }

    /**
     * 获取目标
     *
     * @return 目标
     */
    @Override
    public T target() {
        return target;
    }

    /**
     * 设置目标
     *
     * @param target 目标
     */
    @Override
    public void setTarget(T target) {
        this.target = target;
    }
}
