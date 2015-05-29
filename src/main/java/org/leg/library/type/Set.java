package org.leg.library.type;

import org.leg.library.type.core.ICollection;
import org.leg.library.type.core.ISet;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 集合类
 */
public class Set<I> implements ISet<I> {
    /**
     * 集合
     */
    protected java.util.Set<I> set = null;


    /**
     * 构造函数
     */
    public Set() {
        onNew();
    }
    public Set(I item) {
        onNew();
        set.add(item);
    }

    /**
     * 增加元素
     *
     * @param item 待添加的元素引用
     * @return 操作执行结果
     */
    @Override
    public boolean add(I item) {
        return set.add(item);
    }

    /**
     * 增加元素
     *
     * @param items 待添加的元素引用合集
     * @return 操作执行结果
     */
    @Override
    public boolean add(ICollection<I> items) {
        boolean result = true;
        for(I item : items) {
            result = result & set.add(item);
        }
        return result;
    }

    /**
     * 删除元素
     *
     * @param item 待删除的元素引用
     * @return 操作执行结果
     */
    @Override
    public boolean remove(I item) {
        return set.remove(item);
    }

    /**
     * 删除元素
     *
     * @param items 待删除的元素引用合集
     * @return 操作执行结果
     */
    @Override
    public boolean remove(ICollection<I> items) {
        boolean result = true;
        for(I item : items) {
            result = result & set.remove(item);
        }
        return result;
    }

    /**
     * 获取元素的个数
     *
     * @return 元素的个数
     */
    @Override
    public int size() {
        return set.size();
    }

    /**
     * 判断是否包含指定元素
     *
     * @param item 指定元素
     * @return 是否包含指定元素
     */
    @Override
    public boolean contains(I item) {
        return set.contains(item);
    }

    /**
     * 清理所有元素
     */
    @Override
    public void clear() {
        set.clear();
    }

    /**
     * Returns an iterator over a set of elements of compareType T.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<I> iterator() {
        return set.iterator();
    }

    /**
     * 转为字符串
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        boolean sentry = true;
        for (I jsonObject : this) {
            if (sentry) {
                sentry = false;
            }
            else {
                builder.append(",");
            }
            builder.append(jsonObject);
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * 初始化
     */
    public void onNew() {
        set = new HashSet<I>();
    }
}
