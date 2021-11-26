package sk.upjs.paz1c.project.storage;

import java.time.LocalDateTime;
import java.util.Date;

public class Customer {
	private Long id;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private String gender;
	private Long phoneNumber;
	private String adress;
	private long flight_id;

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
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

	public long getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(long flight_id) {
		this.flight_id = flight_id;
	}
	
	public String toString() {
		return name + " " + surname;
	}

	public Customer(Long id, String name, String surname, Date dateOfBirth, String gender, Long phoneNumber,
			String adress, long flight_id) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.flight_id = flight_id;
	}
	
	
	// Po vymazani nebude mat ID tak ho budeme porovnavat cez tento konstruktor
	public Customer(String name, String surname, Date dateOfBirth, String gender, Long phoneNumber, String adress,
			long flight_id) {
		super();
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.flight_id = flight_id;
	}

	public Customer(Long id, String name, String surname, Date dateOfBirth, String gender, Long phoneNumber,
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
