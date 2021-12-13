package sk.upjs.paz1c.project.gui;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import sk.upjs.paz1c.project.storage.Airport;
import sk.upjs.paz1c.project.storage.AirportDao;
import sk.upjs.paz1c.project.storage.Customer;
import sk.upjs.paz1c.project.storage.CustomerDao;
import sk.upjs.paz1c.project.storage.DaoFactory;

public class AddAirportController {
	
	private AirportFxModel airportFxModel;

    @FXML
    private TextField airportAcronymTextField;

    @FXML
    private TextField airportNameTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField countryTextField;

    @FXML
    private Button createButton;
    
    public AddAirportController() {
    	airportFxModel = new AirportFxModel();
    }
    
    @FXML
    void initialize() {
    	countryTextField.textProperty().bindBidirectional(airportFxModel.countryProperty());
    	cityTextField.textProperty().bindBidirectional(airportFxModel.cityProperty());
    	airportNameTextField.textProperty().bindBidirectional(airportFxModel.airportNameProperty());
    	airportAcronymTextField.textProperty().bindBidirectional(airportFxModel.airportAcronymProperty());
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
    void saveAirport(ActionEvent event) {
    	System.out.println("Ide to");
		AirportDao airportDao = DaoFactory.INSTANCE.getAirportDao();
		Airport savedAirport = airportDao.save(airportFxModel.getAirport());
		System.out.println("SAVED");
		createButton.getScene().getWindow().hide();
    }
}
