package org.leg.library.type;

/**
 * 列表元素类
 */
public class ListItem<I> extends Link<I, ListItem<I>> {
    /**
     * 构造函数
     */
    public ListItem() {
        super();
    }
    public ListItem(I item, ListItem<I> listItem) {
        super(item, listItem);
    }
}
