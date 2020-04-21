package cn.com.blackboard.training.utils;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**      
 * <b>Redis工具类</b>
 * 
 * @author: zhangyuting  <br>
 * @date:   2020-01-21  <br>  
 */  
@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RedisUtil {
	
	/** 静态类注入 */
	@Resource(name = "redisTemplate")
	private RedisTemplate injectRedisTemplate;

	@Autowired
	private static RedisTemplate redisTemplate;

	@PostConstruct
	public void init() {
		redisTemplate = injectRedisTemplate;
	}

	/**
	 * redis ops的set方法
	 * 
	 * @param key
	 * @param value
	 */
	public static void opsSet(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * <b>redis ops的set方法</b>
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 * @param TimeUnit
	 */
	public static void opsSet(String key, Object value, Long timeout, TimeUnit TimeUnit) {
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit);
	}

	/**
	 * <b>redis ops的get方法</b>
	 * 
	 * @param key
	 * @return
	 */
	public static Object opsGet(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * <b>redis leftPush</b>
	 * 
	 * @param key
	 * @param data
	 */
	public static void leftPush(String key, Object data) {
		redisTemplate.opsForList().leftPush(key, data);
	}

	/**
	 * <b>redis leftPop</b>
	 * 
	 * @param key
	 * @return
	 */
	public static Object leftPop(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}
	
}
