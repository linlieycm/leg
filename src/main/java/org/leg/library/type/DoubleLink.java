package org.leg.library.type;


import org.leg.library.type.core.IDoubleLink;

/**
 * 双向连接类
 */
public class DoubleLink<T extends DoubleLink<T>> implements IDoubleLink<T> {
    /**
     * 前一个元素
     */
    protected T previous;
    /**
     * 下一个元素
     */
    protected T next;


    /**
     * 前一个元素
     *
     * @return 前一个元素
     */
    @Override
    public T previous() {
        return previous;
    }

    /**
     * 前一个元素
     *
     * @param previous
     * @return 前一个元素
     */
    @SuppressWarnings("unchecked")
	@Override
    public void setPrevious(T previous) {
        this.previous = previous;
        if(null != previous) {
            previous.next = (T) this;
        }
    }

    /**
     * 下一个元素
     *
     * @return 下一个元素
     */
    @Override
    public T next() {
        return next;
    }

    /**
     * 下一个元素
     *
     * @param next
     * @return 下一个元素
     */
    @SuppressWarnings("unchecked")
	@Override
    public void setNext(T next) {
        this.next = next;
        if(null != next) {
            next.previous = (T) this;
        }
    }
}
