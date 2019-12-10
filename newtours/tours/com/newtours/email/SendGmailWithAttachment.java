package com.newtours.email;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendGmailWithAttachment {

	public SendGmailWithAttachment() {
	}

	/*
	 * Send Report to Email account of User
	 */
	@SuppressWarnings("restriction")
	public static boolean sendGmail(String user, String pswd) {
		final String username = user;
		final String password = pswd;

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user));
			/*
			 * Set the Subject & Body data for email
			 */
			message.setSubject("NewTours Mailer");

			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Mail Sent by Automation Script from NewTours Project");

			/*
			 * Object Creation of MimeMultipart class
			 */
			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// File to Be sent
			String filename1 = "ResultForBookATicket.pdf";

			// Create data source and pass the filename
			DataSource source1 = new FileDataSource(filename1);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source1));

			// set the file
			messageBodyPart2.setFileName(filename1);
			MimeBodyPart messageBodyPart3 = new MimeBodyPart();
			String filename2 = "ResultForBookATicket.pdf";
			DataSource source2 = new FileDataSource(filename2);
			messageBodyPart3.setDataHandler(new DataHandler(source2));
			messageBodyPart3.setFileName(filename2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart2);
			// add body part 1
			multipart.addBodyPart(messageBodyPart1);
			// add body part 3
			multipart.addBodyPart(messageBodyPart3);
			message.setContent(multipart);
			Transport.send(message);

			System.out.println("Execution Report is sent successfully as Mail");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		return true;

	}

}
