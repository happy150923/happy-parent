package com.happy.util.conf;

import com.happy.util.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.happy.log.Log;
import org.happy.log.LogFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Designed to get configurations from property files.
 * It will cache the configurations and file names. 
 * 
 * Support get property's value without specifying a file name. 
 * It will iterate the cache files one by one for the property.
 * Note: Only cache the keys and file names that have(s) been gotten before.
 * @author chengxia
 * @version 2017-01-03 11:44
 */
public class ConfigsUtils {
    private Log logger = LogFactory.getLog(ConfigsUtils.class);
    private static ConcurrentHashMap<String, String> cached = new ConcurrentHashMap();
    private static List<String> fileList = new ArrayList<>();
    private static Properties properties = new Properties();
    
    // the object to lock
    private Object monitor = new Object();

    
    public static String get(String key, String file) {
        String prop = cached.get(key);
        if (StringUtils.isNotBlank(prop)) {
            return prop;
        }
        synchronized (instance.monitor) {
            try {
                // make it support to pass file name without suffix ".properties" as argument
                if (StringUtils.lastIndexOf(file, ".") == -1) {
                    file += ".properties";
                }
                properties.load(ClassLoader.getSystemResourceAsStream(file));
                prop = String.valueOf(properties.get(key));
                if (StringUtils.isNotBlank(prop)) {
                    cached.put(key, prop);
                    if (!fileList.contains(file)) {
                        fileList.add(file);
                    }
                    if (instance.logger.isDebugEnabled()) {
                        instance.logger.debug("Have found value [" + prop + "] for key [" + key + "] in file " + file);
                    }
                    return prop;
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static String get(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        String prop = cached.get(key);
        if (StringUtils.isNotBlank(prop)) {
            return prop;
        }
        for (String file : fileList) {
            prop = get(key, file);
            if (StringUtils.isNotBlank(prop)) {
                return prop;
            }
        }
        return null;
    }
    
    private ConfigsUtils() {
        fileList.add("system.cfg");
    }

    public String toString(){
        return "file list is " + JSONUtils.objToJSON(fileList) + "; cached values are " + JSONUtils.objToJSON(cached);
    }
    /**
     * <br/>
     * This class won't do lots of things. So we can initiate it at start.
     *
     * @author chengxia
     * @version 2017 -01-03 12:01:06
     */
    private static ConfigsUtils instance = new ConfigsUtils();
}
