package org.leg.library.xml;

import org.leg.library.xml.core.IXMLNode;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

/**
 * XML文档对象
 */
public class XMLDocument {
    /**
     * 文件对象
     */
    protected File file = null;
    /**
     * 根节点
     */
    protected XMLNode root = null;


    /**
     * 获取文件对象
     *
     * @return 文件对象
     */
    public File getFile() {
        return file;
    }

    /**
     * 设置文件对象
     *
     * @param file 文件对象
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * 打开
     *
     * @return 是否打开成功
     */
    public boolean open(File file) {
        this.file = file;
        return open();
    }

    /**
     * 打开句柄
     *
     * @return 是否打开成功
     */
    public boolean open() {
        if(null == file) {
            return false;
        }
        XMLHandler handler = new XMLHandler();
        try {
            // 解析配置文件
            SAXParserFactory.newInstance().newSAXParser().parse(new FileInputStream(file), handler);
        }
        catch (Exception e) {
            // 解析配置文件失败
            return false;
        }
        root = handler.root();
        return true;
    }

    /**
     * 关闭句柄
     */
    public void close() {
        file = null;
        root = null;
    }

    /**
     * 保存文档
     *
     * @return 执行是否成功
     */
    public boolean save() {
        if(null == file || null == root) {
            return false;
        }
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(root.toString());
            fw.flush();
            fw.close();
        }
        catch(Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * 获取文档内容
     *
     * @return 文档内容
     */
    public IXMLNode content() {
        return root;
    }
}
