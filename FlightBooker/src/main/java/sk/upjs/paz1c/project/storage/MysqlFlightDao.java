package sk.upjs.paz1c.project.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class MysqlFlightDao implements FlightDao {

	private JdbcTemplate jdbcTemplate;
	private Flight flt;
	List<Flight> result;

	public MysqlFlightDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Flight> getByAirport(Airport airport) {
		String sql = "SELECT flight_id, date_of_flight, airport_from, airport_where, company_name,"
				+ "flight_class, number_of_seats , departure, arrival, " + "FROM flight "
				+ "LEFT JOIN flight_customer AS fc ON flight.id = fc.flight_id "
				+ "LEFT JOIN customer ON fc.customer_id = customer.id " + "WHERE flight.airport_id = 1 "
				+ "ORDER BY flight.id, comapany_name";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Flight>>() {

			@Override
			public List<Flight> extractData(ResultSet rs) throws SQLException, DataAccessException {
				result = new ArrayList<>();
				Flight flt = null;
				while (rs.next()) {
					long id = rs.getLong("flight_id");
					Date flightDate = rs.getDate("date_of_flight");
					long airportFromID = rs.getLong("airport_from");
					long airportWhereID = rs.getLong("airport_where");
					String companyName = rs.getString("company_name");
					String flightClass = rs.getString("flight_class");
					int seats = rs.getInt("number_of_seats");
					LocalDateTime departure = rs.getTimestamp("departure").toLocalDateTime();
					LocalDateTime arrival = rs.getTimestamp("arrival").toLocalDateTime();
					flt = new Flight(id, flightDate, airportFromID, airportWhereID, companyName, flightClass, seats,
							departure, arrival);
					result.add(flt);
					
					

				}
				return result;
			}

		});
	}

	@Override
	public List<Customer> getByFlight(Flight flight) {
		String sql = "SELECT flight_id, date_of_flight, airport_from, airport_where, company_name,"
				+ "flight_class, number_of_seats , departure, arrival, " + "FROM flight "
				+ "LEFT JOIN flight_customer AS fc ON flight.id = fc.flight_id "
				+ "LEFT JOIN customer ON fc.customer_id = customer.id " + "WHERE flight.airport_id = 1 "
				+ "ORDER BY flight.id, comapany_name";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Customer>>() {

			@Override
			public List<Customer> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Customer> cust = new ArrayList<>();
				Customer cst = null;
				while (rs.next()) {
					long customerId = rs.getLong("customer_id");
					String name = rs.getString("name");
					String surname = rs.getString("surname");
					String dateOfBirth = rs.getString("date_of_birth");
					String gender = rs.getString("gender");
					long phoneNumber = rs.getLong("phone_number");
					String adress = rs.getString("adress");
					cst = new Customer(customerId, name, surname, dateOfBirth, gender, phoneNumber, adress,
							flight.getId());
					flt.getCustomers().add(cst);
					cust.add(cst);
				}
				return cust;
			}

		});
	}

	@Override
	public Flight save(Flight flight) throws EntityNotFoundException, NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFull(Flight flight) {
		// TODO Auto-generated method stub
		return false;
	}

}
