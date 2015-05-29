package org.leg.library.db.core;

import org.leg.library.db.StoredProcedureParameters;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;

/**
 * 数据库执行器接口
 */
public interface IDBExecutor {
    /**
     * 执行模式：单条查询
     */
    public final static String MODE_LOAD = "LOAD";
    /**
     * 执行模式：批量查询
     */
    public final static String MODE_SELECT = "SELECT";
    /**
     * 执行模式：变更
     */
    public final static String MODE_ALTER = "ALTER";
    /**
     * 执行模式：插入
     */
    public final static String MODE_INSERT = "INSERT";
    /**
     * 执行模式：插入返回记录
     */
    public final static String MODE_CREATE = "CREATE";


    /**
     * 提取一条数据
     *
     * @param sql 待执行的SQL语句
     * @return 唯一记录
     */
    public Record load(String sql);

    /**
     * 提取数据
     *
     * @param sql 待执行的SQL语句
     * @return 记录集合
     */
    public IList<Record> select(String sql);

    /**
     * 变更
     *
     * @param sql SQL语句
     * @return 变更影响的行数，失败返回负数
     */
    public int alter(String sql);

    /**
     * 插入
     *
     * @param sql SQL语句
     * @return 自增主键，未能自增主键返回null
     */
    public Long insert(String sql);

    /**
     * 调用存储过程提取单记录
     *
     * @param storedProcedureName 存储过程名称
     * @param parameters 存储过程参数集合，顺序必须和SP钟参数申明顺序保持一致
     * @return 唯一记录
     */
    public Record load(String storedProcedureName, StoredProcedureParameters parameters);

    /**
     * 调用存储过程提取数据
     *
     * @param storedProcedureName 存储过程名称
     * @param parameters 存储过程参数集合，顺序必须和SP钟参数申明顺序保持一致
     * @return 记录集合
     */
    public IList<Record> select(String storedProcedureName, StoredProcedureParameters parameters);

    /**
     * 调用存储过程执行变更
     *
     * @param storedProcedureName 存储过程名称
     * @param parameters 存储过程参数集合，顺序必须和SP钟参数申明顺序保持一致
     * @return 变更影响的行数，失败返回负数
     */
    public int alter(String storedProcedureName, StoredProcedureParameters parameters);
}
