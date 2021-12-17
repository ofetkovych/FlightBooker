package sk.upjs.paz1c.project.gui;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import sk.upjs.paz1c.project.storage.Customer;
import sk.upjs.paz1c.project.storage.CustomerDao;
import sk.upjs.paz1c.project.storage.DaoFactory;
import sk.upjs.paz1c.project.storage.Flight;
import sk.upjs.paz1c.project.storage.FlightDao;

public class BookFlightController {

	@FXML
	private Button backButton;

	@FXML
	private Button bookButton;

	@FXML
	private ComboBox<Customer> customerComboBox;

	@FXML
	private ListView<Flight> flightListView;

	@FXML
	void initialize() {
		flightListView.setItems(flightList);
		List<Customer> customer = customerDao.getAll();
		customerComboBox.setItems(FXCollections.observableArrayList(customer));
		customerComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				selectedCustomer = newValue;
			}
		});

	}

	private FlightFxModel flightFxModel;
	private CustomerDao customerDao = DaoFactory.INSTANCE.getCustomerDao();
	private FlightDao flightDao = DaoFactory.INSTANCE.getFlightDao();

	private ObservableList<Flight> flightList;
	private Customer selectedCustomer;

	public BookFlightController(List<Flight> flights) {
		flightList = FXCollections.observableArrayList(flights);
		flightFxModel = new FlightFxModel(flights);
	}
	
	@FXML
    void back(ActionEvent event) {
		backButton.getScene().getWindow().hide();
    }


	@FXML
	void book(ActionEvent event) throws Exception {
		ObservableList<Customer> cust;
		ObservableList<Customer> cst;
		Flight flight = flightListView.getSelectionModel().getSelectedItem();
		if(flight == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("YOU HAVE TO CHOOSE FLIGHT");
			alert.showAndWait();
		}
//		if (flightDao.isFull(flight)) {
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setContentText("Flight is full!");
//			alert.showAndWait();
//		} else {
		cust = FXCollections.observableArrayList(customerDao.getByFlightId(flight.getId()));
		cst = FXCollections.observableArrayList();
		cst.add(selectedCustomer);
		cust.add(selectedCustomer);
		System.out.println("ON FLIGHT: ");
		System.out.println(cust);
		System.out.println(cst);
		if (flightDao.isFull(flight)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("FLIGHT IS FULL");
			alert.showAndWait();
		} else if (flightDao.isCustomerInFlight(selectedCustomer, flight)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("CUSTOMER IS ALREADY IN FLIGHT");
			alert.showAndWait();
		} else {
			try {
				flightDao.insertCustomers(cst, flight.getId());
				cst.remove(selectedCustomer);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Flight BOOKED SUCCESSFULLY!");
				alert.showAndWait();
				bookButton.getScene().getWindow().hide();
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("IDK WHAT ERROR IT IS");
				alert.showAndWait();
				e.printStackTrace();
			}
		}
	}

}