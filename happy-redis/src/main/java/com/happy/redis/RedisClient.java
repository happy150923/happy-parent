package com.happy.redis;

import redis.clients.jedis.SortingParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chengxia
 * @version 2017-01-04 19:01
 */
public interface RedisClient {

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
    String set(String key, String val);

    String setTemp(String key, String val);

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
    String setTemp(String key, String val, int expire);

    String getString(String key);

    Long addForList(String key, String... val);

    String addForList(int idx, String key, String val);

    String getForList(String key, int idx);

    long sizeOfForList(String key);

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
    long removeForList(String key, String val);

    boolean isEmptyForList(String key);

    List getList(String key);

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
    List getList(String key, int start, int end);

    /**
     * Add for set string.
     * @param key
     *         the key
     * @param val
     *         the val
     * @return 1 if the new element was added 0 if the element was already a member of the set
     * @author cx
     * @version 2017 -01-09 16:48:47
     */
    long addForSet(String key, String... val);

    /**
     * Remove for set long.
     * @param key
     *         the key
     * @param val
     *         the val
     * @return 1 if the new element was removed 0 if the new element was not a member of the set
     * @author cx
     * @version 2017 -01-09 16:53:34
     */
    long removeForSet(String key, String... val);

    int sizeForSet(String key);

    boolean isEmptyForSet(String key);

    /**
     * Get set collection, not get the old value while set the new value.
     * @param key
     *         the key
     * @return the set
     * @author cx
     * @version 2017 -01-06 23:11:05
     */
    Set getSet(String key);

    /**
     * Add for zset long.
     * @param key
     *         the key
     * @param score
     *         the score
     * @param val
     *         the val
     * @return 1 if the new element was added 0 if the element was already a member of the sorted set and the score was updated
     * @author cx
     * @version 2017 -01-09 17:02:56
     */
    long addForZSet(String key, double score, String val);

    long removeForZSet(String key, String... val);

    Set getZSet(String key);

    Set getZSetSortedByScore(String key);

    long sizeForZSet(String key);

    boolean isEmptyForZSet(String key);

    double scoreForZSet(String key, String val);

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
    Set getZSet(String key, long start, long end);

    /**
     * Put hash long.
     * @param key
     *         the key
     * @param field
     *         the field
     * @param val
     *         the val
     * @return If the field already exists, and the HSET just produced an update of the value, 0 is returned, otherwise if a new field is created 1 is returned.
     * @author cx
     * @version 2017 -01-09 17:24:04
     */
    long putHashEntry(String key, String field, String val);

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
    String putHash(String key, Map<String, String> map);

    String getForHash(String key, String field);

    Set keysForHash(String key);

    List valesForHash(String key);

    long sizeForHash(String key);

    boolean isEmptyForHash(String key);

    Map getHash(String key);

    /**
     * Delete long.
     * @param key
     *         the key
     * @return an integer greater than 0 if one or more keys were removed 0 if none of the specified key existed
     * @author cx
     * @version 2017 -01-09 17:36:06
     */
    long delete(String... key);

    boolean exists(String key);

    /**
     * Expire long.
     * @param key
     *         the key
     * @param secs
     *         the secs
     * @return 1: the timeout was set. 0: the timeout was not set since the key already has an associated timeout (this may happen only in Redis versions &lt; 2.1.3, Redis &gt;= 2.1.3 will happily
     * update the timeout), or the key does not exist.
     * @author cx
     * @version 2017 -01-09 17:39:31
     */
    long expire(String key, int secs);

    List sort(String key);

    /**
     * Sort list. For instance of SortingParams usage, refer to https://dzone.com/articles/redis-sort-with-jedis-xico-junior-ribeiro for more information.
     * @param key
     *         the key
     * @param sortingParams
     *         the sorting params
     * @return the list
     * @author cx
     * @version 2017 -01-09 17:54:04
     */
    List sort(String key, SortingParams sortingParams);

    RedisTypes type(String key);

    String getHostIp();

    int getPort();


}
