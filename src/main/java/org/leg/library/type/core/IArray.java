package org.leg.library.type.core;

/**
 * 数组接口
 */
public interface IArray<I> extends ICollection<I>, IMapping<Integer, I>, Cloneable {
    /**
     * 设置指定位置的元素
     *
     * @param index 指定位置;
     * @param item 待设置的元素对象
     * @return 原值
     */
    public I set(int index, I item);
}
