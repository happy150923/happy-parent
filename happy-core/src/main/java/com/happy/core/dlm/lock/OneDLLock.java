package com.happy.core.dlm.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * * The interface for one distributed lock got
 * @author cx
 * @version 2017-01-03 20:12
 */
public interface OneDLLock extends Lock{
    long DEFAULT_WAIT_TIME = 10;
    long DEFAULT_LOCK_TIME = 600;
    TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;


    /**
     * <br/>
     * Try lock the resource.
     *
     * @param waitTime     the wait time
     * @param lockTime     the lock time
     * @param timeUnit     the time unit
     * @return the boolean
     * @author chengxia
     * @version 2017 -01-04 14:41:03
     */
    boolean tryLock(long waitTime, long lockTime, TimeUnit timeUnit);

    /**
     * <br/>
     * Try to lock the resource.
     *
     * @return the boolean
     * @author chengxia
     * @version 2017 -01-04 14:41:10
     */
    default boolean tryLock() {
        return tryLock(DEFAULT_WAIT_TIME, DEFAULT_LOCK_TIME, DEFAULT_TIME_UNIT);
    }

    /**
     * Checks if this lock locked by any thread
     *
     * @return <code>true</code> if locked otherwise <code>false</code>
     */
    boolean isLocked();

    /**
     * <br/>
     * Unlock the resource.
     *
     * @author chengxia
     * @version 2017 -01-04 14:41:15
     */
    void unlock();

    /**
     * Gets the wrapped lock.
     * @return the wrapped lock
     * @author cx
     * @version / 2017-01-13 18:45:28
     */
    Lock getWrappedLock();
}
