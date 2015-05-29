package org.leg.library.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

/**
 * XML解析句柄
 */
class XMLHandler extends DefaultHandler {
    /**
     * 解析堆栈
     */
    private Stack<XMLNode> stack = new Stack<XMLNode>();


    /**
     * 节点解析开始
     *
     * @param uri
     * @param localName
     * @param nodeName
     * @param attribute
     */
    @Override
    public void startElement(String uri, String localName, String nodeName, Attributes attribute) throws SAXException {
        stack.push(makeXMLNode(nodeName, attribute));
    }

    /**
     * 节点解析结束
     *
     * @param uri
     * @param local
     * @param raw
     */
    @Override
    public void endElement(String uri, String local, String raw) {
        if(1 == stack.size()) {
            // 已经递归到了根节点
            return;
        }
        XMLNode son = stack.pop();
        XMLNode father = stack.peek();
        // 构建节点父子关系需要设置父节点和加入子节点集
        if(null != son && null != father) {
            son.setParent(father);
            father.children().add(son);
        }
    }

    /**
     * 解析节点值
     *
     * @param ch
     * @param start
     * @param length
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        XMLNode son = stack.peek();
        son.setValue(String.copyValueOf(ch, start, length));
    }

    /**
     * 获取解析生成的根节点
     *
     * @return 根节点
     */
    public XMLNode root() {
        if(0 == stack.size()) {
            return null;
        }
        return stack.pop();
    }

    /**
     * 构建XML配置对象节点
     * @param nodeName 节点名称
     * @param attribute 属性集合
     * @return XML配置对象节点
     */
    protected XMLNode makeXMLNode(String nodeName, Attributes attribute) {
        XMLNode node = new XMLNode();
        node.name = nodeName;
        for(int i = 0; i < attribute.getLength(); i++) {
            node.put(attribute.getLocalName(i), attribute.getValue(i));
        }
        return node;
    }
}
