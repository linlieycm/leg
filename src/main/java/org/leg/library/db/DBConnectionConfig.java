package org.leg.library.db;

import org.leg.library.xml.XMLNode;
import org.leg.library.xml.core.IXMLNode;

/**
 * 数据库连接配置
 *
 * 配置范例:
 * <connection>
 * 	  <db compareType="mysql" ip="127.0.0.1" port="3306" name="dbname" />
 * 	  <user name="user" password="password" />
 * 	  <config autoreconnect="true" />
 * </connection>
 */
public class DBConnectionConfig {
    /**
     * 数据源类型MySQL
     */
    public final static String DB_TYPE_MYSQL = "mysql";
    /**
     * 数据源类型SQLServer
     */
    public final static String DB_TYPE_SQLSERVER = "sqlserver";


    /**
     * 数据库类型
     */
    protected String type;
    /**
     * 数据库服务器IP
     */
    protected String ip;
    /**
     * 数据库服务器端口
     */
    protected int port;
    /**
     * 待访问的数据库名称
     */
    protected String dbName;
    /**
     * 登录用户名
     */
    protected String user;
    /**
     * 登录密码
     */
    protected String password;
    /**
     * 是否断开重连
     */
    protected boolean autoReconnect;


    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public String getDbName() {
        return dbName;
    }
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAutoReconnect(boolean autoReconnect) {
        this.autoReconnect = autoReconnect;
    }
    public boolean isAutoReconnect() {
        return autoReconnect;
    }

    /**
     * 引导配置节点
     *
     * @param node 配置节点
     * @return 执行成功返回true，失败返回false
     */
    public boolean load(IXMLNode node) {
        if(null == node) {
            return false;
        }
        // 处理数据库信息
        XMLNode dbNode = (XMLNode) node.visit("db");
        if(null == dbNode) {
            return false;
        }
        String tmp = null;
        type = dbNode.get("compareType");
        if(!DB_TYPE_SQLSERVER.equals(type)) {
            type = DB_TYPE_MYSQL;
        }
        ip = dbNode.get("ip");
        tmp = dbNode.get("port");
        if(null == tmp) {
            // 设置为默认端口
            if(DB_TYPE_SQLSERVER.equals(type)) {
                port = 1433;
            }
            else {
                port = 3306;
            }
        }
        else {
            port = Integer.parseInt(tmp);
        }
        dbName = dbNode.get("name");
        // 处理用户信息
        XMLNode userNode = (XMLNode) node.visit("user");
        if(null == userNode) {
            return false;
        }
        user = userNode.get("name");
        password= userNode.get("password");
        // 处理配置信息
        XMLNode configNode = (XMLNode) node.visit("config");
        if(null == configNode) {
            autoReconnect = false;
            return true;
        }
        if("true".equalsIgnoreCase(configNode.get("autoreconnect"))) {
            autoReconnect = true;
        }
        return true;
    }

    /**
     * 转化为字符串
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("compareType = ");
        builder.append(type);
        builder.append(",ip = ");
        builder.append(ip);
        builder.append(",port = ");
        builder.append(port);
        builder.append(",dbName = ");
        builder.append(dbName);
        builder.append(",user = ");
        builder.append(user);
        builder.append(",password = ");
        builder.append(password);
        return builder.toString();
    }
}
