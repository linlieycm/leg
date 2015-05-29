package org.leg.library.type.core;

/**
 * 合集接口
 */
public interface ICollection<I> extends Iterable<I> {
    /**
     * 获取元素的个数
     *
     * @return 元素的个数
     */
    public int size();

    /**
     * 判断是否包含指定元素
     *
     * @param item 指定元素
     * @return 是否包含指定元素
     */
    public boolean contains(I item);
}
