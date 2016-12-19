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
import edu.sjsu.db.CaregiverSkills;
import edu.sjsu.db.CaregiverSchedule;

import java.util.ArrayList;
import org.json.simple.JSONArray;

@Path("/")

public class LoginService {
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String inputData) {
		String json = "";
		String email="";
		String pwd="";
		String outputJson = "";
		Gson gsonObj = new Gson();
		
		try {
			// very important, dont delete
			outputJson = inputData.replaceAll("\"", "\\\"");
			Wrapper temp = gsonObj.fromJson(outputJson, Wrapper.class);
			
			
			String result = "outputJson: " + outputJson;
			User user = Dao.getUser(temp.email, temp.password);
			json = gsonObj.toJson(user);
			StringBuilder sb = new StringBuilder();
			
			sb.append("USER INFO::"+json + "\n");
			sb.append("FROM TRY PARSING::: ");
			sb.append(outputJson);
			sb.append("\n");
	
			sb.append("From Wrapper:::"+temp.email+" pwd:::" +temp.password+"\n");

			json = sb.toString();
			
			if(user != null){
				json = "{ \"status\": \"ok\"}";
			}
			else{
				json =  "{ \"status\": \"notfound\"}";
			}
		} catch (Exception e) {

			json = "{\"status\":\" Unable to insert \"}";
		}
		return Response.status(200).entity(json).build();
	}
	
    @GET
	@Path("getAllUsers")
	public Response getAllUsers() {
		String json = "";
        ArrayList<User> listUsers = new ArrayList<>();
		try {
			listUsers = Dao.getAllUsers();
            json = "" + listUsers.size();
            json = new Gson().toJson(listUsers);
            
		} catch (Exception e) {
			e.printStackTrace();
			json = "User not found!";
		}
		return Response.status(200).entity(json).build();
	}
    
	@GET
	@Path("getUser/{email}/{pwd}")
	public Response login(@PathParam("email") String email, @PathParam("pwd") String pwd) {
		String json = "";
		try {
			User user = Dao.getUser(email, pwd);
			json = user.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			json = "User not found!";
		}
		return Response.status(200).entity(json).build();
	}
	
	@GET
	@Path("getUserSkills/{uID}")
	public Response skills(@PathParam("uID") int uID) {
		String json = "";
		try {
			CaregiverSkills skills = Dao.getCaregiverSkills(uID);
			json = skills.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			json = "User not found!";
		}
		return Response.status(200).entity(json).build();
	}
    
    @GET
	@Path("getUserSchedule/{uID}")
	public Response schedule(@PathParam("uID") int uID) {
		String json = "";
		try {
			CaregiverSchedule schedule = Dao.getCaregiverSchedule(uID);
			json = schedule.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			json = "User not found!";
		}
		return Response.status(200).entity(json).build();
	}
	
	public class Wrapper{
		@SerializedName("email")
		String email;
		@SerializedName("pwd")
		String password;
	}
}
