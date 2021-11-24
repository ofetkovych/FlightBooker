package sk.upjs.paz1c.project.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private Long id;
    private LocalDateTime dateOfFlight;
    private Long from;
    private Long where;
    private String companyName;
    private String flightClass;
    private Integer numberOfSeats;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private List<Customer> customers;
    
	public Flight(Long id, LocalDateTime dateOfFlight, Long from, Long where, String companyName, String flightClass,
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

	public Flight(Long id, LocalDateTime dateOfFlight, Long from, Long where, String companyName, String flightClass,
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateOfFlight() {
		return dateOfFlight;
	}

	public void setDateOfFlight(LocalDateTime dateOfFlight) {
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

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
    
}
