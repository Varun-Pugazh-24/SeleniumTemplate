package com.appname.Utilities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import com.appname.Base.ExtentFactory;
import com.appname.Base.TestLogger;
import com.aventstack.extentreports.Status;

public class PDFTextOperations extends PDFTextStripper {

	
	
	static List<String> lines = new ArrayList<String>();
	public PDFTextOperations() throws IOException {
		// TODO Auto-generated constructor stub
	}

	public static List<String> getPDFTextByLine(String fileName, int pageNum) throws IOException {
		
		
		 PDDocument document = null;
	        try {
	            document = PDDocument.load( new File(fileName) );
	            PDFTextStripper stripper = new PDFTextOperations();
	            stripper.setSortByPosition( true );
	            stripper.setStartPage( pageNum );
	            stripper.setEndPage(pageNum );
	            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
	            stripper.writeText(document, dummy);
	            ExtentFactory.getInstance().getExtent().log(Status.PASS, "Extracted Text from PDF");
	            TestLogger.info("Extracted Text from PDF");
	            
	      
	        }
	        finally {
	            if( document != null ) {
	                document.close();
	            }
	        }
			return lines;
		
	}
	
	 /**
     * Override the default functionality of PDFTextStripper.writeString()
     */
	@Override
    protected void writeString(String str, List<TextPosition> textPositions) throws IOException {
        lines.add(str);
        // you may process the line here itself, as and when it is obtained
    }
	
	
	
	
	
	
	
	
	
}
