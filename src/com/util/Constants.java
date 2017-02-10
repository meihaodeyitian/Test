package com.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 */
public final class Constants {
	
	
	
	public static final String SESSION_USER = "user";
	public static final String NAMESPACE_SHARE = "/sh";
	public static final int SESSION_TIMEOUT = 60 * 60 * 24 * 10;//session 超时时间，单位秒
	public static final String RZPT_SourceID = "37";  //统一认证平台RZPT_SourceID
	public static final int RZPT_TIMEOUT_COUNT = 120;  //统一认证平台登录过期时间
	public static final String PROVINCE_CODE = "PROVINCE_CODE";
	public static final String ISBIND_SIGN = "1";
	public static final String UNBIND_SIGN = "0";
	public static final String BIND_PHONE_KEY = "mailbusi_bindphone_";
	public static final String NEW_BIND_PHONE_KEY = "new_mailbusi_";
	public static final String NEW_BIND_ALL = "new_mailbusi_bind_all";
	public static final String BIND_ALL = "mailbusi_bindphone_all";
	public static final String REDIS_ADDR_CFG="172.17.16.41:7000,172.17.16.41:7001,172.17.16.42:7000,172.17.16.42:7001,172.17.16.43:7000,172.17.16.43:7001,172.17.16.44:7000,172.17.16.44:7001,172.17.16.45:7000,172.17.16.45:7001,172.17.16.46:7000,172.17.16.46:7001";
	/*Redis*/
	public interface SYSTEM_PROP_CONFIG {
		String REDIS_ADDR_CFG = "REDIS_ADDR_CFG";// Redis IP地址配置，各ip之间以半角逗号","分隔
		String REMOTE_MQ_IP_PORT = "REMOTE_MQ_IP_PORT";
		String REDIS_CFG_SPLIT = ",";// 分隔符 
		String REDIS_EBS_HTML_KEY_FORMAT="%s:ebs:to:%s:html";
		String REDIS_EBS_H5_KEY_FORMAT = "%s:ebs:to:%s:h5";
		int REDIS_EBS_TIMEOUT=172800;
		int REDIS_VALUE_LENGTH_LIMIT=50000;
		//业务流水号10分钟超时
		int REDIS_EBS_YWLSH_TIMEOUT=600;
		String NEW_LINE = "#nbsp";
	}
	
	public interface SMS_CONFIG {
		String SMS_APIKEY = "SMS_APIKEY";// Redis IP地址配置，各ip之间以半角逗号","分隔
		String SMS_USERNAME = "SMS_USERNAME";
		String SMS_URL = "SMS_URL";// 分隔符 
		String HTTPCLIENT_TIMEOUT="HTTPCLIENT_TIMEOUT";
	}
	// System配置文件key值
		public interface SYS_CONFIG {
			String REMOTE_MQ_IP_PORT = "REMOTE_MQ_IP_PORT";// MQ的IP配置
			String EBS_PDF_TO_JPG_CALL_PROCEDURE_PATH = "EBS_PDF_TO_JPG_CALL_PROCEDURE_PATH";
			String EBS_MAIL_SEND_URL = "EBS_MAIL_SEND_URL";
		}
		protected  static Map<String,String> INTERFACE_STATE_MAP ;
		public static void setINTERFACE_STATE_MAP(Map<String, String> map) {
			INTERFACE_STATE_MAP = map;
		}
		public static Map<String, String> getINTERFACE_STATE_MAP() {
			return INTERFACE_STATE_MAP;
		}
		// Topic的值，注意，消费时的topic需与生产者生产时的值相同,消费类在mqconsumer工程
		public interface MQ_TOPIC{
			String TOPIC_LOG = "TOPIC_BUSI_LOG";
			String TOPIC_BREAKPOINT = "TOPIC_BREAKPOINT";
			String LOG_TAGS_BUSI_OUT="TOPIC_BUSI_LOG_OUT";
			String LOG_TAGS_BUSI_SW="TOPIC_BUSI_LOG_SW";
			String TOPIC_OPER_LOG = "TOPIC_OPER_LOG";// 异步日志处理类
			String TOPIC_SMS = "TOPIC_SMS";
			String TOPIC_BROADCAST="TOPIC_BROADCAST";
			String TAG_MAILBUSI_INTERFACE_STATE="TAG_MAILBUSI_INTERFACE_STATE"; //接口状态
			String TOPIC_EBS_TAGS_ERRSEND="TAGS_EBS_ERRSEND";
			/**
			 * 账单分包topic
			 */
			String TOPIC_DATA_FILTER = "TOPIC_DATA_FILTER";						
		}

	public interface MAILBUSI_CONFIG{
		String TYPE_INVOICE = "INVOICE";
		String MAILINTEFACE_STAT = "COUNT_mailinterface_request";
		String NEW_BIND_ALL = "new_mailbusi_bind_all"; //所以新绑定的号码
		String INTERFACE_OPEN_SIGN="1";
		String INTERFACE_CLOSE_SIGN="0";
		String REDIS_INTERFACE_STATE_KEY="mailbusi_interface_state";
	}
	/*
	 * 缓存配置
	 */
	public interface CACHE_CONFIG{
		int CACHE_EBS_TIMEOUT=259200;//3天
		String CACHE_EBS_HEAD="EBS";
		int CACHE_EBS_TIMEOUT_TEST=600;
	}
	/*
	 * 统计成功率配置
	 */
	public interface REQRATE_CONFIG{
		String LOG_PATH="home/temp/log/requestStat.log";
		int STAT_SECTION =5;//统计区间五分钟
		String FAIL_SIGN="-9999";
		String SUC_SIGN="0";
		double SUC_RATE = 0.75;
//		String PROVCODE_STR="100;210;220;230;851;270;290;311;371;551;591;971;931;571;898;451;250;431;951;531;351;991;200;240;771;731;791;471;871;280;891";
		String PROVCODE_STR="371;571";
//		String INTERFACE_TYPE_STR ="realFeeQry;billQry;flowsQry;pointQry;planRemainQry;bindphone;sendBindCode";
		String INTERFACE_TYPE_STR ="realFeeQry;billQry";
	}
	
	/*
	 * 临时文件存放路径
	 */
	public interface SENDEMAIL_DATA_PATH{
		String SENDEMAIL_DATA_PATH="SENDEMAIL_DATA_PATH";
	}
	/*
	 * 双微调用地址
	 */
//	public interface INVOKE_ADDRESS{
//		String SENDCODE_ADD="http://192.168.100.5:9099/auth/microBusiness/password";
//		String ISBIND_ADD="http://192.168.100.5:9099/auth/microBusiness/contract/";
//		String REALFEE_ADD="http://192.168.100.5:9099/business/microBusiness/fee/";
//		String FLOWS_ADD="http://192.168.100.5:9099/business/microBusiness/flows/";
//		String POINT_ADD="http://192.168.100.5:9099/business/microBusiness/points/";
//		String PLANREMAIN_ADD="http://192.168.100.5:9099/business/microBusiness/offer/";
//		String QUERYBILL_ADD= "http://192.168.100.5:9099/business/microBusiness/bill/";
//		String BINDPHONE_ADD="http://192.168.100.5:9099/auth/microBusiness/contract";
//	}
	public static final String INVOKE_ClIENTID = "com.cmos.esb.provider.csMail";
	/*
	 * 总开关
	 */
	public interface SWITCH_SFIP{
		String SWITCH_SFIP="SWITCH_SFIP";
	}
	/*
	 * 数据库中的状态
	 */
	public interface EBS_LOG_STATE {
		String STATE_START = "0";// 开始
		String STATE_OVER = "1";// 结束 
		String STATE_ERROR = "2";// 出错
	}
	/*
	 * 数据库中的是否删除
	 */
	public interface EBS_LOG_DEL {
		String DEL_NO = "0";// 开始
		String DEL_YES = "1";// 结束 
	}
	/*
	 * 数据库中的操作次数
	 */
	public interface EBS_LOG_HANDLE_TME {
		String HANDLE_ONE = "1";// 开始
		String HANDLE_TWO = "2";// 结束 
		String HANDLE_THREE = "3";// 出错
	}

	
	
	
}
