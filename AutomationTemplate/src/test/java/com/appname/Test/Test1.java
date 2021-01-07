package com.appname.Test;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.appname.Base.Base;
import com.appname.Base.DriverFactory;
import com.appname.Base.ExtentFactory;
import com.appname.Base.TestLogger;
import com.appname.TestEngine.KeywordEngine;
import com.appname.Utilities.ExtentTestNGListeners;
import com.aventstack.extentreports.Status;

@Listeners(ExtentTestNGListeners.class)
public class Test1 {

	public static String tc = null;
	
	
	@Test(dataProvider = "TestSuiteSheet")
	public void TestCase(String testCase, String isSkipped) throws Throwable {
		tc = testCase;
		TestLogger.startTestCase(testCase);
		ExtentFactory.getInstance().getExtent().log(Status.PASS, testCase+" is Started");
		if (isSkipped.equalsIgnoreCase("no")) {
			KeywordEngine.startExecution(testCase);
		}else {
			TestLogger.warn(testCase+" is Skipped");
			ExtentFactory.getInstance().getExtent().log(Status.SKIP, testCase+" is Skipped");
			throw new SkipException("Test Case Skipped");
		}   
		  
	}
	
	
	@AfterMethod
	public void tearDown() {
		TestLogger.endTestCase(tc);
		try {
			DriverFactory.getInstance().closeBrowser();
		} catch (Exception e) {
		}
	}
	
	@DataProvider(name = "TestSuiteSheet")
	public Object[][] TestSuiteSheet() {

		return Base.getTestDataSheet("TestSuite");
	}
	
	

}
