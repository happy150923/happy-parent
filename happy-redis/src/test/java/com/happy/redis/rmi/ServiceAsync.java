package com.happy.redis.rmi;

import org.redisson.api.RFuture;
import org.redisson.api.annotation.RRemoteAsync;

/**
 * @author chengxia
 * @version 2017-01-13 19:54
 */
@RRemoteAsync(ServiceSync.class)
public interface ServiceAsync {
    RFuture<String> sayHello(String name);
    RFuture<String> sayGoodbye(String name);
}
