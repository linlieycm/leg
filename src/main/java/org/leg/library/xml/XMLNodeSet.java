package org.leg.library.xml;

import org.leg.library.type.Map;
import org.leg.library.type.Set;
import org.leg.library.type.core.ICollection;
import org.leg.library.type.core.ISet;
import org.leg.library.xml.core.IXMLNode;
import org.leg.library.xml.core.IXMLNodeSet;

import java.util.Iterator;

/**
 * XML节点集合
 */
public class XMLNodeSet extends Map<String, ISet<IXMLNode>> implements IXMLNodeSet {
    /**
     * XML节点迭代器
     */
    public class XMLNodeIterator implements Iterator<IXMLNode> {
        /**
         * 组节点迭代器
         */
        protected Iterator<java.util.Map.Entry<String, ISet<IXMLNode>>> nodeSetIter = null;
        /**
         * 节点迭代器
         */
        protected Iterator<IXMLNode> nodeIter = null;
        /**
         * 最后一次遍历的节点组
         */
        protected ICollection<IXMLNode> lastNodeSet = null;
        /**
         * 最后一次遍历的节点组名称
         */
        protected String lastNodeName = null;


        /**
         * 构造函数
         */
        public XMLNodeIterator() {
            nodeSetIter = map.entrySet().iterator();
        }

        /**
         * 获取是否有下一元素
         *
         * @return 是否有下一元素
         */
        @Override
        public boolean hasNext() {
            if(null != nodeIter && nodeIter.hasNext()) {
                return true;
            }
            return nodeSetIter.hasNext();
        }

        /**
         * 获取下一元素
         *
         * @return 下一元素对象
         */
        @Override
        public IXMLNode next() {
            if(null != nodeIter && nodeIter.hasNext()) {
                return nodeIter.next();
            }
            if(false == nodeSetIter.hasNext()) {
                return null;
            }
            java.util.Map.Entry<String, ISet<IXMLNode>> entry = nodeSetIter.next();
            lastNodeName = entry.getKey();
            lastNodeSet = entry.getValue();
            nodeIter = lastNodeSet.iterator();
            return nodeIter.next();
        }

        /**
         * 删除元素
         */
        @Override
        public void remove() {
            // 删除元素
            nodeIter.remove();
            // 清理所在组
            if(null == lastNodeName || null == lastNodeSet) {
                return;
            }
            if(0 == lastNodeSet.size()) {
                map.remove(lastNodeSet);
            }
        }
    }

    /**
     * 增加元素
     *
     * @param value 元素对象
     */
    @Override
    public boolean add(IXMLNode value) {
        if(null == value) {
            return false;
        }
        ISet<IXMLNode> nodeSet = map.get(value.getName());
        if(null == nodeSet) {
            nodeSet = new Set<IXMLNode>();
            map.put(value.getName(), nodeSet);
        }
        return nodeSet.add(value);
    }

    /**
     * 增加元素
     *
     * @param items 待添加的元素引用合集
     * @return 操作执行结果
     */
    @Override
    public boolean add(ICollection<IXMLNode> items) {
        return false;
    }

    /**
     * 删除元素
     *
     * @param value 元素对象
     * @return 操作执行结果
     */
    @Override
    public boolean remove(IXMLNode value) {
        Iterator<java.util.Map.Entry<String, ISet<IXMLNode>>> iter = map.entrySet().iterator();
        ISet<IXMLNode> nodeSet = null;
        while(iter.hasNext()) {
            java.util.Map.Entry<String, ISet<IXMLNode>> entry = iter.next();
            nodeSet = entry.getValue();
            if(nodeSet.contains(value)) {
                return nodeSet.remove(value);
            }
        }
        return false;
    }

    /**
     * 删除元素
     *
     * @param items 待删除的元素引用合集
     * @return 操作执行结果
     */
    @Override
    public boolean remove(ICollection<IXMLNode> items) {
        return false;
    }

    /**
     * 获取集合中元素的个数
     *
     * @return 集合中元素的个数
     */
    @Override
    public int size() {
        int ret = 0;
        for(ISet<IXMLNode> nodeSet : map.values()) {
            ret += nodeSet.size();
        }
        return ret;
    }

    /**
     * 判断是否包含指定元素
     *
     * @param value 指定元素
     * @return 是否包含指定元素
     */
    @Override
    public boolean contains(IXMLNode value) {
        Iterator<java.util.Map.Entry<String, ISet<IXMLNode>>> iterator = map.entrySet().iterator();
        ISet<IXMLNode> nodeSet = null;
        while(iterator.hasNext()) {
            java.util.Map.Entry<String, ISet<IXMLNode>> entry = iterator.next();
            nodeSet = entry.getValue();
            if(nodeSet.contains(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取迭代器对象
     *
     * @return 迭代器对象
     */
    @Override
    public Iterator<IXMLNode> iterator() {
        return new XMLNodeIterator();
    }

    /**
     * 转为字符串
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(IXMLNode node : this) {
            if(this.size() == i) {
                sb.append(node);
            }
            else {
                sb.append(node);
                sb.append("\n");
            }
            i++;
        }
        return sb.toString();
    }
}
