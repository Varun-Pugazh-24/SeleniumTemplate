package com.appname.Base;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

public class DriverFactory {

	private DriverFactory() {

	}

	private static DriverFactory instance = new DriverFactory();

	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public static DriverFactory getInstance() {

		return instance;
	}

	public void setDriver(WebDriver wDriver) {

		driver.set(wDriver);

	}

	public WebDriver getDriver() {

		return driver.get();
	}

	public void closeBrowser() {

		driver.get().close();
		driver.remove();
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "Browser Closed");
		TestLogger.info("Browser Closed");
	}

}
