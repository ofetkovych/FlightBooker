package sk.upjs.paz1c.project.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestSuite;


class MysqlCustomerDaoTest {
	
	private CustomerDao customerDao;
	private Customer savedCustomer;
	
	public MysqlCustomerDaoTest() {
		DaoFactory.INSTANCE.testing();
		customerDao = DaoFactory.INSTANCE.getCustomerDao();
	}

	@BeforeEach
	void setUp() throws Exception {
		LocalDate date = LocalDate.of(2021,12,21);
		Customer testerCustomer = new Customer("Test", "Customer", date , "Male", 948438721L, "Košická 2");
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

}
