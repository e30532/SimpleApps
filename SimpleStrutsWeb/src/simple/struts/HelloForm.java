package simple.struts;

import org.apache.struts.action.ActionForm;

public class HelloForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String CIF_DOB;

    public String getCIF_DOB() {
        return CIF_DOB;
    }

    public void setCIF_DOB(String cif_dob) {
        this.CIF_DOB = cif_dob;
    }
}