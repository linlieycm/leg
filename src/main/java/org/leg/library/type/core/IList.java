package org.leg.library.type.core;

/**
 * 列表接口
 */
public interface IList<I> extends ISet<I>, IArray<I> {
    /**
     * 指定位置插入元素
     *
     * @param index 插入位置
     * @param item 待插入元素对象
     * @return 操作执行结果
     */
    public boolean insert(int index, I item);

    /**
     * 删除指定位置的元素
     *
     * @param index 待删除的元素位置
     * @return 操作成功返回元素对象，失败返回null
     */
    public I delete(int index);
}
