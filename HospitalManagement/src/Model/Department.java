package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Department {
	// A common method to connect to the DB
	
			public Connection connect() {
				Connection con = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");

					// Provide the correct details: DBServer/DBName, user name, password
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaresystem", "root", "");

					System.out.print("Successfully connected");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return con;
			}

			public String insertDep(String depID, String depName) {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for inserting.";
					}
					// create a prepared statement
					String query = " insert into department(`depID`,`depName`)"
							+ " values (?,?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, depName);


		//execute the statement
					preparedStmt.execute();
					con.close();
					output = "Inserted successfully";
				} catch (Exception e) {
					output = "Error while Adding Departments.";
					System.err.println(e.getMessage());
				}
				return output;
			}

			public String readDep() {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for reading.";
					}
		// Prepare the html table to be displayed
					output = "<table border=\"1\"><tr><th>Department ID</th><th>Department Name</th><th>Update</th><th>Remove</th></tr>";
					String query = "select * from department";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
					while (rs.next()) {
						String depID = Integer.toString(rs.getInt("depID"));
						String depName = rs.getString("depName");
						
						
		// Add into the html table
						output += "<tr><td>" + depID + "</td>";
						output += "<td>" + depName+ "</td>";
						
		// buttons
						output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
								+ "<td><form method=\"post\" action=\"Index.jsp\">"
								+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
								+ "<input name=\"itemID\" type=\"hidden\" value=\"" + depID + "\">" + "</form></td></tr>";
					}
					con.close();
		// Complete the html table
					output += "</table>";
				} catch (Exception e) {
					output = "Error while reading Departments.";
					System.err.println(e.getMessage());
				}
				return output;
			}

			public String updateDep(String depID, String depName) {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for updating.";
					}
		// create a prepared statement
					String query = "UPDATE department SET depName=?WHERE depID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
					
					preparedStmt.setString(1, depName);
					preparedStmt.setInt(2, Integer.parseInt(depID));
		// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Updated successfully";
				} catch (Exception e) {
					output = "Error while updating the Departments.";
					System.err.println(e.getMessage());
				}
				return output;
			}

			public String deleteDep(String depID) {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}
		// create a prepared statement
					String query = "delete from department where depID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
					preparedStmt.setInt(1, Integer.parseInt(depID));
		// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Deleted successfully";
				} catch (Exception e) {
					output = "Error while deleting the Departments.";
					System.err.println(e.getMessage());
				}
				return output;
			}
		}


