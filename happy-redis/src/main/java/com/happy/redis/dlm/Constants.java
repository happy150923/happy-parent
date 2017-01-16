package com.happy.redis.dlm;

import com.happy.util.conf.ConfigsUtils;

/**
 * The class contains the constants used by distributed lock.
 * @author chengxia
 * @version 2017-01-03 11:35 
 */
public class Constants {
    public final static String BASE_DL_IMPLEMENTER_STR = "dl.implementer";
    public final static String BASE_PROP_FILE = "base.properties";
    public static final String BASE_DL_IMPLEMENTER = ConfigsUtils.get(BASE_DL_IMPLEMENTER_STR, BASE_PROP_FILE);
}
