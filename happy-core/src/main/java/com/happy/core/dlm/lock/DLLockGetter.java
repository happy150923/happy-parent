package com.happy.core.dlm.lock;

/**
 * @author chengxia
 * @version 2017-01-03 10:52
 * This is the Distributed Lock Interface DLLock.
 * The distributed lock can be redis red lock or others.
 * Used Pattern: Abstract Factory Pattern
 */
public interface DLLockGetter {
    /**
     * <br/>
     * Gets lock of given resource.
     *
     * @param resourceName the resource name
     * @return the lock
     * @author chengxia
     * @version / 2017-01-04 16:24:01
     */
    OneDLLock getLock(String resourceName);
    
}
