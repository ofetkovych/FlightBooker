package sk.upjs.paz1c.project.gui;

import java.time.LocalDate;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz1c.project.storage.Customer;

public class CustomerFxModel {

	private Long id;
	private StringProperty name = new SimpleStringProperty();
	private StringProperty surname = new SimpleStringProperty();
	private ObjectProperty<LocalDate> dateOfBirth = new SimpleObjectProperty<LocalDate>();
	private StringProperty gender = new SimpleStringProperty();
	private StringProperty phoneNumber = new SimpleStringProperty();
	private StringProperty adress = new SimpleStringProperty();

	public CustomerFxModel(Customer customer) {
		super();
		this.id = customer.getId();
		setName(customer.getName());
		setSurname(customer.getSurname());
		setDateOfBirth(customer.getDateOfBirth());
		setGender(customer.getGender());
		setPhoneNumber(customer.getPhoneNumber().toString());
		setAdress(customer.getAdress());
	}

	public CustomerFxModel() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getSurname() {
		return surname.get();
	}

	public void setSurname(String surname) {
		this.surname.set(surname);
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth.get();
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth.set(dateOfBirth);
	}

	public String getGender() {
		return gender.get();
	}

	public void setGender(String gender) {
		this.gender.set(gender);
		;
	}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}

	public String getAdress() {
		return adress.get();
	}

	public void setAdress(String adress) {
		this.adress.set(adress);
	}

	public StringProperty nameProperty() {
		return this.name;
	}

	public StringProperty surnameProperty() {
		return this.surname;
	}

	public ObjectProperty<LocalDate> dateOfBirthProperty() {
		return this.dateOfBirth;
	}

	public StringProperty genderProperty() {
		return this.gender;
	}

	public StringProperty phoneNumberProperty() {
		return this.phoneNumber;
	}

	public StringProperty adressProperty() {
		return this.adress;
	}

	public Customer getCustomer() {
		String number = getPhoneNumber();
		Long phoneNumber = Long.parseLong(number);
		return new Customer(id ,getName(), getSurname(), getDateOfBirth(), getGender(), phoneNumber, getAdress());
	}

}
