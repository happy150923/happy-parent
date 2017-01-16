package com.happy.redis.jedis.wrapper;

import com.happy.redis.jedis.ObjectPoolConfigConfBased;
import com.happy.redis.Constants;
import com.happy.redis.RedisTypes;
import com.happy.redis.RedisClient;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.happy.log.Log;
import org.happy.log.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;

import java.util.List;
import java.util.Map;
import java.util.Set;
import static com.happy.redis.RedisTypes.*;
/**
 * This class only implements those similar functions of the related original Java class. 
 * Can implement more functionality of {@link Jedis}.
 * Can get {@link Jedis} object from here.
 * For Class that applies the configured host and port as well as the pool configuration 
 * of the properties files of resources folder, 
 * See {@link com.happy.redis.util.RedisUtil}
 * Those functions that need to invoke java classes' methods won't be supplied here.
 * 
 * For much more information about how these functions really act, refer to {@link Jedis}.
 * There will be detail specifications.
 * 
 * Note: Use it only when cluster is not applied or you know the corresponding slot of the key is really on your specified server. Otherwise, it will show you {@link
 * redis.clients.jedis.exceptions.JedisMovedDataException}. For instance, redis.clients.jedis.exceptions.JedisMovedDataException: MOVED 9457 10.10.6.153:7002.
 * @author cx
 * @version 2017-01-06 20:34
 */
public class JedisSingleWrapper implements RedisClient{

    private Log log = LogFactory.getLog(JedisSingleWrapper.class);
    
    private JedisPool jedisPool;
    /**
     * The Host ip of Redis server. Just one of the cluster servers if using Redis Cluster
     * @author cx
     * @version 2017 -01-06 15:39:31
     */
    private String hostIp;
    /**
     * No doubt it is redis port.
     * @author cx
     * @version 2017 -01-06 15:41:06
     */
    private int port;

    /**
     * The Generic object pool config for redis. Default is {@link ObjectPoolConfigConfBased}
     * @author cx
     * @version 2017 -01-06 20:40:06
     */
    private GenericObjectPoolConfig genericObjectPoolConfig;

    public JedisSingleWrapper() {
        this(Constants.REDIS_DEFAULT_IP, Constants.REDIS_DEFAULT_PORT, ObjectPoolConfigConfBased.getInstance());
    }

    public JedisSingleWrapper(String hostIp, int port) {
        this(hostIp, port, ObjectPoolConfigConfBased.getInstance());
    }

    public JedisSingleWrapper(String hostIp, int port, GenericObjectPoolConfig genericObjectPoolConfig) {
        this.hostIp = hostIp;
        this.port = port;
        this.genericObjectPoolConfig = genericObjectPoolConfig;
        this.jedisPool = new JedisPool(genericObjectPoolConfig, this.hostIp, this.port);

    }

    /**
     * Set string.
     * @param key
     *         the key
     * @param val
     *         the val
     * @return Status code reply
     * @author cx
     * @version 2017 -01-09 15:31:59
     */
    public String set(String key, String val) {
        Jedis jedis = getJedis();
        return jedis.set(key, val);
    }
    public String setTemp(String key, String val) {
        return setTemp(key, val, Constants.DEFAULT_EXPIRE_TIME_SECS);
    }
    /**
     * Sets temp.
     * @param key
     *         the key
     * @param val
     *         the val
     * @param expire
     *         the expire time in second.
     * @return the temp
     * @author cx
     * @version 2017 -01-09 16:08:10
     */
    public String setTemp(String key, String val, int expire) {
        Jedis jedis = getJedis();
        return jedis.setex(key, expire, val);
    }

    public String getString(String key) {
        Jedis jedis = getJedis();
        return jedis.get(key);
    }
    
    public Long addForList(String key, String ... val){
        Jedis jedis = getJedis();
        return jedis.rpush(key, val);
    }
    public String addForList(int idx, String key, String val){
        Jedis jedis = getJedis();
        return jedis.lset(key, idx, val);
    }
    public String getForList(String key, int idx){
        Jedis jedis = getJedis();
        return jedis.lindex(key, idx);
    }
    public long sizeOfForList(String key){
        Jedis jedis = getJedis();
        return jedis.llen(key);
    }

    /**
     * Remove all the elements equal to the <code>val<code> .
     * @param key
     *         the key
     * @param val
     *         the val
     * @return The number of removed elements if the operation succeeded
     * @author cx
     * @version 2017 -01-09 16:39:01
     */
    public long removeForList(String key, String val){
        Jedis jedis = getJedis();
        return jedis.lrem(key, 0, val);
    }

    public boolean isEmptyForList(String key){
        long size = sizeOfForList(key);
        return size == 0;
    }
    
    public List getList(String key) {
        return getList(key, 0, -1);
    }

    /**
     * Gets list.Start and end are zero-based indexes.
     * @param key
     *         the key
     * @param start
     *         the start (included)
     * @param end
     *         the end (included)
     * @return the list
     * @author cx
     * @version / 2017-01-06 21:52:01
     */
    public List getList(String key, int start, int end) {
        Jedis jedis = getJedis();
        return jedis.lrange(key, start, end);
    }

    /**
     * Add for set string.
     * @param key
     *         the key
     * @param val
     *         the val
     * @return 1 if the new element was added 
     *         0 if the element was already a member of the set
     * @author cx
     * @version 2017 -01-09 16:48:47
     */
    public long addForSet(String key, String ... val){
        Jedis jedis = getJedis();
        return jedis.sadd(key, val);
    }

    /**
     * Remove for set long.
     * @param key
     *         the key
     * @param val
     *         the val
     * @return 1 if the new element was removed 
     *         0 if the new element was not a member of the set
     * @author cx
     * @version 2017 -01-09 16:53:34
     */
    public long removeForSet(String key, String ... val) {
        Jedis jedis = getJedis();
        return jedis.srem(key, val);
    }
    public int sizeForSet(String key) {
        Jedis jedis = getJedis();
        return jedis.smembers(key).size();
    }
    public boolean isEmptyForSet(String key) {
        Jedis jedis = getJedis();
        return jedis.smembers(key).isEmpty();
    }
    
    /**
     * Get set collection, not get the old value while set the new value.
     * @param key
     *         the key
     * @return the set
     * @author cx
     * @version 2017 -01-06 23:11:05
     */
    public Set getSet(String key) {
        Jedis jedis = getJedis();
        return jedis.smembers(key);
    }

    /**
     * Add for zset long.
     * @param key
     *         the key
     * @param score
     *         the score
     * @param val
     *         the val
     * @return 1 if the new element was added 
     *         0 if the element was already a member of the sorted set and the score was updated
     * @author cx
     * @version 2017 -01-09 17:02:56
     */
    public long addForZSet(String key, double score, String val){
        Jedis jedis = getJedis();
        return jedis.zadd(key, score, val);
    }

    public long removeForZSet(String key, String ... val){
        Jedis jedis = getJedis();
        return jedis.zrem(key, val);
    }

    public Set getZSet(String key) {
        return getZSet(key, 0, -1);
    }
    
    public Set getZSetSortedByScore(String key) {
        Jedis jedis = getJedis();
        return jedis.zrangeByScore(key, Double.MIN_VALUE, Double.MAX_VALUE);
    }
    
    public long sizeForZSet(String key){
        Jedis jedis = getJedis();
        return jedis.zcount(key, Double.MIN_VALUE, Double.MAX_VALUE);
    }
    public boolean isEmptyForZSet(String key){
        return sizeForSet(key) == 0;
    }
    public double scoreForZSet(String key, String val){
        Jedis jedis = getJedis();
        return jedis.zscore(key, val);
    }

    /**
     * Gets sorted set.
     * @param key
     *         the key
     * @param start
     *         the start zero-based indexes
     * @param end
     *         the end zero-based indexes
     * @return the sorted set
     * @author cx
     * @version / 2017-01-09 15:22:32
     */
    public Set getZSet(String key, long start, long end) {
        Jedis jedis = getJedis();
        return jedis.zrange(key, start, end);
    }

    /**
     * Put hash long.
     * @param key
     *         the key
     * @param field
     *         the field
     * @param val
     *         the val
     * @return If the field already exists, and the HSET just produced an update of the value, 0 is
     *         returned, otherwise if a new field is created 1 is returned.
     * @author cx
     * @version 2017 -01-09 17:24:04
     */
    public long putHashEntry(String key, String field, String val){
        Jedis jedis = getJedis();
        return jedis.hset(key, field, val);
    }

    /**
     * Put hash string.
     * @param key
     *         the key
     * @param map
     *         the map
     * @return Return OK or Exception if hash is empty
     * @author cx
     * @version 2017 -01-09 17:27:02
     */
    public String putHash(String key, Map<String, String> map){
        Jedis jedis = getJedis();
        return jedis.hmset(key, map);
    }
    
    public String getForHash(String key, String field){
        Jedis jedis = getJedis();
        return jedis.hget(key, field);
    }
    
    public Set keysForHash(String key){
        Jedis jedis = getJedis();
        return jedis.hkeys(key);
    }
    
    public List valesForHash(String key){
        Jedis jedis = getJedis();
        return jedis.hvals(key);
    }
    
    public long sizeForHash(String key){
        Jedis jedis = getJedis();
        return jedis.hlen(key);
    }
    public boolean isEmptyForHash(String key){
        return sizeForSet(key) == 0;
    }
    
    public Map getHash(String key) {
        Jedis jedis = getJedis();
        return jedis.hgetAll(key);
    }

    /**
     * Delete long.
     * @param key
     *         the key
     * @return an integer greater than 0 if one or more keys were removed
     *         0 if none of the specified key existed
     * @author cx
     * @version 2017 -01-09 17:36:06
     */
    public long delete (String ... key){
        Jedis jedis = getJedis();
        return jedis.del(key);
    }
    
    public boolean exists(String key) {
        Jedis jedis = getJedis();
        return jedis.exists(key);
    }

    /**
     * Expire long.
     * @param key
     *         the key
     * @param secs
     *         the secs
     * @return 1: the timeout was set. 0: the timeout was not set since
     *         the key already has an associated timeout (this may happen only in Redis versions &lt;
     *         2.1.3, Redis &gt;= 2.1.3 will happily update the timeout), or the key does not exist.
     * @author cx
     * @version 2017 -01-09 17:39:31
     */
    public long expire(String key, int secs){
        Jedis jedis = getJedis();
        return jedis.expire(key, secs);
    }
    
    public List sort(String key) {
        Jedis jedis = getJedis();
        return jedis.sort(key);
    }

    /**
     * Sort list. For instance of SortingParams usage, 
     * refer to https://dzone.com/articles/redis-sort-with-jedis-xico-junior-ribeiro for more information.
     * @param key
     *         the key
     * @param sortingParams
     *         the sorting params
     * @return the list
     * @author cx
     * @version 2017 -01-09 17:54:04
     */
    public List sort(String key, SortingParams sortingParams) {
        Jedis jedis = getJedis();
        return jedis.sort(key, sortingParams);
    }
    
    public RedisTypes type(String key) {
        Jedis jedis = getJedis();
        String type = jedis.type(key);
        switch (type) {
            case "none" :
                return RedisTypes.NONE;
            case "string" :
                return STRING;
            case "list" :
                return LIST;
            case "set" :
                return SET;
            case "zset" :
                return ZSET;
            case "hash" :
                return HASH;
            default: 
                log.error("Redis returns non-expected type string for key [" + key + "]! Returned string : [" + type + "] !");
                return null;
        }
    }

    /**
     * Gets jedis. Can do other things that not supported here.
     * @return the jedis
     * @author cx
     * @version / 2017-01-09 17:52:45
     */
    public Jedis getJedis() {
        return this.jedisPool.getResource();
    }

    public String getHostIp() {
        return hostIp;
    }

    public int getPort() {
        return port;
    }

}
