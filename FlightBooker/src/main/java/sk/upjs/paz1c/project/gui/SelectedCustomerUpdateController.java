package sk.upjs.paz1c.project.gui;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import sk.upjs.paz1c.project.storage.Customer;
import sk.upjs.paz1c.project.storage.CustomerDao;
import sk.upjs.paz1c.project.storage.DaoFactory;

public class SelectedCustomerUpdateController {
	
	private CustomerFxModel customerFxModel;

    @FXML
    private TextField adressTextField;

    @FXML
    private Button cancelButton;

//    @FXML
//    private DatePicker dateOfBirthPicker;

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
    private TextField surnameTextField;

    @FXML
    private Button updateButton;
    
    private Customer savedCustomer;
    
    public SelectedCustomerUpdateController(Customer customer) {
    	customerFxModel = new CustomerFxModel(customer);
    }
    
    public Customer getSavedCustomer() {
		return savedCustomer;
	}
    
    @FXML
    void initialize() {
    	nameTextField.textProperty().bindBidirectional(customerFxModel.nameProperty());
    	surnameTextField.textProperty().bindBidirectional(customerFxModel.surnameProperty());
    	maleRadioButton.selectedProperty().bindBidirectional(customerFxModel.maleProperty());
    	phoneNumberTextField.textProperty().bindBidirectional(customerFxModel.phoneNumberProperty());
    	adressTextField.textProperty().bindBidirectional(customerFxModel.adressProperty());
    	//dateOfBirthPicker.valueProperty().bindBidirectional(customerFxModel.dateOfBirthProperty());
    	
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
    void updateCustomer(ActionEvent event) {
    	System.out.println("Ide to");
		CustomerDao customerDao = DaoFactory.INSTANCE.getCustomerDao();
		Customer savedCustomer = customerDao.save(customerFxModel.getCustomer());
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("SAVED SUCCESSFULLY!");
		alert.showAndWait();
		System.out.println("SAVED");
		updateButton.getScene().getWindow().hide();
	}


}
