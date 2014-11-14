package resources;

import java.io.Serializable;
import java.lang.String;

import org.joda.time.DateTime;

import java.util.HashMap;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "User")
@JsonInclude(Include.NON_EMPTY)
public class User implements Serializable{

	@Id
	private int user_id;
	
	private String email;
	
	private String phone;
	
	private String address;
	
	private String zipcode;
	
	private String credit_card_no;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCredit_card_no() {
		return credit_card_no;
	}

	public void setCredit_card_no(String credit_card_no) {
		this.credit_card_no = credit_card_no;
	}
	
	
}
