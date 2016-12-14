package com.happy.happy.dao.common.junit;

import org.junit.*;

public class JunitAnnotationTestParent {

	@BeforeClass
	public static void beforeClassMethod1() {
		System.out.println("MybatisTestParent.beforeClassMethod1!");
	}
	@BeforeClass
	public static void beforeClassMethod2() {
		System.out.println("MybatisTestParent.beforeClassMethod2!");
	}

	@Before
	public void beforeMethod1() {
		System.out.println("MybatisTestParent.beforeMethod1");
	}
	@Before
	public void beforeMethod2() {
		System.out.println("MybatisTestParent.beforeMethod2");
	}

	@After
	public void afterMethod1() {
		System.out.println("MybatisTestParent.afterMethod1");
	}
	@After
	public void afterMethod2() {
		System.out.println("MybatisTestParent.afterMethod2");
	}
	@AfterClass
	public static void afterClassMethod1(){
		System.out.println("MybatisTestParent.afterClassMethod1");
	}
	@AfterClass
	public static void afterClassMethod2(){
		System.out.println("MybatisTestParent.afterClassMethod2");
	}
}
