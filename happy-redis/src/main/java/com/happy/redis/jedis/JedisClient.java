package com.happy.redis.jedis;

import com.happy.redis.Constants;
import com.happy.redis.RedisClient;
import com.happy.redis.RedisTypes;
import com.happy.redis.jedis.wrapper.JedisClusterWrapper;
import com.happy.redis.jedis.wrapper.JedisSingleWrapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.SortingParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * The redis client implemented by Jedis lib.
 * By default, it will use {@link JedisSingleWrapper} underlying.
 * @author chengxia
 * @version 2017-01-06 14:47
 */
public class JedisClient implements RedisClient{

    /**
     * The Jedis pool conf. Use {@link ObjectPoolConfigConfBased}
     * @author chengxia
     * @version 2017 -01-06 17:38:53
     */
    private GenericObjectPoolConfig jedisPoolConf;

    /**
     * Whether Use cluster or not. Default false.
     * @author chengxia
     * @version 2017 -01-06 15:38:49
     */
    private boolean useCluster;

    /**
     * The Host ip of Redis server.
     * Just one of the cluster servers if using Redis Cluster
     * @author chengxia
     * @version 2017 -01-06 15:39:31
     */
    private String hostIp;
    /**
     * No doubt it is redis port.
     * @author chengxia
     * @version 2017 -01-06 15:41:06
     */
    private int port;

    /**
     * The Redis client implemented using Jedis. 
     * @author cx
     * @version 2017 -01-09 18:51:33
     */
    private RedisClient jedisClient;

    private JedisClient(){
        this(Constants.REDIS_USE_CLUSTER, Constants.JEDIS_SERVER_IP, Constants.JEDIS_SERVER_PORT, ObjectPoolConfigConfBased.getInstance());
    }
    
    private JedisClient(boolean useCluster, String hostIp, int port, GenericObjectPoolConfig jedisPoolConf) {
        this.useCluster = useCluster;
        this.hostIp = hostIp;
        this.port = port;
        this.jedisPoolConf = jedisPoolConf;
        if (this.useCluster) {
            jedisClient = new JedisClusterWrapper(hostIp, port, jedisPoolConf);
        }else {
            jedisClient = new JedisSingleWrapper(hostIp, port, jedisPoolConf);
        }
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
        return jedisClient.set(key, val);
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
        return jedisClient.setTemp(key,val, expire);
    }

    public String getString(String key) {
        return jedisClient.getString(key);
    }

    public Long addForList(String key, String ... val){
        return jedisClient.addForList(key, val);
    }
    public String addForList(int idx, String key, String val){
        return jedisClient.addForList(idx, key, val);
    }
    public String getForList(String key, int idx){
        return jedisClient.getForList(key, idx);
    }
    public long sizeOfForList(String key){
        return jedisClient.sizeOfForList(key);
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
        return jedisClient.removeForList(key, val);
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
        return jedisClient.getList(key, start, end);
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
        return jedisClient.addForSet(key, val);
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
        return jedisClient.removeForSet(key, val);
    }
    public int sizeForSet(String key) {
        return jedisClient.sizeForSet(key);
    }
    public boolean isEmptyForSet(String key) {
        return jedisClient.isEmptyForSet(key);
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
        return jedisClient.getSet(key);
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
        return jedisClient.addForZSet(key, score, val);
    }

    public long removeForZSet(String key, String ... val){
        return jedisClient.removeForZSet(key, val);
    }

    public Set getZSet(String key) {
        return getZSet(key, 0, -1);
    }

    public Set getZSetSortedByScore(String key) {
        return jedisClient.getZSetSortedByScore(key);
    }

    public long sizeForZSet(String key){
        return jedisClient.sizeForZSet(key);
    }
    public boolean isEmptyForZSet(String key){
        return sizeForSet(key) == 0;
    }
    public double scoreForZSet(String key, String val){
        return jedisClient.scoreForZSet(key, val);
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
        return jedisClient.getZSet(key, start, end);
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
        return jedisClient.putHashEntry(key, field, val);
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
        return jedisClient.putHash(key, map);
    }

    public String getForHash(String key, String field){
        return jedisClient.getForHash(key, field);
    }

    public Set keysForHash(String key){
        return jedisClient.keysForHash(key);
    }

    public List valesForHash(String key){
        return jedisClient.valesForHash(key);
    }

    public long sizeForHash(String key){
        return jedisClient.sizeForHash(key);
    }
    public boolean isEmptyForHash(String key){
        return sizeForSet(key) == 0;
    }

    public Map getHash(String key) {
        return jedisClient.getHash(key);
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
        return jedisClient.delete(key);
    }

    public boolean exists(String key) {
        return jedisClient.exists(key);
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
        return jedisClient.expire(key, secs);
    }

    public List sort(String key) {
        return jedisClient.sort(key);
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
        return jedisClient.sort(key, sortingParams);
    }

    public RedisTypes type(String key) {
        return jedisClient.type(key);
    }

    /**
     * Gets JedisClient. Can do other things that not supported here.
     * @return the jedis
     * @author cx
     * @version / 2017-01-09 17:52:45
     */
    public RedisClient getJedisClient() {
        return this.jedisClient;
    }

    public String getHostIp() {
        return hostIp;
    }

    public int getPort() {
        return port;
    }


    public static final JedisClient INSTANCE = new JedisClient();
}
