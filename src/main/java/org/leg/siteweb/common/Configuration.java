package org.leg.siteweb.common;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.leg.library.xml.XMLNode;
import org.leg.library.xml.core.IXMLNode;

/**
 * 系统配置
 */
public class Configuration {
	/**
	 * 隐藏构造函数
	 */
	private Configuration() {}
	
	/**
	 * 获取存储相关配置对象
	 * 
	 * @return 存储相关配置对象
	 */
	public static IXMLNode storage() {
		return XMLNode.convert(localResource("storage.xml"));
	}
	
	/**
	 * 获取图片目录
	 * 
	 * @return 图片目录
	 */
	public static String imageFolder() {
		return storage().visit("image").get("path");
	}

	/**
	 * 获取指定名称的本地资源内容
	 * 
	 * @param resource 资源名称
	 * @return 资源内容
	 */
	public static String localResource(String resource) {
		StringBuilder result = new StringBuilder("");
		ClassLoader classLoader = Configuration.class.getClassLoader();
	    try {
			File file = new File(classLoader.getResource("config/local/" + resource).getFile());
	    	Scanner scanner = new Scanner(file);
	        while (scanner.hasNextLine()) {
	            result.append(scanner.nextLine()).append("\n");
	        }
	        scanner.close();
	    }
	    catch (IOException e) {
	        Logger.instance().error("扫描资源文件" + resource + "失败", e);
	        return null;
	    }
	    return result.toString();
	}
}
