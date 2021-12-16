package sk.upjs.paz1c.project.storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Flight {
    private Long id;
    private LocalDate dateOfFlight;
    private Long from;
    private Long where;
    private String companyName;
    private String flightClass;
    private Integer numberOfSeats;
    private LocalDateTime departure;
    private LocalDateTime arrival;
	private List<Customer> customers;
	
	private AirportDao airportDao = DaoFactory.INSTANCE.getAirportDao();
	
	
	
    public Flight(Long id, LocalDate dateOfFlight, Long from, Long where, String companyName, String flightClass,
			Integer numberOfSeats, LocalDateTime departure, LocalDateTime arrival) {
		super();
		this.id = id;
		this.dateOfFlight = dateOfFlight;
		this.from = from;
		this.where = where;
		this.companyName = companyName;
		this.flightClass = flightClass;
		this.numberOfSeats = numberOfSeats;
		this.departure = departure;
		this.arrival = arrival;
	}





	public Flight(LocalDate dateOfFlight, Long from, Long where, String companyName, String flightClass,
			Integer numberOfSeats, LocalDateTime departure, LocalDateTime arrival) {
		super();
		this.dateOfFlight = dateOfFlight;
		this.from = from;
		this.where = where;
		this.companyName = companyName;
		this.flightClass = flightClass;
		this.numberOfSeats = numberOfSeats;
		this.departure = departure;
		this.arrival = arrival;
	}
    
    
	
	

	public Flight(Long id, LocalDate dateOfFlight, Long from, Long where, String companyName, String flightClass,
			Integer numberOfSeats, LocalDateTime departure, LocalDateTime arrival, List<Customer> customers) {
		super();
		this.id = id;
		this.dateOfFlight = dateOfFlight;
		this.from = from;
		this.where = where;
		this.companyName = companyName;
		this.flightClass = flightClass;
		this.numberOfSeats = numberOfSeats;
		this.departure = departure;
		this.arrival = arrival;
		this.customers = new ArrayList<Customer>();
	}

	public Flight(LocalDate dateOfFlight, Long from, Long where, String companyName, String flightClass,
			Integer numberOfSeats, LocalDateTime departure, LocalDateTime arrival, List<Customer> customers) {
		super();
		this.dateOfFlight = dateOfFlight;
		this.from = from;
		this.where = where;
		this.companyName = companyName;
		this.flightClass = flightClass;
		this.numberOfSeats = numberOfSeats;
		this.departure = departure;
		this.arrival = arrival;
		this.customers = new ArrayList<Customer>();
	}





	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateOfFlight() {
		return dateOfFlight;
	}

	public void setDateOfFlight(LocalDate dateOfFlight) {
		this.dateOfFlight = dateOfFlight;
	}

	public Long getFrom() {
		return from;
	}

	public void setFrom(Long from) {
		this.from = from;
	}

	public Long getWhere() {
		return where;
	}

	public void setWhere(Long where) {
		this.where = where;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public LocalDateTime getDeparture() {
		return departure;
	}

	public void setDeparture(LocalDateTime departure) {
		this.departure = departure;
	}

	public LocalDateTime getArrival() {
		return arrival;
	}

	public void setArrival(LocalDateTime arrival) {
		this.arrival = arrival;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(airportDao, arrival, companyName, customers, dateOfFlight, departure, flightClass, from, id,
				numberOfSeats, where);
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return Objects.equals(airportDao, other.airportDao) && Objects.equals(arrival, other.arrival)
				&& Objects.equals(companyName, other.companyName) && Objects.equals(customers, other.customers)
				&& Objects.equals(dateOfFlight, other.dateOfFlight) && Objects.equals(departure, other.departure)
				&& Objects.equals(flightClass, other.flightClass) && Objects.equals(from, other.from)
				&& Objects.equals(id, other.id) && Objects.equals(numberOfSeats, other.numberOfSeats)
				&& Objects.equals(where, other.where);
	}





	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}





	@Override
	public String toString() {
		return "Flight [id=" + id + ", dateOfFlight=" + dateOfFlight + ", from=" + from + ", where=" + where
				+ ", companyName=" + companyName + ", flightClass=" + flightClass + ", numberOfSeats=" + numberOfSeats
				+ ", departure=" + departure + ", arrival=" + arrival + ", customers=" + customers + "]";
	}

	
//
//	@Override
//	public String toString() {
//		return "ID: " + id + " - FROM: " + airportDao.getById(from) + " | WHERE: " + airportDao.getById(where);
//	}
//	
    
}
