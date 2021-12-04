package sk.upjs.paz1c.project.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestSuite;

import org.junit.jupiter.api.function.Executable;
import org.springframework.dao.DataIntegrityViolationException;

class MysqlCustomerDaoTest {

	private FlightDao flightDao;
	private CustomerDao customerDao;
	private Customer savedCustomer;

	public MysqlCustomerDaoTest() {
		DaoFactory.INSTANCE.testing();
		customerDao = DaoFactory.INSTANCE.getCustomerDao();
	}

	@BeforeEach
	void setUp() throws Exception {
		LocalDate date = LocalDate.of(2021, 12, 21);
		Customer testerCustomer = new Customer("Test", "Customer", date, "Male", 948438721L, "Košická 2");
		savedCustomer = customerDao.save(testerCustomer);
	}

	@AfterEach
	void tearDown() throws Exception {
		customerDao.delete(savedCustomer.getId());
	}

	@Test
	void testGetAll() {
		List<Customer> customers = customerDao.getAll();
		assertNotNull(customers);
		assertTrue(customers.size() > 0);
		System.out.println(customers);
	}

	@Test
	void testGetById() {
		Customer byIdCustomer = customerDao.getById(savedCustomer.getId());
		assertEquals(savedCustomer.getId(), byIdCustomer.getId());
		assertEquals(savedCustomer.getName(), byIdCustomer.getName());
		assertEquals(savedCustomer.getSurname(), byIdCustomer.getSurname());
		assertEquals(savedCustomer.getDateOfBirth(), byIdCustomer.getDateOfBirth());
		assertEquals(savedCustomer.getGender(), byIdCustomer.getGender());
		assertEquals(savedCustomer.getPhoneNumber(), byIdCustomer.getPhoneNumber());
		assertEquals(savedCustomer.getAdress(), byIdCustomer.getAdress());
		assertThrows(EntityNotFoundException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				customerDao.getById(-1L);
			}
		});

	}

	@Test
	void testInsert() {
		LocalDate date = LocalDate.of(2021, 12, 21);
		int initialSize = customerDao.getAll().size();
		Customer newCustomer = new Customer("Testovač", "Insertu", date, "nema gender", 23L, "Presovska ulica");
		Customer savedNewCustomer = customerDao.save(newCustomer);
		assertEquals(savedNewCustomer.getName(), newCustomer.getName());
		assertEquals(savedNewCustomer.getSurname(), newCustomer.getSurname());
		assertEquals(savedNewCustomer.getDateOfBirth(), newCustomer.getDateOfBirth());
		assertEquals(savedNewCustomer.getGender(), newCustomer.getGender());
		assertEquals(savedNewCustomer.getPhoneNumber(), newCustomer.getPhoneNumber());
		assertEquals(savedNewCustomer.getAdress(), newCustomer.getAdress());
		assertNotNull(savedNewCustomer.getId());
		List<Customer> all = customerDao.getAll();
		assertEquals(initialSize + 1, all.size());

		boolean found = false;
		for (Customer customer : all) {
			if (customer.getId().equals(savedNewCustomer.getId())) {
				found = true;
				assertEquals("Testovač", customer.getName());
				assertEquals("Insertu", customer.getSurname());
				assertEquals(date, customer.getDateOfBirth());
				assertEquals("nema gender", customer.getGender());
				assertEquals(23L, customer.getPhoneNumber());
				assertEquals("Presovska ulica", customer.getAdress());
				break;
			}
		}
		assertTrue(found);
		customerDao.delete(savedNewCustomer.getId());
//		Customer newCustomer2 = new Customer("Testova", "Zlho", date, "nema gender", -23L , "Presovska ulica");
//		assertThrows(EntityNotFoundException.class, new Executable() {
//			@Override
//			public void execute() throws Throwable {
//				customerDao.save(newCustomer2);
//			}
//		});
	}

	@Test
	void testUpdate() {
		LocalDate date = LocalDate.of(2021, 12, 21);
		Customer changedCustomer = new Customer(savedCustomer.getId(), "Test", "Update", date, "Male", 24L,
				"Bratislavska ulica");
		Customer savedChangedCustomer = customerDao.save(changedCustomer);
		assertEquals("Test", savedChangedCustomer.getName());
		assertEquals("Update", savedChangedCustomer.getSurname());
		assertEquals(date, savedChangedCustomer.getDateOfBirth());
		assertEquals("Male", savedChangedCustomer.getGender());
		assertEquals(24L, savedChangedCustomer.getPhoneNumber());
		assertEquals("Bratislavska ulica", savedChangedCustomer.getAdress());

		List<Customer> all = customerDao.getAll();
		boolean found = false;
		for (Customer customer : all) {
			if (customer.getId().equals(changedCustomer.getId())) {
				found = true;
				assertEquals("Test", customer.getName());
				assertEquals("Update", customer.getSurname());
				assertEquals(date, customer.getDateOfBirth());
				assertEquals("Male", customer.getGender());
				assertEquals(24L, customer.getPhoneNumber());
				assertEquals("Bratislavska ulica", customer.getAdress());
				break;
			}
		}
		assertTrue(found);
		changedCustomer.setId(-1L);
		assertThrows(EntityNotFoundException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				customerDao.save(changedCustomer);
			}
		});

		changedCustomer.setId(savedChangedCustomer.getId());
		changedCustomer.setPhoneNumber(-1L);
		assertThrows(EntityNotFoundException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				customerDao.save(changedCustomer);
			}
		});

		assertThrows(NullPointerException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				customerDao.save(null);
			}
		});
	}

	@Test
	void testDelete() {
		LocalDate date = LocalDate.of(2021, 12, 21);
		Customer customerToDelete = new Customer("Tester", "Of delete", date, "Male", 24L, "Trencinska ulica");
		Customer saved = customerDao.save(customerToDelete);
		String nameSaved = saved.getName();
		Customer saved2 = customerDao.delete(saved.getId());
		String nameSaved2 = saved2.getName();
		assertEquals(nameSaved, nameSaved2);
		assertThrows(EntityNotFoundException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				customerDao.getById(saved.getId());
			}
		});
	}

//		assertThrows(EntityUndeleteableException.class, new Executable() {
//			@Override
//			public void execute() throws Throwable {
//				customerDao.delete(saved.getId());
//			}
//		});
//	}
	@Test
	void testGetByFlightId() {
		LocalDate date = LocalDate.of(2021, 12, 21);
		LocalDateTime departure = LocalDateTime.of(2021, 12, 5, 12, 34, 45);
		LocalDateTime arrival = LocalDateTime.of(2021, 12, 5, 12, 50, 45);
		Flight flight = new Flight(1L, date, 1L , 2L , "Slovak Airlines", "Bussines", 40, departure, arrival );
		List<Customer> list = customerDao.getByFlightId(1);
		Customer customer = new Customer("Tester", "Of bySubjectId", date, "Male", 24L, "Martinska ulica");
		Flight saved = flightDao.save(flight);
		assertEquals(list.size() + 1, customerDao.getByFlightId(1).size());
		customerDao.delete(saved.getId());

		List<Customer> list2 = customerDao.getByFlightId(-1);
		assertEquals(0, list2.size());
	}
//
//
//	

}
