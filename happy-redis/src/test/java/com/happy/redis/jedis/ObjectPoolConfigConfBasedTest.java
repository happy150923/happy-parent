package com.happy.redis.jedis;

import com.happy.redis.jedis.ObjectPoolConfigConfBased;
import org.junit.Test;

/**
 * @author chengxia
 * @version 2017-01-10 12:21
 */
public class ObjectPoolConfigConfBasedTest {
    
    @Test
    public void getInstanceTest(){
        ObjectPoolConfigConfBased instance = ObjectPoolConfigConfBased.getInstance();
        System.out.println(instance.getMaxIdle());
        System.out.println(instance.getMaxTotal());
        System.out.println(instance.getMaxWaitMillis());
        System.out.println(instance.getBlockWhenExhausted());
        System.out.println(instance.getTimeBetweenEvictionRunsMillis());
    }
    @Test
    public void toStringTest(){
        ObjectPoolConfigConfBased instance = ObjectPoolConfigConfBased.getInstance();
        System.out.println(instance);
    }
}
