package com.happy.redis.dlm.lock.impl;

import com.happy.redis.dlm.DistributedLockException;
import com.happy.core.dlm.lock.DLLockGetter;
import com.happy.redis.redisson.RedissonClientGenerator;
import com.happy.redis.redisson.RedissonClientHolder;
import org.apache.commons.lang3.StringUtils;
import org.happy.log.Log;
import org.happy.log.LogFactory;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * This is a DLLock implemented by redis using red lock.
 * Supports redis cluster and single server(s) mode.
 * @author chengxia
 * @version 2016-12-30 12:08
 */
public class DLRedisLockGetter implements DLLockGetter{
    private static final String LOCK_PREFIX = "lock:";

    private Log log = LogFactory.getLog(DLRedisLockGetter.class);
    private RedissonClient rdsClient;
    
    public DLRedisLockGetter() {
       createRdsClient();
    }

    public DLRedisLockGetter(boolean useCluster, String ipPort) {
        createRdsClient(useCluster, ipPort);
    }

    public DLRedisLockGetter(boolean useCluster, String ip, int port) {
        createRdsClient(useCluster, ip + ":" + port);
    }

    /**
     * <br/>
     * Create redisson client against the configs in property files
     *
     * @author chengxia
     * @version 2017 -01-04 17:18:19
     */
    private void createRdsClient(){
        rdsClient = RedissonClientHolder.getRedisson();
    }

    /**
     * Create rds client with given parameters.
     * @param useCluster
     *         the use cluster
     * @param ipPort
     *         the ip port
     * @author cx
     * @version 2017 -01-10 17:54:46
     */
    private void createRdsClient(boolean useCluster, String ipPort){
        rdsClient = RedissonClientGenerator.createRedissonClient(useCluster, ipPort);
    }
    
    /**
     * <br/>
     * Get lock: one redis dl lock.
     *
     * @param resourceName the resource name
     * @return the one redis dl lock
     * @author chengxia
     * @version 2017 -01-04 15:07:46
     */
    public OneRedisDLLock getLock(String resourceName){
        if (StringUtils.isBlank(resourceName)) {
            throw new DistributedLockException("There is no resource name to be locked!");
        }
        RLock rdsLock = rdsClient.getLock(LOCK_PREFIX + resourceName);
        return new OneRedisDLLock(rdsLock);
    }
    
}
