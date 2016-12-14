package com.happy.happy.dao.mybatis;

import org.junit.Ignore;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.happy.happy.dao.common.mapper.DataDictionaryMapper;
import com.happy.happy.dao.common.model.DataDictionary;
import com.happy.happy.dao.user.mapper.WebUserMapper;
import com.happy.happy.dao.user.model.WebUser;

public class MybatisTest extends MybatisTestParent {

	@Test
	@Ignore
	public void testMybatisSqlSessionWithSpecifyXmlSqlId() {
		DataDictionary dataDict = (DataDictionary) sqlSession
				.selectOne("com.happy.happy.dao.common.dao.DataDictionaryMapper.selectByPrimaryKey", 1);
		System.out.println(JSON.toJSONString(dataDict, SerializerFeature.WriteDateUseDateFormat));
	}

	@Test
	@Ignore
	public void testMybatisSqlSessionWithJavaMethodInvoke() {
		try {
			DataDictionaryMapper dataDictionaryMapper = sqlSession.getMapper(DataDictionaryMapper.class);
			DataDictionary dataDict = dataDictionaryMapper.selectByPrimaryKey(1);
			System.out.println(JSON.toJSONString(dataDict, SerializerFeature.WriteDateUseDateFormat));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testMybatisAnnotation(){
		try {
			WebUserMapper webUserMapper = sqlSession.getMapper(WebUserMapper.class);
			WebUser dataDict = webUserMapper.selectByPrimaryKey(1);
			System.out.println(JSON.toJSONString(dataDict, SerializerFeature.WriteDateUseDateFormat));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
