package sk.upjs.paz1c.project.gui;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.upjs.paz1c.project.storage.Customer;
import sk.upjs.paz1c.project.storage.CustomerDao;
import sk.upjs.paz1c.project.storage.DaoFactory;

public class CustomerListController {
	
    @FXML
    private Button cancelButton;
    
    @FXML
    private ListView<Customer> customersTable;

    
    private CustomerFxModel customerFxModel;
    
    private CustomerDao customerDao = DaoFactory.INSTANCE.getCustomerDao();
    private ObservableList<Customer> customerss;


    public CustomerListController() {
    	customerFxModel = new CustomerFxModel();
    }
    
    
    @FXML
    void initialize() {
    	
    	customerss = FXCollections.observableArrayList(customerDao.getAll());
    	customersTable.setItems(FXCollections.observableArrayList(customerss));
    	
    	
    	
    }
    
    
    @FXML
    void cancel(ActionEvent event) {
    	cancelButton.getScene().getWindow().hide();
    }

}
