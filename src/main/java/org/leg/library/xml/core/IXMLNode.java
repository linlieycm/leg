package org.leg.library.xml.core;

import org.leg.library.type.core.ICollection;
import org.leg.library.type.core.ITable;
import org.leg.library.type.core.IMultiTree;

/**
 * XML节点接口
 */
public interface IXMLNode extends IMultiTree<String, IXMLNode, IXMLNodeSet>, ITable<String, String> {
    /**
     * XML节点路径分隔符
     */
    public final static String XMLNODE_PATH_SEPARATOR = "/";
    /**
     * XML节点路径中节点集索引左括号
     */
    public final static String XMLNODE_PATH_SBL = "[";
    /**
     * XML节点路径中节点集索引右括号
     */
    public final static String XMLNODE_PATH_SBR = "]";


    /**
     * 获取节点名称
     *
     * @return 节点名称
     */
    public String getName();

    /**
     * 设置节点名称
     *
     * @param name 新的节点名称
     */
    public void setName(String name);

    /**
     * 获取节点值
     *
     * @return 节点名称
     */
    public String getValue();

    /**
     * 设置节点值
     *
     * @param value 新的节点值
     */
    public void setValue(String value);

    /**
     * 访问指定路径的节点
     *
     * @param path 节点路径，以/隔开节点名称，以[N]标记节点顺序
     * @return 指定路径的节点
     */
    public IXMLNode visit(String path);

    /**
     * 访问指定路径的节点集合
     *
     * @param path 节点路径，以/隔开节点名称，以[N]标记节点顺序
     * @return 指定路径的节点集合
     */
    public ICollection<IXMLNode> visits(String path);
}
