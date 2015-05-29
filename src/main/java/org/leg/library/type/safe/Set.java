package org.leg.library.type.safe;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 安全集合
 */
public class Set<I> extends org.leg.library.type.Set<I> {
    /**
     * 构造函数
     */
    public Set() {
        set = new ConcurrentSkipListSet<I>();
    }
    public Set(I item) {
        set = new ConcurrentSkipListSet<I>();
        set.add(item);
    }
}
