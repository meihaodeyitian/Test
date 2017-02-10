package com.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.util.DESDecode;
import com.util.HttpsUtil;
import com.util.UnBind;

import net.sf.json.JSONObject;

public class Test142 {
	static HttpClient client = new HttpClient();
	static String longTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	static String sn = "test_4555" + String.valueOf(new Date().getTime() / 1000);
	static String testurl = "https://172.17.15.142:20010/mailbusi/cs/mail/";
//	static String testurl = "https://172.17.15.142:20011/mailbusi/cs/mail/";
	
	static String pnoneNum="13994000000";
	public static void main(String[] args) throws Exception {
	
		System.out.println("测试地址："+testurl);
		System.out.println("输入验证的号码：");
		Scanner s = new Scanner(System.in);
		 pnoneNum=s.nextLine();
		System.out.println("输入方法：1.是否绑定2.绑定号码3,发送短信4.查话费5，查账单6.差积分7.查流量8，查套餐：9.直接双微解绑,10.营业厅解绑");
		int num=s.nextInt();
		if(1==num){
			wetherBindP(pnoneNum);
		}else if (2==num){
			System.out.println("输入随机码：");
			Scanner p = new Scanner(System.in);
			String password=p.nextLine();
			bindPhone(pnoneNum,password);
		}else if (3==num){
			SendPasswordM(pnoneNum);
		}else if(4==num){
			System.out.println("输入密码：");
			Scanner p = new Scanner(System.in);
			String password=p.nextLine();
			 phoneFee(pnoneNum,password);
		}else if(5==num){
			System.out.println("输入密码：");
			Scanner p = new Scanner(System.in);
			String password=p.nextLine();
			checkBill(pnoneNum,password);
		}else if (6==num){
			System.out.println("输入密码：");
			Scanner p = new Scanner(System.in);
			String password=p.nextLine();
			checkScore(pnoneNum,password);
		}else if (7==num){
			System.out.println("输入密码：");
			Scanner p = new Scanner(System.in);
			String password=p.nextLine();
			checkFlows(pnoneNum,password);
		}else if (8==num){
			System.out.println("输入密码：");
			Scanner p = new Scanner(System.in);
			String password=p.nextLine();
			checkBag(pnoneNum,password);
		}else if(9==num){
			UnBind un=new UnBind();
			
			String field ="mailbusi_bindphone_"+pnoneNum;
			String key = "mailbusi_bindphone_all";
			System.out.println("field:"+field);
//			long a=JedisUtil.getCluster().hdel(key, field);
//			System.out.println("long a =="+a);
//			if(a<0){
//				System.out.println("a=="+a);
//				JedisUtil.getCluster().del(key);
//			}
			String res=un.post(pnoneNum);
			System.out.println("掉双微解绑结果：=="+res);
		}else if(10==num){
			UnbindPhone(pnoneNum);
		}
		
	}
	
	public static void wetherBindP(String phone) throws Exception{
		String url = testurl+"isBind";
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
			String result = HttpsUtil.doPost(url, str);
			System.out.println("请求返回: " +result);
		} catch (final Exception e) {
			System.out.println("error"+ e.getMessage());
		}
	}
	public static void bindPhone(String pnoneNum,String password) throws Exception{
		String url = testurl+"bindphone";
		try {
			HashMap<String, Object> req = new HashMap<String, Object>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			HashMap<String, Object> params = new HashMap<String, Object>();
			HashMap<String, Object> logmap = new HashMap<String, Object>();
			params.put("idItemRange", pnoneNum);
			params.put("password", password);
			params.put("pwdType", "02");
			params.put("userType", "01");
			params.put("requestTime", longTime);
			params.put("sn", sn);
			params.put("sourceId", "007");
			data.put("channelId", "20");
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
			String result = HttpsUtil.doPost(url, str);
			System.out.println("请求返回参数: " +result);
		} catch (final Exception e) {
			System.out.println("error"+ e.getMessage());
		}
	}
	
	public static void UnbindPhone(String pnoneNum) throws Exception{
		String url = testurl+"unBind";
		try {
			HashMap<String, Object> req = new HashMap<String, Object>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			HashMap<String, Object> params = new HashMap<String, Object>();
			HashMap<String, Object> logmap = new HashMap<String, Object>();
			params.put("idItemRange",pnoneNum);
//			params.put("password", "123456");
//			params.put("pwdType ", "02");
//			params.put("microAccount", "mail");
			params.put("userType", "01");
			params.put("requestTime", longTime);
			params.put("sn", sn);
			params.put("sourceId", "06");
			data.put("provCode", "371");
			String test =JSONObject.fromObject(params).toString();
			String t=DESDecode.encryptBasedDes(test);
			logmap.putAll(data);
			data.put("params", t);
			logmap.putAll(params);
			System.out.println("加密前的报文：" + logmap.toString());
			data.put("prodId", "06");
			data.put("provCode", "371");
			req.put("params", data);
			JSONObject jsonObject = JSONObject.fromObject(req);
			String str = jsonObject.toString();
			System.out.println("加密后的报文：" + str);
			String result = HttpsUtil.doPost(url, str);
			System.out.println("请求返回参数: " +result);
		} catch (final Exception e) {
			System.out.println("error"+ e.getMessage());
		}
	}
	
	public static void SendPasswordM(String pnoneNum) throws Exception{
		String url = testurl+"sendBindCode";
		try {
			HashMap<String, Object> req = new HashMap<String, Object>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			HashMap<String, Object> params = new HashMap<String, Object>();
			HashMap<String, Object> logmap = new HashMap<String, Object>();
			params.put("idItemRange", pnoneNum);
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
			String result = HttpsUtil.doPost(url, str);
			System.out.println("请求返回参数: " + result);
		} catch (final Exception e) {
			System.out.println("error"+ e.getMessage());
		}
	}
	public static void phoneFee(String pnoneNum,String password) throws Exception{
		String url = testurl+"realFeeQry";
		try {
			HashMap<String, Object> req = new HashMap<String, Object>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			HashMap<String, Object> params = new HashMap<String, Object>();
			HashMap<String, Object> logmap = new HashMap<String, Object>();
			params.put("idItemRange", pnoneNum);
			params.put("password", password);
			params.put("userType", "01");
			params.put("requestTime", longTime);
			params.put("sn",  sn);
			params.put("sourceId",  "007");
			data.put("provCode", "371");
			String test =JSONObject.fromObject(params).toString();
			String t=DESDecode.encryptBasedDes(test);
			logmap.put("params", test);
			logmap.putAll(data);
			data.put("params", t);
//			logmap.putAll(params);
			data.put("prodId", "01");
			System.out.println("加密前的报文："+logmap.toString());
			logmap.putAll(data);
			data.put("params", t);
			logmap.putAll(params);
			data.put("prodId", "01");
			data.put("provCode", "371");
			req.put("params", data);
			JSONObject jsonObject = JSONObject.fromObject(req);  
			String str =jsonObject.toString();
			System.out.println("加密后的报文："+str);
			String result = HttpsUtil.doPost(url, str);
			System.out.println("请求返回参数: " + result);
		} catch (final Exception e) {
			System.out.println("error"+e.getMessage());
		}
	}
	public static void checkBill(String pnoneNum,String password) throws Exception{
		String url = testurl+"billQry";
		try {
			HashMap<String, Object> req = new HashMap<String, Object>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			HashMap<String, Object> params = new HashMap<String, Object>();
			HashMap<String, Object> logmap = new HashMap<String, Object>();
			params.put("idItemRange", pnoneNum);
			params.put("password", password);
			params.put("userType", "01");
			params.put("requestTime", longTime);
			params.put("sn",  sn);
			params.put("sourceId",  "007");
			params.put("beginMonth", "201605");
			params.put("endMonth", "201606");
			data.put("provCode", "371");
			String test =JSONObject.fromObject(params).toString();
			String t=DESDecode.encryptBasedDes(test);
			logmap.put("params", test);
			data.put("prodId", "05");
			logmap.putAll(data);
			data.put("params", t);
			logmap.putAll(data);
			data.put("params", t);
			logmap.putAll(params);
			System.out.println("加密前的报文："+logmap.toString());
			req.put("params", data);
			JSONObject jsonObject = JSONObject.fromObject(req);  
			String str =jsonObject.toString();
			System.out.println("加密后的报文："+str);
			String result = HttpsUtil.doPost(url, str);
			System.out.println("请求返回参数: " + result);
		} catch (final Exception e) {
			System.out.println("error"+e.getMessage());
		}
	}
	public static void checkScore(String pnoneNum,String password) throws Exception{
  		String url = testurl+"pointQry";
  		try {
  			HashMap<String, Object> req = new HashMap<String, Object>();
  			HashMap<String, Object> data = new HashMap<String, Object>();
  			HashMap<String, Object> params = new HashMap<String, Object>();
  			HashMap<String, Object> logmap = new HashMap<String, Object>();
  			params.put("idItemRange", pnoneNum);
  			params.put("password", password);
  			params.put("pwdType ", "02");
  			params.put("userType", "01");
  			params.put("requestTime", longTime);
  			params.put("sn",  sn);
  			params.put("sourceId",  "007");
  			data.put("provCode", "371");
  			String test =JSONObject.fromObject(params).toString();
  			String t=DESDecode.encryptBasedDes(test);
  			logmap.put("params", test);
			data.put("prodId", "03");
			logmap.putAll(data);
  			data.put("params", t);
  			logmap.putAll(data);
  			data.put("params", t);
  			logmap.putAll(params);
  			System.out.println("加密前的报文："+logmap.toString());
  			req.put("params", data);
  			JSONObject jsonObject = JSONObject.fromObject(req);  
  			String str =jsonObject.toString();
  			System.out.println("加密后的报文："+str);
  			String result = HttpsUtil.doPost(url, str);
  			System.out.println("请求返回参数: " + result);
  		} catch (final Exception e) {
  			System.out.println("error"+e.getMessage());
  		}
  	}
	public static void checkBag(String pnoneNum,String password)throws Exception{
  		String url = testurl+"planRemainQry";
  		try {
  			HashMap<String, Object> req = new HashMap<String, Object>();
  			HashMap<String, Object> data = new HashMap<String, Object>();
  			HashMap<String, Object> params = new HashMap<String, Object>();
  			HashMap<String, Object> logmap = new HashMap<String, Object>();
  			params.put("idItemRange", pnoneNum);
  			params.put("password", password);
  			params.put("pwdType ", "01");
  			params.put("userType", "01");
  			params.put("requestTime", longTime);
  			params.put("sn",  sn);
  			params.put("sourceId",  "04");
  			String test =JSONObject.fromObject(params).toString();
  			String t=DESDecode.encryptBasedDes(test);
  			logmap.putAll(data);
  			data.put("params", t);
  			logmap.putAll(params);
  			System.out.println("加密前的报文："+logmap.toString());
  			data.put("prodId", "04");
  			data.put("provCode", "371");
  			req.put("params", data);
  			JSONObject jsonObject = JSONObject.fromObject(req);  
  			String str =jsonObject.toString();
  			System.out.println("加密后的报文："+str);
  			String result = HttpsUtil.doPost(url, str);
  			System.out.println("请求返回参数: " + result);
  		} catch (Exception e) {
  			System.out.println("error"+ e.getMessage());
  		}
  	}
	public static void checkFlows(String pnoneNum,String password)throws Exception{

		String url = testurl+"flowsQry";
		try {
			HashMap<String, Object> req = new HashMap<String, Object>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			HashMap<String, Object> params = new HashMap<String, Object>();
			HashMap<String, Object> logmap = new HashMap<String, Object>();
			params.put("idItemRange", pnoneNum);
			params.put("password", password);
			params.put("pwdType ", "01");
			params.put("userType", "01");
			params.put("requestTime", longTime);
			params.put("sn",  sn);
			params.put("sourceId",  "007");
			data.put("provCode", "371");
			String test =JSONObject.fromObject(params).toString();
			String t=DESDecode.encryptBasedDes(test);
			logmap.putAll(data);
			data.put("params", t);
			logmap.putAll(params);
			System.out.println("加密前的报文："+logmap.toString());
			data.put("prodId", "02");
			req.put("params", data);
			JSONObject jsonObject = JSONObject.fromObject(req);  
			String str =jsonObject.toString();
			System.out.println("加密后的报文："+str);
			String result = HttpsUtil.doPost(url, str);
			System.out.println("请求返回参数: " + result);
		} catch (final Exception e) {
			System.out.println("error"+ e.getMessage());
		}
	}
	
	public static void isBindHttp(String pnoneNum)throws Exception{
		final PostMethod post = new PostMethod(testurl + "isBind");
		try {
			HashMap<String, Object> req = new HashMap<String, Object>();
			HashMap<String, Object> data = new HashMap<String, Object>();
			HashMap<String, Object> params = new HashMap<String, Object>();
			HashMap<String, Object> logmap = new HashMap<String, Object>();
			params.put("idItemRange", pnoneNum);
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
			client.executeMethod(post);
			System.out.println("请求返回状态码Status: " + post.getStatusCode());
			System.out.println("请求返回参数: " + post.getResponseBodyAsString());
		} catch (final Exception e) {
			System.out.println("error"+ e.getMessage());
		} finally {
			post.releaseConnection();
		}
	}
	
}

