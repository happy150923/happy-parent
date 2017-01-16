package com.happy.redis;

import com.happy.redis.jedis.JedisClient;
import com.happy.redis.redisson.wrapper.RedissonClientWrapper;

/**
 * @author chengxia
 * @version 2017-01-16 15:52
 */
public class RedisClientFactory {
    
    public enum Impler {
        Jedis, Redisson;
    }
    
    public static RedisClient getClient(){
        return getClient(Impler.Jedis);
    }
    
    public static RedisClient getClient(Impler impler){
        switch (impler) {
            case Jedis:return JedisClient.INSTANCE;
            case Redisson:return RedissonClientWrapper.INSTANCE;
        }
        throw new UnsupportedOperationException("Not supported impler [" + impler + "].");
    }
    
}
