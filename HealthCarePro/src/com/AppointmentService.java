package com;

import model.Appointment;

//For REST Service 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Appointment")
public class AppointmentService {

	Appointment AppObj = new Appointment();
	
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)

		public String read() {
			return AppObj.read();
		}

		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String insert 
		       (@FormParam("app_no") String app_no, 
				@FormParam("date_time") String date_time,
				@FormParam("Hos_no") String Hos_no, 
				@FormParam("Doc_no") String Doc_no)
		{
			String output =AppObj.insert(app_no,date_time,Hos_no,Doc_no);
			return output;

		}

		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		
		public String update(String AppData)
		{  
			//Convert the input string to a JSON object
			
			JsonObject AppObject = new JsonParser().parse(AppData).getAsJsonObject(); 
			 
			 //Read the values from the JSON object 
			String app_no = AppObject.get("app_no").getAsString();  
			String date_time = AppObject.get("date_time").getAsString();  
			String Hos_no= AppObject.get("Hos_no").getAsString(); 
			String Doc_no = AppObject.get("Doc_no").getAsString();  
			 
			String output = AppObj.update(app_no,date_time,Hos_no,Doc_no); 
			 
			 return output; 
	    } 
		
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String delete(String AppData) 
		{ 
			//Convert the input string to an XML document 
			Document doc = Jsoup.parse(AppData, "", Parser.xmlParser());    
			
			//Read the value from the element <app_no>  
			
			String app_no = doc.select("app_no").text(); 
			 
			String output = AppObj.delete(app_no); 
			 
			 return output; 
			 
		} 
		
}
