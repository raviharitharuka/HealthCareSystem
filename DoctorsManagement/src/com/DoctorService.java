package com;

import java.sql.Date;
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

import model.Doctor;

@Path("/Doctors")
public class DoctorService {
	
	Doctor docObj = new Doctor();
	
	@GET  
	@Path("/")  
	@Produces(MediaType.TEXT_HTML)  
	public String readDoctorDetails(){   
		
		return docObj.readDoctorDetails();  
		
	}
		
	@POST 
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String AddDoctor(@FormParam("dname") String dname, @FormParam("dept") String dept, @FormParam("bday") Date bday, @FormParam("phoneno") String phoneno, @FormParam("address") String address) {  
		
		String output = docObj.AddDoctor(dname, dept, bday, phoneno, address);  
		return output; 
		
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateDoctor(String doctorData) {    
		
		JsonObject doctorObject = new JsonParser().parse(doctorData).getAsJsonObject(); 
		
		
		String did = doctorObject.get("did").getAsString();  
		String dname = doctorObject.get("dname").getAsString();
		String dept = doctorObject.get("dept").getAsString();
		
		String bdate = doctorObject.get("bday").getAsString();
		Date bday = Date.valueOf(bdate);
	
		String phoneno = doctorObject.get("phoneno").getAsString();  
		String address = doctorObject.get("address").getAsString(); 
		 
		String output = docObj.updateDoctor(did, dname, dept, bday, phoneno, address); 
		 
		return output; 
		
	}
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteDoctor(String doctorData) {  
		
		Document doc = Jsoup.parse(doctorData, "", Parser.xmlParser());     
		String did = doc.select("did").text(); 
		 
		String output = docObj.deleteDoctor(did); 
		 
		return output; 
		
	} 

}
