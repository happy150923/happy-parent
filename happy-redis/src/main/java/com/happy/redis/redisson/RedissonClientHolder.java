package com.happy.redis.redisson;

import com.happy.redis.Constants;
import org.redisson.api.RedissonClient;

/**
 * Hold the instance that created against all the configurations in this project.
 * For invocation's convenience in other classes except {@link com.happy.redis.redisson.wrapper.RedissonClientWrapper} .
 * @author chengxia
 * @version 2017-01-13 18:53
 */
public class RedissonClientHolder {
    public static RedissonClient getRedisson(){
        return InnerHolder.redissonClient;
    }
    
    private static class InnerHolder{
        private static RedissonClient redissonClient = init();
        private static RedissonClient init(){
            boolean useCluster = Constants.REDIS_USE_CLUSTER;
            RedissonClient redissonClient;
            if (useCluster) {
                redissonClient = RedissonClientGenerator.createRedissonClientFromJSON(Constants.REDISSON_CLUSTER_JSON_FILE);
            } else {
                redissonClient = RedissonClientGenerator.createRedissonClientFromJSON(Constants.REDISSON_SINGLE_JSON_FILE);
            }
            return redissonClient;
        }
    }
}
