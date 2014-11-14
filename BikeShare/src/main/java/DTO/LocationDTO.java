package DTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "Locations")
@JsonInclude(Include.NON_EMPTY)
public class LocationDTO {
	
	@Id
	private int locationid;
	
	@NotEmpty
	private double latitude;
	
	@NotEmpty
	private double longitude;
	
	@NotEmpty
	private String zipcode;
	
	@NotEmpty
	private String cityname;
	
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;
	private String sunday;
	
}
