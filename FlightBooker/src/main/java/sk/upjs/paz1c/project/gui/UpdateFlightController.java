package sk.upjs.paz1c.project.gui;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.project.storage.Customer;
import sk.upjs.paz1c.project.storage.DaoFactory;
import sk.upjs.paz1c.project.storage.EntityNotFoundException;
import sk.upjs.paz1c.project.storage.Flight;
import sk.upjs.paz1c.project.storage.FlightDao;
import sk.upjs.paz1c.project.storage.MysqlFlightDao;

public class UpdateFlightController {
	
	private FlightFxModel flightFxModel;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteFlightButton;

    @FXML
    private ComboBox<Flight> flightComboBox;

    @FXML
    private Button updateButton;
    
    private ObjectProperty<Flight> selectedFlight = new SimpleObjectProperty<>();
    private FlightDao flightDao = DaoFactory.INSTANCE.getFlightDao();
    
    public UpdateFlightController() {
    	flightFxModel = new FlightFxModel();
    	
    }

    @FXML
	void initialize() {
    	List<Flight> flights = flightDao.getAll();
    	flightComboBox.setItems(FXCollections.observableArrayList(flights));
    	flightComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Flight>() {

			@Override
			public void changed(ObservableValue<? extends Flight> observable, Flight oldValue, Flight newValue) {
				selectedFlight.setValue(newValue);
				
			}
		});
    	selectedFlight.addListener(new ChangeListener<Flight>() {

			@Override
			public void changed(ObservableValue<? extends Flight> observable, Flight oldValue, Flight newValue) {
				if (newValue == null) {
					flightComboBox.getSelectionModel().clearSelection();
				} else {
					flightComboBox.getSelectionModel().select(newValue);
				}		
			}
		});
    	selectedFlight.setValue(flights.get(0));
    	
    	updateButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Flight flight = selectedFlight.get();
				if (flight != null) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setContentText("Are you sure?");
					Optional<ButtonType> button = alert.showAndWait();
					if (button.get() == ButtonType.OK) {
						List<Customer> customers = flightFxModel.getCustomers();
						SelectedFlightUpdateController controller = new SelectedFlightUpdateController(flight, customers);
						openUpdateFlightWindow(controller);

					}
					cancelButton.getScene().getWindow().hide();
				}

				
			}
		});
    	
    	deleteFlightButton.setOnAction(event -> {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Do you want delete customer " + selectedFlight.get().toString() + " ?");
			Optional<ButtonType> buttonType = alert.showAndWait();
			if (buttonType.get() == ButtonType.OK) {
				try {
					flightDao.delete(selectedFlight.get().getId());
					List<Flight> flght = flightDao.getAll();
					flightComboBox.setItems(FXCollections.observableArrayList(flght));
					selectedFlight.set(flght.get(0));
				} catch (EntityNotFoundException e) {
					Alert alertError = new Alert(AlertType.ERROR);
					alertError.setContentText("Flight not found in Database!");
					alertError.show();
				}
			}
    	});
    }
    
    private void openUpdateFlightWindow(SelectedFlightUpdateController controller) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectedFlightUpdate.fxml"));
			loader.setController(controller);
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Flight update");
			stage.showAndWait();

			// ideme �alej po zatvoren� mod�lenho okna
			Flight savedFlight = controller.getSavedFlight();
			if (savedFlight != null) {
				List<Flight> flights = flightDao.getAll();
				flightComboBox.setItems(FXCollections.observableArrayList(flights));
				for (Flight s: flights) {
					if (s.getId().equals(savedFlight.getId())) {
						selectedFlight.set(s);
						break;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    void deleteCustomer(ActionEvent event) {

    }

    @FXML
    void updateCustomer(ActionEvent event) {

    }

}
