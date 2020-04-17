package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

public class Availability {
	
	private Connection connect(){   		
		
		Connection con = null; 
	 
		try{     
			Class.forName("com.mysql.jdbc.Driver");           
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare", "root", "");   
		  
		}catch (Exception e){
		  
			e.printStackTrace();
		  
		} 
	  
		return con;  
		
	}
	public String AddAvailability(String day, String dname, Time time)  {   
		
		String output = ""; 
	 
		try   {    
			Connection con = connect(); 
	 
			if (con == null){
				
				return "Error while connecting to the database for inserting."; 
				
			} 
	 
			String query = "INSERT INTO `availability`(`daid`, `dname`, `day`, `time`) values (?, ?, ?, ?)"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, dname); 
			preparedStmt.setString(3, day); 
			preparedStmt.setTime(4, time); 
	 
	
			preparedStmt.execute();    
			
			con.close(); 
	 
			output = "Inserted successfully";   
			
		}catch (Exception e){    
			
			output = "Error while inserting the Availability Details.";    
			System.err.println(e.getMessage());   
			
		} 
	 
	  return output;  
	  
	}
	public String readAvailabilityDetails(){   
		
		String output = ""; 
	
		try{    
			Connection con = connect(); 
	   
			if (con == null){
				
				return "Error while connecting to the database for reading."; 
				
			} 
			
	 
			output = "<table border=\"1\">"
					+ "<tr>"
					+"<th>Doctor's Name</th>"
					+ "<th>Day</th>"
					+ "<th>Time</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th>"
					+ "</tr>"; 
	 
			String query = "select * from availability";    
			Statement stmt = con.createStatement();    
			ResultSet rs = stmt.executeQuery(query); 
	 
			while (rs.next()){     
				
				String daid = Integer.toString(rs.getInt("daid")); 
				String dname = rs.getString("dname"); 
				String day = rs.getString("day");     
				Time time = rs.getTime("time");
			
				output += "<tr><td>" + dname + "</td>";
				output += "<td>" + day + "</td>";     
				output += "<td>" + time + "</td>";  
		 
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"Availabilty.jsp\">"      
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"      
						+ "<input name=\"daid\" type=\"hidden\" value=\"" + daid + "\">" 
						+ "</form></td>"
						+ "</tr>";    
			} 
	 
			con.close(); 
			output += "</table>";   
			
		}catch (Exception e){    
			
			output = "Error while reading the Availability Details.";    
			System.err.println(e.getMessage());   
			
		} 
	 
	  return output;  
	  
	}
	public String updateAvailability(String daid, String dname, String day, Time time){   
		
		String output = ""; 
	 
		try{    
			Connection con = connect(); 
	 
			if (con == null){
				
				return "Error while connecting to the database for updating."; 
				
			} 
	 
			String query = "UPDATE availability SET dname =?, day=?, time=? WHERE daid=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 

			
			preparedStmt.setString(1, dname);
			preparedStmt.setString(2, day);
			preparedStmt.setTime(3, time);   
			preparedStmt.setInt(4, Integer.parseInt(daid)); 
	 
			preparedStmt.execute();    
			con.close(); 
	 
			output = "Updated successfully";   
			
		}catch (Exception e){    
			
			output = "Error while updating the Availability.";    
			System.err.println(e.getMessage());   
			
		} 
	 
	  return output;  
	  
	}
	public String deleteAvailability(String daid){   
		
		String output = ""; 
	 
		try{    
			
			Connection con = connect(); 
	 
			if (con == null){
				
				return "Error while connecting to the database for deleting."; 
				
			} 
	 
			String query = "delete from availability where daid=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			preparedStmt.setInt(1, Integer.parseInt(daid)); 
	 
			preparedStmt.execute();    
			con.close(); 
	 
			output = "Deleted successfully";   
			
		}catch (Exception e){    
			
			output = "Error while deleting the Availability Details.";    
			System.err.println(e.getMessage());   
			
		} 
	 
	  return output;  
	  
	} 


}
