package org.leg.library.type;

import org.leg.library.type.core.IEdgedLink;
import org.leg.library.type.core.IPointer;

/**
 * 有边连接类
 */
public class EdgedLink<E, T extends IEdgedLink<E, T>> implements IEdgedLink<E, T> {
    /**
     * 下一个元素
     */
    private IPointer<E, T> next;


    /**
     * 获取下一个元素
     */
    @Override
    public IPointer<E, T> next() {
        return next;
    }

    /**
     * 设置下一个元素
     *
     * @param next 下一个元素
     */
    @Override
    public void setNext(IPointer<E, T> next) {
        this.next = next;
    }
}
