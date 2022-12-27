package simple.jsp;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class MyTag extends TagSupport{
	private String msg="";

	@Override
	public int doStartTag() throws JspException{
        try {
			pageContext.getOut().print("Input Message: " + msg );
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
	  
    @Override
    public int doEndTag() throws JspException {
        try {
			pageContext.getOut().print("<BR/>Current Time: " + System.currentTimeMillis() );
		} catch (IOException e) {
			e.printStackTrace();
		}
        return EVAL_PAGE;
    }
    
	public void setMessage(String msg) {
		this.msg=msg;
	}
}
