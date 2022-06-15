package simple.jpa20;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Emp.findAll", query="SELECT e FROM Emp e")
public class Emp implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String name;


	public Emp() {
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
