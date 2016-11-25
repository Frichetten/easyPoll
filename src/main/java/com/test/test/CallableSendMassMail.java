package com.test.test;

import java.util.concurrent.Callable;

public class CallableSendMassMail implements Callable {

	private String info;
	
	public CallableSendMassMail(String info){
		this.info = info;
	}

	@Override
	public Void call() throws Exception {

		return Email.sendMassMail(info);
	}
}
