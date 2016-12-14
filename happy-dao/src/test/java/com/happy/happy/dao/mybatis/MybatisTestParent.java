package com.happy.happy.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class MybatisTestParent {

	protected static SqlSessionFactory sqlSessionFactory;
	protected SqlSession sqlSession;

	@BeforeClass
	public static void getSqlSessionFactory() {
		System.out.println("MybatisTestParent: Start to get sql session factory!");
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("MybatisTestParent: End to get sql session factory!");
	}

	@Before
	public void createSession(){
		sqlSession = sqlSessionFactory.openSession();
		System.out.println("MybatisTestParent: Session created - " + sqlSession);
	}
	@After
	public void closeSession(){
		System.out.println("MybatisTestParent: Session closed - " + sqlSession);
		if (sqlSession != null) {
			sqlSession.close();
		}
	}
	
}
