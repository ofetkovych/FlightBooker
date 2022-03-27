package sk.upjs.paz1c.project.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import sk.upjs.paz1c.project.storage.Airport;
import sk.upjs.paz1c.project.storage.Customer;
import sk.upjs.paz1c.project.storage.EntityNotFoundException;
import sk.upjs.paz1c.project.storage.Flight;

class MysqlFlightDaoTest {

	private FlightDao flightDao;
	private CustomerDao customerDao;
	private String country;
	private Flight savedFlight;
	private Airport airport;
	private AirportDao airportDao;
	private List<Customer> savedCustomer;

	public MysqlFlightDaoTest() {
		DaoFactory.INSTANCE.testing();
		flightDao = DaoFactory.INSTANCE.getFlightDao();
		customerDao = DaoFactory.INSTANCE.getCustomerDao();
		airportDao = DaoFactory.INSTANCE.getAirportDao();
	}

	@BeforeEach
	void setUp() throws Exception {
		savedCustomer = new ArrayList<Customer>();
		LocalDate date12 = LocalDate.now();
		savedCustomer.add(customerDao.save(new Customer(null, "Tester", "Of test", date12, "Male", 24L, "Trencinska ulica")));
		savedCustomer.add(customerDao.save(new Customer(null, "Tester", "Of test2", date12, "Female", 25L, "Kievska ulica")));

		
		
		LocalDate date = LocalDate.of(2021,12,21);
		LocalDateTime localDate = LocalDateTime.of(2021, 12, 21, 12, 12, 10);
		LocalDateTime localDate1 = LocalDateTime.of(2021, 12, 21, 21, 15, 38);
		Airport airport1 = new Airport("England", "London", "London airport", "LND");
		Airport airportSaved = airportDao.save(airport1);	
		Flight flight = new Flight( date, airportSaved.getId() , airportSaved.getId() , "Slovak Airlines", "Bussines", 1, localDate, localDate1 );
		savedFlight = flightDao.save(flight);
	}

	@AfterEach
	void tearDown() throws Exception {
		flightDao.delete(savedFlight.getId());
	}
	

	@Test
	void testDelete() {
	
		  Airport airport1 = new Airport("England", "London", "London airport", "LND");
		  Airport airportSaved = airportDao.save(airport1); 
		  LocalDateTime localDate = LocalDateTime.of(2021, 12, 21, 12, 12, 10); 
		  LocalDateTime localDate1 = LocalDateTime.of(2021, 12, 21, 21, 15, 38); 
		  Flight flightToDelete = new Flight(null, LocalDate.now(), airportSaved.getId(), airportSaved.getId(), "Slovak Airlines", "Bussines", 40, localDate, localDate1); 
		  Flight saved = flightDao.save(flightToDelete); 
		  List<Flight> all = flightDao.getByAirport(airportSaved); 
		  boolean success = flightDao.delete(saved.getId()); 
		  assertTrue(success); 
		  assertEquals(all.size()- 1, flightDao.getByAirport(airportSaved).size());
		  assertFalse(flightDao.delete(saved.getId()));
		  assertFalse(flightDao.delete(-1L));
		 
	}
	
	@Test
	void testIsFull() {
		 Airport airport1 = new Airport("England", "London", "London airport", "LND");
		 Airport airportSaved = airportDao.save(airport1); 
		 LocalDateTime localDate = LocalDateTime.of(2021, 12, 22, 12, 12, 10); 
		 LocalDateTime localDate1 = LocalDateTime.of(2021, 12, 22, 21, 15, 38);
		 Flight flight = new Flight(null,
		 LocalDate.now(), airportSaved.getId(), airportSaved.getId(), "Slovak Airlines", "Bussines", 3, localDate, localDate1); 
		 LocalDate date = LocalDate.of(2021, 12, 21); 
		 Customer customer = new Customer("Tester","Of delete", date, "Male", 24L, "Trencinska ulica"); 
		 Customer savedCustomer = customerDao.save(customer); 
		 List<Customer> customers = new ArrayList<>();
		 customers.add(savedCustomer); 
		 flight.setCustomers(customers);
		 Flight saved = flightDao.save(flight); 
		 System.out.println(saved); 
		 boolean trueOrFalse = flightDao.isFull(saved); 
		 System.out.println(trueOrFalse);
		 if(saved.getCustomers().size() == saved.getNumberOfSeats()) {
			 assertTrue(trueOrFalse);
		 }
		 else {
			 assertFalse(trueOrFalse);
		 }
		 
	}
	
	@Test
	void testInsert() {
		
		 Airport airport1 = new Airport("England", "London", "London airport", "LND");
		 Airport airportSaved = airportDao.save(airport1); 
		 LocalDateTime localDate = LocalDateTime.of(2021, 12, 22, 12, 12, 10); 
		 LocalDateTime localDate1 = LocalDateTime.of(2021, 12, 22, 21, 15, 38); 
		 LocalDate date1 = LocalDate.now(); 
		 List<Customer> listCustomers = new ArrayList<>();
		 Flight flight1 = new Flight(null, date1, airportSaved.getId(), airportSaved.getId(), "Slovak Airlines", "Bussines", 1, localDate, localDate1); 
		 LocalDate date = LocalDate.of(2021, 12, 21); 
		 Customer customer = new Customer("Tester", "Of delete", date, "Male", 24L, "Trencinska ulica");
		 Customer savedCustomer = customerDao.save(customer);
		 
		 int numberOfFlights = flightDao.getByAirport(airportSaved).size();
		 
		 listCustomers.add(savedCustomer);
		 listCustomers.add(customerDao.save(new Customer("Tester2", "Of insert", date, "Female", 27L, "Popradska")));
		 flight1.setCustomers(listCustomers);
		 Flight saved = flightDao.save(flight1); 
		 assertEquals(saved, flight1);
		 assertEquals(listCustomers.size(), saved.getCustomers().size());
		 assertNotNull(saved.getId());
		  
	}
	
	@Test
	void testUpdate() {
		Airport airport1 = new Airport("England1", "London", "London airport", "LND");
		Airport airportSaved = airportDao.save(airport1);	
		//System.out.println(airportSaved);
		List<Customer> customers = customerDao.getAll();
		
		LocalDateTime localDate = LocalDateTime.of(2021, 12, 22, 12, 12, 10);
		LocalDateTime localDate1 = LocalDateTime.of(2021, 12, 22, 21, 15, 38);
		LocalDate date1 = LocalDate.now();
		Flight flight1 = new Flight(savedFlight.getId(), date1, airportSaved.getId(), airportSaved.getId(), "Slovak Airlines", "Bussines", 1, localDate, localDate1);
		
		LocalDate date = LocalDate.of(2003, 9, 21);
		Customer customer = new Customer("Tester", "Of update", date, "Male", 24L, "Presovska ulica");
		Customer savedCustomer1 = customerDao.save(customer);
		
		List<Customer> list = new ArrayList<>();
		list.add(savedCustomer.get(0));
		list.add(savedCustomer.get(1));
		list.add(savedCustomer1);
		flight1.setCustomers(list);	
		Flight saved = flightDao.save(flight1);
		assertEquals(saved, flight1);
		List<Flight> list1 = flightDao.getAll();
		boolean found = false;
		for (Flight flight23 : list1) {
			if (flight23.getId().equals(saved.getId())) {
				found = true;
                assertEquals(saved.getDateOfFlight(), flight23.getDateOfFlight());
                assertEquals(saved.getFrom(), flight23.getFrom());
                assertEquals(saved.getWhere(), flight23.getWhere());
                assertEquals(saved.getCompanyName(), flight23.getCompanyName());
                assertEquals(saved.getFlightClass(), flight23.getFlightClass());
                assertEquals(saved.getNumberOfSeats(), flight23.getNumberOfSeats());
                assertEquals(saved.getDeparture(), flight23.getDeparture());
                assertEquals(saved.getArrival(), flight23.getArrival());
                
				break;
			}
		}
		assertTrue(found);		
		
		flightDao.save(savedFlight); // nahradme tudentov nasp na pvodnch
		flight1.setId(-1L);
		assertThrows(EntityNotFoundException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				flightDao.save(flight1);
			}
		});
		assertThrows(NullPointerException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				flightDao.save(null);
			}
		});

	
	}
	

	
	@Test
	void testFromAtoB() {
		Airport airport1 = new Airport("Chech Republik", "Karlovy Vary", "Karlovy Vary airport", "BRN");
		Airport airport1Saved = airportDao.save(airport1);
		Airport airport2 = new Airport("USA", "New York", "New York airport", "NYK");
		Airport airport2Saved = airportDao.save(airport2);
		Airport airport3 = new Airport("Australia", "Canbera", "Canbera airport", "CBR");
		Airport airport3Saved = airportDao.save(airport3);
		LocalDateTime localDate = LocalDateTime.of(2021, 12, 22, 12, 10, 15);
		LocalDateTime localDate1 = LocalDateTime.of(2021, 12, 22, 17, 15, 39);
		Flight flight1 = new Flight(LocalDate.now(), airport1Saved.getId(), airport2Saved.getId(), "Slovak Airlines",
				"Bussines", 1, localDate, localDate1);
		Flight flight2 = new Flight(LocalDate.now(), airport2Saved.getId(), airport3Saved.getId(), "Slovak Airlines",
				"Bussines", 1, LocalDateTime.of(2021, 12, 22, 19, 23, 48), LocalDateTime.of(2021, 12, 23, 06, 10, 15));

		Flight saved1 = flightDao.save(flight1);
		Flight saved2 = flightDao.save(flight2);

		
		List<Flight> listFlightov = new ArrayList<Flight>();
		listFlightov.add(saved1);
		listFlightov.add(saved2);
		
		
		List<Flight> listZPrestupom = flightDao.fromAtoB(airport1Saved.getAirportName(), airport3Saved.getAirportName());
		System.out.println(listZPrestupom);
		assertEquals(listFlightov, listZPrestupom);	
		
		flightDao.delete(flight1.getId());
		flightDao.delete(flight2.getId());
		
	}
	
	@Test
	void testFromAtoB2() {
		Airport airport1 = new Airport("Chech Republik", "Kralov", "Karlov airport", "BRN");
		Airport airport1Saved = airportDao.save(airport1);
		Airport airport3 = new Airport("Australia", "Canbera", "Canbera airport", "CBR");
		Airport airport3Saved = airportDao.save(airport3);
		
		Flight flight3 = new Flight(LocalDate.now(), airport1Saved.getId(), airport3Saved.getId(), "Slovak Airlines",
				"Bussines", 1, LocalDateTime.of(2021, 12, 22, 19, 23, 48), LocalDateTime.of(2021, 12, 23, 06, 10, 15));
		Flight saved3 = flightDao.save(flight3);
		
		List<Flight> listFlightov = new ArrayList<Flight>();
		listFlightov.add(saved3);
		
		List<Flight> listZPrestupom = flightDao.fromAtoB(airport1Saved.getAirportName(), airport3Saved.getAirportName());
		
		System.out.println(listZPrestupom);
		System.out.println(listFlightov);
		assertEquals(listZPrestupom, listFlightov);
		
		flightDao.delete(saved3.getId());
		
	}
	
	@Test
	void testIsCustomerInFlight() {
		Airport airport1 = new Airport("Chech Republik", "Brno", "Brno airport", "BRN");
		Airport airportSaved = airportDao.save(airport1);
		LocalDateTime localDate = LocalDateTime.of(2021, 12, 22, 12, 12, 10);
		LocalDateTime localDate1 = LocalDateTime.of(2021, 12, 22, 21, 15, 38);
		LocalDate date1 = LocalDate.now();
		Flight flight1 = new Flight(date1, airportSaved.getId(), airportSaved.getId(), "Slovak Airlines", "Bussines", 1, localDate, localDate1);
		
		LocalDate date = LocalDate.of(2003, 9, 21);
		Customer customer = new Customer("Tester", "Of customer in flight", date, "Male", 24L, "Warsavska ulica");
		Customer savedCustomer1 = customerDao.save(customer);
		Flight saved = flightDao.save(flight1);
		
		 List<Customer> customers = new ArrayList<>();
		 customers.add(savedCustomer1); 
		 saved.setCustomers(customers);
		
		flightDao.save(saved);
		
		System.out.println(saved);
		
		boolean isCustInFlight = flightDao.isCustomerInFlight(savedCustomer1, saved);
		
		System.out.println(isCustInFlight);
		
		if(saved.getCustomers().get(0) == savedCustomer1) {
			 assertTrue(isCustInFlight);
		 }
		 else {
			 assertFalse(isCustInFlight);
		 }
		
	}
}
