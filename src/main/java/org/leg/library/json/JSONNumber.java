package org.leg.library.json;

import org.leg.library.json.core.IJSON;

/**
 * JSON值数字类
 */
public class JSONNumber extends Number implements IJSON {
    /**
     * 值
     */
    protected double value = 0F;


    /**
     * 构造函数
     */
    public JSONNumber() { }

    /**
     * 构造函数
     *
     * @param value 值
     */
    public JSONNumber(int value) {
        this.value = value;
    }

    /**
     * 构造函数
     *
     * @param value 值
     */
    public JSONNumber(long value) {
        this.value = value;
    }

    /**
     * 构造函数
     *
     * @param value 值
     */
    public JSONNumber(float value) {
        this.value = value;
    }

    /**
     * 构造函数
     *
     * @param value 值
     */
    public JSONNumber(double value) {
        this.value = value;
    }

    /**
     * 获取对象类型
     *
     * @return 对象类型
     */
    @Override
    public int type() {
        return IJSON.JSON_TYPE_NUMBER;
    }

    /**
     * Returns the value of the specified number as an <code>int</code>.
     * This may involve rounding or truncation.
     *
     * @return the numeric value represented by this object after conversion
     * to compareType <code>int</code>.
     */
    @Override
    public int intValue() {
        return (int)value;
    }

    /**
     * Returns the value of the specified number as a <code>long</code>.
     * This may involve rounding or truncation.
     *
     * @return the numeric value represented by this object after conversion
     * to compareType <code>long</code>.
     */
    @Override
    public long longValue() {
        return (long)value;
    }

    /**
     * Returns the value of the specified number as a <code>float</code>.
     * This may involve rounding.
     *
     * @return the numeric value represented by this object after conversion
     * to compareType <code>float</code>.
     */
    @Override
    public float floatValue() {
        return (float)value;
    }

    /**
     * Returns the value of the specified number as a <code>double</code>.
     * This may involve rounding.
     *
     * @return the numeric value represented by this object after conversion
     * to compareType <code>double</code>.
     */
    @Override
    public double doubleValue() {
        return value;
    }

    /**
     * 设置值
     *
     * @param number 待设置值
     */
    public void setValue(Number number) {
        value = number.doubleValue();
    }

    /**
     * 设置值
     *
     * @param number 待设置值字符串
     */
    public void setValue(String number) {
        value = Double.valueOf(number);
    }

    /**
     * 转为字符串
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
