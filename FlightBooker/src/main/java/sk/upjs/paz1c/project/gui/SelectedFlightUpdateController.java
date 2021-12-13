package sk.upjs.paz1c.project.gui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import sk.upjs.paz1c.project.storage.Airport;
import sk.upjs.paz1c.project.storage.AirportDao;
import sk.upjs.paz1c.project.storage.Customer;
import sk.upjs.paz1c.project.storage.DaoFactory;
import sk.upjs.paz1c.project.storage.Flight;

public class SelectedFlightUpdateController {
	
	private FlightFxModel flightFxModel;

    @FXML
    private TextField arrivalTextField;

    @FXML
    private RadioButton businessRadioButton;

    @FXML
    private TextField companyNameTextField;

    @FXML
    private ListView <Customer> customersListView;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private Button updateButton;

//    @FXML
//    private DatePicker dateOfFlightPicker;

    @FXML
    private TextField departureTextField;

    @FXML
    private RadioButton economyRadioButton;

    @FXML
    private ComboBox<Airport> fromComboBox;

    @FXML
    private TextField numberOfSeatsTextField;

    @FXML
    private ComboBox<Airport> whereComboBox;
    
    private Flight savedFLight;
    
    private ObjectProperty<Airport> selectedAirportFrom = new SimpleObjectProperty<Airport>();
    private ObjectProperty<Airport> selectedAirportWhere = new SimpleObjectProperty<Airport>();
    private AirportDao airportDao = DaoFactory.INSTANCE.getAirportDao();
    
    public SelectedFlightUpdateController(Flight flight, List<Customer> customers) {
    	flightFxModel = new FlightFxModel(flight, customers);
    }

    public Flight getSavedFlight() {
    	return savedFLight;
    }
    
    @FXML
    void initialize() {
    	//datePicker.valueProperty().bindBidirectional(flightFxModel.dateOfFlightProperty());
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
		numberOfSeatsTextField.textProperty().bindBidirectional(flightFxModel.seatsProperty());
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

    }

    @FXML
    void updateFlight(ActionEvent event) {

    }
}
