package sk.upjs.paz1c.project.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MysqlFlightDaoTest {
	
	private FlightDao flightDao;
	private CustomerDao customerDao;
	private String country;
	private Flight savedFlight;
	private List<Customer> savedCustomer;
	
	public MysqlFlightDaoTest() {
		DaoFactory.INSTANCE.testing();
		flightDao = DaoFactory.INSTANCE.getFlightDao();
		customerDao = DaoFactory.INSTANCE.getCustomerDao();
	}



	@BeforeEach
	void setUp() throws Exception {
		LocalDate date = LocalDate.of(2021, 12, 21);
		savedCustomer = new ArrayList<Customer>();
		savedCustomer.add(customerDao.save(new Customer(null, "Flight", "INSERT", date, "Male", 24L, "Martinska ulica")));
		savedCustomer.add(customerDao.save(new Customer(null,  "Flight", "INSERT", date, "Male", 24L, "Martinska ulica")));
		
		LocalDateTime departure = LocalDateTime.of(2021, 12, 5, 12, 34, 45);
		LocalDateTime arrival = LocalDateTime.of(2021, 12, 5, 12, 50, 45);
		Flight testerFlight
			= new Flight(null, date, 1L , 2L , "Slovak Airlines", "Bussines", 40, departure, arrival);
		savedFlight = flightDao.save(testerFlight);

	}

	@AfterEach
	void tearDown() throws Exception {
		flightDao.delete(savedFlight.getId());
		for (Customer c: savedCustomer) {
			customerDao.delete(c.getId());
		}

	}

	@Test
	void testInsert() {
		LocalDate date = LocalDate.of(2021, 12, 21);
		List<Customer> savedCustomers2= new ArrayList<Customer>();
		savedCustomers2.add(customerDao.save(new Customer(null, "Flight", "INSERT", date, "Male", 24L, "Martinska ulica")));
		savedCustomers2.add(customerDao.save(new Customer(null, "Flight", "INSERT", date, "Male", 24L, "Martinska ulica")));
		LocalDateTime departure = LocalDateTime.of(2021, 12, 5, 12, 34, 45);
		LocalDateTime arrival = LocalDateTime.of(2021, 12, 5, 12, 50, 45);
		Flight newFlight
			= new Flight(null, date, 1L , 2L , "Slovak Airlines", "Bussines", 40, departure, arrival);
		Flight savedFlight2 = flightDao.save(newFlight);
		assertEquals(newFlight.getDeparture(),savedFlight2.getDeparture());
		assertEquals(2, savedFlight2.getCustomers().size());
		flightDao.delete(savedFlight2.getId());
		for (Customer s: savedCustomers2) {
			customerDao.delete(s.getId());
		}		

	}

}
