package sk.upjs.paz1c.project.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sk.upjs.paz1c.project.storage.Airport;

public class AirportFxModel {
	private Long id;
	private StringProperty country = new SimpleStringProperty();
	private StringProperty city = new SimpleStringProperty();
	private StringProperty airportName = new SimpleStringProperty();
	private StringProperty airportAcronym = new SimpleStringProperty();
	private ObservableList<Airport> airports;
	
	public AirportFxModel(Airport airport) {
		super();
		this.id = airport.getId();
		setCountry(airport.getCountry());
		setCity(airport.getCity());
		setAirportName(airport.getAirportName());
		setAirportAcronym(airport.getAirportAcronym());
		
	}
	
	public AirportFxModel() {
		airports = FXCollections.observableArrayList();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCountry() {
		return country.get();
	}
	
	public void setCountry(String country) {
		this.country.set(country);
	}
	
	public String getCity() {
		return city.get();
	}
	
	public void setCity(String city) {
		this.city.set(city);
	}
	
	public String getAirportName() {
		return airportName.get();
	}
	
	public void setAirportName(String airportName) {
		this.airportName.set(airportName);
	}
	
	public String getAirportAcronym() {
		return airportAcronym.get();
	}
	
	public void setAirportAcronym(String airportAcronym) {
		this.airportAcronym.set(airportAcronym);
	}
	
	public ObservableList<Airport> getAirportMode() {
		return airports;
	}
	
	public StringProperty countryProperty() {
		return this.country;
	}
	
	public StringProperty cityProperty() {
		return this.city;
	}
	
	public StringProperty airportNameProperty() {
		return this.airportName;
	}
	
	public StringProperty airportAcronymProperty() {
		return this.airportAcronym;
	}
	
	
	public Airport getAirport() {
		return new Airport(id, getCountry(), getCity(), getAirportName(), getAirportAcronym());
	}

	
}
