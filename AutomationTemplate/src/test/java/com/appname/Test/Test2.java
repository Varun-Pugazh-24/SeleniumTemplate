package com.appname.Test;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.appname.Base.DriverFactory;
import com.appname.BaseTest.BaseTest;
import com.appname.Utilities.ActionEngine;
import com.appname.Utilities.ExtentTestNGListeners;

@Listeners(ExtentTestNGListeners.class)
public class Test2 extends BaseTest {



	@Test
	public void TestCase1() throws Throwable {

		System.out.println("Test Case 1"+Thread.currentThread().getId());

		ActionEngine ae = new ActionEngine();

		ae.click(DriverFactory.getInstance().getDriver().findElement(By.linkText("Automobile")),
				"Auto Mobile Link");
		Thread.sleep(2000);
		ae.selectDropDownByValue(DriverFactory.getInstance().getDriver().findElement(By.id("make")), "Make",
				"Audi");
		ae.sendKeys(DriverFactory.getInstance().getDriver().findElement(By.id("engineperormance")),
				"EnginePerf", "125");
		

	}  

	@Test
	public void TestCase2() throws Throwable {
		System.out.println("Test Case 2"+Thread.currentThread().getId());
		
		ActionEngine ae = new ActionEngine();  

		ae.click(DriverFactory.getInstance().getDriver().findElement(By.linkText("Automobile")),
				"Auto Mobile Link");
		Thread.sleep(2000);
		ae.selectDropDownByValue(DriverFactory.getInstance().getDriver().findElement(By.id("make")), "Make",
				"Audi");
		ae.sendKeys(DriverFactory.getInstance().getDriver().findElement(By.id("engineperformance")),
				"EnginePerf", "125");


	}

}
