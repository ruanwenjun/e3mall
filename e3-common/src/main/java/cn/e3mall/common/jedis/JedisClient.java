package cn.e3mall.common.jedis;

import java.util.List;

public interface JedisClient {
	/**
	 * 设置 key value
	 * @param key
	 * @param value
	 * @return
	 */
	String set(String key, String value);
	/**
	 * 根据key 获得value
	 * @param key
	 * @return
	 */
	String get(String key);
	/**
	 * 判断指定的key 是否存在
	 * @param key
	 * @return
	 */
	Boolean exists(String key);
	/**
	 * 设置过期时间
	 * @param key
	 * @param seconds
	 * @return
	 */
	Long expire(String key, int seconds);
	/**
	 * 查看过期时间
	 * @param key
	 * @return
	 */
	Long ttl(String key);
	/**
	 * 自增
	 * @param key
	 * @return
	 */
	Long incr(String key);
	/**
	 * 设置hash key 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	Long hset(String key, String field, String value);
	/**
	 * 根据key field  获得hash的值
	 * @param key
	 * @param field
	 * @return
	 */
	String hget(String key, String field);
	/**
	 * 删除hash 中指定key 的field
	 * @param key
	 * @param field
	 * @return
	 */
	Long hdel(String key, String... field);
	/**
	 * 判断hash 中field是否存在
	 * @param key
	 * @param field
	 * @return
	 */
	Boolean hexists(String key, String field);
	/**
	 * 获得hash 的值
	 * @param key
	 * @return
	 */
	List<String> hvals(String key);
	/**
	 * 删除key
	 * @param key
	 * @return
	 */
	Long del(String key);
}
