package com.example.demo;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Counter implements Serializable{

	private static final long serialVersionUID = 1L;
	int i = 0;
	
	public int increment() {
		return i++;
	}
}
