package bikeshare;

import java.util.ArrayList;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Location 
{
	
	String id;
	
	@NotEmpty (message = "Required Field. Please provide the latitude.")
	private String latitude;
	
	@NotEmpty (message = "Required Field. Please provide the longitude.")
	private String longitude;
	
	@NotEmpty (message = "Required Field. Please provide the city.")
	private String city;
	
	@NotEmpty (message = "Required Field. Please provide the state.")
	private String state;
	
	@NotEmpty (message = "Required Field. Please provide the zipcode.")
	private String zipcode;

	ArrayList<Bike> bList = new ArrayList<Bike>();
	
	@JsonIgnore
	public ArrayList<Bike> getbList() {
		return bList;
	}

	public void setbList(Bike bList) {
		this.bList.add(bList);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	 
	public void setLocation(Location loc)
	{
		this.id = this.getId();
		this.latitude = loc.latitude;
		this.longitude = loc.longitude;
		this.city = loc.city;
		this.state = loc.state;
		this.zipcode = loc.zipcode;
	}
	
	public void putLocation(Location loc, String id)
	{
		this.id = id;
		this.latitude = loc.latitude;
		this.longitude = loc.longitude;
		this.city = loc.city;
		this.state = loc.state;
		this.zipcode = loc.zipcode;
	}
	
		
		
    
	
}
