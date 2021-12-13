package sk.upjs.paz1c.project.gui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sk.upjs.paz1c.project.storage.Airport;
import sk.upjs.paz1c.project.storage.AirportDao;
import sk.upjs.paz1c.project.storage.Customer;
import sk.upjs.paz1c.project.storage.DaoFactory;
import sk.upjs.paz1c.project.storage.Flight;

public class FlightFxModel {
	private Long id;
	private ObjectProperty<LocalDate> dateOfFlight = new SimpleObjectProperty<LocalDate>();
	private ObjectProperty<Airport> from = new SimpleObjectProperty<>();
	private ObjectProperty<Airport> where = new SimpleObjectProperty<>();
	private StringProperty companyName = new SimpleStringProperty();
	private BooleanProperty business = new SimpleBooleanProperty();
	private StringProperty seats = new SimpleStringProperty();
	private ObjectProperty<LocalDateTime> departure = new SimpleObjectProperty<LocalDateTime>();
	private ObjectProperty<LocalDateTime> arrival = new SimpleObjectProperty<LocalDateTime>();
	private ObservableList<Flight> flights;
	private ObservableList<Customer> customers;
	private List<Customer> onFLight = new ArrayList<>();

	public List<Customer> getOnFLight() {
		return onFLight;
	}

	private AirportDao airportDao = DaoFactory.INSTANCE.getAirportDao();

	public void setCustomers(ObservableList<Customer> customers) {
		this.customers = customers;
	}

	public FlightFxModel() {
		flights = FXCollections.observableArrayList();
	}

	public FlightFxModel(Flight flight, List<Customer> onFlight) {
		super();
		this.id = flight.getId();
		setDateOfFlight(flight.getDateOfFlight());
		setFrom(airportDao.getById(flight.getFrom()));
		setWhere(airportDao.getById(flight.getWhere()));
		setCompanyName(flight.getCompanyName());
		setBusiness(flight.getClass().equals("Business"));
		setSeats(flight.getNumberOfSeats().toString());
		setDeparture(flight.getDeparture());
		setArrival(flight.getArrival());
		if (customers == null) {
			onFlight = new ArrayList<>();
		} else {
			for (Customer c : customers) {
				onFlight.add(c);
			}
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateOfFlight() {
		return dateOfFlight.get();
	}

	public void setDateOfFlight(LocalDate dateOfFlight) {
		this.dateOfFlight.set(dateOfFlight);
	}

	public Airport getFrom() {
		return from.get();
	}

	public void setFrom(Airport from) {
		this.from.set(from);
	}

	public Airport getWhere() {
		return where.get();
	}

	public void setWhere(Airport where) {
		this.where.set(where);
		;
	}

	public String getCompanyName() {
		return companyName.get();
	}

	public void setCompanyName(String companyName) {
		this.companyName.set(companyName);
		;
	}

	public Boolean getBusiness() {
		return business.get();
	}

	public void setBusiness(Boolean business) {
		this.business.set(business);
		;
	}

	public String getSeats() {
		return seats.get();
	}

	public void setSeats(String seats) {
		this.seats.set(seats);
	}

	public LocalDateTime getDeparture() {
		return departure.get();
	}

	public void setDeparture(LocalDateTime departure) {
		this.departure.set(departure);
	}

	public LocalDateTime getArrival() {
		return arrival.get();
	}

	public void setArrival(LocalDateTime arrival) {
		this.arrival.set(arrival);
	}

	public ObjectProperty<LocalDate> dateOfFlightProperty() {
		return this.dateOfFlight;
	}

	public ObjectProperty<Airport> fromProperty() {
		return this.from;
	}

	public ObjectProperty<Airport> whereProperty() {
		return this.where;
	}

	public StringProperty companyNameProperty() {
		return this.companyName;
	}

	public BooleanProperty businessProperty() {
		return this.business;
	}

	public StringProperty seatsProperty() {
		return this.seats;
	}

	public ObjectProperty<LocalDateTime> departureProperty() {
		return this.departure;
	}

	public ObjectProperty<LocalDateTime> arrivalProperty() {
		return this.arrival;
	}

	public ObservableList<Flight> getFlights() {
		return flights;
	}

	public ObservableList<Flight> getFlightModel() {
		return flights;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public Flight getFlight() {
		Long idFrom = getFrom().getId();
		Long idWhere = getWhere().getId();
		String number = getSeats();
		Integer seats = Integer.parseInt(number);
		Flight flight = new Flight(id, getDateOfFlight(), idFrom, idWhere, getCompanyName(),
				getBusiness() ? "Economy" : "Business", seats, getDeparture(), getArrival(), getCustomers());
		System.out.println(flight);
		return flight;
	}

}
