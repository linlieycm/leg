package org.leg.library.json;

import org.leg.library.json.core.IJSON;

/**
 * JSON值字符串类
 */
public class JSONString implements IJSON {
    /**
     * 值
     */
    protected String value = "";


    /**
     * 构造函数
     */
    public JSONString() { }

    /**
     * 构造函数
     *
     * @param value 值
     */
    public JSONString(String value) {
        this.value = value;
    }

    /**
     * 获取对象类型
     *
     * @return 对象类型
     */
    @Override
    public int type() {
        return IJSON.JSON_TYPE_STRING;
    }

    /**
     * 属性
     */
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 转为字符串
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        return "" + value + "";
    }
}
