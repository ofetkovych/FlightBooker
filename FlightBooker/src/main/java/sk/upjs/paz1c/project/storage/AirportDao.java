package sk.upjs.paz1c.project.storage;

import java.util.Set;

public interface AirportDao {
	
	Set<Airport> getAllCountries();
	
	Airport getById(long id) throws EntityNotFoundException;
	
	Airport save(Airport airport) throws EntityNotFoundException, NullPointerException;
	
	Airport delete(long idAirport) throws EntityNotFoundException, NullPointerException;
}
