package org.leg.library.db;

/**
 * 数据库字段信息
 */
public class DBFieldInfo {
    /**
     * 字节类型
     */
    public final static String DATATYPE_BYTE = "tinyint";
    /**
     * 短整型
     */
    public final static String DATATYPE_SHORT = "smallint";
    /**
     * 整型
     */
    public final static String DATATYPE_INT = "int";
    /**
     * 字符串
     */
    public final static String DATATYPE_STRING = "varchar";
    /**
     * 日期类型
     */
    public final static String DATATYPE_DATE = "date";
    /**
     * 时间类型
     */
    public final static String DATATYPE_TIME = "time";
    /**
     * 日期时间类型
     */
    public final static String DATATYPE_DATETIME = "datetime";
    /**
     * 时间戳类型
     */
    public final static String DATATYPE_TIMESTAMP = "timestamp";


    /**
     * 字段名称
     */
    public String name;
    /**
     * 数据类型
     */
    public String type;
}
