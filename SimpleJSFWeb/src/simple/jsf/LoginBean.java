package simple.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

public class LoginBean {
    private String name;
    private String password;


    public String getName ()
    {
        return name;
    }


    public void setName (final String name)
    {
        this.name = name;
    }


    public String getPassword ()
    {
        return password;
    }


    public void setPassword (final String password)
    {
        this.password = password;
    }

    public void validateName(FacesContext fc, UIComponent uic, Object value){
        String inputData = (String)value;
        if(inputData.length() > 4){
            throw new ValidatorException(new FacesMessage("should be less than 5"));
        }
    }
}
