package com.happy.redis.redisson.wrapper;

import com.happy.redis.Constants;
import com.happy.redis.RedisClient;
import com.happy.redis.RedisTypes;
import com.happy.redis.RTypeConvertor;
import com.happy.redis.redisson.RedissonClientGenerator;
import com.happy.redis.redisson.RedissonClientHolder;
import org.happy.log.Log;
import org.happy.log.LogFactory;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.client.protocol.RedisCommand;
import org.redisson.client.protocol.RedisCommands;
import org.redisson.client.protocol.ScoredEntry;
import org.redisson.command.CommandExecutor;
import redis.clients.jedis.SortingParams;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

/**
 * You had better not use Redisson and Jedis together.
 * For example, set string with Redisson then get same key with Jedis. 
 * It will show error.
 * Redisson will set string value with quotation mark. Identify value with it when getting it.
 * @author chengxia
 * @version 2017-01-04 19:01
 */
public class RedissonClientWrapper implements RedisClient {
    Log log = LogFactory.getLog(RedissonClientWrapper.class);
    
    private RedissonClient redissonClient;

    private RedissonClientWrapper() {
        redissonClient = RedissonClientHolder.getRedisson();
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
    @Override
    public String set(String key, String val) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(val);
        return "OK";
    }

    @Override
    public String setTemp(String key, String val) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(val, Constants.DEFAULT_EXPIRE_TIME_SECS, TimeUnit.SECONDS);
        return "OK";
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
    @Override
    public String setTemp(String key, String val, int expire) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.set(val, expire, TimeUnit.SECONDS);
        return "OK";
    }

    @Override
    public String getString(String key) {
        RBucket<String> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    @Override
    public Long addForList(String key, String... val) {
        RList<String> list = redissonClient.getList(key);
        int len = val.length;
        if (len == 1) {
            list.add(val[0]);
        } else if (len > 1) {
            list.addAll(Arrays.asList(val));
        } else {
            return 0l;
        }
        return (long) list.size();
    }

    @Override
    public String addForList(int idx, String key, String val) {
        RList<String> list = redissonClient.getList(key);
        list.add(idx, val);
        return "OK";
    }

    @Override
    public String getForList(String key, int idx) {
        RList<Object> list = redissonClient.getList(key);
        return String.valueOf(list.get(idx));
    }

    @Override
    public long sizeOfForList(String key) {
        RList<String> list = redissonClient.getList(key);
        return list.size();
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
    @Override
    public long removeForList(String key, String val) {
        RList<String> list = redissonClient.getList(key);
        if (list.remove(val)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean isEmptyForList(String key) {
        RList<String> list = redissonClient.getList(key);
        return list.isEmpty();
    }

    @Override
    public List getList(String key) {
        return redissonClient.getList(key);
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
    @Override
    public List getList(String key, int start, int end) {
        return redissonClient.getList(key).subList(start, end);
    }

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
    @Override
    public long addForSet(String key, String... val) {
        RSet set = redissonClient.getSet(key);
        boolean changed = set.addAll(Arrays.asList(val));
        return changed ? 1 : 0;
    }

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
    @Override
    public long removeForSet(String key, String... val) {
        RSet set = redissonClient.getSet(key);
        boolean changed = set.removeAll(Arrays.asList(val));
        return changed ? 1 : 0;
    }

    @Override
    public int sizeForSet(String key) {
        RSet set = redissonClient.getSet(key);
        return set == null || set.isEmpty() ? 0 : set.size();
    }

    @Override
    public boolean isEmptyForSet(String key) {
        RSet set = redissonClient.getSet(key);
        return set == null || set.isEmpty();
    }

    /**
     * Get set collection, not get the old value while set the new value.
     * @param key
     *         the key
     * @return the set
     * @author cx
     * @version 2017 -01-06 23:11:05
     */
    @Override
    public Set getSet(String key) {
        return redissonClient.getSet(key);
    }

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
    @Override
    public long addForZSet(String key, double score, String val) {
        RScoredSortedSet scoredSortedSet = redissonClient.getScoredSortedSet(key);
        boolean added = scoredSortedSet.add(score, val);
        return added ? 1 : 0;
    }

    @Override
    public long removeForZSet(String key, String... val) {
        RScoredSortedSet scoredSortedSet = redissonClient.getScoredSortedSet(key);
        boolean removed = scoredSortedSet.removeAll(Arrays.asList(val));
        return removed ? 1: 0 ;
    }

    @Override
    public Set getZSet(String key) {
        return redissonClient.getSortedSet(key);
    }

    @Override
    public Set getZSetSortedByScore(String key) {
        RScoredSortedSet scoredSortedSet = redissonClient.getScoredSortedSet(key);
        Collection collection = scoredSortedSet.valueRange(Double.MIN_VALUE, true, Double.MAX_VALUE, true);
        SortedSet sortedSet = new ConcurrentSkipListSet<>();
        sortedSet.addAll(collection);
        return sortedSet;
    }

    @Override
    public long sizeForZSet(String key) {
        RSortedSet sortedSet = redissonClient.getSortedSet(key);
        return sortedSet.size();
    }

    @Override
    public boolean isEmptyForZSet(String key) {
        return redissonClient.getSortedSet(key).isEmpty();
    }

    @Override
    public double scoreForZSet(String key, String val) {
        RScoredSortedSet scoredSortedSet = redissonClient.getScoredSortedSet(key);
        return scoredSortedSet.getScore(val);
    }

    /**
     * Gets sorted set.
     * @param key
     *         the key
     * @param start
     *         the start zero-based indexes
     * @param end
     *         the end zero-based indexes
     * @return the sorted set with score - {@link ScoredEntry}
     * @author cx
     * @version / 2017-01-09 15:22:32
     */
    @Override
    public Set getZSet(String key, long start, long end) {
        RScoredSortedSet scoredSortedSet = redissonClient.getScoredSortedSet(key);
        Collection collection = scoredSortedSet.entryRange((int) start, (int) end);
        SortedSet sortedSet = new ConcurrentSkipListSet<>();
        sortedSet.addAll(collection);
        return sortedSet;
    }

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
    @Override
    public long putHashEntry(String key, String field, String val) {
        RMap map = redissonClient.getMap(key);
        boolean contained = map.containsKey(field);
        map.put(field, val);
        return contained ? 0 : 1;
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
    @Override
    public String putHash(String key, Map<String, String> map) {
        RMap rmap = redissonClient.getMap(key);
        rmap.putAll(map);
        return "OK";
    }

    @Override
    public String getForHash(String key, String field) {
        RMap map = redissonClient.getMap(key);
        return String.valueOf(map.get(field));
    }

    @Override
    public Set keysForHash(String key) {
        RMap map = redissonClient.getMap(key);
        return map.keySet();
    }

    @Override
    public List valesForHash(String key) {
        RMap map = redissonClient.getMap(key);
        List values = new ArrayList<>();
        values.addAll(map.values());
        return values;
    }

    @Override
    public long sizeForHash(String key) {
        RMap map = redissonClient.getMap(key);
        return map.size();
    }

    @Override
    public boolean isEmptyForHash(String key) {
        RMap map = redissonClient.getMap(key);
        return map.isEmpty();
    }

    @Override
    public Map getHash(String key) {
        
        return redissonClient.getMap(key);
    }

    /**
     * Delete long.
     * @param key
     *         the key
     * @return an integer greater than 0 if one or more keys were removed 0 if none of the specified key existed
     * @author cx
     * @version 2017 -01-09 17:36:06
     */
    @Override
    public long delete(String... key) {
        runCommandMultiKeys(RedisCommands.DEL, key);
        return key.length;
    }

    @Override
    public boolean exists(String key) {
        return redissonClient.getBucket(key).isExists();
    }

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
    @Override
    public long expire(String key, int secs) {
        if (!exists(key)) {
            return 0;
        }
        boolean res = redissonClient.getBucket(key).expire(secs, TimeUnit.SECONDS);
        return res ? 1 : 0;
    }

    @Override
    public List sort(String key) {
        throw new UnsupportedOperationException();
    }

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
    @Override
    public List sort(String key, SortingParams sortingParams) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RedisTypes type(String key) {
        RType rType = runCommand(RedisCommands.TYPE, key);
        return RTypeConvertor.convert(rType);
    }

    @Override
    public String getHostIp() {
        return null;
    }

    @Override
    public int getPort() {
        return 0;
    }
    
    public void shutdown(){
        redissonClient.shutdown();
    }
    
    public RedissonClient getRedissonClient(){
        return redissonClient;
    }

    public static final RedissonClientWrapper INSTANCE = new RedissonClientWrapper();
    
    private  <T> void runCommandMultiKeys(RedisCommand<T> command,String ... key){
        Redisson redisson = (Redisson) redissonClient;
        if (redissonClient instanceof Redisson) {
            redisson = (Redisson) redissonClient;
        } else {
            throw new RuntimeException("The client gotten is not an instance of Redisson!");
        }
        CommandExecutor commandExecutor = redisson.getCommandExecutor();
        
        commandExecutor.get(commandExecutor.writeAllAsync(command, key));
    }
    private <T, R> R runCommand(RedisCommand<T> command,String key, Object... params){
        Redisson redisson = (Redisson) redissonClient;
        if (redissonClient instanceof Redisson) {
            redisson = (Redisson) redissonClient;
        } else {
            throw new RuntimeException("The client gotten is not an instance of Redisson!");
        }
        CommandExecutor commandExecutor = redisson.getCommandExecutor();
        return commandExecutor.write(key, command, params);
    }
    
}
