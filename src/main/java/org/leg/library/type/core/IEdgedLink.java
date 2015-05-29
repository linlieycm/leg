package org.leg.library.type.core;

/**
 * 有边连接接口
 *
 * T 目标模板，E 边模板
 */
public interface IEdgedLink<E, T extends IEdgedLink<E, T>> {
    /**
     * 获取下一个元素
     */
    public IPointer<E, T> next();

    /**
     * 设置下一个元素
     *
     * @param next 下一个元素
     */
    public void setNext(IPointer<E, T> next);
}
