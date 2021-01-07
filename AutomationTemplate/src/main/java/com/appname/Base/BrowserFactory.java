package com.appname.Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory extends Base {
	static WebDriver driver;

	static ChromeDriver chromedriver;
	static InternetExplorerDriver iedriver;
	static EdgeDriver edgedriver;
	static FirefoxDriver firefoxdriver;
	static OperaDriver operadriver;

	public static WebDriver initDriver(String browserName) throws Exception {

		switch (browserName.toLowerCase()) {

		case "chrome":
			return startChromeBrowser();

		case "ie":
			return startIEBrowser();

		case "edge":
			return startEdgeBrowser();

		case "firefox":
			return startFireFoxBrowser();

		case "opera":
			return startOperaBrowser();

		}
		return null;

	}

	public static ChromeDriver startChromeBrowser() {

		ChromeOptions options = new ChromeOptions();
		if (prop.getProperty("headless").equals("yes")) {
			options.addArguments("--headless");
			WebDriverManager.chromedriver().setup();
			// System.setProperty("webdriver.chrome.driver",
			// prop.getProperty("ChromeDriver"));
			chromedriver = new ChromeDriver(options);
			ExtentFactory.getInstance().getExtent().log(Status.PASS,"==> Chrome browser started in headless mode");
			TestLogger.info("==> Chrome browser started in headless mode");
			chromedriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} else {
			options.addArguments("--disable-notifications");
			WebDriverManager.chromedriver().setup();
			// System.setProperty("webdriver.chrome.driver",
			// prop.getProperty("ChromeDriver"));
			chromedriver = new ChromeDriver(options);
			ExtentFactory.getInstance().getExtent().log(Status.PASS,"==> Chrome browser started");
			TestLogger.info("==> Chrome browser started");
			chromedriver.manage().window().maximize();
			chromedriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		}

		return chromedriver;
	}

	public static InternetExplorerDriver startIEBrowser() {

		WebDriverManager.iedriver().arch32().setup();
		// System.setProperty("webdriver.ie.driver", prop.getProperty("IEDriver"));
		iedriver = new InternetExplorerDriver();
		ExtentFactory.getInstance().getExtent().log(Status.PASS,"==> InternetExplorer browser started");
		TestLogger.info("==> InternetExplorer browser started");
		iedriver.manage().window().maximize();
		iedriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return iedriver;
	}

	public static EdgeDriver startEdgeBrowser() {
		WebDriverManager.edgedriver().arch32().setup();
		// System.setProperty("webdriver.edge.driver", prop.getProperty("EdgeDriver"));
		edgedriver = new EdgeDriver();
		ExtentFactory.getInstance().getExtent().log(Status.PASS,"==> Edge browser started");
		TestLogger.info("==> Edge browser started");
		edgedriver.manage().window().maximize();
		edgedriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return edgedriver;
	}

	public static FirefoxDriver startFireFoxBrowser() {
		FirefoxOptions options = new FirefoxOptions();
		if (prop.getProperty("headless").equals("yes")) {
			options.addArguments("--headless");
			WebDriverManager.firefoxdriver().arch32().setup();
			// System.setProperty("webdriver.gecko.driver",
			// prop.getProperty("FireFoxDriver"));
			firefoxdriver = new FirefoxDriver();
			ExtentFactory.getInstance().getExtent().log(Status.PASS,"==> Firefox browser started in headless mode");
			TestLogger.info("==> Firefox browser started in headless mode");
			firefoxdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		} else {
			options.addArguments("--disable-notifications");
			// WebDriverManager.firefoxdriver().arch32().setup();
			System.setProperty("webdriver.gecko.driver", prop.getProperty("FireFoxDriver"));
			firefoxdriver = new FirefoxDriver();
			ExtentFactory.getInstance().getExtent().log(Status.PASS,"==> Firefox browser started");
			TestLogger.info("==> Firefox browser started");
			firefoxdriver.manage().window().maximize();
			firefoxdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		}

		return firefoxdriver;
	}

	public static OperaDriver startOperaBrowser() {
		OperaOptions options = new OperaOptions();
		if (prop.getProperty("headless").equals("yes")) {
			options.addArguments("--headless");
			WebDriverManager.operadriver().arch32().setup();
			// System.setProperty("webdriver.opera.driver",
			// prop.getProperty("OperaDriver"));
			operadriver = new OperaDriver();
			ExtentFactory.getInstance().getExtent().log(Status.PASS,"==> Opera browser started in headless mode");
			TestLogger.info("==> Opera browser started in headless mode");
			operadriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} else {
			options.addArguments("--disable-notifications");
			WebDriverManager.operadriver().arch32().setup();
			// System.setProperty("webdriver.opera.driver",
			// prop.getProperty("OperaDriver"));
			operadriver = new OperaDriver();
			ExtentFactory.getInstance().getExtent().log(Status.PASS,"==> Opera browser started");
			TestLogger.info("==> Opera browser started");
			operadriver.manage().window().maximize();
			operadriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		}

		return operadriver;
	}

}