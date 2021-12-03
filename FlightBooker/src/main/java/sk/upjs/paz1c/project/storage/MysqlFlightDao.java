package sk.upjs.paz1c.project.storage;


import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDate;
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
				+ "left outer join customer on flight_customer.flight_id = customer.id\n" + "where airport_from = "
				+ airport.getId() + ";";
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
					LocalDate dateOfBirth = rs.getDate("date_Of_Birth").toLocalDate();
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
			insert.usingColumns("date_of_flight", "airport_from", "airport_where", "company_name", "flight_class",
					"number_of_seats", "departure", "arrival");

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
						flight.getFrom(), flight.getWhere(), flight.getCompanyName(), flight.getFlightClass(),
						flight.getNumberOfSeats(), flight.getDeparture(), flight.getArrival(), flight.getCustomers());
			} catch (DataIntegrityViolationException e) {
				throw new EntityNotFoundException(
						"Cannot insert flight, customer with id " + flight.getCustomers() + " not found", e);
			}
		} else {
			String sql = "UPDATE flight SET date_of_flight = ?, "
					+ "airport_from = ?, airport_where = ?, company_name = ?, flight_class = ?, number_of_seats = ?, departure = ?, "
					+ "arrival = ? " + "WHERE id = ?";
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
				+ "join flight on flight.id = flight_customer.flight_id " + "where flight_id = " + flight.getId()
				+ " group by flight_id; ";
		int count = jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int volne = rs.getInt("volne");
				return volne;
			}

		});
		if (count == flight.getNumberOfSeats()) {
			throw new FlightFullException("Flight is full!");
		} else {
			return false;
		}
	}

	@Override
	public List<List<String>> fromAtoB(Flight from, Flight where) {
	     String sql = "SELECT a1.airport_name as a111, a2.airport_name as a222\r\n" + 
                 "FROM Flight AS f\r\n" + 
                 "INNER JOIN Airport AS a1\r\n" + 
                 "ON f.airport_from = a1.id\r\n" + 
                 "INNER JOIN Airport AS a2\r\n" + 
                 "ON f.airport_where = a2.id\r\n" +
                 "WHERE airport_from = " + from.getId() + " AND airport_where = " +
                 where.getId();
         List<List<String>> flights = jdbcTemplate.query(sql, new RowMapper<List<String>>() {

             @Override
             public List<String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                 List<String> AtoB = new ArrayList<>();
                 String airportFrom = rs.getString("a111");
                 String airportWhere = rs.getString("a222");
                 AtoB.add(airportFrom);
                 AtoB.add(airportWhere);
                 return AtoB;
             }

         });
         return flights;
	}

	@Override
	public List<List<String>> fromAtoC(Flight from, Flight where) {
		 String sql = " WITH JoinedFlights AS (\n"
		 		+ "  SELECT\n"
		 		+ "    f.id as id,\n"
		 		+ "    a1.id as origin,\n"
		 		+ "    a2.id as target,\n"
		 		+ "    f.departure as departure,\n"
		 		+ "    f.arrival as arrival\n"
		 		+ "  FROM Flight AS f\n"
		 		+ "  INNER JOIN Airport AS a1\n"
		 		+ "  ON f.airport_from = a1.id\n"
		 		+ "  INNER JOIN Airport AS a2\n"
		 		+ "  ON f.airport_where = a2.id\n"
		 		+ ")\n"
		 		+ "SELECT DISTINCT\n"
		 		+ "  f1.id as firstFlightId,\n"
		 		+ "  f2.id as secondFlightId\n"
		 		+ "FROM\n"
		 		+ "  JoinedFlights f1\n"
		 		+ "INNER JOIN\n"
		 		+ "  JoinedFlights f2\n"
		 		+ "ON\n"
		 		+ "  f1.arrival < f2.departure\n"
		 		+ "WHERE\n"
		 		+ "  (f1.origin = 4 AND f1.target = 3) AND (f2.origin = 3 AND f2.target = 1 )\n"
		 		+ "GROUP BY f1.id, f2.id\n"
		 		+ "ORDER BY TIMESTAMPDIFF(SECOND, f1.arrival, f2.departure)";
		 		
         List<List<String>> flightsAtoC = jdbcTemplate.query(sql, new RowMapper<List<String>>() {

             @Override
             public List<String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                 List<String> AtoB = new ArrayList<>();
                 String airportFrom = rs.getString("a111");
                 String through = rs.getString("");
                 String airportWhere = rs.getString("a222");
                 AtoB.add(airportFrom);
                 AtoB.add(through);
                 AtoB.add(airportWhere);
                 return AtoB;
             }

         });
         return flightsAtoC;
	}
}
