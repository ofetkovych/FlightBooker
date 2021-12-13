package sk.upjs.paz1c.project.storage;

import java.util.List;

public interface FlightDao {
	
	List<Flight> getAll();
	
	List<List<String>> fromAtoB(Flight from, Flight where);
	
	List<List<String>> fromAtoC(Flight from, Flight where);
	
	List<Flight> getByAirport(Airport airport);
	
	Flight save(Flight flight) throws EntityNotFoundException;
	
	boolean delete(long id);
	
	boolean isFull(Flight flight);

}
