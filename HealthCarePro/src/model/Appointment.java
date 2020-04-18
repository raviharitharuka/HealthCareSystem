package model;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Appointment {
	
			// A common method to connect to the DB
			public Connection connect() {
				
				Connection con = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");

					// Provide the correct details: DBServer/DBName, user name, password
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");

					System.out.print("Successfully connected");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return con;
			}

			public String insert(String app_no,String date_time,String Hos_no,String Doc_no) {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for inserting.";
					}
					// create a prepared statement
					String query = " insert into appointment(`app_no`,`date_time`,`Hos_no`,`Doc_no`)"
							+ " values (?,?,?,?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					//preparedStmt.setInt(1, 0);
					preparedStmt.setString(1, app_no);
					preparedStmt.setString(2, date_time);      
					preparedStmt.setString(3, Hos_no);
					preparedStmt.setString(4, Doc_no);


		//execute the statement
					preparedStmt.execute();
					con.close();
					output = "Inserted successfully";
				} catch (Exception e) {
					output = "Error while Adding Appointment.";
					System.err.println(e.getMessage());
				}
				return output;
			}

			public String read() {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for reading.";
					}
		// Prepare the html table to be displayed
					output = "<table border=\"1\"><tr><th>Appointment No</th><th>Date and Time</th><th>Hospital Name</th><th>Doctor Name</th><th>Update</th><th>Remove</th></tr>";
					String query = "select * from appointment";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
					while (rs.next()) {
						String app_no = Integer.toString(rs.getInt("app_no"));
						String date_time = rs.getString("date_time");
						String Hos_no = rs.getString("Hos_no");
						String Doc_no = rs.getString("Doc_no");
						
		// Add into the html table
						output += "<tr><td>" + app_no + "</td>";
						output += "<td>" + date_time + "</td>";
						output += "<td>" + Hos_no + "</td>";
						output += "<td>" + Doc_no + "</td>";
	
		// buttons
						output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
								+ "<td><form method=\"post\" action=\"items.jsp\">"
								+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
								+ "<input name=\"itemID\" type=\"hidden\" value=\"" + app_no + "\">" + "</form></td></tr>";
					}
					con.close();
		// Complete the html table
					output += "</table>";
				} catch (Exception e) {
					output = "Error while reading Hospitals.";
					System.err.println(e.getMessage());
				}
				return output;
			}

			public String update(String app_no,String date_time,String Hos_no,String Doc_no) {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for updating.";
					}
		// create a prepared statement
					String query = "UPDATE appointment SET app_no=?,date_time=?,Hos_no=?,Doc_no=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
					preparedStmt.setString(1, app_no);
					preparedStmt.setString(2, date_time);
					preparedStmt.setString(3, Hos_no);
					preparedStmt.setString(4, Doc_no);
		
		// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Updated successfully";
				} catch (Exception e) {
					output = "Error while updating the Appointment.";
					System.err.println(e.getMessage());
				}
				return output;
			}

			public String delete(String app_no) {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}
		// create a prepared statement
					String query = "delete from appointment where app_no=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
					preparedStmt.setInt(1, Integer.parseInt(app_no));
		// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Deleted successfully";
				} catch (Exception e) {
					output = "Error while deleting the Appointment.";
					System.err.println(e.getMessage());
				}
				return output;
			}
			
}
