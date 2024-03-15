package com.example.demo;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class MySession extends WebSession{

	private static final long serialVersionUID = 1L;

	private int counter=0;

	public MySession(Request request) {
		super(request);
	}
	
	public int getCounter() {
		return this.counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

}
