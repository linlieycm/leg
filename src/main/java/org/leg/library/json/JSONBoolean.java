package org.leg.library.json;

import org.leg.library.json.core.IJSON;

/**
 * JSON值布尔类
 */
public class JSONBoolean implements IJSON {
    /**
     * 值
     */
    protected boolean value = false;


    /**
     * 构造函数
     */
    public JSONBoolean() { }

    /**
     * 构造函数
     *
     * @param value 值
     */
    public JSONBoolean(boolean value) {
        this.value = value;
    }

    /**
     * 获取对象类型
     *
     * @return 对象类型
     */
    @Override
    public int type() {
        return IJSON.JSON_TYPE_BOOLEAN;
    }

    /**
     * 属性
     */
    public boolean getValue() {
        return value;
    }
    public void setValue(boolean value) {
        this.value = value;
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
