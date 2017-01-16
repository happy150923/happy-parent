package com.happy.redis.rmi;

/**
 * @author chengxia
 * @version 2017-01-13 19:49
 */
public class ServiceSyncImpl implements ServiceSync{

    @Override
    public String sayHello(String name) {
        return "Hello " + name + ".";
    }

    @Override
    public String sayGoodbye(String name) {
        return "Goodbye, " + name + "!";
    }
}
