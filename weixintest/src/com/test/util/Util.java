package com.test.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


public class Util {
	private static final String DEF_CHARSET="UTF-8";
	
	/**
	 * 向指定的地址发送get请求
	 * 如果地址中有中文可能会造成乱码,可以用URLEncoder.encode("中文", "UTF-8")处理
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		StringBuilder sBuilder = new StringBuilder();
		try {
			URL urlObj = new URL(url);
			//开链接
			URLConnection connection = urlObj.openConnection();
			InputStream is = connection.getInputStream();
			byte[] b = new byte[1024];
			int len;
			
			while ((len=is.read(b))!=-1 ) {
				sBuilder.append(new String(b,0,len)); 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return sBuilder.toString();
	}
	/**
	 * 向指定地址发送post请求,带着数据  
	 * data格式"qrcode=1111&longitude=21.222&latitude=23.344&address=空间大姐夫"
	 * @param url
	 * @param data
	 * @return
	 */
	public static String post(String url,String data) {
		StringBuilder sBuilder = new StringBuilder();
		try {
			URL urlObj = new URL(url);
			//开链接
			URLConnection connection = urlObj.openConnection();
			connection.setRequestProperty("charset", "utf-8");
			
			//要发送数据出去,必须要设置为可发送数据状态
			connection.setDoOutput(true);
			//获取输出流
			OutputStream os = connection.getOutputStream();
			//写出数据
			os.write(data.getBytes());
			os.close();
			//获取输入流读取数据
			InputStream is = connection.getInputStream();
			byte[] b = new byte[1024];
			int len;
			
			while ((len=is.read(b))!=-1 ) {
				sBuilder.append(new String(b,0,len)); 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sBuilder.toString();
	}
	public static void main(String[] args) {
		
		post("http://192.168.1.4:8080/zzserver/qrcodejb/saveQRCodeJB","qrcode=1111&longitude=21.222&latitude=23.344&address=空间大姐夫");

	}
}
