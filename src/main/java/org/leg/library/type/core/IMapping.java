package org.leg.library.type.core;

/**
 * 映射接口
 */
public interface IMapping<K, V> {
    /**
     * 获取指定参数对应值
     *
     * @param key 参数
     * @return 值，若值不存在则返回null
     */
    public V get(K key);
}
