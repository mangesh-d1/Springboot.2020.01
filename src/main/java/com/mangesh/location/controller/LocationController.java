package com.mangesh.location.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mangesh.location.entities.Location;
import com.mangesh.location.repoes.LocationRepository;
import com.mangesh.location.service.LocationService;
import com.mangesh.location.util.EmailUtil;
import com.mangesh.location.util.ReportUtil;

@Controller
public class LocationController 
{
	@Autowired
	LocationRepository repository;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	ReportUtil reportUtil;
	
	@Autowired
	ServletContext servletContext;
	
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
		emailUtil.sendEmail("mangsdar48@gmail.com","locationSaved","Location");
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
	
	@RequestMapping("/generateReport")
	public String generateReport() {
		String path = servletContext.getRealPath("/");
		List<Object[]> data = repository.findTypeandTypeCount();
		reportUtil.generatePichart(path, data);
		
		return "reportjsp";
		
	}

}
