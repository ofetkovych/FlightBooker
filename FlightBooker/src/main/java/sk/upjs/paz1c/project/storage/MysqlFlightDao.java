package sk.upjs.paz1c.project.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlFlightDao implements FlightDao {

	private JdbcTemplate jdbcTemplate;


	public MysqlFlightDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Flight> getByAirport(Airport airport) {
		String sql = "select flight.id as flight_id, date_of_flight, airport_from, airport_where, company_name, flight_class, number_of_seats, departure, arrival, customer.id, customer.name, surname, date_Of_Birth, gender, phoneNumber, adress from flight\n"
				+ "left outer join airport on airport.id = flight.airport_from\n"
				+ "left outer join flight_customer on flight.id = flight_customer.flight_id\n"
				+ "left outer join customer on flight_customer.flight_id = customer.id\n" + "where airport_from = " + airport.getId() +";";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Flight>>() {

			@Override
			public List<Flight> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Flight> list = new ArrayList<Flight>();
				Flight flight = null;
				while (rs.next()) {
					Long id = rs.getLong("flight_id");
					if (flight == null || flight.getId() != id) {
						Date dateOfFlight = rs.getDate("date_of_flight");
						Long from = rs.getLong("airport_from");
						Long where = rs.getLong("airport_where");
						String companyName = rs.getString("company_name");
						String flightClass = rs.getString("flight_class");
						Integer numberOfSeats = rs.getInt("number_of_seats");
						LocalDateTime departure = rs.getTimestamp("departure").toLocalDateTime();
						LocalDateTime arrival = rs.getTimestamp("arrival").toLocalDateTime();
						list.add(flight = new Flight(id, dateOfFlight, from, where, companyName, flightClass,
								numberOfSeats, departure, arrival));
					}
					if (rs.getString("name") == null) {
						continue;
					}
					long id_user = rs.getLong("id");
					String name = rs.getString("name");
					String surname = rs.getString("surname");
					Date dateOfBirth = rs.getDate("date_Of_Birth");
					String gender = rs.getString("gender");
					long phoneNumber = rs.getLong("phoneNumber");
					String adress = rs.getString("adress");
					Customer cust = new Customer(id, name, surname, dateOfBirth, gender, phoneNumber, adress);
					List<Customer> customers = flight.getCustomers();
					customers.add(cust);
					flight.setCustomers(customers);
				}
				return list;
			}
		});
	}

	@Override
	public Flight save(Flight flight) throws EntityNotFoundException, NullPointerException {
		
		if (flight.getId() == null) { // INSERT
			SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
			insert.withTableName("flight");
			insert.usingGeneratedKeyColumns("id");
			insert.usingColumns("date_of_flight", "airport_from", "airport_where", "company_name", "flight_class", "number_of_seats", "departure", "arrival");
			
			Map<String, Object> values = new HashMap<>();
			values.put("date_of_flight", flight.getDateOfFlight());
			values.put("airport_from", flight.getFrom());
			values.put("airport_where", flight.getWhere());
			values.put("company_name", flight.getCompanyName());
			values.put("flight_class", flight.getFlightClass());
			values.put("number_of_seats", flight.getNumberOfSeats());
			values.put("departure", flight.getDeparture());
			values.put("arrival", flight.getArrival());
			
			try {
				return new Flight(insert.executeAndReturnKey(values).longValue(), flight.getDateOfFlight(),
						flight.getFrom(), flight.getWhere(), flight.getCompanyName(),
						flight.getFlightClass(), flight.getNumberOfSeats(), flight.getDeparture(),
						flight.getArrival(), flight.getCustomers());
			} catch (DataIntegrityViolationException e) {
				throw new EntityNotFoundException(
						"Cannot insert flight, customer with id " + flight.getCustomers() + " not found", e);
			}		
		}
		else {
			String sql = "UPDATE flight SET date_of_flight = ?, "
					+ "airport_from = ?, airport_where = ?, company_name = ?, flight_class = ?, number_of_seats = ?, departure = ?, "
					+ "arrival = ? "
					+ "WHERE id = ?";
			int changed = jdbcTemplate.update(sql, flight.getDateOfFlight(), flight.getFrom(), flight.getWhere(),
					flight.getCompanyName(), flight.getFlightClass(), flight.getNumberOfSeats(), flight.getDeparture(),
					flight.getArrival(), flight.getId());
			if (changed == 1) {
				return flight;
			} else {
				throw new EntityNotFoundException("Flight with id " + flight.getId() + " not found in DB!");
			}
			
		}
	}

	@Override
	public boolean delete(long id) {
		jdbcTemplate.update("DELETE FROM flight_customer WHERE flight_id = ?", id);
		String sql = "DELETE FROM flight WHERE id = " + id; 
		int deleted = jdbcTemplate.update(sql);
		return deleted == 1;
	}

	@Override
	public boolean isFull(Flight flight) {
		String sql = "select number_of_seats-count(customer_id) as volne from flight_customer "
				+ "join flight on flight.id = flight_customer.flight_id " + "where flight_id = " + flight.getId() + " group by flight_id; ";
		int count = jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int volne = rs.getInt("volne");
				return volne;
			}
			
		});
		if(count == flight.getNumberOfSeats()) {
			throw new FlightFullException("Flight is full!" );
		}
		else {
			return false;
		}
	}

}
