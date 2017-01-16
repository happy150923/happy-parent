package com.happy.redis.jedis.wrapper;

import com.happy.redis.RTypeConvertor;
import com.happy.redis.jedis.ObjectPoolConfigConfBased;
import com.happy.redis.Constants;
import com.happy.redis.RedisClient;
import com.happy.redis.RedisTypes;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.happy.log.Log;
import org.happy.log.LogFactory;
import redis.clients.jedis.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.happy.redis.RedisTypes.*;
import static com.happy.redis.RedisTypes.HASH;

/**
 * You can use this class to access every slot of your redis cluster with only one host ip-port pair that one redis node is on.
 * 
 * This class only implements those similar functions of the related original Java class. 
 * Can implement more functionality of {@link JedisCluster}.
 * Can get {@link JedisCluster} object from here.
 * For Class that applies the configured host and port as well as the pool configuration 
 * of the properties files of resources folder, 
 * See {@link com.happy.redis.util.RedisUtil}, 
 * Those functions that need to invoke java classes' methods won't be supplied here.
 *
 * For much more information about how these functions really act, refer to {@link Jedis}, 
 * Since {@link JedisCluster} is implemented mostly with {@link Jedis}.
 * There will be detail specifications.
 *
 * @author chengxia
 * @version 2017-01-06 20:32
 */
public class JedisClusterWrapper implements RedisClient{
    
    private JedisCluster jedisCluster;

    private Log log = LogFactory.getLog(JedisClusterWrapper.class);

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

    public JedisClusterWrapper() {
        this(Constants.REDIS_DEFAULT_IP, Constants.REDIS_DEFAULT_PORT, ObjectPoolConfigConfBased.getInstance());
    }

    public JedisClusterWrapper(String hostIp, int port) {
        this(hostIp, port, ObjectPoolConfigConfBased.getInstance());
    }

    public JedisClusterWrapper(String hostIp, int port, GenericObjectPoolConfig genericObjectPoolConfig) {
        this.hostIp = hostIp;
        this.port = port;
        this.genericObjectPoolConfig = genericObjectPoolConfig;
        HostAndPort hostAndPort = new HostAndPort(hostIp, port);
        this.jedisCluster = new JedisCluster(hostAndPort, this.genericObjectPoolConfig);
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
        return jedisCluster.set(key, val);
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
        return jedisCluster.setex(key, expire, val);
    }

    public String getString(String key) {
        return jedisCluster.get(key);
    }

    public Long addForList(String key, String ... val){
        return jedisCluster.rpush(key, val);
    }
    public String addForList(int idx, String key, String val){
        return jedisCluster.lset(key, idx, val);
    }
    public String getForList(String key, int idx){
        return jedisCluster.lindex(key, idx);
    }
    public long sizeOfForList(String key){
        return jedisCluster.llen(key);
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
        return jedisCluster.lrem(key, 0, val);
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
        return jedisCluster.lrange(key, start, end);
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
        return jedisCluster.sadd(key, val);
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
        return jedisCluster.srem(key, val);
    }
    public int sizeForSet(String key) {
        return jedisCluster.smembers(key).size();
    }
    public boolean isEmptyForSet(String key) {
        return jedisCluster.smembers(key).isEmpty();
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
        return jedisCluster.smembers(key);
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
        return jedisCluster.zadd(key, score, val);
    }

    public long removeForZSet(String key, String ... val){
        return jedisCluster.zrem(key, val);
    }

    public Set getZSet(String key) {
        return getZSet(key, 0, -1);
    }

    public Set getZSetSortedByScore(String key) {
        return jedisCluster.zrangeByScore(key, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    public long sizeForZSet(String key){
        return jedisCluster.zcount(key, Double.MIN_VALUE, Double.MAX_VALUE);
    }
    public boolean isEmptyForZSet(String key){
        return sizeForSet(key) == 0;
    }
    public double scoreForZSet(String key, String val){
        return jedisCluster.zscore(key, val);
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
        return jedisCluster.zrange(key, start, end);
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
        return jedisCluster.hset(key, field, val);
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
        return jedisCluster.hmset(key, map);
    }

    public String getForHash(String key, String field){
        return jedisCluster.hget(key, field);
    }

    public Set keysForHash(String key){
        return jedisCluster.hkeys(key);
    }

    public List valesForHash(String key){
        return jedisCluster.hvals(key);
    }

    public long sizeForHash(String key){
        return jedisCluster.hlen(key);
    }
    public boolean isEmptyForHash(String key){
        return sizeForSet(key) == 0;
    }

    public Map getHash(String key) {
        return jedisCluster.hgetAll(key);
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
        return jedisCluster.del(key);
    }

    public boolean exists(String key) {
        return jedisCluster.exists(key);
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
        return jedisCluster.expire(key, secs);
    }

    public List sort(String key) {
        return jedisCluster.sort(key);
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
        return jedisCluster.sort(key, sortingParams);
    }

    public RedisTypes type(String key) {
        String type = jedisCluster.type(key);
        return RTypeConvertor.convert(type);
    }

    /**
     * Gets JedisCluster. Can do other things that not supported here.
     * @return the jedis
     * @author cx
     * @version / 2017-01-09 17:52:45
     */
    public JedisCluster getJedisCluster() {
        return this.jedisCluster;
    }

    public String getHostIp() {
        return hostIp;
    }

    public int getPort() {
        return port;
    }


}
