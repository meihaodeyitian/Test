package com.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
//import org.apache.http.client.params.ClientPNames;

import net.sf.json.JSONObject;

public class Http250 {

	HttpClient client2 = new HttpClient();
	String longTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	String sn = "test_4555" + String.valueOf(new Date().getTime() / 1000);

	public void test250(String testurl) throws IOException, ParseException{
		System.out.println("测试地址："+testurl);
		System.out.println("输入验证的号码：");
		Scanner s = new Scanner(System.in);
		String phone=s.nextLine();
		System.out.println("输入方法：1.是否绑定2.绑定号码3,发送短信4.fapiao");
		int num=s.nextInt();
		if(1==num){
			isBind(testurl,phone);
		}else if (2==num){
			System.out.println("输入随机码：");
			Scanner p = new Scanner(System.in);
			String password=p.nextLine();
			testBindphone(testurl,phone,password);
		}else if (3==num){
			sendBindCode(testurl,phone);
		}else if (4==num){
			String url = "http://172.17.15.250:20060/mailinterface/services/IOutService";
//			InvoiceUtil iu=new InvoiceUtil();
//			iu.testInvoice();
		}
	}
	public void testBindphone(String testurl,String phone,String pwd) {
		final PostMethod post = new PostMethod(testurl + "bindphone");
		try {
			HashMap<String, Object> req = new HashMap<String, Object>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			HashMap<String, Object> params = new HashMap<String, Object>();
			HashMap<String, Object> logmap = new HashMap<String, Object>();
			params.put("idItemRange", phone);
			params.put("password", pwd);
			params.put("pwdType ", "02");
			params.put("userType", "01");
			params.put("requestTime", longTime);
			params.put("sn", sn);
			params.put("sourceId", "007");
			data.put("provCode", "371");
			String test =JSONObject.fromObject(params).toString();
			String t=DESDecode.encryptBasedDes(test);
			logmap.putAll(data);
			data.put("params", t);
			logmap.putAll(params);
			System.out.println("加密前的报文：" + logmap.toString());
			data.put("prodId", "10");
			data.put("provCode", "371");
			req.put("params", data);
			JSONObject jsonObject = JSONObject.fromObject(req);
			String str = jsonObject.toString();
			System.out.println("加密后的报文：" + str);
			final RequestEntity re = new StringRequestEntity(str, "application/json", "utf-8");
			post.setRequestEntity(re);
			client2.executeMethod(post);
			System.out.println("请求返回状态码Status: " + post.getStatusCode());
			System.out.println("请求返回参数: " + post.getResponseBodyAsString());
		} catch (final Exception e) {
			System.out.println("error"+ e.getMessage());
		} finally {
			post.releaseConnection();
		}
	}
	
	public void isBind(String testurl,String phone) {
		final PostMethod post = new PostMethod(testurl + "isBind");
		try {
			HashMap<String, Object> req = new HashMap<String, Object>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			HashMap<String, Object> params = new HashMap<String, Object>();
			HashMap<String, Object> logmap = new HashMap<String, Object>();
			params.put("idItemRange", phone);
			// params.put("password", "1000");
			// params.put("pwdType ", "02");
			params.put("userType", "01");
			params.put("requestTime", longTime);
			params.put("sn", sn);
			params.put("sourceId", "007");
			data.put("provCode", "371");
			String test = JSONObject.fromObject(params).toString();
			String t = DESDecode.encryptBasedDes(test);
			data.put("params", t);
			logmap.putAll(data);
			data.put("params", t);
			logmap.putAll(params);
			System.out.println("加密前的报文：" + logmap.toString());
			data.put("prodId", "12");
			data.put("provCode", "371");
			req.put("params", data);
			JSONObject jsonObject = JSONObject.fromObject(req);
			String str = jsonObject.toString();
			System.out.println("加密后的报文：" + str);
			final RequestEntity re = new StringRequestEntity(str, "application/json", "utf-8");
			post.setRequestEntity(re);
			System.out.println("------------1---------");
//			client2.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
			client2.executeMethod(post);
			System.out.println("请求返回状态码Status: " + post.getStatusCode());
			System.out.println("请求返回参数: " + post.getResponseBodyAsString());
		} catch (final Exception e) {
			System.out.println("error"+ e.getMessage());
		} finally {
			post.releaseConnection();
		}
	}
	
	public void sendBindCode(String testurl,String phone) {
		final PostMethod post = new PostMethod(testurl + "sendBindCode");
		try {
			HashMap<String, Object> req = new HashMap<String, Object>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			HashMap<String, Object> params = new HashMap<String, Object>();
			HashMap<String, Object> logmap = new HashMap<String, Object>();
			params.put("idItemRange", phone);
			params.put("userType", "01");
			params.put("requestTime", longTime);
			params.put("sn", sn);
			params.put("sourceId", "06");
			data.put("provCode", "371");
			String test = JSONObject.fromObject(params).toString();
			String t = DESDecode.encryptBasedDes(test);
			logmap.putAll(data);
			data.put("params", t);
			logmap.putAll(params);
			System.out.println("加密前的报文：" + logmap.toString());
			data.put("prodId", "11");
			data.put("provCode", "371");
			req.put("params", data);
			JSONObject jsonObject = JSONObject.fromObject(req);
			String str = jsonObject.toString();
			System.out.println("加密后的报文：" + str);
			final RequestEntity re = new StringRequestEntity(str, "application/json", "utf-8");
			post.setRequestEntity(re);
			client2.executeMethod(post);
			System.out.println("请求返回状态码Status: " + post.getStatusCode());
			System.out.println("请求返回参数: " + post.getResponseBodyAsString());
		} catch (final Exception e) {
			System.out.println("error"+ e.getMessage());
		} finally {
			post.releaseConnection();
		}
	}
}
