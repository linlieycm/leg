package org.leg.library.db;

import org.leg.library.xml.XMLNode;
import org.leg.library.xml.core.IXMLNode;

/**
 * 数据库连接池配置
 */
public class DBConnectionPoolConfig extends DBConnectionConfig {
    /**
     * 初始连接池大小
     */
    protected int initialPoolSize;
    /**
     * 最大连接池大小
     */
    protected int maxPoolSize;
    /**
     * 最小连接池大小
     */
    protected int minPoolSize;
    /**
     * 最大语句大小
     */
    protected int maxStatements;
    /**
     * 最大等待时间
     */
    protected int maxIdleTime;


    /**
     * 属性
     */
    public int getInitialPoolSize() {
        return initialPoolSize;
    }
    public void setInitialPoolSize(int initialPoolSize) {
        this.initialPoolSize = initialPoolSize;
    }
    public int getMaxPoolSize() {
        return maxPoolSize;
    }
    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
    public int getMinPoolSize() {
        return minPoolSize;
    }
    public void setMinPoolSize(int minPoolSize) {
        this.minPoolSize = minPoolSize;
    }
    public int getMaxStatements() {
        return maxStatements;
    }
    public void setMaxStatements(int maxStatements) {
        this.maxStatements = maxStatements;
    }
    public int getMaxIdleTime() {
        return maxIdleTime;
    }
    public void setMaxIdleTime(int maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
    }

    /**
     * 引导配置节点
     *
     * @param node 配置节点
     * @return 执行成功返回true，失败返回false
     */
    public boolean load(IXMLNode node) {
        boolean result = super.load(node);
        if(!result) {
            return false;
        }
        // 处理配置信息
        XMLNode configNode = (XMLNode) node.visit("config");
        if(null == configNode) {
            return false;
        }
        initialPoolSize = Integer.valueOf(configNode.get("initialpoolsize"));
        maxPoolSize = Integer.valueOf(configNode.get("maxPoolSize"));
        minPoolSize = Integer.valueOf(configNode.get("minPoolSize"));
        maxStatements = Integer.valueOf(configNode.get("maxStatements"));
        maxIdleTime = Integer.valueOf(configNode.get("maxIdleTime"));
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
        builder.append(super.toString());
        builder.append(",initialPoolSize = ");
        builder.append(initialPoolSize);
        builder.append(",maxPoolSize = ");
        builder.append(maxPoolSize);
        builder.append(",minPoolSize = ");
        builder.append(minPoolSize);
        builder.append(",maxStatements = ");
        builder.append(maxStatements);
        builder.append(",maxIdleTime = ");
        builder.append(maxIdleTime);
        return builder.toString();
    }
}
