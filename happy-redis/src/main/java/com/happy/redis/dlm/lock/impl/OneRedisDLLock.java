package com.happy.redis.dlm.lock.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import com.happy.core.dlm.lock.OneDLLock;
import org.redisson.api.RLock;

/**
 * One lock obtained by {@link DLRedisLockGetter} This is an wrapper of {@link RLock}. By now, it is simply composed of two basic methods. It will be fulfilled as demanded.
 * @author chengxia
 * @version 2017-01-03 20:13
 */
public class OneRedisDLLock implements OneDLLock {

    private RLock rdsLock;

    public OneRedisDLLock(RLock rdsLock) {
        this.rdsLock = rdsLock;
    }

    @Override
    public boolean tryLock(long waitTime, long lockTime, TimeUnit timeUnit) {
        if (rdsLock == null) {
            return false;
        }
        boolean lockRst = false;
        try {
            lockRst = rdsLock.tryLock(waitTime, lockTime, timeUnit);
        } catch (InterruptedException e) {
            e.printStackTrace();
            lockRst = false;
        }
        return lockRst;
    }

    /**
     * Checks if this lock locked by any thread
     * @return <code>true</code> if locked otherwise <code>false</code>
     */
    public boolean isLocked() {
        return rdsLock.isLocked();
    }

    @Override
    public void lock() {
        rdsLock.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        rdsLock.lockInterruptibly();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return rdsLock.tryLock(time, unit);
    }

    @Override
    public void unlock() {
        rdsLock.unlock();
    }

    @Override
    public Condition newCondition() {
        return rdsLock.newCondition();
    }

    @Override
    public Lock getWrappedLock() {
        return rdsLock;
    }

}
