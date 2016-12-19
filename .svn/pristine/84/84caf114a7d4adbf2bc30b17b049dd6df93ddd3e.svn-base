//package edu.sjsu.rest;
//
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//
//@Path("/register")
//public class RegisterService {
//	
//	@GET
//	@Consumes(MediaType.TEXT_PLAIN)
//	public Response signup(String user) {
//		//ObjectMapper om = new ObjectMapper();
////			try {
////				User newUser = om.readValue(user, User.class);
////				Dao.addUser(newUser);
////				
////			} catch (IOException e) {
////				// TODO Auto-generated catch block
////				//e.printStackTrace();
////				return Response.status(500).entity("Server was not able to process your request!").build();
////			} catch (SQLException e) {
////				// TODO Auto-generated catch block
////				//e.printStackTrace();
////				return Response.status(500).entity("Unable to process user").build();
////			}
package edu.sjsu.rest;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import edu.sjsu.db.Dao;
import edu.sjsu.db.User;

@Path("/signup")
public class RegisterService {
	
    @POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response signup(String inputData) {
        String json = "";
        String firstName = "";
        String lastName = "";
        String gender = "";
        String birthday = "";
        String address = "";
        String city = "";
        String zipCode = "";
        String state = "";
		String email= "";
		String password = "";
        String accType = "";
		String outputJson = "";
		Gson gsonObj = new Gson();
		System.out.println(inputData);
		
		try {
			// very important, dont delete
			outputJson = inputData.replaceAll("\"", "\\\"");
			Wrapper temp = gsonObj.fromJson(outputJson, Wrapper.class);
			
//			String result = "birthday: " + temp.birthday;
			String result = Dao.addUser(temp.email, temp.password, temp.firstName, temp.lastName, temp.birthday, temp.gender, temp.address, temp.city, temp.state, temp.zipCode, temp.accType);
            
//			//            if(result == "Successful"){
//				json = "{ \"status\": \"ok\"}";
//			}
//			else{
//				json =  "{ \"status\": \"unable to insert\"}";
//			}
			json = "{\"status\":\"" + result + "\"}";
		} catch (Exception e) {
			//e.printStackTrace();
			//json = e.getStackTrace().toString();
			//json = "{\"status\":\" Unable to insert \"}";
			json = "{\"status\":\"" + e + "\"}";
//			StringWriter writer = new StringWriter();
//			PrintWriter printWriter = new PrintWriter( writer );
//			e.printStackTrace(printWriter);
//			
//			printWriter.flush();
//
//			json = writer.toString();
		}
		return Response.status(200).entity(json).build();
	}
	
	public class Wrapper{
        @SerializedName("firstName")
		String firstName;
		@SerializedName("lastName")
		String lastName;
        @SerializedName("gender")
		String gender;
		@SerializedName("birthday")
		String birthday;
		@SerializedName("address")
		String address;
		@SerializedName("city")
		String city;
        @SerializedName("zipCode")
		String zipCode;
        @SerializedName("state")
		String state;
		@SerializedName("email")
		String email;
		@SerializedName("password")
		String password;
		@SerializedName("accType")
		String accType;
	}
}
