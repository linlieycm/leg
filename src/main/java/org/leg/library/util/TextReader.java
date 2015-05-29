package org.leg.library.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * UTF-8文本读取类
 */
public class TextReader {
	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(TextReader.class);
	/**
	 * FileInputStream
	 */
	protected FileInputStream inputStream = null; 
	/**
	 * BufferedReader
	 */
	protected BufferedReader bufferedReader = null;
	
	
	/**
	 * 打开文本文件
	 * 
	 * @param filePath 文件路径
	 * @return 打开成功返回true,失败返回false
	 */
	public boolean open(String filePath) {
		if(null == filePath || filePath.equals("")) {
			return false;
		}
		File file = new File(filePath);
		if(!file.exists() || file.isDirectory()) {
			return false;
		}
		try {
			inputStream = new FileInputStream(file);
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		}
		catch(FileNotFoundException ex) {
			logger.error("execute TextReader.open(" + filePath + ") failed", ex);
			return false;
		}
		catch(Exception ex) {
			try {
				inputStream.close();
			}
			catch (IOException e) {
				logger.error("call FileInputStream.close() failed", e);
				return false;
			}
			inputStream = null;
			return false;
		}
		return true;
	}
	
	/**
	 * 按行读取
	 * 
	 * @return 文本行字符串
	 */
	public String readLine() {
		String ret = null;
		try {
			ret = bufferedReader.readLine();
		}
		catch(IOException ex) {
			logger.error("execute TextReader.readLine failed", ex);
			return null;
		}
		return ret;
	}

	/**
	 * 读取所有文件内容
	 * 
	 * @return 文件内容
	 */
	public String readAll() {
		StringBuilder sb = new StringBuilder();
		while(true) {
			String line = readLine();
			if(null == line) {
				break;
			}
			sb.append(line);
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * 关闭文本文件
	 */
	public void close() {
		if(null != bufferedReader) {
			try {
				bufferedReader.close();
			}
			catch (IOException ex) {
				logger.error("call BufferedReader.close() failed", ex);
			}
			bufferedReader = null;
		}
		if(null != inputStream) {
			try {
				inputStream.close();
			}
			catch (IOException ex) {
				logger.error("call FileInputStream.close() failed", ex);
			}
			inputStream = null;
		}
	}
}
