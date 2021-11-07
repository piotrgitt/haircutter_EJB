package jsf.haircutter.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.haircutter.entities.Service;


@Stateless
public class ServiceDAO {
	
	
	@PersistenceContext
	protected EntityManager em;
	
	
	public void create(Service service) {
		em.persist(service);
	}

	public Service merge(Service service) {
		return em.merge(service);
	}

	public void remove(Service service) {
		em.remove(em.merge(service));
	}

	public Service find(Object id) {
		return em.find(Service.class, id);
	}
	
	public List<Service> getList(Map<String, Object> searchParams) {
		List<Service> list = null;

		// 1. Build query string with parameters
		String select = "select s ";
		String from = "from Service s ";
		String where = "";


		// search for surname
		String name = (String) searchParams.get("name");
		if (name != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.name like :name ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where);

		// 3. Set configured parameters
		if (name != null) {
			query.setParameter("name", name+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Service> getFullList() {
		List<Service> list = null;

		Query query = em.createQuery("select s from Service s");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}	
}
