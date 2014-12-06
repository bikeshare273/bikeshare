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

import java.util.Calendar;
import java.util.Date;

import resources.User;

import java.util.ArrayList;
import java.util.HashMap;

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

import DTO.LoginDTO;
import DTO.UserDTO;
import bikeshareimpl.AuthInterfaceImpl;
import bikeshareimpl.UserOperationsImpl;
import bikeshareinterfaces.AuthInterface;
import bikeshareinterfaces.UserOperationInterface;

@Component
@EnableAutoConfiguration
@RestController
@RequestMapping("/api/v1/*")
public class BikeShareController  extends WebMvcConfigurerAdapter{

	UserOperationInterface userOperationInterface = new UserOperationsImpl();
	AuthInterface authInterface = new AuthInterfaceImpl();
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
	
}
