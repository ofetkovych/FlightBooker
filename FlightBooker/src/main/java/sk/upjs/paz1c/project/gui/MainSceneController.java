package sk.upjs.paz1c.project.gui;

import java.io.IOException;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.project.storage.Customer;
import sk.upjs.paz1c.project.storage.CustomerDao;
import sk.upjs.paz1c.project.storage.DaoFactory;

public class MainSceneController {

	@FXML
	private Button creditsButton;

	@FXML
	private Button addAirportButton;

	@FXML
	private Button addCustomerButton;

	@FXML
	private Button addFlightButton;

	@FXML
	private Button updateCustomerButton;

	@FXML
	private Button updateFlightButton;

	@FXML
	private Button bookButton;

	@FXML
	private Button customersButton;

	@FXML
	private Button exitButton;

	private ObjectProperty<Customer> selectedCustomer = new SimpleObjectProperty<>();
	private CustomerDao customerDao = DaoFactory.INSTANCE.getCustomerDao();

	private FlightFxModel flightFxModel;

	@FXML
	void credits(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Thanks for using our FlightBooker app!\n" + "Made by:\n Oleksandr Fetkovych - Саша\n"
				+ "and\n" + "Adam Kundracik");
		alert.showAndWait();
	}
	
	  @FXML
	    void exit(ActionEvent event) {
		  Stage stage = (Stage) exitButton.getScene().getWindow();
		    stage.close();
	    }

	@FXML
	void initialize() {

		addCustomerButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					AddCustomerController controller = new AddCustomerController();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
					loader.setController(controller);
					Parent parent = loader.load();
					Scene scene = new Scene(parent);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("ADD CUSTOMER");
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		updateCustomerButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					UpdateCustomerController controller = new UpdateCustomerController();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateCustomer.fxml"));
					loader.setController(controller);
					Parent parent = loader.load();
					Scene scene = new Scene(parent);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("UPDATE CUSTOMER");
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		addAirportButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					AddAirportController controller = new AddAirportController();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAirport.fxml"));
					loader.setController(controller);
					Parent parent = loader.load();
					Scene scene = new Scene(parent);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("ADD AIRPORT");
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		addFlightButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					AddFlightController controller = new AddFlightController();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFlight.fxml"));
					loader.setController(controller);
					Parent parent = loader.load();
					Scene scene = new Scene(parent);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("ADD FLIGHT");
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		updateFlightButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// ObservableList<Customer> onFlight = flightFxModel.getCustomers();
				try {
					UpdateFlightController controller = new UpdateFlightController();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateFlight.fxml"));
					loader.setController(controller);
					Parent parent = loader.load();
					Scene scene = new Scene(parent);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("ADD FLIGHT");
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		bookButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					SelectFlightController controller = new SelectFlightController();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("selectFlight.fxml"));
					loader.setController(controller);
					Parent parent = loader.load();
					Scene scene = new Scene(parent);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("SELECT FLIGHT");
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		customersButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					CustomerListController controller = new CustomerListController();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerList.fxml"));
					loader.setController(controller);
					Parent parent = loader.load();
					Scene scene = new Scene(parent);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("SELECT FLIGHT");
					stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
	}
}
