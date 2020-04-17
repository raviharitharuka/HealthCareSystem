package com;
import Model.Hospital;

//For REST Service 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Hospital")
public class HospitalService {


	Hospital HosObj = new Hospital();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)

	public String readHos() {
		return HosObj.readHos();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertItem 
	       (@FormParam("hosRegNo") String hosRegNo, 
			@FormParam("hosName") String hosName,
			@FormParam("hosAddress") String hosAddress, 
			@FormParam("hosPhone") String hosPhone,
	        @FormParam("hosEmail") String hosEmail, 
			@FormParam("Departments") String Departments)
	{
		String output =HosObj.insertHos(hosRegNo, hosName, hosAddress, hosPhone,hosEmail,Departments);
		return output;

	}

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateHos(String HosData)
	{  
		//Convert the input string to a JSON object
		
		JsonObject HosObject = new JsonParser().parse(HosData).getAsJsonObject(); 
		 
		 //Read the values from the JSON object 
		String hosID = HosObject.get("hosID").getAsString();  
		String hosRegNo = HosObject.get("hosRegNo").getAsString();  
		String hosName= HosObject.get("hosName").getAsString(); 
		String hosAddress = HosObject.get("hosAddress").getAsString();  
		String hosPhone = HosObject.get("hosPhone").getAsString(); 
		String hosEmail = HosObject.get("hosEmail").getAsString();  
		String Departments = HosObject.get("Departments").getAsString(); 
		 
		String output = HosObj.updateHos(hosID,hosRegNo, hosName, hosAddress, hosPhone,hosEmail,Departments); 
		 
		 return output; 
    } 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteHos(String HosData) 
	{ 
		//Convert the input string to an XML document 
		Document doc = Jsoup.parse(HosData, "", Parser.xmlParser());    
		
		//Read the value from the element <hosID>  
		
		String hosID = doc.select("hosID").text(); 
		 
		String output = HosObj.deleteHos(hosID); 
		 
		 return output; 
		 
	} 
	
}


