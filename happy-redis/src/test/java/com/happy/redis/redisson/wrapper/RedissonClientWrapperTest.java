package com.happy.redis.redisson.wrapper;

import com.happy.redis.jedis.JedisClient;
import com.happy.util.JSONUtils;
import org.junit.Test;

/**
 * @author chengxia
 * @version 2017-01-10 16:48
 */
public class RedissonClientWrapperTest {

    @Test
    public void setTest() {
//        System.out.println(RedissonClientWrapper.INSTANCE.set("h6", "60"));
//        System.out.println(RedissonClientWrapper.INSTANCE.getString("h6"));
//        System.out.println(JedisClient.INSTANCE.set("h7", "70"));
//        System.out.println(JedisClient.INSTANCE.getString("h7"));
//        String s = JedisClient.INSTANCE.getString("h6");
//        System.out.println("--" + s + "==");
        System.out.println(RedissonClientWrapper.INSTANCE.set("h9","hello9"));
        System.out.println(RedissonClientWrapper.INSTANCE.getString("h9"));
        System.out.println(RedissonClientWrapper.INSTANCE.set("h8", "hello8"));
        System.out.println(RedissonClientWrapper.INSTANCE.getString("h8"));
        System.out.println(JedisClient.INSTANCE.getString("h8"));
        
        System.out.println(JedisClient.INSTANCE.set("h8", "hello8J"));
        System.out.println(JedisClient.INSTANCE.getString("h8"));
        System.out.println(RedissonClientWrapper.INSTANCE.set("h8", "hello8R"));
        System.out.println(RedissonClientWrapper.INSTANCE.getString("h8"));
    }
    @Test
    public void addForList(){
        System.out.println(RedissonClientWrapper.INSTANCE.addForList("l1", "l01"));
        System.out.println(RedissonClientWrapper.INSTANCE.sizeOfForList("l1"));
        System.out.println(JSONUtils.objToJSON(RedissonClientWrapper.INSTANCE.getList("l1")));
        System.out.println(RedissonClientWrapper.INSTANCE.getForList("l1", 0));
    }
    
    @Test
    public void removeForList(){
        System.out.println(RedissonClientWrapper.INSTANCE.removeForList("l1", "l01"));
        System.out.println(RedissonClientWrapper.INSTANCE.sizeOfForList("l1"));
        System.out.println(JSONUtils.objToJSON(RedissonClientWrapper.INSTANCE.getList("l1")));
    }
    
    @Test
    public void runCommandTest(){
        System.out.println(RedissonClientWrapper.INSTANCE.set("h10","hello10"));
        System.out.println(RedissonClientWrapper.INSTANCE.getString("h10"));
//        RedissonClientWrapper.INSTANCE.runCommand("h10");
        System.out.println(RedissonClientWrapper.INSTANCE.getString("h10"));
    }
}
