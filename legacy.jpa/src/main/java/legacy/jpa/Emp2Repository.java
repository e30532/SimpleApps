package legacy.jpa;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class Emp2Repository {
	  @PersistenceContext
	  private EntityManager em;

	  public List<Emp2> findAll() {
	      return em.createQuery("from Emp2 e", Emp2.class).getResultList();
	  }
}

