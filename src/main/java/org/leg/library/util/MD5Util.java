package org.leg.library.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedAction;

/**
 * MD5实用类
 */
public class MD5Util {
	/**
	 * 默认的密码字符串组合
	 * apache校验下载的文件采用该组合
	 */
	private static final char HEXDIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f'};
	/**
	 * 加解密类实例
	 */
	private static MessageDigest messagedigest = null;
	
	
	/**
	 * 工具类隐藏构造函数
	 */
	private MD5Util() {}
	
	
	/**
	 * 获取文件MD5码
	 * @param filePath 被操作文件路径
	 * @return 文件MD5码，失败返回null
	 */
	public static String getFileMD5String(String filePath) {
		return getFileMD5String(new File(filePath));
	}

	/**
	 * 获取文件MD5码
	 * @param file 被操作文件
	 * @return 文件MD5码，失败返回null
	 */
	public static String getFileMD5String(File file) {
		if(null == file || !file.exists()) {
			return null;
		}
		if(null == getMessageDigest()) {
			return null;
		}
		FileInputStream in = null;
		FileChannel ch = null;
		String result = null;
		try
		{
			in = new FileInputStream(file);
			ch = in.getChannel();
			MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			synchronized(MD5Util.class) {
				messagedigest.update(byteBuffer);
				result = arrayToHex(messagedigest.digest());
			}
			closeMappedBuffer(byteBuffer);
		}
		catch(Exception ex) {
			return null;
		}
		finally {
			if(null != ch) {
				try {
					ch.close();
				}
				catch (IOException e) { }
				ch = null;
			}
			if(null != in) {
				try {
					in.close();
				}
				catch (IOException e) { 
					e.printStackTrace();
				}
				in = null;
			}
		}
		return result;
	}
	
	/***/
	public static final void closeMappedBuffer(final Buffer buffer) {
        if (null == buffer)
            return;
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                try {
                    final Method cleanerMethod = buffer.getClass().getMethod("cleaner");
                    if (null == cleanerMethod)
                        return null;
                    cleanerMethod.setAccessible(true);
                    final Object cleanerObj = cleanerMethod.invoke(buffer);
                    if (null == cleanerObj)
                        return null;
                    final Method cleanMethod = cleanerObj.getClass().getMethod("clean");
                    if (null == cleanMethod)
                        return null;
                    cleanMethod.invoke(cleanerObj);
                } catch (final Throwable e) {
                    // do nothing
                }
                return null;
            }
        });
    }

	/**
	 * 获取字符串的MD5码
	 * 
	 * @param 待操作的字符串
	 * @return 字符串MD5码
	 */
	public static String getMD5String(String s) {
		return getMD5String(s.getBytes());
	}

	/**
	 * 获取字节数组的MD5码
	 * 
	 * @param 待操作的字节数组
	 * @return 字节数组MD5码
	 */
	public static String getMD5String(byte[] bytes) {
		if(null == getMessageDigest()) {
			return null;
		}
		String result = null;
		synchronized(MD5Util.class) {
			messagedigest.update(bytes);
			result = arrayToHex(messagedigest.digest());
		}
		return result;
	}
	
	/**
	 * 创建指定文件的MD5文件
	 * 
	 * @param filePath 指定待创建MD5的文件
	 * @return 创建成功返回true，失败返回false
	 */
	public static boolean makeMD5File(String filePath) {
		String md5 = getFileMD5String(new File(filePath));
		if(null == md5) {
			return false;
		}
		TextWriter tw = new TextWriter();
		if(tw.open(filePath + ".md5")) {
			tw.write(md5);
			tw.close();
			return true;
		}
		return false;
	}

	/**
	 * 检查文件是否符合MD5规范
	 * 
	 * @param filePath 指定待检查的文件路径
	 * @return 检查合法返回true，失败返回false
	 */
	public static boolean checkFileMD5(String filePath) {
		String md5 = null;
		TextReader tr = new TextReader();
		if(tr.open(filePath + ".md5")) {
			md5 = tr.readLine();
			tr.close();
		}
		if(null == md5) {
			return false;
		}
		if(md5.equals(getFileMD5String(filePath))) {
			return true;
		}
		return false;
	}

	/**
	 * 获取可用加解密类实例
	 * 
	 * @return 可用加解密类实例
	 */
	protected static MessageDigest getMessageDigest() {
		if(null != messagedigest) {
			return messagedigest;
		}
		synchronized(MD5Util.class) {
			if(null != messagedigest) {
				return messagedigest;
			}
			try {
				messagedigest = MessageDigest.getInstance("MD5");
			}
			catch (NoSuchAlgorithmException ex){
				messagedigest = null;
			}
		}
		return messagedigest;
	}

	/**
	 * 获取字节数组转字符串
	 * 
	 * @param 待操作的字节数组
	 * @return 字符串
	 */
	private static String arrayToHex(byte bytes[]) {
	   return arrayToHex(bytes, 0, bytes.length);
	}

	/**
	 * 字节数组转字符串
	 * 
	 * @param 待操作的字节数组
	 * @param begin 其实索引
	 * @param length 字节长度
	 * @return 字符串
	 */
	private static String arrayToHex(byte bytes[], int begin, int length) {
	   StringBuffer stringbuffer = new StringBuffer(2 * length);
	   int k = begin + length;
	   for (int l = begin; l < k; l++) {
		   stringbuffer.append(HEXDIGITS[(bytes[l] & 0xf0) >> 4]);
		   stringbuffer.append(HEXDIGITS[bytes[l] & 0xf]);
	   }
	   return stringbuffer.toString();
	}
}
