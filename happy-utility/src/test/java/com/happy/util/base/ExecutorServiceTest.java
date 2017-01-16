package com.happy.util.base;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.concurrent.*;

/**
 * @author chengxia
 * @version 2017-01-16 12:03
 */
public class ExecutorServiceTest {

    @Test
    public void scheduleTest() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        CallableImpl impl = new CallableImpl();
        ScheduledFuture future = executorService.schedule(impl, 10l, TimeUnit.SECONDS);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        future = executorService.schedule(impl, 20l, TimeUnit.SECONDS);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

class RunnableScheduler implements Runnable {
    int i;

    @Override
    public void run() {
        System.out.println(new Timestamp(System.currentTimeMillis()) + " " + Thread.currentThread() + " " + (i++));
    }
}
class CallableImpl implements Callable<Integer>{

    int c;
    @Override
    public Integer call() throws Exception {
        return c++;
    }
}
