package simple.struts;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.html.BaseFieldTag;

public class MyTag extends BaseFieldTag{
	private String CIF_DOB="";
	private String form="";
	@Override
	public int doStartTag() throws JspException{
//        try {
//			pageContext.getOut().print("Input Message: " + msg );
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        return super.doStartTag();
//				return EVAL_BODY_INCLUDE;
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
    
	public void setCIF_DOB(String CIF_DOB) {
		this.CIF_DOB=CIF_DOB;
	}

	public String getForm() {
		return form;
    }
	public void setForm(final String form) {
		this.form = form;
    }
}
