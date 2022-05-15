package com;
import java.sql.*;

	
	

	public class Employee {

		public Connection connect()
		{
		 Connection con = null;

		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");
			 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee\", \"root\", \"wpcak6789");
			 
			 
		 //For testing
		 System.out.print("Successfully connected");
		 }
		 
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }

		 return con;
		}
		
		public String readEmp()
		{ 
				String output = "";
				
				try
				{ 
						Connection con = connect();
						
						if (con == null) 
						{ 
							return "Error while connecting to the database for reading."; 
						} 
					 
			 // Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>Employee ID</th>" 
								 +"<th>Item Name</th><th>Employee Name</th>"
								 + "<th>Position</th>" 
								 + "<th>Salary</th>"
								 + "<th>Employee Type</th>"
								 + "<th>Update</th><th>Remove</th></tr>"; 
					 
					 String query = "select * from employee"; 
					 Statement stmt = con.createStatement(); 
					 ResultSet rs = stmt.executeQuery(query); 
					 
			 // iterate through the rows in the result set
					 while (rs.next()) 
					 { 
						 String empId = Integer.toString(rs.getInt("empId")); 
						 String eName = rs.getString("eName"); 
						 String position = rs.getString("position"); 
						 String salary = rs.getString("salary"); 
						 String empType = rs.getString("empType"); 
						 
			 // Add a row into the html table
						 output += "<tr><td><input id ='hidempIdUpdate' name ='hidempIdUpdate' type='hidden' value='" + empId + " '>"	+ eName + "</td>";
						
						 output += "<td>" + position + "</td>"; 
						 output += "<td>" + salary + "</td>"; 
						 output += "<td>" + empType + "</td>";
			 // buttons
						 output += "<td><input name='btnUpdate' id ='" + empId + " ' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td><td>"
						 		+ "<input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-rechearcherid='"+ empId + " '>" + "</td></tr>";  
					 } 
					 con.close(); 
					 
			 // Complete the html table
					 output += "</table>"; 
					 
					 
					 
			 } 
				catch (Exception e) 
				{ 
					output = "Error while reading the items."; 
					System.err.println(e.getMessage()); 
				} 
				return output; 
		}

		public String insertEmp(String empId, String eName,String position, String salary, String empType)
	    {
				 String output = "";
				 
				 try
				 {
					 Connection con = connect();
					 
					 if (con == null)
					 {
						 return "Error while connecting to the database for inserting.";
					 }
					 
					 // create a prepared statement
					 String query = " insert into employee(`empId`,`eName`,`position`,`salary`,`empType`)"+ " values (?, ?, ?, ?, ?)";
				 
				 
					 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, eName);
					 preparedStmt.setString(3, position);
					 preparedStmt.setDouble(4, Double.parseDouble(salary));
					 preparedStmt.setString(5, empType);
				 
				 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newEmp = readEmp();
					 output = "{\"status\":\"success\", \"data\": \"" + newEmp + "\"}";
				 }
				 catch (Exception e)
				 {
					 output = "{\"status\":\"error\", \"data\":\"Error while inserting the employee.\"}";
					 System.err.println(e.getMessage());
					 
				 }
				 return output;
				 
				 
	    
	     
			
	    }
		public String updateEmp(int empId,String eName,String position,String salary,String empType)
		{ 
				String output = ""; 
				try
				 { 
					 Connection con = connect(); 
					 if (con == null) 
					 { 
						 return "Error while connecting to the database for updating."; 
					 } 
				 // create a prepared statement
					 String query = "update items set  eName = ?,  position = ?, salary = ?, empType = ? where empId = ?"; 
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 
					 // binding values
					 preparedStmt.setString(1, eName);
					 preparedStmt.setString(2, position);
					 preparedStmt.setDouble(3, Double.parseDouble(salary));
					 preparedStmt.setString(4, empType);
					 preparedStmt.setInt(5, empId);


					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 
					 String newEmp = readEmp();
					 output = "{\"status\":\"success\", \"data\": \"" +newEmp + "\"}";
					
				 } 
				catch (Exception e) 
				 { 
					output = "{\"status\":\"error\", \"data\":\"Error while updating the employee.\"}";
					 System.err.println(e.getMessage()); 
				 } 
				return output; 
		}

		public String deleteEmp(String empIdData)
		{ 
				String output = ""; 
				try
			    { 
					 Connection con = connect(); 
					 if (con == null) 
					 { 
						 return "Error while connecting to the database for deleting."; 
					 } 
				 // create a prepared statement
					 String query = "delete from employee where empId=?"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(empIdData)); 

					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					
					 String newEmp = readEmp();
					 output = "{\"status\":\"success\", \"data\": \"" +
			 newEmp + "\"}";
					 
				} 
				catch (Exception e) 
				{ 
					output = "{\"status\":\"error\", \"data\": \"Error while deleting the employee.\"}"; 
					 System.err.println(e.getMessage()); 
			    } 
				return output; 
			}


		
	}
	


