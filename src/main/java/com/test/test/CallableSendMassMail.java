package com.test.test;

import java.util.concurrent.Callable;

public class CallableSendMassMail implements Callable {

	private String info;
	
	public CallableSendMassMail(String info){
		this.info = info;
	}

	//thread function that sends mass emails to all registered users.
	//A thread is used to help speed up responsiveness after an admin sends out a newsletter.
	//It used to be that an admin would have to wait for all the emails to send before the UI would refresh.
	@Override
	public Void call() throws Exception {

		return Email.sendMassMail(info);
	}
}
