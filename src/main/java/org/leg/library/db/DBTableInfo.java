package org.leg.library.db;

import org.leg.library.db.core.IDBExecutor;
import org.leg.library.type.*;
import org.leg.library.type.core.IList;
import org.leg.library.type.core.ITable;

/**
 * 数据库表类
 */
public class DBTableInfo {
    /**
     * 数据库名称
     */
    public String db;
    /**
     * 表名称
     */
    public String table;
    /**
     * 字段表
     */
    public ITable<String, DBFieldInfo> fields = null;
    /**
     * 主键列表
     */
    public IList<String> primaryKeys = null;


    /**
     * 构造函数
     */
    public DBTableInfo() { }

    /**
     * 构造函数
     *
     * @param db 数据库名
     * @param table 表名称
     * @param fields 字段表
     * @param primaryKeys 主键列表
     */
    public DBTableInfo(String db, String table, ITable<String, DBFieldInfo> fields, IList<String> primaryKeys) {
        this.db = db;
        this.table = table;
        this.fields = fields;
        this.primaryKeys = primaryKeys;
    }

    /**
     * 构建数据库表
     *
     * @param conf 配置字符串
     * @param executor 数据库执行器
     * @return 数据库表
     */
    public static DBTableInfo build(String conf, IDBExecutor executor) {
        if(null == conf) {
            return null;
        }
        int i = conf.indexOf(".");
        if(-1 == i) {
            return null;
        }
        DBTableInfo result = new DBTableInfo();
        result.db = conf.substring(0, i);
        result.table = conf.substring(i + 1);
        IList<DBFieldInfo> fieldList = DBTableInfo.getColumns(result.table, executor);
        result.fields = new Table<String, DBFieldInfo>();
        for(DBFieldInfo fieldInfo : fieldList) {
            result.fields.put(fieldInfo.name, fieldInfo);
        }
        result.primaryKeys = DBTableInfo.getPrimaryKeys(result.table, executor);
        return result;
    }

    /**
     * 获取指定表的主键集合
     *
     * @param tableName 表名
     * @param executor 数据库执行器
     * @return 主键集合
     */
    public static IList<String> getPrimaryKeys(String tableName, IDBExecutor executor) {
        IList<Record> ret = executor.select("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME = '" + tableName + "'");
        IList<String> result = new List<String>();
        for(Record record : ret) {
            if(!result.contains(record.getString("COLUMN_NAME"))) {
                result.add(record.getString("COLUMN_NAME"));
            }
        }
        return result;
    }

    /**
     * 获取指定表的字段信息
     *
     * @param tableName 表名
     * @param executor 数据库执行器
     * @return 字段信息
     */
    public static IList<DBFieldInfo> getColumns(String tableName, IDBExecutor executor) {
        IList<Record> ret = executor.select("SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + tableName + "'");
        IList<DBFieldInfo> result = new List<DBFieldInfo>();
        for(Record record : ret) {
            DBFieldInfo field = new DBFieldInfo();
            field.name = record.getString("COLUMN_NAME");
            field.type = record.getString("DATA_TYPE");
            result.add(field);
        }
        return result;
    }
}
