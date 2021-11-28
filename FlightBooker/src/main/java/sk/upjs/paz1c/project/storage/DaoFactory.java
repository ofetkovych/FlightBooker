package sk.upjs.paz1c.project.storage;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

public enum DaoFactory {

	INSTANCE;

	private JdbcTemplate jdbcTemplate;
	private CustomerDao customerDao;
	private FlightDao flightDao;
	private AirportDao airportDao;
	private boolean testing = false;

	public void testing() {
		testing = true;
	}

	public CustomerDao getCustomerDao() {
		if (customerDao == null) {
			customerDao = new MysqlCustomerDao(getJdbcTemplate());
		}
		return customerDao;
	}

	public FlightDao getFlightDao() {
		if (flightDao == null) {
			flightDao = new MysqlFlightDao(getJdbcTemplate());
		}
		return flightDao;
	}

	public AirportDao getAirportDao() {
		if (airportDao == null) {
			airportDao = new MysqlAirportDao(getJdbcTemplate());
		}
		return airportDao;
	}

	private JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser("root");
			dataSource.setPassword("halflife");
			if (testing == false) {
				dataSource.setUrl("jdbc:mysql://localhost:3306/Flightbooker?" + "serverTimezone=Europe/Bratislava");
			} else {
				dataSource
						.setUrl("jdbc:mysql://localhost:3306/Flightbooker_test?" + "serverTimezone=Europe/Bratislava");
			}
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}
}
