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
	
	//Private class variables
	private static final Void Void = null;
	private String address;

	/**
	 * This function will send an email to a recipient using the Gmail account 
	 * that I created.
	 * @param recipient
	 * @param subject
	 * @param info
	 * @return
	 */
	public static Void sendMail(String recipient, String subject, String info){
		final String username = "easypollsystem@gmail.com";
		//To defend from malicious bots on the internet trying to mess with things 
		//I will split password so that nobody takes over the account.
		String pass = "Team3";
		String word = "IT326";
		final String password = pass + word;

		//This is using the Google SMTP server
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		System.out.println("DEBUG: Recipient: " + recipient);
		
		//Authenticating
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		
		//Try sending the email
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
		return Void;
	}
	
	/**
	 * This function will send an email to all users in the 
	 * websites database
	 * @param info
	 * @return
	 */
	public static Void sendMassMail(String info){
		final String username = "easypollsystem@gmail.com";
		
		//To defend from malicious bots on the internet trying to mess with things 
		//I will split password so that nobody takes over the account.
		String pass = "Team3";
		String word = "IT326";
		final String password = pass + word;
		
		final String subject = "easyPoll Newsletter";

		//Get every email in the DB
		ArrayList<String> recipients = DBQuery.getAllEmails();
		for(String recipient: recipients){
			System.out.println(recipient);
		}
		
		//Setting the Google SMTP Server
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		//Authenticating
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		//Sending email
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
		return Void;
	}
	
	//Getters and setters
	public String getAddress(){
		return this.address;
	}
	public String setAddress(String address){
		return this.address = address;
	}
	public Email(String address){
		this.address = address;
	}
	public Email(){	
	}
}
