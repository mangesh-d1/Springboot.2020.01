package com.mangesh.location.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mangesh.location.entities.Location;
import com.mangesh.location.service.LocationService;

@Controller
public class LocationController 
{
	@Autowired
	LocationService service;
	
	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location,ModelMap modelMap) {
		Location LocationSaved = service.saveLocation(location);
		String msg = "Location saved with id:"+LocationSaved.getId();
		modelMap.addAttribute("msg", msg);
		return "createLocation";
		}
	@RequestMapping("/displayLocations")
	public String displayLocation(ModelMap modelMap) {
		List<Location> locations = service.getAllLocation();
		modelMap.addAttribute("locations" ,locations);
		return "displayLocations";
	}
	@RequestMapping("deleteLocation")
	public String deleteLocation(@RequestParam("id") int id,ModelMap modelMap)
	{
//		Optional<Location> locattion = service.getLocattion(id);
		
		Location location = new Location();
		location.setId(id); 
		service.deleteLocation1(location);
		List<Location> locations = service.getAllLocation();
		modelMap.addAttribute("locations" ,locations);
		return "displayLocations";
	}
	
	@RequestMapping("/showUpdate")
	public String showUpdate(@RequestParam("id") int id,ModelMap modelMap) {
		Optional<Location> location = service.getLocattion(id);
		modelMap.addAttribute("location",location.get());
 		return "updateLocation";
		
	}
	@RequestMapping("/updateLoc")
	public String updatelocation(@ModelAttribute("location") Location location,ModelMap modelMap)
	{
	   service.updateLocation(location);
	   List<Location> locations= service.getAllLocation();
	   modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}

}
