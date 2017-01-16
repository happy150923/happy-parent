package com.happy.redis;

import com.happy.redis.RedisTypes;
import com.happy.redis.redisson.RedissonException;
import org.redisson.api.RType;

/**
 * @author chengxia
 * @version 2017-01-13 18:21
 */
public class RTypeConvertor {
    
    public static RedisTypes convert(String jType){
        if ("string".equals(jType)) {
            return RedisTypes.STRING;
        }
        if ("list".equals(jType)) {
            return RedisTypes.LIST;
        }
        if ("set".equals(jType)) {
            return RedisTypes.SET;
        }
        if ("zset".equals(jType)) {
            return RedisTypes.ZSET;
        }
        if ("hash".equals(jType)) {
            return RedisTypes.HASH;
        }
        if ("none".equals(jType)) {
            return RedisTypes.NONE;
        }
        throw new RedissonException("Can't recognise redis type : " + jType);
    }
    
    public static RedisTypes convert(RType rType){
        if (rType == null ) {
            return RedisTypes.NONE;
        } else if (rType == RType.OBJECT) {
            return RedisTypes.STRING;
        } else if (rType == RType.LIST) {
            return RedisTypes.LIST;
        } else if (rType == RType.SET) {
            return RedisTypes.SET;
        } else if (rType == RType.ZSET) {
            return RedisTypes.ZSET;
        } else if (rType == RType.MAP) {
            return RedisTypes.HASH;
        }
        throw new RedissonException("Can't recognise redisson type : " + rType);
    }
}
