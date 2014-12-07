package controller;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Configuration;

import interceptors.SessionValidatorInterceptor;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import resources.Bike;
import resources.LocationInventory;
import resources.User;

import java.util.*;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Location;

import DTO.LoginDTO;
import DTO.UserDTO;
import DTO.BookingDTO;
import bikeshareimpl.AuthInterfaceImpl;
import bikeshareimpl.BikeOperationsImpl;
import bikeshareimpl.LocationInvetoryOperations;
import bikeshareimpl.UserOperationsImpl;
import bikeshareinterfaces.AuthInterface;
import bikeshareinterfaces.BikeOperationsInterface;
import bikeshareinterfaces.LocationInventoryInterface;
import bikeshareinterfaces.UserOperationInterface;

@Component
@EnableAutoConfiguration
@RestController
@RequestMapping("/api/v1/*")
public class BikeShareController  extends WebMvcConfigurerAdapter{

	UserOperationInterface userOperationInterface = new UserOperationsImpl();
	AuthInterface authInterface = new AuthInterfaceImpl();
	LocationInventoryInterface LocationInventoryOps = new LocationInvetoryOperations();
	public static String globalReservationIndicator = "RESERVED";

	@Bean
	public FilterRegistrationBean shallowEtagHeaderFilter() {

		ShallowEtagHeaderFilter shallowEtagHeaderFilter = new ShallowEtagHeaderFilter();
		FilterRegistrationBean etagBean = new FilterRegistrationBean();
		etagBean.setFilter(shallowEtagHeaderFilter);
		ArrayList<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/api/v1/users/*");
		etagBean.setUrlPatterns(urlPatterns);
		return etagBean;

	}
	
    @Bean
    public SessionValidatorInterceptor sessionValidatorInterceptor() {
        return new SessionValidatorInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionValidatorInterceptor()).addPathPatterns(
        		"/api/v1/users/*");
    }
 
   	// User Related Operations
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseBody
	public UserDTO createUser(@Valid @RequestBody UserDTO user) {
		return userOperationInterface.createUser(user);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/users/{user_id}", method = RequestMethod.PUT)
	@ResponseBody
	public UserDTO updateUser(@PathVariable String user_id,
			@Valid @RequestBody UserDTO user) {
		return userOperationInterface.updateUser(user_id, user);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/users/{user_id}", method = RequestMethod.GET)
	@ResponseBody
	public UserDTO getUser(@PathVariable String user_id) {
		return userOperationInterface.getUser(user_id);
	}

	@RequestMapping("/login")
	@ResponseBody
	private LoginDTO login(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response) {
		loginDTO = authInterface.login(loginDTO);
        response.addCookie(new Cookie("sessionid", loginDTO.getSessionId()));
        response.addCookie(new Cookie("username", loginDTO.getUsername()));
        return loginDTO;
	}
	
	// Location Inventory Related Function - To be used for Checking Availability, Bike Reservation and Cancellation. 
	@RequestMapping("/availability")
	@ResponseBody
	private Bike[] checkAvailability(@RequestBody BookingDTO bookingDTO, HttpServletResponse response) {
		
		int location_id = bookingDTO.getLocation_id();
		int fromHour= bookingDTO.getFromHour();
		int toHour = bookingDTO.getToHour();
		Bike [] bikes;				
		
		bikes = LocationInventoryOps.getAvailableBikes(location_id, fromHour, toHour);
						
		return bikes;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping("/reserve")
	@ResponseBody
	private LocationInventory updateInvForReservation(@RequestBody BookingDTO bookingDTO, HttpServletResponse response)
	{
		
		int location_id = bookingDTO.getLocation_id();
		int fromHour= bookingDTO.getFromHour();
		int toHour = bookingDTO.getToHour();
		String bikeID = bookingDTO.getBike_id();
			
		LocationInventory loc = LocationInventoryOps.updateInvForReservation(location_id, fromHour, toHour, bikeID);
	
		return loc;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping("/cancel")
	@ResponseBody
	private LocationInventory updateInvForCancellation(@RequestBody BookingDTO bookingDTO, HttpServletResponse response)
	{
		
		int location_id = bookingDTO.getLocation_id();
		int fromHour= bookingDTO.getFromHour();
		int toHour = bookingDTO.getToHour();
		String bikeID = bookingDTO.getBike_id();
		
		LocationInventory loc = LocationInventoryOps.updateInvForCancellation(location_id, fromHour, toHour, bikeID);
	
		return loc;
	}
	
}

		
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* GENERAL PURPOSE FUNCTIONS - PLEASE DON'T REMOVE
	
	
	//General Purpose Function to Load Location DataBaste - Please don't remove
	@RequestMapping("/loadDatabase")
	@ResponseBody
	private void loadTable() {
		
		LocationInvetoryOperations.loadDatabase();
	
	
	}
	
	//General Purpose Function to test Location Inventory - Please don't remove
	@RequestMapping("/getLocation")
	@ResponseBody
	private String[] getLocaiton() {
	
		LocationInventoryInterface lop = new LocationInvetoryOperations();
		
			
	//	LocationInventory loc = new LocationInventory();
		String [] hour = null;
		
		try {
			hour = lop.getInvForAnHour(900, 5);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//LocationInvetoryOperations.loadDatabase();
	
		return hour;
	
	}
	
	
	//General Purpose Function to Load Bikes - Please don't remove
	@RequestMapping("/addBikes")
	@ResponseBody
	private void addBikes() {
	
		Bike bike = new Bike();
		BikeOperationsImpl impl = new BikeOperationsImpl();
		
		for(int i= 900; i<=910; i++)
		{
			bike.setBike_id(""+i);
			bike.setBikename("A-One");
			bike.setPrice(""+(i*10));
			bike.setStatus("Available");
			bike.setType("Classic");
			impl.addBike(bike);
*/
	