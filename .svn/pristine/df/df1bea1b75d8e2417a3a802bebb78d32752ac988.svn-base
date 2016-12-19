package edu.sjsu.db;

import java.sql.Date;

public class User {
	private int userId;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String dob;
	private String gender;
	private String address;
	private String city;
	private String state;
	private String accType;
	private String zipCode;


    
	public User(int userId, String email, String password, String firstName, String lastName,
			String dob, String gender, String address, String city, String state, String zipCode,
			String accType) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.accType = accType;
	}
    
    
	
	public String toJson() {
		String json = "{\"userId\":\"" + userId + "\",\"email\":\"" + email + "\",\"password\":\"" +
				password + "\",\"firstName\":\"" + firstName + "\",\"lastName\":\"" + lastName +
				"\",\"dob\":\"" + dob.toString() + "\",\"gender\":\"" + gender + "\",\"address\":\""
				+ address + "\",\"city\":\"" + city + "\",\"state\":\"" + state + 
				"\",\"zipCode\":\"" + zipCode + "\",\"accountType\":\"" + accType +
				"\"}";
		// "\",\"verifiedEmail\":\"" + String.valueOf(verifiedEmail) +
		// "\",\"verifiedCaregiver\":\"" + String.valueOf(verifiedCaregiver) + 
		return json;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getDob() {
		return dob;
	}
	
	public String getGender() {
		return gender;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getZipcode() {
		return zipCode;
	}
	
	public String getAccountType() {
		return accType;
	}
	
//	public boolean verifiedEmail() {
//		return verifiedEmail;
//	}
//	
//	public boolean verifiedCaregiver() {
//		return verifiedCaregiver;
//	}
}
