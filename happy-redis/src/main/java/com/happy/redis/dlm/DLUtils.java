package com.happy.redis.dlm;

import com.happy.core.dlm.lock.DLLockGetter;
import com.happy.core.dlm.lock.OneDLLock;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chengxia
 * @version 2017-01-03 10:54
 * This is a utility for distributed lock.
 * Get lock implemented by implementer in base.properties.
 */
public class DLUtils {
    /**
     * used to cache the created DLLock instances to avoid to create instances with same config info.
     */
    private ConcurrentHashMap<String, DLLockGetter> dlLockGetters = new ConcurrentHashMap<>();
    private static Class<? extends DLLockGetter> DEFAULT_IMPLER_CLASS;

    /**
     * Get one dl lock referred to the configuratoin of the file base.properties.
     *
     * @param resourceName the resource name
     * @return the one dl lock
     * @author chengxia
     * @version 2017 -01-04 18:00:18
     */
    public OneDLLock getLock(String resourceName){
        String key = "default";
        DLLockGetter dlLock = dlLockGetters.get(key);
        if (dlLock != null) {
            return dlLock.getLock(resourceName);
        }
        if (DEFAULT_IMPLER_CLASS == null) {
            getDefaultImplerClass();
        }
        try {
            dlLock = DEFAULT_IMPLER_CLASS.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (dlLock != null) {
            dlLockGetters.putIfAbsent(key, dlLock);
            return dlLock.getLock(resourceName);
        }
        return null;
    }

    /**
     * Get one dl lock using given ip and port implemented by the implementer in base.properties.
     * 
     * @param ip           the ip
     * @param port         the port
     * @param resourceName the resource name
     * @return the one dl lock
     * @author chengxia
     * @version 2017 -01-04 18:05:12
     */
    public OneDLLock getLock(String ip, int port, String resourceName){
        return getLock(true, ip, port, resourceName);
    }

    /**
     * Get one dl lock using given ip and port implemented by the implementer in base.properties.
     *
     * @param useCluster   the use cluster
     * @param ip           the ip
     * @param port         the port
     * @param resourceName the resource name
     * @return the one dl lock
     * @author chengxia
     * @version 2017 -01-04 18:06:47
     */
    public OneDLLock getLock(boolean useCluster, String ip, int port, String resourceName) {
        String key = String.valueOf(useCluster) + ip + ":" + port;
        DLLockGetter dlLock = dlLockGetters.get(key);
        if (dlLock != null) {
            return dlLock.getLock(resourceName);
        }
        if (DEFAULT_IMPLER_CLASS == null) {
            getDefaultImplerClass();
        }
        try {
            dlLock = DEFAULT_IMPLER_CLASS.getConstructor(boolean.class, String.class, int.class).newInstance(useCluster, ip, port);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (dlLock != null) {
            dlLockGetters.putIfAbsent(key, dlLock);
            return dlLock.getLock(resourceName);
        }
        return null;
    }

    /**
     * Get default implementer's class configured in base.properties file.
     * Validate it's type as well.
     * @author chengxia
     * @version 2017 -01-04 17:53:11
     */
    private void getDefaultImplerClass(){
        String implementerClassName = Constants.BASE_DL_IMPLEMENTER;
        if (StringUtils.isBlank(implementerClassName)) {
            throw new DistributedLockException("There is no specified DL implementer in config file(s) suffixed with '.properties'.");
        }
        try {
            Class confImplerClass = Class.forName(implementerClassName);
            if (!DLLockGetter.class.isAssignableFrom(confImplerClass)) {
                throw new DistributedLockException("The DL implementer specified in config file(s) must implement class: com.happy.dlm.lock.DLLock!");
            }
            DEFAULT_IMPLER_CLASS = confImplerClass;
        } catch (ClassNotFoundException e) {
            throw new DistributedLockException(e);
        }
    }
    
    private DLUtils(){}
    public static DLUtils getInstance(){
        return InstanceHolder.DLINSTANCE;
    }
    
    /**
     * @author cx
     * @version 2017-01-03 11:05:32
     */
    private static class InstanceHolder{
        private InstanceHolder(){}
        private final static DLUtils DLINSTANCE = new DLUtils();
    }
}

