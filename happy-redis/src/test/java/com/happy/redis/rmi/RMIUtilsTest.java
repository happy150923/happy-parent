package com.happy.redis.rmi;

import org.junit.Test;
import org.redisson.api.RFuture;

import java.util.concurrent.ExecutionException;

/**
 * @author chengxia
 * @version 2017-01-13 19:46
 */
public class RMIUtilsTest {

    @Test
    public void syncTest() {
        ServiceSync serviceSyncImpl = new ServiceSyncImpl();
        RMIUtils.getInstance().registerRemoteService(ServiceSync.class, serviceSyncImpl);
        ServiceSync remoteService = RMIUtils.getInstance().getRemoteService(ServiceSync.class);
        System.out.println(remoteService.sayHello("baby"));
        System.out.println(remoteService.sayGoodbye("mummy"));
    }

    @Test
    public void asyncTest() {
        ServiceSync serviceSyncImpl = new ServiceSyncImpl();
        RMIUtils.getInstance().registerRemoteService(ServiceSync.class, serviceSyncImpl);
        ServiceAsync remoteService = RMIUtils.getInstance().getRemoteService(ServiceAsync.class);
        RFuture<String> rFuture = remoteService.sayHello("baby");
        try {
            System.out.println(rFuture.get());
            rFuture = remoteService.sayGoodbye("dady");
            System.out.println(rFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
