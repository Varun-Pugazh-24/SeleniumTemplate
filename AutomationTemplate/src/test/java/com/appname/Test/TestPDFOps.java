package com.appname.Test;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.appname.Base.TestLogger;
import com.appname.Utilities.ExtentTestNGListeners;
import com.appname.Utilities.PDFOCROperations;
import com.appname.Utilities.PDFTextOperations;

@Listeners(ExtentTestNGListeners.class)
public class TestPDFOps {

	@BeforeMethod
	public void setupTest() {
		TestLogger.startTestCase("PDFTest");
		
	}
	
	
	@Test
	public void TestOCRPDF() throws IOException {
		
		
		PDFOCROperations.readImageTextFromPDF("./src/test/resources/sample-pdf-file-image.pdf").forEach(i -> System.out.println("From Test Case ---->"+i));
		
		
	}
	
	@Test
	public void TestTextPDF() throws IOException {
		
		
		PDFTextOperations.getPDFTextByLine("./src/test/resources/sample-pdf-file-text.pdf",2).forEach(i -> System.out.println("From Test Case ---->"+i));
	}
	
	
	
	@AfterMethod
	public void TearDown() {
		TestLogger.endTestCase("PDFTest");
		
	}
	
	
	
	
	
}
