package com.appname.Utilities;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.appname.Base.ExtentFactory;
import com.appname.Base.TestLogger;
import com.aventstack.extentreports.Status;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.PDFStreamEngine;
 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import javax.imageio.ImageIO;
 
/**
* This will extract images from pdf and reads texts from image.
*/
public class PDFOCROperations extends PDFStreamEngine
{
   
    public PDFOCROperations() throws IOException
    {
    }
 
    public int imageNumber = 1;
    
    public static ArrayList<String> extractedOCRText = new ArrayList<String>();
 
 // OCR operations using Tesseract
	public static String doOCR(String filePath) {
		File imageFile = new File(filePath);
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        instance.setDatapath("./src/test/resources/PDFExtractedImages/tessdata"); // path to tessdata directory
        String result = null;

        try {
            result = instance.doOCR(imageFile);
            ExtentFactory.getInstance().getExtent().log(Status.PASS, "OCR Extracted Text from PDF Image");
            TestLogger.info("OCR Extracted Text from PDF Image");
        } catch (TesseractException e) {
            ExtentFactory.getInstance().getExtent().log(Status.FAIL, "OCR Error in Tesseract: "+ e.getMessage());
            TestLogger.error("OCR Error in Tesseract: "+ e.getMessage());
        }
		return result;
	}

	// converts image to text
	public static ArrayList<String> readImageTextFromPDF(String filePath) throws IOException {
		PDDocument document = null;
        try
        {
            document = PDDocument.load( new File(filePath) );
            PDFOCROperations printer = new PDFOCROperations();
            int pageNum = 0;
            for( PDPage page : document.getPages() )
            {
                pageNum++;
                ExtentFactory.getInstance().getExtent().log(Status.PASS, "Processing PDF Page: "+ pageNum );
                TestLogger.info("Processing PDF Page: "+ pageNum);
                printer.processPage(page);
            }
        }
        finally
        {
            if( document != null )
            {
            	deleteAllImages("./src/test/resources//PDFExtractedImages/");
                document.close();
            }
        }
		return extractedOCRText;
	}
	
 
    /**
     * @param operator The operation to perform.
     * @param operands The list of arguments.
     *
     * @throws IOException If there is an error processing the operation.
     */
    @Override
    protected void processOperator( Operator operator, List<COSBase> operands) throws IOException
    {
        String operation = operator.getName();
        if( "Do".equals(operation) )
        {
            COSName objectName = (COSName) operands.get( 0 );
            PDXObject xobject = getResources().getXObject( objectName );
            if( xobject instanceof PDImageXObject)
            {
                PDImageXObject image = (PDImageXObject)xobject;
 
                // same image to local
                BufferedImage bImage = image.getImage();
                String filePath = "./src/test/resources//PDFExtractedImages/image_"+imageNumber+".png";
                ImageIO.write(bImage,"PNG",new File(filePath));
                extractedOCRText.add(doOCR(filePath));
                imageNumber++;
 
            }
            else if(xobject instanceof PDFormXObject)
            {
                PDFormXObject form = (PDFormXObject)xobject;
                showForm(form);
            }
        }
        else
        {
            super.processOperator( operator, operands);
        }
    }
    
    //delets all saved images from pdf
    public static void deleteAllImages(String dir) {
    	
    	File folder = new File(dir);
        File fList[] = folder.listFiles();

        for (File f : fList) {
            if (f.getName().endsWith(".png")) {
                f.delete(); 
            }}
    }
 
}