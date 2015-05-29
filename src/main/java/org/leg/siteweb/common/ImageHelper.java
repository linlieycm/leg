package org.leg.siteweb.common;

import org.leg.library.util.MD5Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 图片辅助类
 */
public class ImageHelper {
	/**
	 * 隐藏构造函数
	 */
	private ImageHelper() { }
	
	/**
	 * 保存图片
	 * 
	 * @param file 上传的图片文件
	 * @param filename 上传的图片文件名称
	 * @return 新的图片名称
	 */
	public static String saveImage(File file, String filename) {
		String suffix = filename.substring(filename.lastIndexOf(".") + 1);
		String result = MD5Util.getFileMD5String(file) + "."+ suffix;
		copyFile(file.getAbsolutePath(), Configuration.imageFolder() + result);
		return result;
	}

	/**
	 * 文件拷贝
	 * 
	 * @param source 源文件
	 * @param destination 目标文件
	 * @return 是否执行成功
	 */
	public static boolean copyFile(String source, String destination) {
		File srcFile = new File(source);
		if(!srcFile.exists()) {
			return false;
		}
		FileChannel in = null;
		FileChannel out = null;
		try {
			in = new FileInputStream(srcFile).getChannel();
			out = new FileOutputStream(new File(destination)).getChannel();
			in.transferTo(0, in.size(), out);
		}
		catch(Exception ex) {
			return false;
		}
		finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (IOException e) {
					return false;
				}
			}
			if (out != null) {
				try {
					out.close();
				}
				catch (IOException e) {
					return false;
				}
			}
		}
		return true;
	}
}
