package com.happy.zkeeper;

import com.happy.util.conf.ConfigsUtils;

/**
 * @author chengxia
 * @version 2017-01-16 16:20
 */
public class Constants {
    private static final String ZOOKEEPER_SERVERS_KEY = "zookeeper.servers";
    
    private static final String ZOOKEEPER_CONFIG_FILE = ConfigsUtils.get(com.happy.core.Constants.SYS_ZOOKEEPER_CONFIG_FILE_KEY);
    
    public static final String ZOOKEEPER_SERVERS_STR = ConfigsUtils.get(ZOOKEEPER_SERVERS_KEY, ZOOKEEPER_CONFIG_FILE);
    
}
