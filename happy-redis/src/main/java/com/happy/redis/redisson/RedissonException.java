package com.happy.redis.redisson;

/**
 * @author chengxia
 * @version 2017-01-13 17:42
 */
public class RedissonException extends RuntimeException {
    public RedissonException() {
    }

    public RedissonException(String message) {
        super(message);
    }

    public RedissonException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedissonException(Throwable cause) {
        super(cause);
    }

    public RedissonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
