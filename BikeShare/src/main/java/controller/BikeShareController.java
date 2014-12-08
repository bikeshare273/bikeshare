package controller;

import bikeshareimpl.TransactionsImpl;
import bikeshareinterfaces.TransactionsInterface;
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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import resources.Bike;
import resources.SmsNotifyUser;
import resources.Transactions;
import resources.User;
import scala.Equals;

import java.util.ArrayList;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import javax.servlet.annotation.WebFilter;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import DTO.UserDTO;
import bikeshareimpl.BikeOperationsImpl;
import bikeshareimpl.UserOperationsImpl;
import bikeshareinterfaces.BikeOperationsInterface;
import bikeshareinterfaces.UserOperationInterface;
import util.SendSMSNotifications;

@Component
@EnableAutoConfiguration
@RestController
@RequestMapping("/api/v1/*")
public class BikeShareController {

	UserOperationInterface userOperationInterface = new UserOperationsImpl();
	BikeOperationsInterface bikeOperationInterface = new BikeOperationsImpl();
    TransactionsInterface transactionsInterface = new TransactionsImpl();
		
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


// ----------------------------- Bike Resource Operations -------------------------

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/bikes", method = RequestMethod.POST)
	@ResponseBody
	public void addBike(@Valid @RequestBody Bike bike)
	{
		bikeOperationInterface.addBike(bike);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/bikes/{bike_id}", method = RequestMethod.GET)
	@ResponseBody
	public Bike getBike(@PathVariable String bike_id)
	{
		return bikeOperationInterface.getBike(bike_id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/bikes/AllBikes", method = RequestMethod.GET)
	@ResponseBody
	public List<Bike> getAllBikes()
	{
		return bikeOperationInterface.getAllBikes();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/bikes/bikestatus/reserve/{bike_id}", method = RequestMethod.PUT)
	@ResponseBody
	public void updateBikeStatusToReserved(@PathVariable String bike_id)
	{
		bikeOperationInterface.updateBikeStatusToReserved(bike_id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/bikes/bikestatus/available/{bike_id}", method = RequestMethod.PUT)
	@ResponseBody
	public void updateBikeStatusToAvailable(@PathVariable String bike_id)
	{
		bikeOperationInterface.updateBikeStatusToAvailable(bike_id);
	}


// ----------------------------- SMS Notifications Operations -------------------------

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/notifyUserBySms/OnReservation", method = RequestMethod.GET)
    @ResponseBody
    public void sendSMSNotification(@Valid @RequestBody SmsNotifyUser smsNotifyUser)
    {
        SendSMSNotifications.sendSMSOnReservation(smsNotifyUser.getToPhoneNumber(),smsNotifyUser.getReceiver());
    }

// ----------------------------- Transaction Resource Operations -------------------------

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    @ResponseBody
    public void saveTransaction(@Valid @RequestBody Transactions transactions)
    {
        transactionsInterface.addTransaction(transactions);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/transactions/{transaction_id}", method = RequestMethod.GET)
    @ResponseBody
    public Transactions getSpecificTransaction(@PathVariable int transaction_id)
    {
        return transactionsInterface.getTransaction(transaction_id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/transactions/AllTransactions", method = RequestMethod.GET)
    @ResponseBody
    public List<Transactions> getAllTransactions()
    {
        return transactionsInterface.getAllTransactions();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/transactions/cancel/{transaction_id}", method = RequestMethod.GET)
    @ResponseBody
    public void cancelTransaction(@PathVariable int transaction_id)
    {
        transactionsInterface.updateTransaction(transaction_id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/transactions/AllTransactions/{user_id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Transactions> getAllUserTransactions(@PathVariable int user_id)
    {
        return transactionsInterface.getUserTransactions(user_id);
    }

}