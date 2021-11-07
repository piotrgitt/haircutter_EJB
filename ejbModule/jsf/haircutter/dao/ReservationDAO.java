package jsf.haircutter.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.haircutter.entities.Reservation;
import jsf.haircutter.entities.User;


@Stateless
public class ReservationDAO {
	
	
	@PersistenceContext
	protected EntityManager em;
	
	
	public void create(Reservation reservation) {
		em.persist(reservation);
	}

	public Reservation merge(Reservation reservation) {
		return em.merge(reservation);
	}

	public void remove(Reservation reservation) {
		em.remove(em.merge(reservation));
	}

	public Reservation find(Object id) {
		return em.find(Reservation.class, id);
	}
	
	public List<Reservation> getList(Map<String, Object> searchParams) {
		List<Reservation> list = null;

		// 1. Build query string with parameters
		String select = "select r ";
		String from = "from Reservation r ";
		String where = "";
		String orderby = "order by r.time";

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
	
	public List<Reservation> getUserReservationList(User user) {
		List<Reservation> list = null;

		// 1. Build query string with parameters
		String select = "select r ";
		String from = "from Reservation r ";
		int userID = user.getIdUser();
		String where = "where id_user like " + userID;


		// 2. Create query object
		Query query = em.createQuery(select + from + where);



		// 4. Execute query and retrieve list of User reservation objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Reservation> getFullList() {
		List<Reservation> list = null;

		Query query = em.createQuery("select r from Reservation r order by r.time DESC");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
}
