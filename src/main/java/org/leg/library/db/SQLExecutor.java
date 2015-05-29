package org.leg.library.db;

import org.leg.library.db.core.IDBExecutor;
import org.leg.library.type.Record;
import org.leg.library.type.Table;
import org.leg.library.type.core.IList;

/**
 * SQL执行类
 */
public class SQLExecutor extends Table<String, DBExecutor> implements IDBExecutor {
    /**
     * 默认数据库执行器
     */
    protected DBExecutor defaultDBExecutor;


    /**
     * 设置默认DB执行器
     *
     * @param defaultDBExecutor 默认DB执行器
     */
    public void setDefault(DBExecutor defaultDBExecutor) {
        this.defaultDBExecutor = defaultDBExecutor;
    }

    /**
     * 提取一条数据
     *
     * @param sql 待执行的SQL语句
     * @return 唯一记录
     */
    @Override
    public Record load(String sql) {
        if(null == defaultDBExecutor) {
            return null;
        }
        return defaultDBExecutor.load(sql);
    }

    /**
     * 提取数据
     *
     * @param sql 待执行的SQL语句
     * @return 记录集合
     */
    @Override
    public IList<Record> select(String sql) {
        if(null == defaultDBExecutor) {
            return null;
        }
        return defaultDBExecutor.select(sql);
    }

    /**
     * 变更
     *
     * @param sql SQL语句
     * @return 变更影响的行数，失败返回负数
     */
    @Override
    public int alter(String sql) {
        if(null == defaultDBExecutor) {
            return -2;
        }
        return defaultDBExecutor.alter(sql);
    }

    /**
     * 插入
     *
     * @param sql SQL语句
     * @return 自增主键，未能自增主键返回null
     */
    @Override
    public Long insert(String sql) {
        if(null == defaultDBExecutor) {
            return 0L;
        }
        return defaultDBExecutor.insert(sql);
    }

    /**
     * 调用存储过程提取单记录
     *
     * @param storedProcedureName 存储过程名称
     * @param parameters 存储过程参数集合，顺序必须和SP钟参数申明顺序保持一致
     * @return 唯一记录
     */
    @Override
    public Record load(String storedProcedureName, StoredProcedureParameters parameters) {
        if(null == defaultDBExecutor) {
            return null;
        }
        return defaultDBExecutor.load(storedProcedureName, parameters);
    }

    /**
     * 调用存储过程提取数据
     *
     * @param storedProcedureName 存储过程名称
     * @param parameters 存储过程参数集合，顺序必须和SP钟参数申明顺序保持一致
     * @return 记录集合
     */
    @Override
    public IList<Record> select(String storedProcedureName, StoredProcedureParameters parameters) {
        if(null == defaultDBExecutor) {
            return null;
        }
        return defaultDBExecutor.select(storedProcedureName, parameters);
    }

    /**
     * 调用存储过程执行变更
     *
     * @param storedProcedureName 存储过程名称
     * @param parameters 存储过程参数集合，顺序必须和SP钟参数申明顺序保持一致
     * @return 变更影响的行数，失败返回负数
     */
    @Override
    public int alter(String storedProcedureName, StoredProcedureParameters parameters) {
        if(null == defaultDBExecutor) {
            return -2;
        }
        return defaultDBExecutor.alter(storedProcedureName, parameters);
    }
}
