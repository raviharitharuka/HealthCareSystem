package com;

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

import Model.Department;

@Path("/Departments")
public class DepartmentService {

	Department DepObj = new Department();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)

	public String readDep() {
		return DepObj.readDep();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public String insertItem
	    (@FormParam("depID") String depID,
	     @FormParam("depName") String depName)
	{
		String output = DepObj.insertDep(depID,depName);
		return output;

	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateDep(String DepData) {
		// Convert the input string to a JSON object

		JsonObject DepObject = new JsonParser().parse(DepData).getAsJsonObject();

		// Read the values from the JSON object
		String depID = DepObject.get("depID").getAsString();
		String depName = DepObject.get("depName").getAsString();

		String output = DepObj.updateDep(depID, depName);

		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)

	public String deleteDep(String DepData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(DepData, "", Parser.xmlParser());

		// Read the value from the element <DepID>

		String DepID = doc.select("DepID").text();

		String output = DepObj.deleteDep(DepID);

		return output;

	}

}
