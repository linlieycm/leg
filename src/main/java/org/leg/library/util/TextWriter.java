package org.leg.library.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.apache.log4j.Logger;

/**
 * UTF-8文本写入类
 */
public class TextWriter {
	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(TextWriter.class);
	/**
	 * Writer
	 */
	protected Writer writer = null;
	
	
	/**
	 * 打开文本文件准备写入
	 * 
	 * @param filePath 文件路径
	 * @return 打开成功返回true,失败返回false
	 */
	public boolean open(String filePath) {
		try {
			writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8"));
		}
		catch (UnsupportedEncodingException e) {
			logger.error("execute TextWriter.open(" + filePath + ") failed", e);
			return false;
		}
		catch (FileNotFoundException e) {
			logger.error("execute TextWriter.open(" + filePath + ") failed", e);
			return false;
		}  
		return true;
	}
	
	/**
	 * 创建文本文件，如果文件已存在，则删除重建
	 * 
	 * @param filePath 文件路径
	 * @return 打开成功返回true,失败返回false
	 */
	public boolean create(String filePath) {
		try {
			writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath, false), "UTF-8"));
		}
		catch (UnsupportedEncodingException e) {
			logger.error("execute TextWriter.create(" + filePath + ") failed", e);
			return false;
		}
		catch (FileNotFoundException e) {
			logger.error("execute TextWriter.create(" + filePath + ") failed", e);
			return false;
		}
		return true;
	}
	
	/**
	 * 写入文本文件
	 * 
	 * @param filePath 文件路径
	 * @return 打开成功返回true,失败返回false
	 */
	public void writeLine(String line) {
		if(null == writer || null == line) {
			return;
		}
		try {
			writer.write(line + "\n");
		}
		catch (IOException e) {
			logger.error("execute TextWriter.writeLine(" + line + ") failed", e);
		}
	}
	
	/**
	 * 写入文本文件
	 * 
	 * @param filePath 文件路径
	 * @return 打开成功返回true,失败返回false
	 */
	public void write(String line) {
		if(null == writer || null == line) {
			return;
		}
		try {
			writer.write(line);
		}
		catch (IOException e) {
			logger.error("execute TextWriter.write(" + line + ") failed", e);
		}
	}
	
	/**
	 * 关闭文本文件
	 */
	public void close() {
		if(null == writer) {
			return;
		}
		try {
			writer.close();
		}
		catch (IOException e) {
			logger.error("call Writer.close() failed", e);
		}
		writer = null;
	}
}
