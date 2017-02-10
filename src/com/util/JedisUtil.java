package com.util;

import java.util.HashSet;
import java.util.Set;

import com.cmos.core.logger.Logger;
import com.cmos.core.logger.LoggerFactory;
import com.util.Constants.SYSTEM_PROP_CONFIG;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;


public class JedisUtil {
	private static final Logger logger = LoggerFactory.getServiceLog(JedisUtil.class);
	private static Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
	private static JedisCluster jedisCluster = null;
	static {
	try {
		jedisCluster = reloadJedisCluster();
	} catch (Exception e) {
		logger.info("jedisCluster异常",e.getMessage());
	}
}

/**
 * 获取JedisCluster对象
 * 
 * @return
 * @
 */
public static JedisCluster getCluster()  {
	if (jedisCluster == null) {
		logger.debug("","进入去了==================");
		synchronized (JedisUtil.class) {
			jedisCluster = reloadJedisCluster();
		}
	}
	/*logger.debug("","===========set:"+jedisCluster.set("skl", "value"));
	logger.debug("","===========get:"+jedisCluster.get("skl"));*/
	return jedisCluster;
}
/*yangzy自己添加*/
//public static Jedis getCluster()  {
//	return new Jedis("127.0.0.1",6379);
//}

	
	public static JedisCluster reloadJedisCluster()  {
		logger.debug("","初始化实体");
		JedisCluster cluster = null;
		String redisAddrCfg = Constants.REDIS_ADDR_CFG;
		if (StringUtil.isEmpty(redisAddrCfg)
				|| redisAddrCfg.split(SYSTEM_PROP_CONFIG.REDIS_CFG_SPLIT).length == 0) {
			logger.error("error", "System.properties中REDIS_ADDR_CFG属性为空");
		}
		String[] addrs = redisAddrCfg.split(SYSTEM_PROP_CONFIG.REDIS_CFG_SPLIT);
		for (String addr : addrs) {
			String[] ipAndPort = addr.split(":");
			if (ipAndPort == null || ipAndPort.length != 2
					|| !StringUtil.isNum(ipAndPort[1])) {
				logger.error("error", "System.properties中REDIS_ADDR_CFG属性为空");
			}
			jedisClusterNodes.add(new HostAndPort(ipAndPort[0], Integer
					.parseInt(ipAndPort[1])));
		}
		//cluster = new JedisCluster(jedisClusterNodes, 2000, 6);
		// private int maxTotal = 8;
		//private int maxIdle = 8;
		//private int minIdle = 0;
		/*JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxWaitMillis(20000);
		config.setMaxTotal(8);
		config.setMaxIdle(8);
		config.setMinIdle(0);
		
		cluster = new JedisCluster(jedisClusterNodes, 2000, 6,config);
		*/
		cluster = new JedisCluster(jedisClusterNodes, 2000 , 6);
		return cluster;
	}
	
	public static void main(String[] args)  {
		
		
		
	}
	public static String getBase64(){
		return "aa";
	}
}
