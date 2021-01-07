package com.appname.Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.appname.Base.DriverFactory;
import com.appname.Base.ExtentFactory;
import com.appname.Base.TestLogger;
import com.aventstack.extentreports.Status;

public class ActionEngine {

	// Customized sendkeys method-> To log sendkeys message for every occ.
	public void sendKeys(WebElement element, String fieldName, String valueToBeSent) {
		try {
			element.sendKeys(valueToBeSent);
			// log success message in exgent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS,
					fieldName + "==> Entered value as: " + valueToBeSent);
			TestLogger.info(fieldName + "==> Entered value as: " + valueToBeSent);
		} catch (Exception e) {
			// log failure in extent
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Value enter in field: " + fieldName + " is failed due to exception: " + e);
			TestLogger.error("Value enter in field: " + fieldName + " is failed due to exception: " + e);
		}
	}

	// custom click method to log evey click action in to extent report
	public void click(WebElement element, String fieldName) {
		try {
			element.click();
			// log success message in exgent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName + "==> Clicked Successfully! ");
			TestLogger.info(fieldName + "==> Clicked Successfully! ");
		} catch (Exception e) {
			// log failure in extent
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Unable to click on field: " + fieldName + " due to exception: " + e);
			TestLogger.error("Unable to click on field: " + fieldName + " due to exception: " + e);
		}
	}

	// clear data from field
	public void clear(WebElement element, String fieldName) {
		try {
			element.clear();
			Thread.sleep(250);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName + "==> Data Cleared Successfully! ");
			TestLogger.info(fieldName + "==> Data Cleared Successfully! ");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Unable to clear Data on field: " + fieldName + " due to exception: " + e);
			TestLogger.error("Unable to clear Data on field: " + fieldName + " due to exception: " + e);
		}
	}

	// custom mouseHover
	public void goToWebElement(WebElement element, String fieldName) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());
			actions.moveToElement(element).build().perform();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName + "==> Mouse hovered Successfully! ");
			TestLogger.info(fieldName + "==> Mouse hovered Successfully! ");
			Thread.sleep(1000);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Unable to hover mouse on field: " + fieldName + " due to exception: " + e);
			TestLogger.error("Unable to hover mouse on field: " + fieldName + " due to exception: " + e);

		}
	}

	// check if element is Present
	public boolean isDisplayed(WebElement element, String fieldName) {
		boolean flag = false;
		try {
			flag = element.isDisplayed();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName + "==> Presence of field is: " + flag);
			TestLogger.info(fieldName + "==> Presence of field is: " + flag);
			return flag;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Checking for presence of field: " + fieldName + " not tested due to exception: " + e);
			TestLogger.error("Checking for presence of field: " + fieldName + " not tested due to exception: " + e);
			return flag;
		}
	}
	
	// check if element is selected
		public boolean isSelected(WebElement element, String fieldName) {
			boolean flag = false;
			try {
				flag = element.isSelected();
				ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName + "==> is Selected");
				TestLogger.info(fieldName + "==> is Selected");
				return flag;
			} catch (Exception e) {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL,
						"Checking for field: " + fieldName + " not tested due to exception: " + e);
				TestLogger.error("Checking for field: " + fieldName + " not tested due to exception: " + e);
				return flag;
			}
		}
		
		// check if element is selected
				public boolean isEnabled(WebElement element, String fieldName) {
					boolean flag = false;
					try {
						flag = element.isEnabled();
						ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName + "==> is Enabled");
						TestLogger.info(fieldName + "==> is Enabled");
						return flag;
					} catch (Exception e) {
						ExtentFactory.getInstance().getExtent().log(Status.FAIL,
								"Checking for field: " + fieldName + " not tested due to exception: " + e);
						TestLogger.error("Checking for field: " + fieldName + " not tested due to exception: " + e);
						return flag;
					}
				}

	// Select dropdown value value by visibleText
	public void selectDropDownByVisibleText(WebElement element, String fieldName, String ddVisibleText)
			throws Throwable {
		try {
			Select s = new Select(element);
			s.selectByVisibleText(ddVisibleText);
			ExtentFactory.getInstance().getExtent().log(Status.PASS,
					fieldName + "==> Dropdown Value Selected by visible text: " + ddVisibleText);
			TestLogger.info(fieldName + "==> Dropdown Value Selected by visible text: " + ddVisibleText);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Dropdown value not selected for field: " + fieldName + "  due to exception: " + e);
			TestLogger.error("Dropdown value not selected for field: " + fieldName + "  due to exception: " + e);
		}
	}

	// Select dropdown value value by value
	public void selectDropDownByValue(WebElement element, String fieldName, String ddValue) throws Throwable {
		try {
			Select s = new Select(element);
			s.selectByValue(ddValue);
			ExtentFactory.getInstance().getExtent().log(Status.PASS,
					fieldName + "==> Dropdown Value Selected by visible text: " + ddValue);
			TestLogger.info(fieldName + "==> Dropdown Value Selected by visible text: " + ddValue);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Dropdown value not selected for field: " + fieldName + "  due to exception: " + e);
			TestLogger.error("Dropdown value not selected for field: " + fieldName + "  due to exception: " + e);
		}
	}

	// String Asserts
	public void assertEqualsString(String expvalue, String actualValue, String locatorName) throws Throwable {
		try {
			if (actualValue.equals(expvalue)) {
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "String Assertion is successful on field "
						+ locatorName + " Expected value was: " + expvalue + " actual value is: " + actualValue);
				TestLogger.info("String Assertion is successful on field " + locatorName + " Expected value was: "
						+ expvalue + " actual value is: " + actualValue);
				Assert.assertEquals(expvalue, actualValue);
			} else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "String Assertion FAILED on field "
						+ locatorName + " Expected value was: " + expvalue + " actual value is: " + actualValue);
				TestLogger.error("String Assertion FAILED on field " + locatorName + " Expected value was: " + expvalue
						+ " actual value is: " + actualValue);
				Assert.assertEquals(expvalue, actualValue);
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.toString());
		}
	}

	public void assertTrue(boolean bool, String fieldName) {

		try {
			if (bool) {
				ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName +" Is Displayed");
				TestLogger.info(fieldName +" Is Displayed");
				
				Assert.assertTrue(bool);
			} else {

				ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName +" Is not Displayed");
				TestLogger.info(fieldName +" Is not Displayed");
				
				Assert.assertTrue(bool);
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.toString());
		}
	}

	// Get text from webelement
	public String getText(WebElement element, String fieldName) {
		String text = "";
		try {
			text = element.getText();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName + "==> Text retrived is: " + text);
			TestLogger.info(fieldName + "==> Text retrived is: " + text);
			return text;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					fieldName + "==> Text not retrived due to exception: " + e);
			TestLogger.error(fieldName + "==> Text not retrived due to exception: " + e);

		}
		return text;
	}

}
