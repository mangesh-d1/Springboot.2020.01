package com.mangesh.location.service;

import java.util.List;
import java.util.Optional;

import com.mangesh.location.entities.Location;

public interface LocationService {
	Location saveLocation(Location location);
	Location updateLocation(Location location);
	Optional<Location> getLocattion(int id);
	List<Location> getAllLocation();
	void deleteLocation1(Location locattion);
	

}
