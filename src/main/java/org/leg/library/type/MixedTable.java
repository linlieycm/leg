package org.leg.library.type;

import org.leg.library.type.core.IMixedMapping;

import java.math.BigDecimal;

/**
 * 对象表
 */
public class MixedTable<K, V> extends Table<K, V> implements IMixedMapping<K, V> {
    /**
     * 获取布尔字段值
     *
     * @param key 字段名
     * @return 布尔值
     */
    @Override
    public Boolean getBoolean(K key) {
        return (Boolean) get(key);
    }

    /**
     * 获取布尔字段值
     *
     * @param key 字段名
     * @param defaultValue 默认值
     * @return 布尔值
     */
    @Override
    public boolean getBoolean(K key, boolean defaultValue) {
        Boolean result = getBoolean(key);
        if(null == result) {
            return defaultValue;
        }
        return result;
    }

    /**
     * 获取字符字段值
     *
     * @param key 字段名
     * @return 字符值
     */
    @Override
    public Character getCharacter(K key) {
        return (Character) get(key);
    }

    /**
     * 获取字符字段值
     *
     * @param key 字段名
     * @param defaultValue 默认值
     * @return 字符值
     */
    @Override
    public char getCharacter(K key, char defaultValue) {
        Character result = getCharacter(key);
        if(null == result) {
            return defaultValue;
        }
        return result;
    }

    /**
     * 获取字节字段值
     *
     * @param key 字段名
     * @return 字节值
     */
    @Override
    public Byte getByte(K key) {
        Object result = get(key);
        if(null == result) {
            return null;
        }
        if(result instanceof BigDecimal) {
            return ((BigDecimal)result).byteValue();
        }
        return (Byte) result;
    }

    /**
     * 获取字节字段值
     *
     * @param key 字段名
     * @param defaultValue 默认值
     * @return 字节值
     */
    @Override
    public byte getByte(K key, byte defaultValue) {
        Byte result = getByte(key);
        if(null == result) {
            return defaultValue;
        }
        return result;
    }

    /**
     * 获取短整型字段值
     *
     * @param key 字段名
     * @return 短整型值
     */
    @Override
    public Short getShort(K key) {
        Object result = get(key);
        if(null == result) {
            return null;
        }
        if(result instanceof BigDecimal) {
            return ((BigDecimal)result).shortValue();
        }
        return (Short) result;
    }

    /**
     * 获取短整型字段值
     *
     * @param key 字段名
     * @param defaultValue 默认值
     * @return 短整型值
     */
    @Override
    public short getShort(K key, short defaultValue) {
        Short result = getShort(key);
        if(null == result) {
            return defaultValue;
        }
        return result;
    }

    /**
     * 获取整型字段值
     *
     * @param key 字段名
     * @return 整型值
     */
    @Override
    public Integer getInteger(K key) {
        Object result = get(key);
        if(null == result) {
            return null;
        }
        if(result instanceof BigDecimal) {
            return ((BigDecimal)result).intValue();
        }
        return (Integer) result;
    }

    /**
     * 获取整型字段值
     *
     * @param key 字段名
     * @param defaultValue 默认值
     * @return 整型值
     */
    @Override
    public int getInteger(K key, int defaultValue) {
        Integer result = getInteger(key);
        if(null == result) {
            return defaultValue;
        }
        return result;
    }

    /**
     * 获取长整型字段值
     *
     * @param key 字段名
     * @return 长整型值
     */
    @Override
    public Long getLong(K key) {
        Object result = get(key);
        if(null == result) {
            return null;
        }
        if(result instanceof BigDecimal) {
            return ((BigDecimal)result).longValue();
        }
        return (Long) result;
    }

    /**
     * 获取长整型字段值
     *
     * @param key 字段名
     * @param defaultValue 默认值
     * @return 长整型值
     */
    @Override
    public long getLong(K key, long defaultValue) {
        Long result = getLong(key);
        if(null == result) {
            return defaultValue;
        }
        return result;
    }

    /**
     * 获取单精度字段值
     *
     * @param key 字段名
     * @return 单精度值
     */
    @Override
    public Float getFloat(K key) {
        Object result = get(key);
        if(null == result) {
            return null;
        }
        if(result instanceof BigDecimal) {
            return ((BigDecimal)result).floatValue();
        }
        return (Float) result;
    }

    /**
     * 获取单精度字段值
     *
     * @param key 字段名
     * @param defaultValue 默认值
     * @return 单精度值
     */
    @Override
    public float getFloat(K key, float defaultValue) {
        Float result = getFloat(key);
        if(null == result) {
            return defaultValue;
        }
        return result;
    }

    /**
     * 获取双精度字段值
     *
     * @param key 字段名
     * @return 双精度值
     */
    @Override
    public Double getDouble(K key) {
        Object result = get(key);
        if(null == result) {
            return null;
        }
        if(result instanceof BigDecimal) {
            return ((BigDecimal)result).doubleValue();
        }
        return (Double) result;
    }

    /**
     * 获取双精度字段值
     *
     * @param key 字段名
     * @param defaultValue 默认值
     * @return 双精度值
     */
    @Override
    public double getDouble(K key, double defaultValue) {
        Double result = getDouble(key);
        if(null == result) {
            return defaultValue;
        }
        return result;
    }
}
