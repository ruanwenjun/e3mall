package cn.e3mall.common.jedis;

/**
 * 
 * @author RUANWENJUN
 *
 */
public interface JedisClient {
	/**
	 * 添加key,value到redis,采用string key
	 * @param key
	 * @param value
	 * @return
	 */
	String set(String key, String value);
	/**
	 * 获得string key的value值
	 * @param key
	 * @return
	 */
	String get(String key);
	/**
	 * 判断是否存在这样的key
	 * @param key
	 * @return
	 */
	Boolean exists(String key);
	/**
	 * 改变key的过期时间
	 * @param key
	 * @param seconds
	 * @return
	 */
	Long expire(String key, int seconds);
	/**
	 * 查看key的过期时间
	 * @param key
	 * @return
	 */
	Long ttl(String key);
	/**
	 * key的值自增
	 * @param key
	 * @return
	 */
	Long incr(String key);
	/**
	 * 增加key,采用hash key
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	Long hset(String key, String field, String value);
	/**
	 * 获得hash key ,指定域的value
	 * @param key
	 * @param field
	 * @return
	 */
	String hget(String key, String field);
	/**
	 * 删除hash key 指定的域
	 * @param key
	 * @param field
	 * @return
	 */
	Long hdel(String key, String... field);
}
