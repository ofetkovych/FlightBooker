package sk.upjs.paz1c.project.storage;

public class Airport {
	private Long id;
	private String country;
	private String city;
	private String airportName;
	private String airportAcronym;
	
	public Airport(Long id, String country, String city, String airportName, String airportAcronym) {
		super();
		this.id = id;
		this.country = country;
		this.city = city;
		this.airportName = airportName;
		this.airportAcronym = airportAcronym;
	}

	public Airport(String country, String city, String airportName, String airportAcronym) {
		super();
		this.country = country;
		this.city = city;
		this.airportName = airportName;
		this.airportAcronym = airportAcronym;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		if (country == null) {
			throw new NullPointerException();
		}
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		if (city == null) {
			throw new NullPointerException();
		}
		this.city = city;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		if (airportName == null) {
			throw new NullPointerException();
		}
		this.airportName = airportName;
	}

	public String getAirportAcronym() {
		return airportAcronym;
	}

	public void setAirportAcronym(String airportAcronym) {
		if (airportAcronym == null) {
			throw new NullPointerException();
		}
		this.airportAcronym = airportAcronym;
	}
	
	
}

