package org.leg.library.db;

import java.sql.*;

import org.leg.library.db.core.IDBExecutor;
import org.leg.library.text.Text;
import org.leg.library.type.List;
import org.leg.library.type.core.IList;
import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.leg.library.type.Record;

/**
 * 数据库执行器
 */
public class DBExecutor implements IDBExecutor {
    /**
     * JDBC
     */
    private final static String DRIVER_JDBC = "com.mysql.jdbc.Driver";


    /**
     * 日志对象
     */
    protected static Logger logger = Logger.getLogger(DBExecutor.class);
    /**
     * 数据源对象
     */
    protected ComboPooledDataSource dataSource;
    /**
     * 数据库连接池配置对象
     */
    protected DBConnectionPoolConfig conf;


    /**
     * 初始化
     *
     * @param conf 数据库连接池配置
     * @return 是否初始化成功
     */
    public boolean initialize(DBConnectionPoolConfig conf) {
        this.conf = conf;
        return initialize();
    }

    /**
     * 初始化
     *
     * @return 是否初始化成功
     */
    public boolean initialize() {
        if(null == conf) {
            return false;
        }
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setUser(conf.getUser());
            dataSource.setPassword(conf.getPassword());
            String url = null;
            if(DBConnectionConfig.DB_TYPE_MYSQL.equalsIgnoreCase(conf.getType())) {
                url = "jdbc:" + conf.getType() + "://" + conf.getIp() + ":" + conf.getPort() + "/" + conf.getDbName() + "?characterEncoding=UTF8";
                if(conf.isAutoReconnect()) {
                    url = url + "&autoReconnect=true";
                }
            }
            else {
                url = "jdbc:" + conf.getType() + "://" + conf.getIp() + ":" + conf.getPort() + ";DatabaseName=" + conf.getDbName();
            }
            dataSource.setJdbcUrl(url);
            dataSource.setDriverClass(DRIVER_JDBC);
            dataSource.setInitialPoolSize(conf.getInitialPoolSize());
            dataSource.setMinPoolSize(conf.getMinPoolSize());
            dataSource.setMaxPoolSize(conf.getMaxPoolSize());
            dataSource.setMaxStatements(conf.getMaxStatements());
            dataSource.setMaxIdleTime(conf.getMaxIdleTime());
        }
        catch(Exception ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }

    /**
     * 终止
     */
    public void terminate() {
        dataSource.close();
        dataSource = null;
        conf = null;
    }

    /**
     * 提取一条数据
     *
     * @param sql 待执行的SQL语句
     * @return 唯一记录
     */
    @Override
    public Record load(String sql) {
        Record result = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            int columnCount = resultSet.getMetaData().getColumnCount();
            if(resultSet.next()) {
                result = new Record();
                for(int i = 1; i <= columnCount; i++) {
                    result.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getObject(i));
                }
                return result;
            }
        }
        catch(Exception ex) {
            logger.error("execute DBExecutor.load(" + sql + ") failed", ex);
            return null;
        }
        finally {
            if(null != resultSet) {
                try {
                    resultSet.close();
                }
                catch (Exception e) {
                    logger.error("call ResultSet.close() failed", e);
                }
            }
            if(null != statement) {
                try {
                    statement.close();
                }
                catch (SQLException e) {
                    logger.error("call Statement.close() failed", e);
                }
            }
            if(null != connection) {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    logger.error("call Connection.close() failed", e);
                }
            }
        }
        return result;
    }

    /**
     * 提取数据
     *
     * @param sql 待执行的SQL语句
     * @return 记录集合
     */
    @Override
    public IList<Record> select(String sql) {
        IList<Record> result = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(null == resultSet) {
                logger.error("unexpected return null when call Statement.executeQuery(" + sql + ")");
                return null;
            }
            result = convert(resultSet);
        }
        catch(Exception ex) {
            logger.error("execute DBExecutor.select(" + sql + ") failed", ex);
            return null;
        }
        finally {
            if(null != resultSet) {
                try {
                    resultSet.close();
                }
                catch (SQLException e) {
                    logger.error("call ResultSet.close() failed", e);
                }
            }
            if(null != statement) {
                try {
                    statement.close();
                }
                catch (SQLException e) {
                    logger.error("call Statement.close() failed", e);
                }
            }
            if(null != connection) {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    logger.error("call Connection.close() failed", e);
                }
            }
        }
        return result;
    }

    /**
     * 变更
     *
     * @param sql SQL语句
     * @return 变更影响的行数，失败返回负数
     */
    @Override
    public int alter(String sql) {
        int result = 0;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            result = statement.executeUpdate(sql);
        }
        catch(Exception ex) {
            logger.error("execute DBExecutor.alter(" + sql + ") failed", ex);
            return -1;
        }
        finally {
            if(null != statement) {
                try {
                    statement.close();
                }
                catch (SQLException e) {
                    logger.error("call Statement.close() failed", e);
                }
            }
            if(null != connection) {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    logger.error("call Connection.close() failed", e);
                }
            }
        }
        return result;
    }

    /**
     * 插入
     *
     * @param sql SQL语句
     * @return 自增主键，未能自增主键返回0
     */
    @Override
    public Long insert(String sql) {
        long result = 0L;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                result = resultSet.getLong(1);
                return result;
            }
        }
        catch(Exception ex) {
            logger.error("execute DBExecutor.insert(" + sql + ") failed", ex);
            return 0L;
        }
        finally {
            if(null != resultSet) {
                try {
                    resultSet.close();
                }
                catch (SQLException e) {
                    logger.error("call ResultSet.close() failed", e);
                }
            }
            if(null != statement) {
                try {
                    statement.close();
                }
                catch (SQLException e) {
                    logger.error("call Statement.close() failed", e);
                }
            }
            if(null != connection) {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    logger.error("call Connection.close() failed", e);
                }
            }
        }
        return result;
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
        Record result = null;
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = createSPStatement(connection, storedProcedureName, parameters);
            resultSet = statement.executeQuery();
            int columnCount = resultSet.getMetaData().getColumnCount();
            if(resultSet.next()) {
                result = new Record();
                for(int i = 1; i <= columnCount; i++) {
                    result.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getObject(i));
                }
            }
            resetSPParameters(parameters, statement);
        }
        catch (SQLException ex) {
            logger.error("execute DBExecutor.load(" + storedProcedureName + ", [StoredProcedureParameters]) failed", ex);
            return null;
        }
        finally {
            if(null != resultSet) {
                try {
                    resultSet.close();
                }
                catch (SQLException e) {
                    logger.error("call ResultSet.close() failed", e);
                }
            }
            if(null != statement) {
                try {
                    statement.close();
                }
                catch (SQLException e) {
                    logger.error("call CallableStatement.close() failed", e);
                }
            }
            if(null != connection) {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    logger.error("call Connection.close() failed", e);
                }
            }
        }
        return result;
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
        IList<Record> result = null;
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = createSPStatement(connection, storedProcedureName, parameters);
            resultSet = statement.executeQuery();
            result = convert(resultSet);
            resetSPParameters(parameters, statement);
        }
        catch (SQLException ex) {
            logger.error("execute DBExecutor.select(" + storedProcedureName + ", [StoredProcedureParameters]) failed", ex);
            return null;
        }
        finally {
            if(null != resultSet) {
                try {
                    resultSet.close();
                }
                catch (SQLException e) {
                    logger.error("call ResultSet.close() failed", e);
                }
            }
            if(null != statement) {
                try {
                    statement.close();
                }
                catch (SQLException e) {
                    logger.error("call CallableStatement.close() failed", e);
                }
            }
            if(null != connection) {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    logger.error("call Connection.close() failed", e);
                }
            }
        }
        return result;
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
        int result = 0;
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = createSPStatement(connection, storedProcedureName, parameters);
            if(null == statement) {
                return -1;
            }
            statement.execute();
            result = statement.getUpdateCount();
            resetSPParameters(parameters, statement);
        }
        catch(Exception ex) {
            logger.error("execute DBExecutor.alter(" + storedProcedureName + ", [StoredProcedureParameters]) failed", ex);
            return -1;
        }
        finally {
            if(null != statement) {
                try {
                    statement.close();
                }
                catch (SQLException e) {
                    logger.error("call CallableStatement.close() failed", e);
                }
            }
            if(null != connection) {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    logger.error("call Connection.close() failed", e);
                }
            }
        }
        return result;
    }

    /**
     * 将DB结果集转换为记录数组
     *
     * @param resultSet DB结果集
     * @return 记录数组
     */
    public static IList<Record> convert(ResultSet resultSet) {
        IList<Record> result = null;
        try {
            result = new List<Record>();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while(resultSet.next()) {
                Record record = new Record();
                for(int j = 1; j <= columnCount; j++) {
                    try {
                        record.put(resultSet.getMetaData().getColumnLabel(j), resultSet.getObject(j));
                    }
                    catch(Exception ex) {
                        logger.error("error in record.put(resultSet.getMetaData().getColumnLabel(j), resultSet.getObject(j))", ex);
                    }
                }
                result.add(record);
            }
        }
        catch(Exception ex) {
            logger.error("DBExecutor.convert(?) failed", ex);
            return null;
        }
        return result;
    }

    /**
     * 创建存储过程申明
     *
     * @param connection 数据库连接
     * @param sql 待执行的存储过程名称
     * @param parameters 存储过程参数集合，顺序必须和SP钟参数申明顺序保持一致
     * @return DB回调申明
     */
    protected CallableStatement createSPStatement(Connection connection, String sql, StoredProcedureParameters parameters) {
        CallableStatement statement = null;
        try {
            statement = connection.prepareCall("{call " + sql + "(" + Text.repeat("?", ",", parameters.size()) + ")}");
            for(int i = 0; i < parameters.size(); i++) {
                Object item = parameters.get(i);
                if(item instanceof Byte) {
                    statement.setByte(i + 1, (Byte)item);
                }
                else if(item instanceof Short) {
                    statement.setShort(i + 1, (Short)item);
                }
                else if(item instanceof Integer) {
                    statement.setInt(i + 1, (Integer)item);
                }
                else if(item instanceof Long) {
                    statement.setLong(i + 1, (Long)item);
                }
                else if(item instanceof String) {
                    statement.setString(i + 1, (String)item);
                }
                else if(item instanceof Date) {
                    statement.setDate(i + 1, new Date(((Date)item).getTime()));
                }
                else if(item instanceof Class<?>) {
                    Class<?> clazz = (Class<?>) item;
                    if(clazz.isAssignableFrom(Byte.class)) {
                        statement.registerOutParameter(i + 1, Types.BIT);
                    }
                    else if(clazz.isAssignableFrom(Short.class)) {
                        statement.registerOutParameter(i + 1, Types.SMALLINT);
                    }
                    else if(clazz.isAssignableFrom(Integer.class)) {
                        statement.registerOutParameter(i + 1, Types.INTEGER);
                    }
                    else if(clazz.isAssignableFrom(Long.class)) {
                        statement.registerOutParameter(i + 1, Types.BIGINT);
                    }
                    else if(clazz.isAssignableFrom(String.class)) {
                        statement.registerOutParameter(i + 1, Types.NVARCHAR);
                    }
                    else if(clazz.isAssignableFrom(Date.class)) {
                        statement.registerOutParameter(i + 1, Types.DATE);
                    }
                    else {
                        logger.error("unsupported return compareType [" + clazz.getName() + "] before call procedure in DBExecutor.createSPStatement(" + sql + ", [StoredProcedureParameters])");
                        return null;
                    }
                }
                else {
                    logger.error("unsupported parameter[" + item.toString() + "] before call procedure in DBExecutor.createSPStatement(" + sql + ", [StoredProcedureParameters])");
                    return null;
                }
            }
        }
        catch (SQLException ex) {
            logger.error("execute DBExecutor.createSPStatement(" + sql + ", [StoredProcedureParameters]) failed", ex);
            return null;
        }
        return statement;
    }

    /**
     * 重置参数序列，将返回值传入
     *
     * @param parameters 参数序列
     * @param statement 申明语句
     *
     */
    protected void resetSPParameters(StoredProcedureParameters parameters, CallableStatement statement) {
        try {
            for(int i = 0; i < parameters.size(); i++) {
                Object item = parameters.get(i);
                if(!(item instanceof Class<?>)) {
                    continue;
                }
                Class<?> clazz = (Class<?>) item;
                if(clazz.isAssignableFrom(Byte.class)) {
                    parameters.put(i, statement.getByte(i + 1));
                }
                else if(clazz.isAssignableFrom(Short.class)) {
                    parameters.put(i, statement.getShort(i + 1));
                }
                else if(clazz.isAssignableFrom(Integer.class)) {
                    parameters.put(i, statement.getInt(i + 1));
                }
                else if(clazz.isAssignableFrom(Long.class)) {
                    parameters.put(i, statement.getLong(i + 1));
                }
                else if(clazz.isAssignableFrom(String.class)) {
                    parameters.put(i, statement.getString(i + 1));
                }
                else if(clazz.isAssignableFrom(Date.class)) {
                    parameters.put(i, statement.getDate(i + 1));
                }
                else {
                    // 暂时不支持的参数类型
                    logger.error("unsupported return compareType [" + clazz.getName() + "] when call procedure in DBExecutor.resetSPParameters([StoredProcedureParameters], [CallableStatement])");
                }
            }
        }
        catch (Exception ex) {
            logger.error("execute DBExecutor.resetSPParameters([StoredProcedureParameters], [CallableStatement]) failed", ex);
        }
    }
}
