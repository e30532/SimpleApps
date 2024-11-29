package legacy.jpa;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EMP2 database table.
 * 
 */
@Entity
@NamedQuery(name="Emp2.findAll", query="SELECT e FROM Emp2 e")
public class Emp2 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String name;


	public Emp2() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

