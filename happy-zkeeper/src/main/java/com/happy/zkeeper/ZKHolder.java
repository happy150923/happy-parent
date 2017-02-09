package com.happy.zkeeper;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author chengxia
 * @version 2017-01-16 16:36
 */
public class ZKHolder {
    
    private ZKHolder(){}
    public static final ZkClient getZkClient(){
        return Holder.INSTANCE;
    }
    private static class Holder{
        private static ZkClient INSTANCE = new ZkClient(Constants.ZOOKEEPER_SERVERS_STR);
    }
}
