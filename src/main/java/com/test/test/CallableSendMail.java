package com.test.test;

import java.util.concurrent.Callable;

public class CallableSendMail implements Callable{
	
	private String address;
	private String subject;
	private String info;
	
	public CallableSendMail(String email, String subject, String info){
		this.address = email;
		this.subject = subject;
		this.info = info;
	}

	@Override
	public Void call() throws Exception {
		return Email.sendMail(address, subject, info);
	}

}
