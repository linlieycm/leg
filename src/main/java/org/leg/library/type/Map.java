package org.leg.library.type;

import org.leg.library.type.core.IMap;

/**
 * 映射类
 */
public class Map<K, V> implements IMap<K, V> {
    /**
     * 映射
     */
    protected java.util.Map<K, V> map = null;


    /**
     * 构造函数
     */
    public Map() {
        onNew();
    }

    /**
     * 获取指定键的值
     *
     * @param key 键
     * @return 键对应的值，若指定键不存在则返回null
     */
    @Override
    public V get(K key) {
        return map.get(key);
    }

    /**
     * 设置指定键的值
     *
     * @param key   键
     * @param value 值
     * @return 键对应的原值，若原值不存在则返回null
     */
    @Override
    public V put(K key, V value) {
        if(null == value) {
            return delete(key);
        }
        return map.put(key, value);
    }

    /**
     * 删除指定键的值
     *
     * @param key 键
     * @return 键对应的原值，若原值不存在则返回null
     */
    @Override
    public V delete(K key) {
        return map.remove(key);
    }

    /**
     * 清理所有元素
     */
    @Override
    public void clear() {
        map.clear();
    }

    /**
     * 初始化
     */
    public void onNew() {
        map = new java.util.HashMap<K, V>();
    }


    /**
     * 深度克隆
     *
     * @return 克隆对象
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Map<K, V> result = new Map<K, V>();
        for(java.util.Map.Entry<K, V> entry : map.entrySet()) {
            if(null == entry.getValue()) {
                result.put(entry.getKey(), entry.getValue());
            }
            else {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }
}
