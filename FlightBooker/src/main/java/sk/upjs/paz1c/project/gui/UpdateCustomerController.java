package sk.upjs.paz1c.project.gui;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.project.storage.Customer;
import sk.upjs.paz1c.project.storage.CustomerDao;
import sk.upjs.paz1c.project.storage.DaoFactory;
import sk.upjs.paz1c.project.storage.EntityNotFoundException;
import sk.upjs.paz1c.project.storage.EntityUndeleteableException;

public class UpdateCustomerController {

	@FXML
	private CustomerFxModel customerFxModel;

	@FXML
	private Button cancelButton;

	@FXML
	private ComboBox<Customer> customerComboBox;

// 
	@FXML
	private Button updateButton;

	@FXML
	private Button deleteCustomerButton;

	private ObjectProperty<Customer> selectedCustomer = new SimpleObjectProperty<>();
	private CustomerDao customerDao = DaoFactory.INSTANCE.getCustomerDao();

	public UpdateCustomerController() {
		customerFxModel = new CustomerFxModel();
	}

	@FXML
	void initialize() {
		List<Customer> customers = customerDao.getAll();
		customerComboBox.setItems(FXCollections.observableArrayList(customers));
		customerComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {

			@Override
			public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {
				selectedCustomer.setValue(newValue);

			}

		});
		selectedCustomer.addListener(new ChangeListener<Customer>() {

			@Override
			public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {
				if (newValue == null) {
					customerComboBox.getSelectionModel().clearSelection();
				} else {
					customerComboBox.getSelectionModel().select(newValue);
				}

			}

		});
		selectedCustomer.setValue(customers.get(0));

		updateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Customer customer = selectedCustomer.get();
				if (customer != null) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setContentText("Are you sure?");
					Optional<ButtonType> button = alert.showAndWait();
					if (button.get() == ButtonType.OK) {
						SelectedCustomerUpdateController controller = new SelectedCustomerUpdateController(customer);
						openUpdateCustomerWindow(controller);

					}
					cancelButton.getScene().getWindow().hide();
				}

			}
		});

		deleteCustomerButton.setOnAction(event -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Do you want delete customer " + selectedCustomer.get().toString() + " ?");
			Optional<ButtonType> buttonType = alert.showAndWait();
			if (buttonType.get() == ButtonType.OK) {
				try {
					customerDao.delete(selectedCustomer.get().getId());
					List<Customer> cstms = customerDao.getAll();
					customerComboBox.setItems(FXCollections.observableArrayList(cstms));
					selectedCustomer.set(cstms.get(0));
				} catch (EntityNotFoundException e) {
					Alert alertError = new Alert(AlertType.ERROR);
					alertError.setContentText("Customer not found in Database!");
					alertError.show();
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
	void updateCustomer(ActionEvent event) {

	}

	@FXML
    void deleteCustomer(ActionEvent event) {
    }

	private void openUpdateCustomerWindow(SelectedCustomerUpdateController controller) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectedCustomerUpdate.fxml"));
			loader.setController(controller);
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Customer update");
			stage.showAndWait();

			// ideme �alej po zatvoren� mod�lenho okna
			Customer savedCustomer = controller.getSavedCustomer();
			if (savedCustomer != null) {
				List<Customer> customers = customerDao.getAll();
				customerComboBox.setItems(FXCollections.observableArrayList(customers));
				for (Customer s : customers) {
					if (s.getId().equals(savedCustomer.getId())) {
						selectedCustomer.set(s);
						break;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
