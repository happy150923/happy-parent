package com.happy.core.conf;

import com.happy.core.Constants;
import com.happy.util.conf.ConfigsUtils;
import org.junit.Test;

/**
 * @author chengxia
 * @version 2017-01-10 11:32
 */
public class ConfigsUtilsTest {
    
    @Test
    public void systemCfgTest(){
        System.out.println(ConfigsUtils.get(Constants.SYS_REDIS_PROP_FILE_KEY, Constants.SYSTEM_CONF_FILE));
        System.out.println(ConfigsUtils.get(Constants.SYS_REDIS_REDISSON_CLUSTER_FILE_KEY, Constants.SYSTEM_CONF_FILE));
        System.out.println(ConfigsUtils.get(Constants.SYS_REDIS_REDISSON_SINGLE_FILE_KEY, Constants.SYSTEM_CONF_FILE));
        System.out.println(ConfigsUtils.get(Constants.SYS_REDIS_PROP_FILE_KEY));
        System.out.println(ConfigsUtils.get(Constants.SYS_REDIS_REDISSON_CLUSTER_FILE_KEY));
        System.out.println(ConfigsUtils.get(Constants.SYS_REDIS_REDISSON_SINGLE_FILE_KEY));
//        System.out.println(ConfigsUtils.get("redis.test"));
    }
}
