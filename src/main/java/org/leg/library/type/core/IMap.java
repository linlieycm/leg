package org.leg.library.type.core;

/**
 * 映射接口
 */
public interface IMap<K, V> extends IMapping<K, V>, Cloneable {
    /**
     * 设置指定键的值
     *
     * @param key 键
     * @param value 值
     * @return 键对应的原值，若原值不存在则返回null
     */
    public V put(K key, V value);

    /**
     * 删除指定键的值
     *
     * @param key 键
     * @return 键对应的原值，若原值不存在则返回null
     */
    public V delete(K key);

    /**
     * 清理所有元素
     */
    public void clear();
}
