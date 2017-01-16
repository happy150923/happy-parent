package com.happy.redis.rmi;

import com.happy.redis.redisson.RedissonClientHolder;
import org.redisson.api.RRemoteService;
import org.redisson.api.RemoteInvocationOptions;

/**
 * The RMI utility that implemented using redisson.
 * @author chengxia
 * @version 2017-01-13 19:11
 */
public class RMIUtils {
    
    private RRemoteService remoteService = RedissonClientHolder.getRedisson().getRemoteService();
    private RemoteInvocationOptions options = RemoteInvocationOptions.defaults();

    /**
     * The Default workers amount. A int value depends on your situation.
     * @author cx
     * @version 2017 -01-13 19:44:09
     */
    private int defaultWorkersAmount = 3;
    
    public <T, I extends T> void registerRemoteService(Class<T> sericeClass, I impl){
        registerRemoteService(sericeClass, impl, defaultWorkersAmount);
    }
    public <T, I extends T> void registerRemoteService(Class<T> sericeClass, I impl, int maxConcurrentInvocations){
        remoteService.register(sericeClass, impl,maxConcurrentInvocations);
    }
    
    public <T> T getRemoteService(Class<T> remoteServiceClass){
        return getRemoteService(remoteServiceClass, options);
    }
    public <T> T getRemoteService(Class<T> remoteServiceClass, RemoteInvocationOptions options){
        return remoteService.get(remoteServiceClass, options);
    }

    /**
     * Gets instance.
     * Use this method to get instance is to save jvm space as much as possible.
     * @return the instance
     * @author cx
     * @version / 2017-01-13 19:25:33
     */
    public static RMIUtils getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private InstanceHolder() {
        }

        private final static RMIUtils INSTANCE = new RMIUtils();
    }

}
