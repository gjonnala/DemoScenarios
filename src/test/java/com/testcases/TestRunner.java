package com.testcases;

import org.testng.TestNG;

public class TestRunner {

	
	static TestNG testng;
	
	public static void main(String[] args) {
		
		testng = new TestNG();
		testng.setTestClasses(new Class[]{TestScenario1.class});
		
		testng.run();
		
	}

}
