package jsf.haircutter.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.haircutter.entities.Reservation;


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
	
	
	
	
	
	
}
