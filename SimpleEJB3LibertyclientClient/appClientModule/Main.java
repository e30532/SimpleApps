import javax.ejb.EJB;

import simple.ejb3.view.HelloRemote;

public class Main {
    
	@EJB(name="ejb/Hello")
    static HelloRemote hello;	
    
	public static void main(String[] args) {
		System.out.println(hello.say("IBM"));	
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}