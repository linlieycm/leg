package org.leg.library.type;

import org.leg.library.type.core.IArray;

import java.util.Iterator;

/**
 * 数组类
 */
public class Array<I> implements IArray<I> {
    /**
     * 数组迭代器
     */
    public class ArrayIterator implements Iterator<I> {
        /**
         * 当前索引
         */
        protected int currentIndex = 0;


        /**
         * 获取是否有下一元素
         *
         * @return 是否有下一元素
         */
        @Override
        public boolean hasNext() {
            return (currentIndex < array.length);
        }

        /**
         * 获取下一元素
         *
         * @return 下一元素对象
         */
        @SuppressWarnings("unchecked")
		@Override
        public I next() {
            return (I)array[currentIndex++];
        }

        /**
         * 删除元素
         */
        @Override
        public void remove() {
            array[currentIndex - 1] = null;
        }
    }

    /**
     * 数组
     */
    protected Object array[];


    /**
     * 构造函数
     *
     * @param size 尺寸
     */
    public Array(int size) {
        array = new Object[size];
    }

    /**
     * 获取元素
     *
     * @param index 指定位置;
     * @return 成功返回元素对象，失败返回null
     */
    @SuppressWarnings("unchecked")
	@Override
    public I get(Integer index) {
        return (I)array[index];
    }

    /**
     * 设置指定位置的元素
     *
     * @param index 指定位置;
     * @param item  待设置的元素对象
     * @return 原值
     */
    @SuppressWarnings("unchecked")
	@Override
    public I set(int index, I item) {
        I result = (I)array[index];
        array[index] = item;
        return result;
    }

    /**
     * 获取元素的个数
     *
     * @return 元素的个数
     */
    @Override
    public int size() {
        return array.length;
    }

    /**
     * 判断是否包含指定元素
     *
     * @param item 指定元素
     * @return 是否包含指定元素
     */
    @Override
    public boolean contains(I item) {
        for(Object eachItem : array) {
            if(null == item) {
                if(null == eachItem) {
                    return true;
                }
            }
            else {
                if(item == eachItem || item.equals(eachItem)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns an iterator over a set of elements of compareType T.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<I> iterator() {
        return new ArrayIterator();
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
}
