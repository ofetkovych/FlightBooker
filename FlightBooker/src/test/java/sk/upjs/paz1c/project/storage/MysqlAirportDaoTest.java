package sk.upjs.paz1c.project.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


class MysqlAirportDaoTest {

	private AirportDao airportDao;
	private Airport savedAirport;

	public MysqlAirportDaoTest() {
		DaoFactory.INSTANCE.testing();
		airportDao = DaoFactory.INSTANCE.getAirportDao();
	}

	@BeforeEach
	void setUp() throws Exception {
		Airport testerAirport = new Airport("Slovakia", "Košice", "Košice Airport", "KSC");
		savedAirport = airportDao.save(testerAirport);

	}

	@AfterEach
	void tearDown() throws Exception {
		airportDao.delete(savedAirport.getId());
	}

	@Test
	void testGetAllCountries() {
		Set<String> countries = airportDao.getAllCountries();
		assertNotNull(countries);
		assertTrue(countries.size() > 0);
		//System.out.println(countries);
	}

	@Test
	void testGetCityByCountry() {
		Set<String> countries = airportDao.getAllCountries();
		// System.out.println(countries);
		String countr = "Slovakia";
		Set<String> cities = airportDao.getCityByCountry(countr); // alebo
																	// airportDao.getCityByCountry(countries.iterator().next();
		assertNotNull(cities);
		assertTrue(cities.size() > 0);
		// System.out.println(cities);
	}

	@Test
	void testGetByCity() {
		String city = "Trnava";
//		Set<String> countries = airportDao.getAllCountries();
//		Set<String> cities = airportDao.getCityByCountry(countries.iterator().next());
//		System.out.println(cities);
		Set<String> airports = airportDao.getByCity(city);
		System.out.println(airports);
		assertNotNull(airports);
		assertTrue(airports.size() > 0);
		System.out.println(airports);
	}
	
//	@Test
//	void testGetByCity() {
//		System.out.println(airportDao.getByCity("London"));
//	}

	@Test
	void testGetById() {
		Airport byId = airportDao.getById(savedAirport.getId());
		assertEquals(savedAirport, byId);
	}

	@Test
	void testUpdate() {
		Airport changedAirport = new Airport(savedAirport.getId(), "Test", "update", "Miskolc", "MSC");
		Airport savedChangedAirport = airportDao.save(changedAirport);
		assertEquals(changedAirport, savedChangedAirport);
		
		List<Airport> all = airportDao.getAll();
		boolean found = false;
		for(Airport airport: all) {
			if (airport.getId().equals(changedAirport.getId())) { //long > 127 musi .equals() | long < 128 moze s == !!!
				found = true;
				assertEquals(changedAirport, airport);
				break;
			}
		}
		assertTrue(found);
		changedAirport.setId(-1L);
		assertThrows(EntityNotFoundException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				airportDao.save(changedAirport);
				
			}
		});
		assertThrows(NullPointerException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				airportDao.save(null);
			}
		});


	}
	@Test
	void testDelete() {
		Airport airportToDelete = new Airport("Tester", "Of delete", "to", "DEL");
		Airport saved = airportDao.save(airportToDelete);
		Airport saved2 = airportDao.delete(saved.getId());
		assertEquals(saved.getId(), saved2.getId());
		assertThrows(EntityNotFoundException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				airportDao.getById(saved.getId());
			}
		});
		assertThrows(EntityUndeleteableException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				airportDao.delete(1L);
			}
		});
	}
	@Test
	void testInsert() {
		int initialSize = airportDao.getAll().size();
		Airport newAirport =  new Airport("Tester", "Of delete", "to", "DEL");
		Airport savedNewAirport = airportDao.save(newAirport);
		assertEquals(savedNewAirport.getCountry(), newAirport.getCountry());
		assertEquals(savedNewAirport.getCity(), newAirport.getCity());
		assertEquals(savedNewAirport.getAirportName(), newAirport.getAirportName());
		assertEquals(savedNewAirport.getAirportAcronym(), newAirport.getAirportAcronym());
		assertNotNull(savedNewAirport.getId());
		List<Airport> all = airportDao.getAll();
		assertEquals(initialSize + 1, all.size());

		boolean found = false;
		for (Airport airport : all) {
			if (airport.getId().equals(savedNewAirport.getId())) {
				found = true;
				assertEquals("Tester", airport.getCountry());
				assertEquals("Of delete", airport.getCity());
				assertEquals("to", airport.getAirportName());
				assertEquals("DEL", airport.getAirportAcronym());
				break;
			}
		}
		assertTrue(found);
		airportDao.delete(savedNewAirport.getId());
	}
}
