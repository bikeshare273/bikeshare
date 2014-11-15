package bikeshare;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1")
public class LocationController 
{
	@Autowired
	private LocationRepository locRepo;
	
		//Post method
		@RequestMapping(value="/location/", method=RequestMethod.POST)
		//@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity<Location> Location(@Valid @RequestBody Location set)
		{
				Location location_object = new Location();
				location_object.setLocation(set);
				locRepo.save(location_object);  //Saving Location in the repository
				return new ResponseEntity<Location>(location_object, HttpStatus.CREATED);
		}
		
		//Get Method
		@RequestMapping(value="/location/{location_id}", method=RequestMethod.GET)
		
		public ResponseEntity<Location> getusers(@PathVariable String location_id)
		{ 
			
			Location location_object = locRepo.findById(location_id);
			if(location_object != null)
			{
		
				return new ResponseEntity<Location>(location_object, HttpStatus.OK);
			}
			return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
		}
		
		//Put method
		@RequestMapping(value="/location/{location_id}", method=RequestMethod.PUT)
		//@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity<Location> updateuser(@PathVariable String location_id,@Valid @RequestBody Location set)
		{
			
			Location location_object = locRepo.findById(location_id);
			
			if(location_object != null)
			{
			location_object.putLocation(set, location_id);
			locRepo.save(location_object);
			return new ResponseEntity<Location>(location_object, HttpStatus.CREATED);
			
			}
			return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
			
		}
		
		
		@ExceptionHandler({MethodArgumentNotValidException.class, ServletRequestBindingException.class})
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public ModelMap handleException(MethodArgumentNotValidException error)
		{
			List<FieldError> errors = error.getBindingResult().getFieldErrors();
			ModelMap errorMap = new ModelMap();
			
			for(FieldError e : errors)
			{
				errorMap.addAttribute(e.getField(), e.getDefaultMessage());
			}
			return errorMap;
		}
		
}
