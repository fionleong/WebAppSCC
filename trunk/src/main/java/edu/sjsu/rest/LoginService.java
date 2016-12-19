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
			//System.out.println("FROM TRY PARSING::: "+ outputJson);
	
			sb.append("From Wrapper:::"+temp.email+" pwd:::" +temp.password+"\n");
			//System.out.println("From Wrapper:::" + temp.email);
			//System.out.println("From Wrapper:::" + temp.password);
			json = sb.toString();
			
			if(user != null){
				json = "{ \"status\": \"ok\"}";
			}
			else{
				json =  "{ \"status\": \"notfound\"}";
			}
		} catch (Exception e) {
			//e.printStackTrace();
			//json = e.getStackTrace().toString();
			//json = "User not found!";
//			StringWriter writer = new StringWriter();
//			PrintWriter printWriter = new PrintWriter( writer );
//			e.printStackTrace(printWriter);
//			
//			printWriter.flush();
//
//			json = writer.toString();
			json = "{\"status\":\" Unable to insert \"}";
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
