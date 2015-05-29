package org.leg.siteweb.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.leg.library.json.JSONObject;
import org.leg.library.json.JSONString;
import org.leg.library.net.HttpUtil;

/**
 * 微信交互辅助类
 */
public class WechartHelper {
	/**
	 * 微信合作ID
	 */
	private final static String WECHART_CORPID = "wxb849aa4895921c8c";
	/**
	 * 微信合作密钥
	 */
	private final static String WECHART_CORPSECRET = "-eEVOChChj9Xf5Z_D7WglXBjxzaCf-tLc04K6IOD_BnMm74-x5ZMdBb5e2xiiWTs";
	

	/**
	 * 隐藏构造函数
	 */
	private WechartHelper() {}
	
	/**
	 * 获取JS坐标获取代码
	 * 
	 * @param uri 当前URL，需要带上http://
	 * @return JS坐标获取代码
	 */
	public static String location(String uri) {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + WECHART_CORPID + "&corpsecret=" + WECHART_CORPSECRET;
		String result = HttpUtil.getString(url);
		if(null == result) {
			return "";
		}
		JSONObject object = JSONObject.convert(result);
		if(null == object) {
			return "";
		}
		String accessToken = ((JSONString) object.get("access_token")).getValue();
		//
		url = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=" + accessToken;
		result = HttpUtil.getString(url);
		if(null == result) {
			return "";
		}
		object = JSONObject.convert(result);
		if(null == object) {
			return "";
		}
		String ticket = ((JSONString) object.get("ticket")).getValue();
		//
		long timestamp = (new Date()).getTime();
		String string = "jsapi_ticket=" + ticket + "&noncestr=leg&timestamp=" + timestamp + "&url=" + uri;
		String signature = getSHA1(string);
		//
		return "<script>var latitude = 0;var longitude = 0;wx.config({\"debug\": false,\"appId\": \"" + WECHART_CORPID + "\",\"timestamp\":" + timestamp + " ,\"nonceStr\": \"leg\",\"signature\": \"" + signature + "\",\"jsApiList\": [\"getLocation\"]});wx.ready(function(){wx.getLocation({success: function (res) {latitude = res.latitude;longitude = res.longitude;}});});</script>";
	}

	/**
	 * SHA1加密
	 * 
	 * @param decript 带加密字符串
	 * @return 加密后字符串
	 */
	public static String getSHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
