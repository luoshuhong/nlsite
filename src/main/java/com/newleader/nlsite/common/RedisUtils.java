package com.newleader.nlsite.common;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis操作工具类
 * @author luoshuhong
 * @Company donottel.me
 * 2015年10月10日
 */
@Service
public class RedisUtils {
	@Resource(name = "jedisPool")
    private JedisPool jedisPool;
	
	/**
     * 序列化成给定模式的JSON并保存到redis
     * @param args
     * @return
     */
    @SuppressWarnings({ "finally", "deprecation" })
	public String lpop(String redisKey){
    	Jedis jedis = jedisPool.getResource();
    	String result = "";
    	// 连接redis失败，返回
    	if(jedis == null){
    		return result;
    	}
		try {
			
			// 保存到redis中
//			Transaction transaction = jedis.multi();
//			transaction.lpush("order_push", json);
//			transaction.exec();
			
			result = jedis.rpop(redisKey);
//			String result1 = jedis.lpop(redisKey);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedisPool.returnResource(jedis);
			return result;
		}
    }
    
    
}
