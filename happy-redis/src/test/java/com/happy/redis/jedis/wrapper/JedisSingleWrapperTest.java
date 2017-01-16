package com.happy.redis.jedis.wrapper;


import com.happy.redis.Constants;
import org.junit.Test;

/**
 * @author chengxia
 * @version 2017-01-06 22:04
 */
public class JedisSingleWrapperTest {
    
    @Test
    public void getStringTestWithCluster(){
        JedisSingleWrapper jedisSingleUtil = new JedisSingleWrapper(Constants.JEDIS_SERVER_IP, Constants.JEDIS_SERVER_PORT);
        System.out.println(jedisSingleUtil.getString("h3"));
        System.out.println(jedisSingleUtil.getString("h1"));
        System.out.println(jedisSingleUtil.getString("h2"));
    }
    
}
