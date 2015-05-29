package org.leg.library.type;

import org.leg.library.type.core.ILink;
import org.leg.library.type.core.ITable;

import java.util.Iterator;

/**
 * Map类
 */
public class Table<K, V> extends Map<K, V> implements ITable<K, V> {
    /**
     * 映射迭代器
     */
    public class TableIterator implements Iterator<ILink<K, V>> {
        /**
         * 迭代器
         */
        protected Iterator<java.util.Map.Entry<K, V>> iterator = null;


        /**
         * 构造函数
         *
         * @param iterator 迭代器
         */
        public TableIterator(Iterator<java.util.Map.Entry<K, V>> iterator) {
            this.iterator = iterator;
        }

        /**
         * 获取是否有下一元素
         *
         * @return 是否有下一元素
         */
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        /**
         * 获取下一元素
         *
         * @return 下一元素对象
         */
        @Override
        public ILink<K, V> next() {
            java.util.Map.Entry<K, V> entry = iterator.next();
            return new Link<K, V>(entry.getKey(), entry.getValue());
        }

        /**
         * 删除元素
         */
        @Override
        public void remove() {
            iterator.remove();
        }
    }


    /**
     * 获取元素的个数
     *
     * @return 元素的个数
     */
    @Override
    public int size() {
        return map.size();
    }

    /**
     * 判断是否包含指定元素
     *
     * @param item 指定元素
     * @return 是否包含指定元素
     */
    @Override
    public boolean contains(ILink<K, V> item) {
        return map.containsKey(item.getOrigin());
    }

    /**
     * Returns an iterator over a set of elements of compareType T.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<ILink<K, V>> iterator() {
        return new TableIterator(map.entrySet().iterator());
    }
}
