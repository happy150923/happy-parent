package com.happy.redis.dlm;

import com.happy.core.dlm.lock.OneDLLock;
import org.junit.Test;

/**
 * @author chengxia
 * @version 2017-01-03 16:54
 */
public class DLUtilsTest {
    
    @Test
    public void getInstanceTest(){
        System.out.println(DLUtils.getInstance());
    }
    @Test
    public void getLockTest(){
        try {
            OneDLLock oneDLLock = DLUtils.getInstance().getLock("hello");
            boolean success = oneDLLock.tryLock();
            System.out.println("Lock " + String.valueOf(success));
            System.out.println("Lock " + oneDLLock.isLocked());
            oneDLLock.unlock();
            System.out.println("Lock " + oneDLLock.isLocked());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
