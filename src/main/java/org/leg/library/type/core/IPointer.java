package org.leg.library.type.core;

/**
 * 指向接口
 *
 * E 边模板，T 目标模板
 */
public interface IPointer<E, T> {
    /**
     * 获取边
     *
     * @return 边
     */
    public E edge();

    /**
     * 设置边
     *
     * @param edge 边
     */
    public void setEdge(E edge);

    /**
     * 获取目标
     *
     * @return 目标
     */
    public T target();

    /**
     * 设置目标
     *
     * @param target 目标
     */
    public void setTarget(T target);
}
