package org.leg.library.type;

import org.leg.library.type.core.ICollection;
import org.leg.library.type.core.IList;

import java.util.Iterator;

/**
 * 链表类
 *
 * 支持空元素和重复元素
*/
public class List<I> implements IList<I> {
    /**
     * 链表迭代器
     */
    public class ListIterator implements Iterator<I> {
        /**
         * 当前元素的前一个元素
         */
        protected ListItem<I> previousItem = null;
        /**
         * 当前元素
         */
        protected ListItem<I> currentItem = null;
        /**
         * 当前元素的下一个元素
         */
        protected ListItem<I> nextItem = null;


        /**
         * 构造函数
         */
        public ListIterator() {
            nextItem = head;
        }

        /**
         * 获取是否有下一元素
         *
         * @return 是否有下一元素
         */
        @Override
        public boolean hasNext() {
            return (null != nextItem);
        }

        /**
         * 获取下一元素
         *
         * @return 下一元素对象
         */
        @Override
        public I next() {
            previousItem = currentItem;
            currentItem = nextItem;
            nextItem = nextItem.getDestination();
            return currentItem.getOrigin();
        }

        /**
         * 删除元素
         */
        @Override
        public void remove() {
            if(null == previousItem) {
                head = nextItem;
            }
            else {
                previousItem.setDestination(nextItem);
            }
            currentItem = previousItem;
        }
    }


    /**
     * 头部元素
     */
    protected ListItem<I> head = null;


    /**
     * 构造函数
     */
    public List() { }

    /**
     * 构造函数
     *
     * @param item 初始元素
     */
    public List(I item) {
        this.add(item);
    }

    /**
     * 获取元素
     *
     * @param index 指定位置;
     * @return 成功返回元素对象，失败返回null
     */
    @Override
    public I get(Integer index) {
        return getListItem(index).getOrigin();
    }

    /**
     * 设置指定位置的元素
     *
     * @param index 指定位置;
     * @param item  待设置的元素对象
     */
    @Override
    public I set(int index, I item) {
        ListItem<I> originItem = getListItem(index);
        I result = originItem.getOrigin();
        originItem.setOrigin(item);
        return result;
    }

    /**
     * 指定位置插入元素
     *
     * @param index 插入位置
     * @param item  待插入元素对象
     */
    @Override
    public boolean insert(int index, I item) {
        if(0 == index) {
            head = new ListItem<I>(item, head);
            return true;
        }
        ListItem<I> prevItem = getListItem(index - 1);
        prevItem.setDestination(new ListItem<I>(item, prevItem.getDestination()));
        return true;
    }

    /**
     * 删除指定位置的元素
     *
     * @param index 待删除的元素位置
     * @return 操作成功返回元素对象
     */
    @Override
    public I delete(int index) {
        if(null == head) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        ListItem<I> result = head;
        if(0 == index) {
            head = head.getDestination();
            return result.getOrigin();
        }
        ListItem<I> prevItem = getListItem(index - 1);
        result = prevItem.getDestination();
        if(null == result) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        prevItem.setDestination(result.getDestination());
        return result.getOrigin();
    }

    /**
     * 增加元素
     *
     * @param item 待添加的元素引用
     * @return 操作执行结果
     */
    @Override
    public boolean add(I item) {
        if(null == head) {
            head = new ListItem<I>(item, null);
            return true;
        }
        getTailItem().setDestination(new ListItem<I>(item, null));
        return true;
    }

    /**
     * 增加元素
     *
     * @param items 待添加的元素引用合集
     * @return 操作执行结果
     */
    @Override
    public boolean add(ICollection<I> items) {
        ListItem<I> tailItem = getTailItem();
        for(I item : items) {
            if(null == tailItem) {
                tailItem = (head = new ListItem<I>(item, null));
            }
            else {
                tailItem.setDestination(new ListItem<I>(item, null));
                tailItem = tailItem.getDestination();
            }
        }
        return true;
    }

    /**
     * 删除元素
     *
     * @param item 待删除的元素引用
     * @return 操作执行结果
     */
    @Override
    public boolean remove(I item) {
        for(Iterator<I> iterator = this.iterator(); iterator.hasNext();) {
            if(null == item) {
                if(null == iterator.next()) {
                    iterator.remove();
                }
            }
            else {
                I curItem = iterator.next();
                if (item == curItem || item.equals(curItem)) {
                    iterator.remove();
                }
            }
        }
        return false;
    }

    /**
     * 删除元素
     *
     * @param items 待删除的元素引用合集
     * @return 操作执行结果
     */
    @Override
    public boolean remove(ICollection<I> items) {
        if(null == items) {
            throw new IllegalArgumentException();
        }
        for(Iterator<I> iterator = this.iterator(); iterator.hasNext();) {
            I curItem = iterator.next();
            if(null != curItem && items.contains(curItem)) {
                iterator.remove();
            }
        }
        return true;
    }

    /**
     * 获取元素的个数
     *
     * @return 元素的个数
     */
    @Override
    public int size() {
        if(null == head) {
            return 0;
        }
        ListItem<I> link = head;
        int result = 1;
        while(true) {
            if(null == link.getDestination()) {
                return result;
            }
            link = link.getDestination();
            result++;
        }
    }

    /**
     * 判断是否包含指定元素
     *
     * @param item 指定元素
     * @return 是否包含指定元素
     */
    @Override
    public boolean contains(I item) {
        for(I eachItem : this) {
            if(null == item) {
                if(null == eachItem) {
                    return true;
                }
                continue;
            }
            if(eachItem == item || item.equals(eachItem)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 清理所有元素
     */
    @Override
    public void clear() {
        head = null;
    }

    /**
     * Returns an iterator over a set of elements of compareType T.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<I> iterator() {
        return new ListIterator();
    }

    /**
     * 获取元素
     *
     * @param index 指定位置;
     * @return 成功返回列表元素对象，失败返回null
     */
    protected ListItem<I> getListItem(int index) {
        if(index < 0 || null == head) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int i = 0;
        ListItem<I> link = head;
        while(true) {
            if(index == i) {
                return link;
            }
            link = link.getDestination();
            if(null == link) {
                break;
            }
            i++;
        }
        throw new ArrayIndexOutOfBoundsException(index);
    }

    /**
     * 获取尾部元素
     *
     * @return 成功返回列表尾部元素对象，列表为空返回null
     */
    protected ListItem<I> getTailItem() {
        ListItem<I> link = head;
        if(null == link) {
            return null;
        }
        while(true) {
            if(null == link.getDestination()) {
                return link;
            }
            link = link.getDestination();
        }
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
