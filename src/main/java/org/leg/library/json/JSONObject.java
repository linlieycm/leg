package org.leg.library.json;

import org.leg.library.json.core.IJSON;
import org.leg.library.time.Date;
import org.leg.library.type.Table;
import org.leg.library.type.core.ILink;

import java.text.SimpleDateFormat;

/**
 * JSON值对象类
 */
public class JSONObject extends Table<String, IJSON> implements IJSON {

    /**
     * 获取对象类型
     *
     * @return 对象类型
     */
    @Override
    public int type() {
        return IJSON.JSON_TYPE_OBJECT;
    }

    /**
     * 转为字符串
     *
     * @return 字符串
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        boolean sentry = true;
        for (ILink<String, IJSON> link : this) {
            if (sentry) {
                sentry = false;
            }
            else {
                builder.append(",");
            }
            builder.append("\"");
            builder.append(link.getOrigin());
            builder.append("\":");
            builder.append(link.getDestination());
        }
        builder.append("}");
        return builder.toString();
    }

    /**
     * 将字符串转换为JSON对象
     *
     * @param jsonText 字符串
     * @return JSON对象
     */
    public static JSONObject convert(String jsonText) {
        return JSONObject.convert(new org.leg.library.json.thirdparty.JSONObject(jsonText));
    }

    /**
     * 将表转换为JSON对象
     *
     * @param table 表
     * @return JSON对象
     */
    public static <T> JSONObject convert(Table<String, T> table) {
        JSONObject result = new JSONObject();
        for(ILink<String, T> link : table) {
            if(null == link.getDestination()) {
                result.put(link.getOrigin(), null);
                continue;
            }
            if(link.getDestination() instanceof Boolean) {
                result.put(link.getOrigin(), new JSONBoolean((Boolean) link.getDestination()));
            }
            else if(link.getDestination() instanceof Number) {
                result.put(link.getOrigin(), new JSONNumber((Double) link.getDestination()));
            }
            else if(link.getDestination() instanceof String) {
                result.put(link.getOrigin(), new JSONString((String) link.getDestination()));
            }
            else if(link.getDestination() instanceof Date) {
                result.put(link.getOrigin(), new JSONString(((Date) link.getDestination()).toString()));
            }
            else if(link.getDestination() instanceof JSONObject) {
                result.put(link.getOrigin(), (JSONObject) link.getDestination());
            }
            else {
                result.put(link.getOrigin(), new JSONString(link.getDestination().toString()));
            }
        }
        return result;
    }

    /**
     * 将字符串转换为JSON对象
     *
     * @param jsonObject JSON对象
     * @return JSON对象
     */
    static JSONObject convert(org.leg.library.json.thirdparty.JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        for(String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if(value instanceof Boolean) {
                result.put(key, new JSONBoolean((Boolean)value));
            }
            else if(value instanceof Integer) {
                result.put(key, new JSONNumber((Integer)value));
            }
            else if(value instanceof Long) {
                result.put(key, new JSONNumber((Long)value));
            }
            else if(value instanceof Double) {
                result.put(key, new JSONNumber((Double)value));
            }
            else if(value instanceof String) {
                result.put(key, new JSONString((String)value));
            }
            else if(value instanceof org.leg.library.json.thirdparty.JSONObject) {
                result.put(key, JSONObject.convert((org.leg.library.json.thirdparty.JSONObject)value));
            }
            else if(value instanceof org.leg.library.json.thirdparty.JSONArray) {
                result.put(key, JSONArray.convert((org.leg.library.json.thirdparty.JSONArray)value));
            }
        }
        return result;
    }
}
