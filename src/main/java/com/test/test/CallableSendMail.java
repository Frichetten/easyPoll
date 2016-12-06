package com.test.test;

import java.util.concurrent.Callable;

public class CallableSendMail implements Callable{
	
	private String address;
	private String subject;
	private String info;
	
	//constructor
	public CallableSendMail(String email, String subject, String info){
		this.address = email;
		this.subject = subject;
		this.info = info;
	}

	//thread function that sends emails to recommend a poll to someone.
	//A thread is used to help speed up responsiveness after a user tries to recommend.
	//It used to be that a user would have to wait for the email to send before the UI would refresh.
	@Override
	public Void call() throws Exception {
		return Email.sendMail(address, subject, info);
	}

}
