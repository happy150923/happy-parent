package com.happy.redis.jedis;

import com.happy.redis.Constants;
import com.happy.util.JSONUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.happy.log.Log;
import org.happy.log.LogFactory;

/**
 * The Pool Config Object based on the values given in pool.conf.
 * @author chengxia
 * @version 2017-01-06 17:26
 */
public class ObjectPoolConfigConfBased extends GenericObjectPoolConfig{
    Log log = LogFactory.getLog(ObjectPoolConfigConfBased.class);
    private ObjectPoolConfigConfBased() {
    }
    
    public static ObjectPoolConfigConfBased getInstance(){
        ObjectPoolConfigConfBased instance = JSONUtils.JSONFileToObj(Constants.POOL_JSON_FILE, ObjectPoolConfigConfBased.class);
        return instance == null ? new ObjectPoolConfigConfBased() : instance;
    }
    
    public String toString(){
        return JSONUtils.objToJSON(this);
    }
}
