package com.example.demo;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.form.Button;

public class MyPage extends WebPage{

	private static final long serialVersionUID = 1L;

	public MyPage() {
		super();

		final MySession session = (MySession) getSession();
		
		add(new Label("counter", Integer.toString(session.getCounter())));
	    
		Form form = new Form("f");
	    add(form);

	    
	    form.add(new Button("increment") {
	        @Override
	        public void onSubmit() {
	          int counter_current = session.getCounter();
	          session.setCounter(++counter_current);
	          setResponsePage(new MyPage());
	        }
	    });
	    
	}
}
