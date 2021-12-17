package sk.upjs.paz1c.project.storage;

import java.util.List;

public interface FlightDao {
	
	List<Flight> getAll();
	
	List<Flight> getByAirport(Airport airport);
	
	Flight save(Flight flight) throws EntityNotFoundException;
	
	boolean delete(long id);
	
	boolean isFull(Flight flight);
	
	List<Flight> fromAtoB(String from, String where);
	
	void insertCustomers(List<Customer> customers, long flightId);
	
	boolean isCustomerInFlight(Customer customer, Flight flight);
	

}
