package com.example.demo;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

public class WicketApplication extends WebApplication{

	public WicketApplication() {
//		super();
	}

	@Override
	public Class getHomePage() {
		return MyPage.class;
	}
	
	@Override
	public Session newSession(Request req, Response res){
		return new MySession(req);
	}

}
