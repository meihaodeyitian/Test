package com.util;
import java.util.Scanner;

import org.apache.logging.log4j.util.PropertiesUtil;
import com.cmos.core.logger.Logger;
import com.cmos.core.logger.LoggerFactory;
import com.cmos.esbclient.bean.MessageInfo;
import com.cmos.esbclient.bean.RestMethodType;
import com.cmos.esbclient.remote.RestClientUtil;
/**
 * 解绑手机号
 * @author Administrator
 *
 */
public class UnBind {
	private static final Logger log = LoggerFactory.getActionLog(UnBind.class);
	private static final String serverUri="http://10.254.221.26:9099/auth/microBusiness/contract/";
//	private static final String serverUri ="http://192.168.100.5:9099/auth/microBusiness/contract/";
	/**
	 * 测试方法
	 */
	public String post(String phone)  {
		System.out.println("url=="+serverUri);
//		String str = "{\"object\":{\"target\":\"A\",\"busiCode\":\"isBind\",\"reqSource\":\"06\",\"reqParam\":{\"tradeTime\":\"2015-11-19 17:14:07\",\"tradeId\":\"1a76c000-9449-4c0c-a75f-82a6daeadd85\",\"reqData\":{\"idItemRange\":\"13653824211\"}}}}";
		String str= "{\"object\":{\"target\":\"A\",\"busiCode\":\"contractRelieve\",\"reqSource\":\"06\",\"reqParam\":{\"tradeTime\":\"2015-11-19 17:14:07\",\"tradeId\":\"1a76c000-9449-4c0c-a75f-82a6daeadd85\",\"reqData\":{\"idItemRange\":\""+phone+"\",\"microAccount\":\"13519506370_NGBA\",\"userType\":\"01\"}}}}";
		System.out.println("请求报文："+str);
		MessageInfo info = new MessageInfo();
		info.setInput(str);
		info.setUri(serverUri);
		info.setClientId("com.cmos.esb.provider.csMail");
		info.setRestMethodType(RestMethodType.DELETE);
		String result = null;
		try {
			result = RestClientUtil.invoke(info);
			log.debug("",result);
		} catch (Exception e) {
		}
		return result;
	}

	public static void main(String[] args)  {
		UnBind jc = new UnBind();
		System.out.println("输入解绑号码：");
		Scanner p = new Scanner(System.in);
		String phone=p.nextLine();
		 String r=jc.post(phone);
		 System.out.println("解绑返回："+r);
	}

}
