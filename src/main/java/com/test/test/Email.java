package com.test.test;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	private String address;

	public static void sendMail(String recipient, String subject, String info){
		final String username = "easypollsystem@gmail.com";
		final String password = "Team3IT326";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		System.out.println("DEBUG: Recipient: " + recipient);
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("easypollsystem@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recipient));
			message.setSubject(subject);
			message.setText(info);
			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void sendMassMail(String info){
		final String username = "easypollsystem@gmail.com";
		final String password = "Team3IT326";
		final String subject = "easyPoll Newsletter";

		ArrayList<String> recipients = DBQuery.getAllEmails();
		for(String recipient: recipients){
			System.out.println(recipient);
		}
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
			for(String recipient: recipients){
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("easypollsystem@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipient));
				message.setSubject(subject);
				message.setText(info);
				Transport.send(message);
				System.out.println("Done");
			}
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Email(String email){
		this.address = email;
	}
	
	public Email(){
		
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
