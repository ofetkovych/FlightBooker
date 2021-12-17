package sk.upjs.paz1c.project.gui;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.project.storage.Airport;
import sk.upjs.paz1c.project.storage.AirportDao;
import sk.upjs.paz1c.project.storage.DaoFactory;
import sk.upjs.paz1c.project.storage.Flight;
import sk.upjs.paz1c.project.storage.FlightDao;

public class SelectFlightController {

	@FXML
	private ComboBox<String> airportFromComboBox;

	@FXML
	private ComboBox<String> airportWhereComboBox;

	@FXML
	private ComboBox<String> cityFromComboBox;

	@FXML
	private ComboBox<String> cityWhereComboBox;

	@FXML
	private ComboBox<String> countryFromComboBox;

	@FXML
	private ComboBox<String> countryWhereComboBox;

	@FXML
	private Button backButton;

	@FXML
	private Button selectButton;

	private AirportDao airportDao = DaoFactory.INSTANCE.getAirportDao();
	private StringProperty selectedCountryFrom = new SimpleStringProperty();
	private StringProperty selectedCityFrom = new SimpleStringProperty();
	private StringProperty selectedCountryWhere = new SimpleStringProperty();
	private StringProperty selectedCityWhere = new SimpleStringProperty();
	private StringProperty selectedAirportFrom = new SimpleStringProperty();
	private StringProperty selectedAirportWhere = new SimpleStringProperty();

	private FlightFxModel flightFxModel;
	private FlightDao flightDao = DaoFactory.INSTANCE.getFlightDao();

	public SelectFlightController() {
		flightFxModel = new FlightFxModel();
	}

	@FXML
	void initialize() {
		Set<String> countries = airportDao.getAllCountries();
		countryFromComboBox.setItems(FXCollections.observableArrayList(countries));
		countryFromComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				selectedCountryFrom.setValue(newValue);
				System.out.println(selectedCountryFrom);
				Set<String> cityFrom = airportDao.getCityByCountry(selectedCountryFrom.get());
				System.out.println(cityFrom);
				cityFromComboBox.setItems(FXCollections.observableArrayList(cityFrom));

			}
		});
		selectedCountryFrom.addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue == null) {
					countryFromComboBox.getSelectionModel().clearSelection();
				} else {
					countryFromComboBox.getSelectionModel().select(newValue);
					System.out.println(newValue);
				}
			}
		});

		cityFromComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				selectedCityFrom.setValue(newValue);
				Set<String> airportsFrom = airportDao.getByCity(selectedCityFrom.get());
				System.out.println(airportsFrom);
				airportFromComboBox.setItems(FXCollections.observableArrayList(airportsFrom));
			}
		});
		selectedCityFrom.addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue == null) {
					cityFromComboBox.getSelectionModel().clearSelection();
				} else {
					cityFromComboBox.getSelectionModel().select(newValue);
				}

			}
		});

		airportFromComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				selectedAirportFrom.setValue(newValue);
			}
		});

		selectedAirportFrom.addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue == null) {
					airportFromComboBox.getSelectionModel().clearSelection();
				} else {
					airportFromComboBox.getSelectionModel().select(newValue);
					if (selectedAirportFrom != null || newValue != null) {
						selectButton.setDisable(true);
					}
				}

			}
		});

		////////

		countryWhereComboBox.setItems(FXCollections.observableArrayList(countries));
		countryWhereComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				selectedCountryWhere.setValue(newValue);
				System.out.println(selectedCountryWhere);
				Set<String> cityWhere = airportDao.getCityByCountry(selectedCountryWhere.get());
				System.out.println(cityWhere);
				cityWhereComboBox.setItems(FXCollections.observableArrayList(cityWhere));

			}
		});
		selectedCountryWhere.addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue == null) {
					countryWhereComboBox.getSelectionModel().clearSelection();
				} else {
					countryWhereComboBox.getSelectionModel().select(newValue);
					System.out.println(newValue);
				}
			}
		});

		cityWhereComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				selectedCityWhere.setValue(newValue);
				Set<String> airportsWhere = airportDao.getByCity(selectedCityWhere.get());
				System.out.println(airportsWhere);
				airportWhereComboBox.setItems(FXCollections.observableArrayList(airportsWhere));
			}
		});
		selectedCityWhere.addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue == null) {
					cityWhereComboBox.getSelectionModel().clearSelection();
				} else {
					cityWhereComboBox.getSelectionModel().select(newValue);
				}

			}
		});

		airportWhereComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				selectedAirportWhere.setValue(newValue);
			}
		});

		selectedAirportWhere.addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue == null) {
					airportWhereComboBox.getSelectionModel().clearSelection();
				} else {
					airportWhereComboBox.getSelectionModel().select(newValue);
					if (newValue == null || selectedAirportWhere == null) {
						selectButton.setDisable(true);
					} else {
						selectButton.setDisable(false);
					}
				}

			}
		});

		selectButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				List<Flight> flights = flightDao.fromAtoB(selectedAirportFrom.get(), selectedAirportWhere.get());
				System.out.println(flights);
				if (flights.isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("FLIGHT DOES NOT EXISTS!");
					alert.showAndWait();
				} else {
					BookFlightController controller = new BookFlightController(flights);
					openBookFlightWindow(controller);
				}
			}
		});
	}

	private void openBookFlightWindow(BookFlightController controller) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BookFlight.fxml"));
			loader.setController(controller);
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("book flight");
			stage.showAndWait();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void select(ActionEvent event) {

	}
	
	@FXML
    void back(ActionEvent event) {
		backButton.getScene().getWindow().hide();
    }

}
