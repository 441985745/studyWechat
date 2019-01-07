package com.test.service;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.test.model.AccessToken;
import com.test.util.*;

import jdk.nashorn.api.scripting.URLReader;
import net.sf.json.JSONObject;


public class WxService {
	private static final String APPID="wxbc263ebd77c5ded9";
	private static final String APPSECRET="fb8ec98541935103a0140e1e5314915f";
	private static final String GET_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//用于存储token
	private static AccessToken at;
	/**
	 * 获取token
	 */
	private static void getToken() {
		String url = GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		System.out.println(url);
		String str = Util.get(url);
		
		
		
		JSONObject object= JSONObject.fromObject(str);
		String token = object.getString("access_token");
		String expireIn = object.getString("expires_in");
		//创建token对象并存起来
		at = new AccessToken(token,expireIn);
		
		
	}
	/**
	 * 向外暴露获取token的方法
	 * @return
	 */
	public static String getAccessToken() {
		if(at==null || at.isExpired()) {
			System.out.println("1");
			
			getToken();
		}
		return at.getAccessToken();
	}
	/**
	 * 获取用户的基本信息
	 * @param openId
	 * @return
	 */
	public static String getUserInfo(String accessToken,String openId) {
		String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		url = url.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		String result=Util.get(url);
		System.out.println("getUserInfo:"+result);
		return  result;
	}
	/**
	 * 根据code获取openId
	 * 注意:此方法只能获取openid,accesstoken要用getAccessToken()获取,否则报错;
	 * @param code
	 * @return
	 * {"access_token":"17_GGijMMF0rx8PpOrQ7QaHXzXvWa5TuaHl4TJ_uTrL0t4SJNy9KdX2OqUytRmMnFto919ikZEH_pwNolsolZUJ49PaEMSP1lXc80VKa53r0fo","expires_in":7200,"refresh_token":"17_MhG27a3Y84FJEAms_8P-LQx1sfCRwlRy5DFG3zHJ28XuSqLr94GgSd8H9fZVx7Cz8eoy5nMuO0RfflJHcOi7K3Izvf-z2nBhFB2dlScfqgo","openid":"opPyS0gqyIf5bDT0hpyahIAOl5iU","scope":"snsapi_userinfo"}
	 */
	public static String getOpenId(String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		url=url.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
		String result = Util.get(url);
		System.out.println("getOpenId:"+result);
		return result;
	}
	public static void main(String[] args) {
		//getAccessToken();
		//getUserInfo("");
	}
	
	
	
	
	/**
	 * 验证签名
	 */
	public static boolean check(String token, String timestamp, String nonce, String signature) {
		/**
		 * 1）将token、timestamp、nonce三个参数进行字典序排序 
		 * 2）将三个参数字符串拼接成一个字符串进行sha1加密 
		 * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		 */
		String[] strs=new String[] {token,timestamp,nonce};
		System.out.println(strs);
		Arrays.sort(strs);
		System.out.println(strs);
		String str = strs[0]+strs[1]+strs[2];
		String mysig=sha1(str);
		System.out.println(mysig);
		System.out.println(signature);
		
		return mysig.equalsIgnoreCase(signature);
	}
	
	private static String sha1(String str) {
		//获取一个加密对象
		try {
			MessageDigest md = MessageDigest.getInstance("sha1");
			//加密
			byte[] digest = md.digest(str.getBytes());
			char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
			StringBuilder sb = new StringBuilder();
			//处理加密结果
			for(byte b:digest) {
				sb.append(chars[(b>>4)&15]);
				sb.append(chars[b&15]);
				
			}
			return sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> parseRequest(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			InputStream is=request.getInputStream();
			SAXReader reader = new SAXReader();
			//读取输入流
			Document document = reader.read(is);
			//根据文档对象获取节点
			Element root = document.getRootElement();
			//获取根节点的所有子节点
			List<Element> elements = root.elements();
			for (Element e : elements) {
				map.put(e.getName(), e.getStringValue());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

}
