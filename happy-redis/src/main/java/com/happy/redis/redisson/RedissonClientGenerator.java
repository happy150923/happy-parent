package com.happy.redis.redisson;

import org.apache.commons.lang3.StringUtils;
import org.happy.log.Log;
import org.happy.log.LogFactory;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.io.IOException;

/**
 * @author chengxia
 * @version 2017-01-09 19:23
 */
public class RedissonClientGenerator {

    public static Log log = LogFactory.getLog(RedissonClientGenerator.class);

    public static RedissonClient createRedissonClient(String ipPort){
        return createRedissonClient(false, ipPort);
    }
    public static RedissonClient createRedissonClient(boolean useCluster, String ipPort){
        Config rdsConfig = new Config();
        if (useCluster) {
            ClusterServersConfig clusterServersConfig = rdsConfig.useClusterServers();
            clusterServersConfig.addNodeAddress(ipPort);
        } else {
            SingleServerConfig singleServerConfig = rdsConfig.useSingleServer();
            singleServerConfig.setAddress(ipPort);
        }
        log.debug("Use " + (useCluster ? "redis cluster" : "single redis server") + ", IP:PORT is follows: " + ipPort + ".");
        return Redisson.create(rdsConfig);
    }


    public static RedissonClient createRedissonClientFromJSON(String file) {
        if (StringUtils.isBlank(file)) {
            log.error("Specify one json file path that used to create RedissonClient object!");
            return null;
        } else {
            log.debug("Will use file [" + file + "] to generate a RedissonClient object.");
        }
        try {
            Config rdsConfig = Config.fromJSON(ClassLoader.getSystemResourceAsStream(file));
            return Redisson.create(rdsConfig);
        } catch (IOException e) {
            log.error("Failed to create RedissonClient object with file " + file + "!");
            e.printStackTrace();
            return null;
        }
    }
    

}
