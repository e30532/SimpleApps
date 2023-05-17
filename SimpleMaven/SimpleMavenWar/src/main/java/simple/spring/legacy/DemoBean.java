package simple.spring.legacy;

import org.springframework.stereotype.Component;

@Component
public class DemoBean {
	public String say() {
		return "Hello";
	}
}
