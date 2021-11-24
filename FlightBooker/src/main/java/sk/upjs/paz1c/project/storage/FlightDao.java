package sk.upjs.paz1c.project.storage;

import java.util.List;

public interface FlightDao {
	
	List<Flight> getByAirport(Airport airport);
	
	Flight save(Flight flight) throws EntityNotFoundException, NullPointerException;
	
	boolean delete(long id);
	
	boolean isFull(Flight flight);

}
