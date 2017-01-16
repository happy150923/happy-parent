package com.happy.redis;

import com.happy.util.conf.ConfigsUtils;

/**
 * @author chengxia
 * @version 2017-01-04 18:33
 */
public class Constants {
    
    public static final String POOL_JSON_FILE = "pool.json";

    /**
     * The constant JEDIS_SERVER_IP_STR_KEY.
     * The key in redis properties file whose value is the redis server ip.
     * Just used when Jedis is used.
     * @author cx
     * @version 2017 -01-10 13:33:37
     */
    private static final String JEDIS_SERVER_IP_STR_KEY = "redis.server.ip";
    /**
     * The constant JEDIS_SERVER_PORT_STR_KEY.
     * The key in redis properties file whose value is the redis server port.
     * Just used when Jedis is used.
     * @author cx
     * @version 2017 -01-10 13:33:37
     */
    private static final String JEDIS_SERVER_PORT_STR_KEY = "redis.server.port";
    /**
     * The constant REDIS_USE_CLUSTER_STR_KEY.
     * Indicate if this project uses redis cluster. For both Jedis and Redisson.
     * @author cx
     * @version 2017 -01-10 13:32:53
     */
    private static final String REDIS_USE_CLUSTER_STR_KEY = "redis.use.cluster";

    /**
     * The constant REDIS_PROP_FILE. 
     * The file name that will be used to create redis client.
     * @author cx
     * @version 2017 -01-10 13:30:42
     */
    public static final String REDIS_PROP_FILE = ConfigsUtils.get(com.happy.core.Constants.SYS_REDIS_PROP_FILE_KEY, com.happy.core.Constants.SYSTEM_CONF_FILE);

    /**
     * The constant REDISSON_CLUSTER_JSON_FILE. 
     * The file name used to create RedissonClient for redis cluster.
     * @author cx
     * @version 2017 -01-10 13:31:31
     */
    public static final String REDISSON_CLUSTER_JSON_FILE = ConfigsUtils.get(com.happy.core.Constants.SYS_REDIS_REDISSON_CLUSTER_FILE_KEY, com.happy.core.Constants.SYSTEM_CONF_FILE);
    /**
     * The constant REDISSON_SINGLE_JSON_FILE.
     * The file name used to create RedissonClient for redis single server.
     * @author cx
     * @version 2017 -01-10 13:32:12
     */
    public static final String REDISSON_SINGLE_JSON_FILE = ConfigsUtils.get(com.happy.core.Constants.SYS_REDIS_REDISSON_SINGLE_FILE_KEY, com.happy.core.Constants.SYSTEM_CONF_FILE);
    
    /**
     * The constant REDIS_DEFAULT_IP used by redis default.
     * @author chengxia
     * @version 2017 -01-06 15:55:30
     */
    public static final String REDIS_DEFAULT_IP = "127.0.0.1";

    /**
     * The constant REDIS_DEFAULT_PORT used by redis default.
     * @author chengxia
     * @version 2017 -01-06 15:55:43
     */
    public static final int REDIS_DEFAULT_PORT = 6379;

    /**
     * The constant USE_CLUSTER: true or false for redis server(s).
     * @author chengxia
     * @version 2017 -01-04 18:39:39
     */
    public static final boolean REDIS_USE_CLUSTER = Boolean.valueOf(ConfigsUtils.get(REDIS_USE_CLUSTER_STR_KEY, REDIS_PROP_FILE));

    /**
     * The constant JEDIS_SERVER_IP.
     * Used to create redis client implemented with Jedis.
     * @author cx
     * @version 2017 -01-10 13:37:50
     */
    public static final String JEDIS_SERVER_IP = ConfigsUtils.get(JEDIS_SERVER_IP_STR_KEY, REDIS_PROP_FILE);
    /**
     * The constant JEDIS_SERVER_PORT.
     * Used to create redis client implemented with Jedis.
     * @author cx
     * @version 2017 -01-10 13:37:50
     */
    public static final int JEDIS_SERVER_PORT = Integer.valueOf(ConfigsUtils.get(JEDIS_SERVER_PORT_STR_KEY, REDIS_PROP_FILE));

    /**
     * The constant EXPIRE_TIME_ONE_DAY_SECS.
     * The number that stands for one day in second.
     * @author cx
     * @version 2017 -01-10 13:38:56
     */
    public static final int EXPIRE_TIME_ONE_DAY_SECS = 86400;
    /**
     * The constant DEFAULT_EXPIRE_TIME_SECS. 
     * Default the expire time for temp value in redis is 1 hour.
     * @author cx
     * @version 2017 -01-09 16:10:52
     */
    public static final int DEFAULT_EXPIRE_TIME_SECS = 3600;
}
