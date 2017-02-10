package com.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.util.DESDecode;
import com.util.HttpsUtil;
import com.util.UnBind;

import net.sf.json.JSONObject;

public class Test85 {


	static HttpClient client = new HttpClient();
	static String longTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	static String sn = "test_4555" + String.valueOf(new Date().getTime() / 1000);
	static String testurl = "http://211.138.30.208:8443";
//	static String testurl = "https://172.17.15.142:20011/mailbusi/cs/mail/";
	
	static String pnoneNum="13994000000";
	public static void main(String[] args) throws Exception {
	
		System.out.println("测试地址："+testurl);
		System.out.println("输入验证的号码：");
		Scanner s = new Scanner(System.in);
		 pnoneNum=s.nextLine();
		System.out.println("输入方法：1.话费2.账单3,积分4.套餐");
		int num=s.nextInt();
		if(1==num){
			 phoneFee(pnoneNum,"");
		}else if (2==num){
			checkBill(pnoneNum,"");
		}else if (3==num){
			checkScore(pnoneNum,"");
		}else if(4==num){
			checkBag(pnoneNum,"");
		}else {
			
		}
		
	}

	public static void phoneFee(String pnoneNum,String password) throws Exception{
		String url = testurl+"/uip/getFareBalance";
		try {
			String str="user_mobile="+pnoneNum;
			System.out.println("str==="+str);
			url=url+"?"+str;
//			String param = url.replace("\"", "%22").replace("{", "%7b").replace("}", "%7d");  
			System.out.println("请求报文==="+url);
			String charset = "utf-8"; 
			String result = doGet(url, charset).toString();
			System.out.println("请求返回参数: " + result);
		} catch (final Exception e) {
			System.out.println("error"+e.getMessage());
		}
	}
	public static void checkBill(String pnoneNum,String password) throws Exception{
		String url = testurl+"/uip/getBillSum";
		try {
			System.out.println("输入开始月份yyyymm：");
			Scanner p = new Scanner(System.in);
			String bm=p.nextLine();
			System.out.println("输入结束月份yyyymm：");
			Scanner p2 = new Scanner(System.in);
			String em=p2.nextLine();
//			String str="{\"user_mobile\":\""+pnoneNum+"\",\"begin_month\":\""+bm+"\",\"end_month\":\""+em+"\"}";
			String str="user_mobile="+pnoneNum+"&begin_month="+bm+"&end_month="+em;
			System.out.println("str==="+str);
			url=url+"?"+str;
//			String param = url.replace("\"", "%22").replace("{", "%7b").replace("}", "%7d");  
			System.out.println("请求报文==="+url);
			String charset = "utf-8"; 
			String result = doGet(url, charset).toString();
			System.out.println("请求返回参数: " + result);
			
		} catch (final Exception e) {
			System.out.println("error"+e.getMessage());
		}
	}
	public static void checkScore(String pnoneNum,String password) throws Exception{
  		String url = testurl+"/uip/getPoints";
  		try {
//  	String str="{\"user_mobile\":\""+pnoneNum+"\"}";
  		String str="user_mobile="+pnoneNum;
		System.out.println("str==="+str);
		url=url+"?"+str;
//		String param = url.replace("\"", "%22").replace("{", "%7b").replace("}", "%7d");  
		System.out.println("请求报文==="+url);
		String charset = "utf-8"; 
		String result = doGet(url, charset).toString();
		System.out.println("请求返回参数: " + result);
		} catch (final Exception e) {
  			System.out.println("error"+e.getMessage());
  		}
  	}
	public static void checkBag(String pnoneNum,String password)throws Exception{
  		String url = testurl+"/uip/getPlanRemian";
  		try {
  			String str="user_mobile="+pnoneNum;
  			System.out.println("str==="+str);
  			url=url+"?"+str;
//  			String param = url.replace("\"", "%22").replace("{", "%7b").replace("}", "%7d");  
  			System.out.println("请求报文==="+url);
  			String charset = "utf-8"; 
  			String result = doGet(url, charset).toString();
  			System.out.println("请求返回参数: " + result);
  			
  		} catch (Exception e) {
  			System.out.println("error"+ e.getMessage());
  		}
  	}

	public static String doGet(String url, String charset) throws Exception {
		/*
		 * 使用 GetMethod 来访问一个 URL 对应的网页,实现步骤: 1:生成一个 HttpClinet 对象并设置相应的参数。
		 * 2:生成一个 GetMethod 对象并设置响应的参数。 3:用 HttpClinet 生成的对象来执行 GetMethod 生成的Get
		 * 方法。 4:处理响应状态码。 5:若响应正常，处理 HTTP 响应内容。 6:释放连接。
		 */
		/* 1 生成 HttpClinet 对象并设置参数 */
		HttpClient httpClient = new HttpClient();
		// 设置 Http 连接超时为5秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		/* 2 生成 GetMethod 对象并设置参数 */
		GetMethod getMethod = new GetMethod(url);
		// 设置 get 请求超时为 5 秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		
		String response = "";
		/* 3 执行 HTTP GET 请求 */
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			System.out.println("返回状态码=="+statusCode);
			/* 4 判断访问的状态码 */
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("请求出错: " + getMethod.getStatusLine());
			}
			/* 5 处理 HTTP 响应内容 */
			// HTTP响应头部信息，这里简单打印
//			Header[] headers = getMethod.getResponseHeaders();
//			for (Header h : headers)
//				System.out.println(h.getName() + "------------ " + h.getValue());
			// 读取 HTTP 响应内容，这里简单打印网页内容
//			byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
//			response = new String(responseBody, charset);
//			System.out.println("----------response:" + response);
//			String res = getMethod.getResponseBodyAsString();
//			System.out.println("res="+res);
			
			// 读取为 InputStream，在网页内容数据量大时候推荐使用
			 InputStream inputStream = getMethod.getResponseBodyAsStream();
			 
			 BufferedInputStream bis = new BufferedInputStream(inputStream);  
             byte[] bytes = new byte[1024];  
             ByteArrayOutputStream bos = new ByteArrayOutputStream();  
             int count = 0;  
             while((count = bis.read(bytes))!= -1){  
                 bos.write(bytes, 0, count);  
             }  
             byte[] strByte = bos.toByteArray();  
             response = new String(strByte,0,strByte.length,"utf-8");  
             bos.close();  
             bis.close();  
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("请检查输入的URL!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			System.out.println("发生网络异常!");
			e.printStackTrace();
		} finally {
			/* 6 .释放连接 */
			getMethod.releaseConnection();
		}
		return response;
	}

}
