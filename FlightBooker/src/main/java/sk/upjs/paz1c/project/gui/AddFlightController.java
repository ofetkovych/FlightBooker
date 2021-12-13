package sk.upjs.paz1c.project.gui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;
import sk.upjs.paz1c.project.storage.Airport;
import sk.upjs.paz1c.project.storage.AirportDao;
import sk.upjs.paz1c.project.storage.Customer;
import sk.upjs.paz1c.project.storage.CustomerDao;
import sk.upjs.paz1c.project.storage.DaoFactory;
import sk.upjs.paz1c.project.storage.Flight;
import sk.upjs.paz1c.project.storage.FlightDao;

public class AddFlightController {
	
	private FlightFxModel flightFxModel;

    @FXML
    private TextField arrivalTextField;

    @FXML
    private RadioButton businessRadioButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField companyNameTextField;

    @FXML
    private Button createFlightButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField departureTextField;

    @FXML
    private RadioButton economyRadioButton;

    @FXML
    private ComboBox<Airport> fromComboBox;

    @FXML
    private ToggleGroup klas;

    @FXML
    private TextField seatsTextField;

    @FXML
    private ComboBox<Airport> whereComboBox;
    
    private ObjectProperty<Airport> selectedAirportFrom = new SimpleObjectProperty<Airport>();
    private ObjectProperty<Airport> selectedAirportWhere = new SimpleObjectProperty<Airport>();
    private AirportDao airportDao = DaoFactory.INSTANCE.getAirportDao();
    private ObservableList<Customer> customers;
//    private Long idFrom;
//    private Long idWhere;
//
    
    
    public AddFlightController() {
    	flightFxModel = new FlightFxModel();
    }
    
    @FXML 
    void initialize() {
    	datePicker.valueProperty().bindBidirectional(flightFxModel.dateOfFlightProperty());
    	List<Airport> airports = airportDao.getAll();
		fromComboBox.setItems(FXCollections.observableArrayList(airports));
		fromComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {

			@Override
			public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
				selectedAirportFrom.setValue(newValue);
				flightFxModel.setFrom(newValue);
				System.out.println(selectedAirportFrom);

			}

		});
		selectedAirportFrom.addListener(new ChangeListener<Airport>() {

			@Override
			public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
				if (newValue == null) {
					fromComboBox.getSelectionModel().clearSelection();
				} else {
					fromComboBox.getSelectionModel().select(newValue);
					System.out.println(newValue);
				}

			}

		});
		fromComboBox.setValue(airports.get(0));
		
		
		whereComboBox.setItems(FXCollections.observableArrayList(airports));
		whereComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {

			@Override
			public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
				selectedAirportWhere.setValue(newValue);
				flightFxModel.setWhere(newValue);


			}

		});
		selectedAirportWhere.addListener(new ChangeListener<Airport>() {

			@Override
			public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
				if (newValue == null) {
					whereComboBox.getSelectionModel().clearSelection();
				} else {
					whereComboBox.getSelectionModel().select(newValue);
				}

			}

		});
		whereComboBox.setValue(airports.get(0));
		
		// Idk what next
		fromComboBox.valueProperty().addListener(new ChangeListener<Airport>() {

			@Override
			public void changed(ObservableValue<? extends Airport> observable, Airport oldValue, Airport newValue) {
				
				
			}
		});
		
		businessRadioButton.selectedProperty().bindBidirectional(flightFxModel.businessProperty());
		companyNameTextField.textProperty().bindBidirectional(flightFxModel.companyNameProperty());
		businessRadioButton.selectedProperty().bindBidirectional(flightFxModel.businessProperty());
		seatsTextField.textProperty().bindBidirectional(flightFxModel.seatsProperty());
		departureTextField.textProperty().bindBidirectional(flightFxModel.departureProperty(), new StringConverter<LocalDateTime>() {

			private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm:ss");
			@Override
			public String toString(LocalDateTime object) {
				if (object == null)
    				return "";
				return formatter.format(object);
			}

			@Override
			public LocalDateTime fromString(String string) {
				try {
					return LocalDateTime.parse(string, formatter);
				} catch (DateTimeParseException e) {
					return null;					
				}
			}
		});
		
		arrivalTextField.textProperty().bindBidirectional(flightFxModel.arrivalProperty(), new StringConverter<LocalDateTime>() {

			private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm:ss");
			@Override
			public String toString(LocalDateTime object) {
				if (object == null)
    				return "";
				return formatter.format(object);
			}

			@Override
			public LocalDateTime fromString(String string) {
				try {
					return LocalDateTime.parse(string, formatter);
				} catch (DateTimeParseException e) {
					return null;					
				}
			}
		});
		
		
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
    void createFlight(ActionEvent event) {
    	System.out.println("Ide to");
		FlightDao flightDao = DaoFactory.INSTANCE.getFlightDao();
		Flight savedFlight = flightDao.save(flightFxModel.getFlight());
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("SAVED SUCCESSFULLY!");
		alert.showAndWait();
		System.out.println("SAVED");
		createFlightButton.getScene().getWindow().hide();
	}

}
