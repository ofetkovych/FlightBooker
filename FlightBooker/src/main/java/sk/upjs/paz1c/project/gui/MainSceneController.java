package sk.upjs.paz1c.project.gui;

import java.io.IOException;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.project.storage.Customer;
import sk.upjs.paz1c.project.storage.CustomerDao;
import sk.upjs.paz1c.project.storage.DaoFactory;

public class MainSceneController {

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
	    
	    private ObjectProperty<Customer> selectedSubject = new SimpleObjectProperty<>();
	    private CustomerDao customerDao = DaoFactory.INSTANCE.getCustomerDao();
	    
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
	    	
	}
}

