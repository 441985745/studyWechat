package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.service.WxService;

/**
 * Servlet implementation class Wxservlet
 */
@WebServlet("/wx")
public class Wxservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Wxservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("get");
		/**
		 * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
			timestamp	时间戳
			nonce	随机数
			echostr	随机字符串

		 */
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		System.out.println(signature);
		System.out.println(timestamp);
		System.out.println(nonce);
		System.out.println(echostr);
		String token="zsl";//微信后台中配置的
		//校验请求
		if(WxService.check(token,timestamp,nonce,signature)) {
			System.out.println("接入成功");
			PrintWriter out = response.getWriter();
			//原样返回echostr参数
			
			out.print(echostr);
			out.flush();
			out.close();
		}else {
			System.out.println("接入失败");

		}
	}

	/**
	 * 接收消息和时间推送
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post");
//		ServletInputStream is = request.getInputStream();
//		byte[] b = new byte[1024];
//		int len;
//		StringBuilder sb = new StringBuilder();
//		while((len=is.read(b))!=-1) {
//			sb.append(new String(b,0,len));
//		}
//		System.out.println(sb.toString());
		//处理消息和事件推送
		Map<String,String> requestMap=WxService.parseRequest(request);
		System.out.println(requestMap);
	}

}
