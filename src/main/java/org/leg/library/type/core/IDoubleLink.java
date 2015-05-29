package org.leg.library.type.core;

/**
 * 双向连接接口
 */
public interface IDoubleLink<T extends IDoubleLink<T>> {
    /**
     * 前一个元素
     *
     * @return 前一个元素
     */
    public T previous();

    /**
     * 前一个元素
     *
     * @return 前一个元素
     */
    public void setPrevious(T previous);

    /**
     * 下一个元素
     *
     * @return 下一个元素
     */
    public T next();

    /**
     * 下一个元素
     *
     * @return 下一个元素
     */
    public void setNext(T next);
}
