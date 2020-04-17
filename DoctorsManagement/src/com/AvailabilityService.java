package com;

import java.sql.Time;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Availability;

@Path("/Availability")
public class AvailabilityService {
	
	Availability avaObj = new Availability();
	
	@GET  
	@Path("/")  
	@Produces(MediaType.TEXT_HTML)  
	public String readAvailabilityDetails(){   
		
		return avaObj.readAvailabilityDetails();  
		
	}
	@POST 
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String AddAvailability(@FormParam("dname") String dname, @FormParam("day") String day, @FormParam("time") Time time) {  
		
		String output = avaObj.AddAvailability(dname, day, time);  
		return output; 
		
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateAvailability(String availabilityData) {    
		
		JsonObject availabilityObject = new JsonParser().parse(availabilityData).getAsJsonObject(); 
		
		
		String daid = availabilityObject.get("daid").getAsString();
		String dname = availabilityObject.get("dname").getAsString(); 
		String day = availabilityObject.get("day").getAsString(); 
		
		String timee = availabilityObject.get("time").getAsString();
		Time time = Time.valueOf(timee);
		 
		String output = avaObj.updateAvailability(daid, dname, day, time); 
		 
		return output; 
		
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteAvailability(String availabilityData) {  
		
		Document doc = Jsoup.parse(availabilityData, "", Parser.xmlParser());     
		String daid = doc.select("daid").text(); 
		 
		String output = avaObj.deleteAvailability(daid); 
		 
		return output; 
		
	}
	

}
