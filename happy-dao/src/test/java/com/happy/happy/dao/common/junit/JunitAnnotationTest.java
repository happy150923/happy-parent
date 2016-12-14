package com.happy.happy.dao.common.junit;

import org.junit.Test;

public class JunitAnnotationTest extends JunitAnnotationTestParent{
	@Test
	public void testBeforeClassAnnotation(){
		System.out.println("MybatisTest.testBeforeClassAnnotation running!");
	}
	/**
	 * Test result: <br/>
	 * The method annotated with {@code @After} with be invoked no matter whether the method annotated with {@code @Test} throws Exception
	 */
	@Test
	public void testAfterJunitAnnotationWithExcep() throws Exception{
		throw new Exception("MybatisTest.testAfterJunitAnnotationWithExcep: hello, error occurred! ");
	}
}
