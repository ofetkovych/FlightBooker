package sk.upjs.paz1c.project.gui.widgets;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.application.Platform;
import javafx.scene.control.ListCell;
import sk.upjs.paz1c.project.storage.Flight;
import sk.upjs.paz1c.project.storage.MysqlFlightDao;

public class flightListFactory<T extends Flight> extends ListCell<T> {

	private MysqlFlightDao mysqlFlightDao;
	
	public flightListFactory () {
		mysqlFlightDao = new MysqlFlightDao(null)
	}
	
	
	
	@Override
	protected void updateItem(final T item, boolean empty) {
		super.updateItem(item, empty);
		if (item == null || empty ) {
			setText(null);
			setGraphic(null);
		} else {
			Runnable rn=()->{
	            //Load the info from db here
				Long id = item.getId();
				LocalDate dateOfFlight = item.getDateOfFlight();
				Long airportFrom = item.getFrom();
				Long airportWhere = item.getWhere();
				String companyName = item.getCompanyName();
				String flightClass = item.getFlightClass();
				Integer seats = item.getNumberOfSeats();
				LocalDateTime departure = item.getDeparture();
				LocalDateTime arrival = item.getDeparture();
	            //Then use this function  to update the ui
	            Platform.runLater(()->{
	                //Inside here it's safe to update the ui like set text of label for example
	            });
	        };
	        Thread t=new Thread(rn);
	        t.start();
		}
	}
}
