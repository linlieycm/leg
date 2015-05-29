package org.leg.library.xml;

import org.leg.library.type.Table;
import org.leg.library.type.Set;
import org.leg.library.type.core.ICollection;
import org.leg.library.type.core.ILink;
import org.leg.library.type.core.ISet;
import org.leg.library.xml.core.IXMLNode;
import org.leg.library.xml.core.IXMLNodeSet;

import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;

/**
 * XML节点类
 */
public class XMLNode extends Table<String, String> implements IXMLNode, Cloneable {
    /**
     * 节点名称
     */
    protected String name = null;
    /**
     * 节点的值
     */
    protected String value = null;
    /**
     * 父节点
     */
    protected IXMLNode parent = null;
    /**
     * 子节点集
     */
    protected XMLNodeSet children = new XMLNodeSet();


    /**
     * 获取节点名称
     *
     * @return 节点名称
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 设置节点名称
     *
     * @param name 新节点名称
     * @return 节点名称
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取节点值
     *
     * @return 节点名称
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * 设置节点值
     *
     * @param value 新的节点值
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取父节点
     *
     * @return 父节点
     */
    @Override
    public IXMLNode parent() {
        return parent;
    }

    /**
     * 设置父节点
     *
     * @param parent 父节点
     */
    public IXMLNode setParent(IXMLNode parent) {
        IXMLNode result = this.parent;
        this.parent = parent;
        return result;
    }

    /**
     * 获取子节点集合
     *
     * @return 子节点集合
     */
    @Override
    public IXMLNodeSet children() {
        return children;
    }

    /**
     * 获取指定连接的子节点集合
     *
     * @param link 父子连接对象
     * @return 子节点集合
     */
    @Override
    public IXMLNodeSet children(String link) {
        IXMLNodeSet result = new XMLNodeSet();
        for(IXMLNode child : children) {
            if(child.getName().equals(link)) {
                result.add(child);
            }
        }
        return result;
    }

    /**
     * 获取邻居节点集合
     *
     * @return 邻居节点集合
     */
    @Override
    public ISet<IXMLNode> neighbor() {
        return null;
    }

    /**
     * 转为字符串
     *
     * @return 字符串
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(name);
        for(ILink<String, String> property : this) {
            sb.append(" ");
            sb.append(property.getOrigin());
            sb.append("=\"");
            sb.append(xmlTextToString(property.getDestination()));
            sb.append("\"");
        }
        if(0 == children.size()) {
            if(null == value) {
                sb.append(" />");
            }
            else {
                sb.append(">");
                sb.append(xmlTextToString(value));
                sb.append("</");
                sb.append(name);
                sb.append(">");
            }
        }
        else {
            sb.append(">");
            String tmp = "\n" + children.toString();
//			控制缩进
//			StringBuilder childrenSB = new StringBuilder();
//			for(IXMLNode child : children) {
//				childrenSB.append("\n");
//				childrenSB.append(child.toString());
//			}
            sb.append(tmp.replace("\n", "\n\t"));
            sb.append("\n</");
            sb.append(name);
            sb.append(">");
        }
        return sb.toString();
    }

    /**
     * 访问指定路径的节点
     *
     * @param path 节点路径，以/隔开节点名称，以[N]标记节点顺序
     * @return 指定路径的节点
     */
    @Override
    public IXMLNode visit(String path) {
        if(null == path || path.equals("")) {
            return this;
        }
        String head = null;
        String tail = null;
        // 取出本次分析的段
        int i = path.indexOf(XMLNODE_PATH_SEPARATOR);
        if(-1 == i) {
            head = path;
        }
        else {
            head = path.substring(0, i) ;
            tail = path.substring(i + 1);
        }
        // 尝试取出子节点索引
        String name = null;
        int index = 0;
        i = head.indexOf(XMLNODE_PATH_SBL);
        if(-1 == i) {
            name = head;
        }
        else {
            int j = head.indexOf(XMLNODE_PATH_SBR, i);
            if(-1 == j) {
                name = head;
            }
            else {
                name = head.substring(0, i) ;
                index = Integer.valueOf(head.substring(i + 1, j));
            }
        }
        // 提取子节点
        ICollection<IXMLNode> nodeSet = children.get(name);
        if(null == nodeSet) {
            // 子节点名称查找失败
            return null;
        }
        i = 0;
        for(IXMLNode node : nodeSet) {
            if(index == i) {
                if(null == tail) {
                    return node;
                }
                else {
                    // 迭代遍历
                    return node.visit(tail);
                }
            }
            i++;
        }
        // 子节点索引查找失败
        return null;
    }

    /**
     * 访问指定路径的节点集合
     *
     * @param path 节点路径，以/隔开节点名称，以[N]标记节点顺序
     * @return 指定路径的节点集合
     */
    @Override
    public ICollection<IXMLNode> visits(String path) {
        if(null == path || path.equals("")) {
            return new Set<IXMLNode>(this);
        }
        String head = null;
        String tail = null;
        // 取出本次分析的段
        int i = path.indexOf(XMLNODE_PATH_SEPARATOR);
        if(-1 == i) {
            head = path;
        }
        else {
            head = path.substring(0, i) ;
            tail = path.substring(i + 1);
        }
        // 尝试取出子节点索引
        String name = null;
        int index = -1;
        i = head.indexOf(XMLNODE_PATH_SBL);
        if(-1 == i) {
            name = head;
        }
        else {
            int j = head.indexOf(XMLNODE_PATH_SBR, i);
            if(-1 == j) {
                name = head;
            }
            else {
                name = head.substring(0, i) ;
                index = Integer.valueOf(head.substring(i + 1, j));
            }
        }
        // 提取子节点
        ICollection<IXMLNode> nodeSet = children.get(name);
        if(null == nodeSet) {
            // 子节点名称查找失败
            return new Set<IXMLNode>();
        }
        if(null == tail) {
            // 末节点解析
            if(-1 == index) {
                return nodeSet;
            }
            else {
                i = 0;
                for(IXMLNode node : nodeSet) {
                    if(index == i) {
                        return new Set<IXMLNode>(node);
                    }
                    i++;
                }
                return new Set<IXMLNode>();
            }
        }
        // 需要继续迭代
        if(-1 == index) {
            index = 0;
        }
        i = 0;
        for(IXMLNode node : nodeSet) {
            if(index == i) {
                // 迭代遍历
                return node.visits(tail);
            }
            i++;
        }
        // 子节点索引查找失败
        return new Set<IXMLNode>();
    }

    /**
     * 深度克隆对象
     *
     * @return 克隆对象
     */
    @Override
    public Object clone() {
        XMLNode result = new XMLNode();
        // 更新节点自身
        result.setName(this.getName());
        result.setValue(this.getValue());
        // 更新属性
        for(ILink<String, String> property : this) {
            result.put(property.getOrigin(), property.getDestination());
        }
        // 更新子节点
        for(IXMLNode son : this.children()) {
            result.children().add((IXMLNode)((XMLNode)son).clone());
        }
        return result;
    }

    /**
     * 普通字符串转XML字符串
     *
     * @param string 普通字符串
     * @return XML字符串
     */
    public static String stringToXMLText(String string) {
        if(null == string) {
            return null;
        }
        String result = string.replace("\"", "&quot;");
        result = result.replace("<", "&lt;");
        result = result.replace(">", "&gt;");
        result = result.replace("&", "&amp;");
        result = result.replace("\r", "&#x0D;");
        result = result.replace("\n", "&#x0A;");
        return result;
    }

    /**
     * XML字符串转普通字符串
     *
     * @param xmlText XML字符串
     * @return 普通字符串
     */
    public static String xmlTextToString(String xmlText) {
        if(null == xmlText) {
            return null;
        }
        String result = xmlText.replace("&quot;", "\"");
        result = result.replace("&lt;", "<");
        result = result.replace("&gt;", ">");
        result = result.replace("&amp;", "&");
        result = result.replace("&#x0D;", "\r");
        result = result.replace("&#x0A;", "\n");
        return result;
    }

    /**
     * 字符串转XML
     *
     * @param xmlText XML字符串
     * @return XML节点对象
     */
    public static XMLNode convert(String xmlText) {
        XMLHandler handler = new XMLHandler();
        try {
            // 解析配置文件
            SAXParserFactory.newInstance().newSAXParser().parse(new ByteArrayInputStream(xmlText.getBytes("UTF-8")), handler);
        }
        catch (Exception e) {
            // 解析配置文件失败
            return null;
        }
        return handler.root();
    }
}
