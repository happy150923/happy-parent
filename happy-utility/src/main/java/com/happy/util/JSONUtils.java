package com.happy.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * @author chengxia
 * @version 2017-01-10 18:25
 */
public class JSONUtils {

    public static final String TIME_PATTER_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_PATTER_DATE = "yyyy-MM-dd";
    public static final String TIME_PATTER_TIME = "HH:mm:ss";

    public static final <T> T JSONFileToObj(String file, Class<T> clazz) {
        try {
            return getMapper().readValue(ClassLoader.getSystemResourceAsStream(file), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final <T> T JSONFileToObj(String file, TypeReference typeReference) {
        try {
            ObjectMapper mapper = getMapper();
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(file);
            return mapper.readValue(inputStream, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static final <T> T JSONToObj(String json, Class<T> clazz) {
        try {
            return getMapper().readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final <T> T JSONToObj(String json, TypeReference typeReference) {
        try {
            ObjectMapper mapper = getMapper();
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final String objToJSON(Object obj) {
        return objToJSON(obj, TIME_PATTER_DATETIME);
    }

    public static final String objToJSON(Object obj, String timePattern) {
        try {
            return getMapper(timePattern).writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ObjectMapper getMapper() {
        return getMapper(TIME_PATTER_DATETIME);
    }

    private static ObjectMapper getMapper(String timePattern) {
        ObjectMapper mapper = new ObjectMapper();
        if (StringUtils.isBlank(timePattern)) {
            mapper.setDateFormat(null);
        } else {
            mapper.setDateFormat(new SimpleDateFormat(timePattern));
        }
        return mapper;
    }

}
