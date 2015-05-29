package org.leg.library.type.core;

/**
 * 混合型映射
 */
public interface IMixedMapping<K, V> extends IMapping<K, V> {
    /**
     * 获取布尔值
     *
     * @param key 名
     * @return 布尔值
     */
    public Boolean getBoolean(K key);

    /**
     * 获取布尔值
     *
     * @param key 名
     * @param defaultValue 默认值
     * @return 布尔值
     */
    public boolean getBoolean(K key, boolean defaultValue);

    /**
     * 获取字符值
     *
     * @param key 名
     * @return 字符值
     */
    public Character getCharacter(K key);

    /**
     * 获取字符值
     *
     * @param key 名
     * @param defaultValue 默认值
     * @return 字符值
     */
    public char getCharacter(K key, char defaultValue);

    /**
     * 获取字节值
     *
     * @param key 名
     * @return 字节值
     */
    public Byte getByte(K key);

    /**
     * 获取字节值
     *
     * @param key 名
     * @param defaultValue 默认值
     * @return 字节值
     */
    public byte getByte(K key, byte defaultValue);

    /**
     * 获取短整型值
     *
     * @param key 名
     * @return 短整型值
     */
    public Short getShort(K key);

    /**
     * 获取短整型值
     *
     * @param key 名
     * @param defaultValue 默认值
     * @return 短整型值
     */
    public short getShort(K key, short defaultValue);

    /**
     * 获取整型值
     *
     * @param key 名
     * @return 整型值
     */
    public Integer getInteger(K key);

    /**
     * 获取整型值
     *
     * @param key 名
     * @param defaultValue 默认值
     * @return 整型值
     */
    public int getInteger(K key, int defaultValue);

    /**
     * 获取长整型值
     *
     * @param key 名
     * @return 长整型值
     */
    public Long getLong(K key);

    /**
     * 获取长整型值
     *
     * @param key 名
     * @param defaultValue 默认值
     * @return 长整型值
     */
    public long getLong(K key, long defaultValue);

    /**
     * 获取单精度值
     *
     * @param key 名
     * @return 单精度值
     */
    public Float getFloat(K key);

    /**
     * 获取单精度值
     *
     * @param key 名
     * @param defaultValue 默认值
     * @return 单精度值
     */
    public float getFloat(K key, float defaultValue);

    /**
     * 获取双精度值
     *
     * @param key 名
     * @return 双精度值
     */
    public Double getDouble(K key);

    /**
     * 获取双精度值
     *
     * @param key 名
     * @param defaultValue 默认值
     * @return 双精度值
     */
    public double getDouble(K key, double defaultValue);
}
