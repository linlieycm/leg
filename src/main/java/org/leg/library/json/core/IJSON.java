package org.leg.library.json.core;

/**
 * JSON对象接口
 */
public interface IJSON {
    /**
     * JSON对象类型
     */
    public final static int JSON_TYPE_BOOLEAN = 1;
    public final static int JSON_TYPE_NUMBER = 2;
    public final static int JSON_TYPE_STRING = 3;
    public final static int JSON_TYPE_ARRAY = 4;
    public final static int JSON_TYPE_OBJECT = 5;

    /**
     * 获取对象类型
     *
     * @return 对象类型
     */
    public int type();
}
