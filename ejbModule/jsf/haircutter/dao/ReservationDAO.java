package jsf.haircutter.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.haircutter.entities.Reservation;

public class ReservationDAO {
	@PersistenceContext
	protected EntityManager em;
	
	
	public void create(Reservation reservaton) {
		em.persist(reservaton);
	}

	public Reservation merge(Reservation reservaton) {
		return em.merge(reservaton);
	}

	public void remove(Reservation reservaton) {
		em.remove(em.merge(reservaton));
	}

	public Reservation find(Object id) {
		return em.find(Reservation.class, id);
	}
	
	
	public List<Reservation> getFullList() {
		List<Reservation> list = null;

		Query query = em.createQuery("select r from Reservation r");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	
	
	public List<Reservation> getList(Map<String, Object> searchParams) {
		List<Reservation> list = null;

		// 1. Build query string with parameters
		String select = "select r ";
		String from = "from Reservation r ";
		String where = "";
		String orderby = "order by r.name";

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
		Query query = em.createQuery(select + from + where + orderby);

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
}
