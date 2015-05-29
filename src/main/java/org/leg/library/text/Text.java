package org.leg.library.text;

import java.util.regex.Pattern;

/**
 * 文本
 */
public class Text {
    /**
     * 判断字符串是否为空或者空内容
     *
     * @param string 字符串
     * @return 如果输入字符串为空或者空内容则返回true，否则返回false
     */
    public static boolean isBlank(String string) {
        return (null == string) ? true : (string.equals(""));
    }

    /**
     * 重复字符串
     *
     * @param item 字符串片段
     * @param count 重复次数
     * @return 重复后的字符串，错误返回null
     */
    public static<T> String repeat(T item, int count) {
        if(count < 0) {
            throw new IllegalArgumentException();
        }
        else if(0 == count) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < count; i++) {
            builder.append(item);
        }
        return builder.toString();
    }

    /**
     * 重复字符串
     *
     * @param item 字符串片段
     * @param separator 分隔符
     * @param count 重复次数
     * @return 重复后的字符串，错误返回null
     */
    public static<T> String repeat(T item, String separator, int count) {
        if(count < 0) {
            throw new IllegalArgumentException();
        }
        else if(0 == count) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < count; i++) {
            if(i > 0) {
                builder.append(separator);
            }
            builder.append(item);
        }
        return builder.toString();
    }

    /**
     * 判断字符串是否是数字
     *
     * @param string 字符串
     * @return 是否是数字
     */
    public static boolean isNumber(String string) {
        return Pattern.compile("[0-9]*").matcher(string).matches();
    }

    /**
     * 截取字符串
     *
     * @param text 被截取的字符串
     * @param begin 起始字符，空字符表示从首字符开始
     * @param end 截止字符，空字符表示无截止
     * @return 被截取的字符串
     */
    public static String substring(String text, String begin, String end) {
        int i = 0;
        if(!isBlank(begin)) {
            i = text.indexOf(begin);
            if(-1 == i) {
                return null;
            }
            i += begin.length();
        }
        int j = text.length();
        if(!isBlank(end)) {
            j = text.indexOf(end, i);
            if(-1 == j) {
                return null;
            }
        }
        return text.substring(i, j);
    }
}
