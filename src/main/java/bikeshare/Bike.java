package bikeshare;

import java.util.Random;

import org.hibernate.validator.constraints.NotEmpty;

public class Bike 

{
	String bike_id;
	
	@NotEmpty(message = "Please enter 'Bike Name'.")
	private String bikename;
	
	@NotEmpty(message = "Please enter 'Bike Price'.")
	private String price;
	
	@NotEmpty(message = "Please enter 'Bike Status'.")
	private String status;
	
	@NotEmpty(message = "Please enter 'Bike Type'.")
	private  String type;

	
	public String getBike_id() {
		return bike_id;
	}

	public void setBike_id(String bike_id) {
		this.bike_id = bike_id;
	}

	public String getBikename() {
		return bikename;
	}

	public void setBikename(String bikename) {
		this.bikename = bikename;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	public String generateRandomBikeId(){
		Random random = new Random();
		return "b-"+random.nextInt(Integer.MAX_VALUE)  + 1 ;
	}
	
	public void setBike(Bike set)
	{
		this.bike_id = this.generateRandomBikeId();
		this.bikename = set.bikename;
		this.price = set.price;
		this.status = set.status;
		this.type = set.type;
	}
}
