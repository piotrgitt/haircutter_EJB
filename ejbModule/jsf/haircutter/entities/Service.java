package jsf.haircutter.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the service database table.
 * 
 */
@Entity
@NamedQuery(name="Service.findAll", query="SELECT s FROM Service s")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_service")
	private int idService;

	@Column(name="service_name")
	private String serviceName;

	@Column(name="service_price")
	private float servicePrice;

	@Column(name="service_time")
	private Time serviceTime;

	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="service")
	private List<Reservation> reservations;

	public Service() {
	}

	public int getIdService() {
		return this.idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public float getServicePrice() {
		return this.servicePrice;
	}

	public void setServicePrice(float servicePrice) {
		this.servicePrice = servicePrice;
	}

	public Time getServiceTime() {
		return this.serviceTime;
	}

	public void setServiceTime(Time serviceTime) {
		this.serviceTime = serviceTime;
	}

	public List<Reservation> getReservations() {
		return this.reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation addReservation(Reservation reservation) {
		getReservations().add(reservation);
		reservation.setService(this);

		return reservation;
	}

	public Reservation removeReservation(Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setService(null);

		return reservation;
	}

}