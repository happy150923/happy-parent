package com.happy.happy.dao.common;

import java.util.Date;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.happy.happy.dao.common.model.DataDictionary;

public class FastJSONTest {
	/**
	 * Test result: <br/>
	 * com.alibaba.fastjson can deal with the time by just using a parameter {@link SerializerFeature#WriteDateUseDateFormat}<br/>
	 * The property of a class with null value will not be print out when convert Object to JSON string.
	 * 
	 */
	@Test
	public void testFastJsonDateFormat(){
		DataDictionary dataDict = new DataDictionary();
		dataDict.setCreateTime(new Date());
		System.out.println(JSONObject.toJSON(dataDict));
		System.out.println(JSON.toJSONString(dataDict,SerializerFeature.UseSingleQuotes));
		System.out.println(JSON.toJSONString(dataDict, SerializerFeature.WriteDateUseDateFormat));
		String jsonString = "{\"createTime\":\"2016-08-24\",\"dictKey\":\"001001\",\"dictText\":\"hello\"}";
		DataDictionary dataDict2 = JSON.parseObject(jsonString,DataDictionary.class);
		System.out.println(JSON.toJSONString(dataDict2,SerializerFeature.WriteDateUseDateFormat));
		
	}
}
