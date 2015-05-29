package org.leg.siteweb.common;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import java.util.Date;

/**
 * 用户身份验证
 */
public class User {
	/**
	 * 用户ID的Cookie键
	 */
	public final static String COOKIE_U = "leg_u";
	
	
	/**
	 * 获取用户ID
	 * 
	 * @return 用户ID
	 */
	public static int getUserId() {
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		if(null == cookies) {
			return 0;
		}
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(COOKIE_U)) {
				return extractUserId(cookie.getValue());
			}
		}
		return 0;
	}

	/**
	 * 设置用户ID
	 * 
	 * @param userId 用户ID
	 */
	public static void setUserId(int userId) {
		Cookie cookie = new Cookie(COOKIE_U, generateUserId(userId));
		cookie.setMaxAge(60 * 30);
		ServletActionContext.getResponse().addCookie(cookie);
	}

	/**
	 * 从字符串中解出用户ID
	 * 
	 * @param code 字符串
	 * @return 用户ID
	 */
	private static int extractUserId(String code) {
		if(null == code || code.length() != 24) {
			return 0;
		}
		byte[] result = new byte[12];
		int i = 0;
		while(i < 12) {
			result[i] = (byte)(Integer.parseInt(code.substring(2 * i, 2 * i + 2), 16));
			i++;
		}
		long tick = ((((long) result[0] & 0xff) << 56) 
	               | (((long) result[1] & 0xff) << 0) 
	               | (((long) result[3] & 0xff) << 48) 
	               | (((long) result[4] & 0xff) << 8) 
	               | (((long) result[6] & 0xff) << 40) 
	               | (((long) result[7] & 0xff) << 16) 
	               | (((long) result[9] & 0xff) << 32) 
	               | (((long) result[10] & 0xff) << 24));
        int userId = ((((int) result[2] & 0xff) << 0) 
	               | (((int) result[5] & 0xff) << 8) 
	               | (((int) result[8] & 0xff) << 16) 
	               | (((int) result[11] & 0xff) << 24));
        if(Math.abs(tick - (new Date()).getTime()) >= 60 * 30 * 1000) {
        	return 0;
        }
		return userId;
	}

	/**
	 * 加密用户ID字符串
	 * 
	 * @param userId 用户ID
	 * @return 字符串
	 */
	private static String generateUserId(int userId) {
		byte[] result = new byte[12];
		long tick = (new Date()).getTime();
		result[0] = (byte) (tick >> 56);		// 0
        result[1] = (byte) (tick >> 0); 		// 7
        result[2] = (byte) (userId >> 0); 		//  0
		result[3] = (byte) (tick >> 48); 		// 1
        result[4] = (byte) (tick >> 8); 		// 6
        result[5] = (byte) (userId >> 8); 		//  8
		result[6] = (byte) (tick >> 40); 		// 2
		result[7] = (byte) (tick >> 16); 		// 5
        result[8] = (byte) (userId >> 16);		//  16
		result[9] = (byte) (tick >> 32); 		// 3
		result[10] = (byte) (tick >> 24); 		// 4
        result[11] = (byte) (userId >> 24); 	//  24
        String code = "";
        for(byte b : result) {
        	int i = 0;
        	if(b < 0) {
        		i = 256 + b;
        	}
        	else {
        		i = b;
        	}
        	String s = Integer.toHexString(i);
        	if(s.length() == 2) {
        		code += s;
        	}
        	else if(s.length() == 1) {
        		code += "0" + s;
        	}
        	else if(s.length() == 0) {
        		code += "00";
        	}
        	else {
        		code += "";
        	}
        }
		return code;
	}
}
