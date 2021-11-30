package sk.upjs.paz1c.project.storage;

import java.util.Set;

public interface AirportDao {
	Set<String> getByCity(String city);
	
	Set<String> getAllCountries();
	
	Set<String> getCityByCountry(String country);
	
	Airport getById(long id) throws EntityNotFoundException;
	
	Airport save(Airport airport) throws EntityNotFoundException, NullPointerException;
	
	Airport delete(long idAirport) throws EntityNotFoundException, NullPointerException;
}
