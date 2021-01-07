package com.appname.Utilities;

import java.io.File;
import java.util.Base64;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.appname.Base.Base;

public class MailSetup {

	
	  public static void main(String[] args) {
	  
	  
			
			/*
			 * String originalInput = "enteryoupasswordtoencode"; String encodedString =
			 * Base64.getEncoder().encodeToString(originalInput.getBytes());
			 * 
			 * byte[] decodedBytes = Base64.getDecoder().decode(encodedString); String
			 * decodedString = new String(decodedBytes);
			 * 
			 * System.out.println(encodedString); System.out.println(decodedString);
			 * 
			 * 
			 * String reportPath = System.getProperty("user.dir")
			 * +"/Reports/ExecutionReport_31-12-2020 00-02-53.html";
			 * 
			 * EmailUtil.sendEmail(GMailSession(),
			 * "varunprasadi10@gmail.com","Sending from java mail api",
			 * "Body of the mail 2", MailSetup.getLatestReport());
			 */
			 
	  
	 // System.out.println(getLatestReport());
	  
	  }
	 

	public static Session GMailSession() {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
		props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); // SMTP Port

		Properties configProperties = Base.initProperties();
		// Decode encoded password - generate gmail app password to send email if you enabled 2 factor authentication in Gmail
		byte[] decodedBytes = Base64.getDecoder().decode(configProperties.getProperty("Password"));
		String decodedString = new String(decodedBytes);

		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(configProperties.getProperty("FromEmail"), decodedString);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);

		return session;

	}
	
	public static String getLatestReport()
	{
	    File directory = new File("./Reports/");
	    File[] files = directory.listFiles(File::isFile);
	    long lastModifiedTime = Long.MIN_VALUE;
	    File chosenFile = null;

	    if (files != null)
	    {
	        for (File file : files)
	        {
	            if (file.lastModified() > lastModifiedTime)
	            {
	                chosenFile = file;
	                lastModifiedTime = file.lastModified();
	            }
	        }
	    }
	    

	    return chosenFile.getPath();
	}

}
