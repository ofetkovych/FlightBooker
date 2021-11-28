package sk.upjs.paz1c.project.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class MysqlAirportDao implements AirportDao {

	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public MysqlAirportDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Set<String> getAllCountries() {
		String sql = "SELECT country FROM airport";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Set<String>>() {

			@Override
			public Set<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Set<String> list = new HashSet<String>();
				Airport airport = null;
				while (rs.next()) {
					String countr = rs.getString("country");
					list.add(countr);
				}
				return list;
			}

		});
	}

	@Override
	public Airport getById(long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airport save(Airport airport) throws EntityNotFoundException, NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airport delete(long idAirport) throws EntityNotFoundException, NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getByCity(String city) {
		String sql = "SELECT * FROM airport WHERE city = " + city + ";";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Set<String>>() {

			public Set<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Set<String> list = new HashSet<String>();
				Airport airport = null;
				while (rs.next()) {
					long id = rs.getLong("id");
					if (airport == null || airport.getId() != id) {
						String country = rs.getString("country");
						String city = rs.getString("city");
						String airportName = rs.getString("airport_name");
						String airportAcronym = rs.getString("acronym_of_airport");
						list.add(airportName);
					}
				}
				return list;
			}
		});
	}

	@Override
	public Set<String> getCityByCountry() {
		// TODO Auto-generated method stub
		return null;
	}

}
