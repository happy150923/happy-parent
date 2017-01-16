package com.happy.redis;

/**
 * The types in redis db.
 * @author chengxia
 * @version 2017-01-09 15:35
 */
public enum RedisTypes {
    NONE("none" , "The key does not exist!"),
    STRING("string", "The type of value is string!"),
    LIST("list","The type of value is list!"),
    SET("set","The type of value is set!"),
    ZSET("zset", "The type of value is sorted set!"),
    HASH("hash", "The type of value is hash!");

    private String typeStr;
    private String desc;
    private RedisTypes (String typeStr, String desc){
        this.typeStr = typeStr;
        this.desc = desc;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public String getDesc() {
        return desc;
    }
}
