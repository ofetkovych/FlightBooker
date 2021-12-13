package sk.upjs.paz1c.project.storage;

import java.time.LocalDate;
import java.util.Date;

public class Customer {
	private Long id;
	private String name;
	private String surname;
	private LocalDate dateOfBirth;
	private String gender;
	private Long phoneNumber;
	private String adress;
	private long flightId;
	
	
	public Customer(String name, String surname, LocalDate dateOfBirth, String gender, Long phoneNumber, String adress,
			long flightId) {
		super();
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.flightId = flightId;
	}

	public Customer(Long id, String name, String surname, LocalDate dateOfBirth, String gender, Long phoneNumber,
			String adress, long flightId) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.flightId = flightId;
	}

	public long getFlightId() {
		return flightId;
	}

	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new NullPointerException("Name can not be null");
		} else {
			this.name = name;
		}
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		if (surname == null) {
			throw new NullPointerException("Surname can not be null");
		} else {
			this.surname = surname;
		}
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		if (dateOfBirth == null) {
			throw new NullPointerException("Date of Birth can not be null");
		} else {
		this.dateOfBirth = dateOfBirth;
		}
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		if (phoneNumber == null) {
			throw new NullPointerException("Phone number can not be null");
		} else {
		this.phoneNumber = phoneNumber;
		}
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		if (adress == null) {
			throw new NullPointerException("adress can not be null");
		} else {
		this.adress = adress;
		}
	}

	
	public String toString() {
		return "ID: " + id + " - " + name + " " + surname;
	}

	// Po vymazani nebude mat ID tak ho budeme porovnavat cez tento konstruktor
	public Customer(String name, String surname, LocalDate dateOfBirth, String gender, Long phoneNumber, String adress) {
		super();
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
	}

	public Customer(Long id, String name, String surname, LocalDate dateOfBirth, String gender, Long phoneNumber,
			String adress) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
	}
	
	

}
