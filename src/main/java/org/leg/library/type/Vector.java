package org.leg.library.type;

import java.io.Serializable;

import org.leg.library.time.Date;

/**
 * 向量
 */
public class Vector<T> extends MixedMapping<String, T> implements Serializable {
    private static final long serialVersionUID = 4006878817967145438L;

    /**
     * 列名与列内容映射
     */
    protected java.util.Map<String, List<T>> data = new java.util.LinkedHashMap<String, List<T>>();


    /**
     * 当前所在行索引
     */
    protected int cursor = -1;


    /**
     * 获取列名列表
     *
     * @return 列名列表
     */
    public Array<String> columns() {
        Array<String> result = new Array<String>(data.keySet().size());
        int i = 0;
        for(String columnName : data.keySet()) {
            result.set(i++, columnName);
        }
        return result;
    }

    /**
     * 获取数据内容行数
     *
     * @return 数据内容行数
     */
    public int rowCount() {
        for (java.util.Map.Entry<String, List<T>> entry : data.entrySet()) {
            return entry.getValue().size();
        }
        return 0;
    }

    /**
     * 切换到下一行
     *
     * @return 切换成功返回true，没有下一行则返回false
     */
    public boolean previous() {
        if(cursor > 0) {
            cursor--;
            return true;
        }
        return false;
    }

    /**
     * 切换到下一行
     *
     * @return 切换成功返回true，没有下一行则返回false
     */
    public boolean next() {
        if(cursor < rowCount() - 1) {
            cursor++;
            return true;
        }
        return false;
    }

    /**
     * 获取当前行指定列名的数据
     *
     * @param fieldName 列名
     * @return 列内容
     */
    @Override
    public T get(String fieldName) {
        List<T> row = data.get(fieldName);
        if(null == row) {
            return null;
        }
        return row.get(cursor);
    }

    /**
     * 设置指定字段名的值
     *
     * @param fieldName 字段名
     * @param value 替换值
     */
    public T put(String fieldName, T value) {
        List<T> row = data.get(fieldName);
        if(null == row) {
            return null;
        }
        return row.set(cursor, value);
    }

    /**
     * 删除当前行
     *
     * @return 执行成功返回true，失败返回false
     */
    public boolean remove() {
        if(cursor < 0 || cursor >= rowCount()) {
            return false;
        }
        for(java.util.Map.Entry<String, List<T>> entry : data.entrySet()) {
            entry.getValue().delete(cursor);
        }
        return true;
    }

    /**
     * 重置读取位置为第一行
     */
    public void rewind() {
        cursor = -1;
    }

    /**
     * 获取当前读取的索引
     */
    public int getCursor() {
        return this.cursor;
    }

    /**
     * 设置当前读取的索引
     *
     * @param cursor 当前索引
     */
    public void setCursor(int cursor) {
        this.cursor = cursor;
    }



    /**
     * 获取字符串字段值
     *
     * @param key 字段名
     * @return 字符串值
     */
    public String getString(String key) {
        return (String)this.get(key);
    }

    /**
     * 获取当前行指定列名的日期数据
     *
     * @param fieldName 列名
     * @return 日期数据
     */
    public Date getDate(String fieldName) {
        return (Date) get(fieldName);
    }

    /**
     * 转换成字符串
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        return toString(",");
    }

    /**
     * 转化为字符串
     *
     * @param separator 分隔符
     * @return 字符串内容
     */
    public String toString(String separator) {
        return toString(separator, true);
    }

    /**
     * 转化为字符串
     *
     * @param separator 分隔符
     * @param haveColumn 是否带上列头
     * @return 字符串
     */
    public String toString(String separator, boolean haveColumn) {
        StringBuilder result = new StringBuilder();
        List<StringBuilder> builderList = new List<StringBuilder>();
        boolean sentry = false;
        for (java.util.Map.Entry<String, List<T>> entry : data.entrySet()) {
            if(haveColumn) {
                if(sentry) {
                    result.append(separator);
                }
                result.append(entry.getKey());
            }
            int i = 0;
            for(T item : entry.getValue()) {
                StringBuilder subBuilder = null;
                if(builderList.size() > i) {
                    subBuilder = builderList.get(i);
                }
                else {
                    subBuilder = new StringBuilder();
                    builderList.add(subBuilder);
                }
                if(sentry) {
                    subBuilder.append(separator);
                }
                subBuilder.append(item);
                i++;
            }
            sentry = true;
        }
        for(StringBuilder subBuilder : builderList) {
            result.append("\n");
            result.append(subBuilder.toString());
        }
        return result.toString();
    }
}
