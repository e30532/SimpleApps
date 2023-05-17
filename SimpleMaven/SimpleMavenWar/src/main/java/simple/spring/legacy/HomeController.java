package simple.spring.legacy;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
		
	@Autowired
	private DemoBean demoBean; 
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Welcome home! The client locale is " + locale);		
		System.out.println(demoBean.say());
		return "home";
	}
	
}
