package com.happy.redis.dlm;

/**
 * @author chengxia
 * @version 2017-01-03 16:46
 */
public class DistributedLockException extends RuntimeException{
    private static final long serialVersionUID = 4135217328897249711L;

    public DistributedLockException() {
        super();
    }

    public DistributedLockException(String message) {
        super(message);
    }

    public DistributedLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistributedLockException(Throwable cause) {
        super(cause);
    }

    public DistributedLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
