package com.appname.Utilities;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {

	Session session;
	String toEmail;
	String subject;
	String body;
	String attachmentFilePath;

	private EmailUtil(Builder builder) {

		this.session = builder.session;
		this.toEmail = builder.toEmail;
		this.subject = builder.subject;
		this.body = builder.body;
		this.attachmentFilePath = builder.attachmentFilePath;
	}

	public void sendEmail() {
		try {
			MimeMessage msg = new MimeMessage(this.session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("no_reply@extentreport.com", "NoReply-JD"));

			msg.setReplyTo(InternetAddress.parse("no_reply@extentreport.com", false));

			msg.setSubject(this.subject, "UTF-8");

			msg.setText(this.body, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.toEmail, false));

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(body);

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = this.attachmentFilePath;
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			msg.setContent(multipart);

			// Send message
			Transport.send(msg);
			System.out.println("EMail Sent Successfully with attachment!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static class Builder {

		private Session session;
		private String toEmail;
		private String subject;
		private String body;
		private String attachmentFilePath;

		public Builder() {

		}

		public Builder setSession(Session session) {

			this.session = session;

			return this;

		}

		public Builder setToEmail(String toEmail) {

			this.toEmail = toEmail;

			return this;

		}

		public Builder setSubject(String subject) {

			this.subject = subject;

			return this;

		}

		public Builder setBody(String body) {

			this.body = body;

			return this;

		}

		public Builder setAttachmentPath(String attachmentFilePath) {

			this.attachmentFilePath = attachmentFilePath;

			return this;

		}

		public EmailUtil build() {

			return new EmailUtil(this);
		}

	}

}
