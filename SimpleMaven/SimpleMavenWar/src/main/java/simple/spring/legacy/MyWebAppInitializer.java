package simple.spring.legacy;

/**
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

  public class MyWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	    XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
	    rootContext.setConfigLocation("/WEB-INF/spring/root-context.xml");

	    servletContext.addListener(new ContextLoaderListener(rootContext));
		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
	    appContext.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");
	    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("appServlet", new DispatcherServlet(appContext));
	    dispatcher.setLoadOnStartup(1);
	    dispatcher.addMapping("/");
	}
**/
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {MyMvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
        String arr[] = { "/" };
        return arr;
	}
}
