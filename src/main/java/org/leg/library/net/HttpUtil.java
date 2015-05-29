package org.leg.library.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

/**
 * HTTP协议客户端
 */
public class HttpUtil {
	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(HttpUtil.class);
	/**
	 * 默认超时时间是一个小时
	 */
	public final static int TIMEOUT_DEFAULT = 1000 * 60 * 60;

	
	/**
	 * 获取远程HTTP服务指定链接的字符文本
	 * 
	 * @param url 指定链接
	 * @return 字符文本
	 */
	public static String getString(String url, Map<String, String> parameters) {
		url = url.replace(" ", "%20");
		url = url.replace("\n", "%0A");
		url = url.replace("\r", "%0D");
		url = url.replace("\t", "%09");
		url = url.replace("\"", "%22");
		url = url.replace("'", "%27");
		String result = null;
		CloseableHttpClient httpClient = null;
        try {
        	httpClient = HttpClientBuilder.create().build();
        	HttpPost httpPost = new HttpPost(url);
        	if(parameters.size() > 0) {
            	List<NameValuePair> parameterList = new ArrayList<NameValuePair>();
            	for(Entry<String, String> entry : parameters.entrySet()) {
            		parameterList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
            	}
            	httpPost.setEntity(new UrlEncodedFormEntity(parameterList));  
        	}
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null && 200 == response.getStatusLine().getStatusCode()) {
            	result = streamToString(entity.getContent());
            	httpPost.abort();
            }
            else {
            	logger.error("execute HttpUtil.getString(" + url + ", " + parameters + ") faild");
            }
        }
        catch(Throwable ex) {
        	logger.error("execute HttpUtil.getString(" + url + ", " + parameters + ") faild", ex);
        	return null;
        }
        finally {
        	try {
        		if(null != httpClient) {
                     httpClient.close();
        		}
        	}
        	catch(Exception ex) {
        		logger.error("call httpClient.close() failed in HttpUtil.getString(" + url + ")", ex);
        		return result;
        	}
        }
        return result;
	}

	/**
	 * 获取远程HTTP服务指定链接的字符文本
	 * 
	 * @param url 指定链接
	 * @return 字符文本
	 */
	public static String getString(String url, Map<String, String> parameters, Map<String, String> files) {
		url = url.replace(" ", "%20");
		url = url.replace("\n", "%0A");
		url = url.replace("\r", "%0D");
		url = url.replace("\t", "%09");
		url = url.replace("\"", "%22");
		url = url.replace("'", "%27");
		String result = null;
		CloseableHttpClient httpClient = null;
        try {
        	httpClient = HttpClientBuilder.create().build();
        	HttpPost httpPost = new HttpPost(url);
        	if(parameters.size() > 0) {
            	List<NameValuePair> parameterList = new ArrayList<NameValuePair>();
            	for(Entry<String, String> entry : parameters.entrySet()) {
            		parameterList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
            	}
            	httpPost.setEntity(new UrlEncodedFormEntity(parameterList));  
        	}
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null && 200 == response.getStatusLine().getStatusCode()) {
            	result = streamToString(entity.getContent());
            	httpPost.abort();
            }
        }
        catch(Throwable ex) {
        	logger.error("execute HttpUtil.getString(" + url + ", " + parameters + ") faild", ex);
        	return null;
        }
        finally {
        	try {
        		if(null != httpClient) {
                     httpClient.close();
        		}
        	}
        	catch(Exception ex) {
        		logger.error("call httpClient.close() failed in HttpUtil.getString(" + url + ")", ex);
        		return result;
        	}
        }
        return result;
	}

	/**
	 * 获取远程HTTP服务指定链接的字符文本
	 * 
	 * @param url 指定链接
	 * @return 字符文本
	 */
	public static String getString(String url) {
		url = url.replace(" ", "%20");
		url = url.replace("\n", "%0A");
		url = url.replace("\r", "%0D");
		url = url.replace("\t", "%09");
		url = url.replace("\"", "%22");
		url = url.replace("'", "%27");
		String result = null;
		CloseableHttpClient httpClient = null;
        try {
        	httpClient = HttpClientBuilder.create().build();
        	HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null && 200 == response.getStatusLine().getStatusCode()) {
            	result = streamToString(entity.getContent());
                httpGet.abort();
            }
        }
        catch(Throwable ex) {
        	logger.error("execute HttpUtil.getString(" + url + ") faild", ex);
        	return null;
        }
        finally {
        	try {
        		if(null != httpClient) {
                     httpClient.close();
        		}
        	}
        	catch(Exception ex) {
        		logger.error("call httpClient.close() failed in HttpUtil.getString(" + url + ")", ex);
        		return result;
        	}
        }
        return result;
	}

	/**
	 * 解析URL生成目标地址和参数
	 * 
	 * @param url 地址
	 * @param parameters 参数
	 * @return 目标地址
	 */
	public static String parseURL(String url, Map<String, String> parameters) {
		int i = url.indexOf("?");
		if(-1 == i) {
			return url;
		}
		String[] parameterString = url.substring(i + 1).split("&");
		for(String parameter : parameterString) {
			int j = parameter.indexOf("=");
			if(-1 == j) {
				continue;
			}
			parameters.put(parameter.substring(0, j).trim(), parameter.substring(j + 1).trim());
		}
		return url.substring(0, i);
	}

	/**
	 * 从输入流中提取字符串数据
	 * 
	 * @param stream 输入流
	 * @return 字符串数据
	 */
	private static String streamToString(InputStream stream) {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder buider = new StringBuilder();
        // 提取数据
        String line = null;
        try {
        	while ((line = reader.readLine()) != null) {
        		buider.append(line + "\n");      
            }
        }
        catch (Exception ex) {
        	logger.error("execute HttpUtil.streamToString() faild", ex);
            return null;
        }
        finally {
        	try {
            	stream.close();
            }
        	catch (IOException e) {
        		logger.error("call InputStream.close() failed in HttpUtil.streamToString()", e);
               return buider.toString();
            }
        }
        return buider.toString();
    }
}
