package sk.upjs.paz1c.project.gui;

import java.time.LocalDate;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import sk.upjs.paz1c.project.storage.Customer;
import sk.upjs.paz1c.project.storage.CustomerDao;
import sk.upjs.paz1c.project.storage.DaoFactory;

public class AddCustomerController {

	private CustomerFxModel customerFxModel;

	@FXML
	private TextField adressTextField;

	@FXML
	private Button cancelButton;

	@FXML
	private DatePicker dateOfBirthDatePicker;

	@FXML
	private RadioButton femaleRadioButton;

	@FXML
	private ToggleGroup gender;

	@FXML
	private RadioButton maleRadioButton;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField phoneNumberTextField;

	@FXML
	private Button saveButton;

	@FXML
	private TextField surnameTextField;

	public AddCustomerController() {
		customerFxModel = new CustomerFxModel();
	}

	

	@FXML
	    void initialize() {
	    	nameTextField.textProperty().bindBidirectional(customerFxModel.nameProperty());
	    	surnameTextField.textProperty().bindBidirectional(customerFxModel.surnameProperty());
	    	dateOfBirthDatePicker.valueProperty().bindBidirectional(customerFxModel.dateOfBirthProperty());
	    	maleRadioButton.selectedProperty().bindBidirectional(customerFxModel.maleProperty());
	    	phoneNumberTextField.textProperty().bindBidirectional(customerFxModel.phoneNumberProperty());
	    	adressTextField.textProperty().bindBidirectional(customerFxModel.adressProperty());
	    }

	@FXML
	void cancel(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setContentText("Do you want cancel this action?");
    	Optional<ButtonType> button = alert.showAndWait();
    	if (button.get() == ButtonType.OK) {
    		cancelButton.getScene().getWindow().hide();
    	}
	}

	@FXML
	void saveCustomer(ActionEvent event) {
		System.out.println("Ide to");
		CustomerDao customerDao = DaoFactory.INSTANCE.getCustomerDao();
		Customer savedCustomer = customerDao.save(customerFxModel.getCustomer());
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("SAVED SUCCESSFULLY!");
		alert.showAndWait();
		System.out.println("SAVED");
		saveButton.getScene().getWindow().hide();
	}

}
