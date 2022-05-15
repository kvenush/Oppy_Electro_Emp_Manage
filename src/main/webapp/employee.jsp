<%@ page import="com.Employee"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/item.js"></script>
</head>
<body>
	<div class = "container">
		<div class = "row">
				<div class = "col-6">
					<h1>Employee Management</h1>
	
	<form id="formEmp" name="formEmp" method="post" action="employee.jsp">
		 employee ID:
		 <input id="empId" name="empId" type="text"
 						class="form-control form-control-sm">
 						
		<br> employee name:
		<input id="eName" name="eName" type="text"
 						class="form-control form-control-sm">
 						
		<br> position:
		<input id="position" name="position" type="text"
 						class="form-control form-control-sm">
 						
 						
		<br> salary:
		<input id="salary" name="salary" type="text"
						 class="form-control form-control-sm">
						 
		<br> employee Type:
		<input id="empType" name="empType" type="text"
						 class="form-control form-control-sm">
						 
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save"
						 class="btn btn-primary">
						 
		<input type="hidden" id="hidEmpIdSave" name="hidEmpIdSave" value="">
	</form>
	
	<br/>
	<!-- Show output -->

	<div id= "alertSuccess" class="alert alert-success"></div>
 	<div id = "alertError" class="alert alert-danger"></div>
	
	<br>
	<div id ="divEmpGrid">
		<%
			 Employee empObj = new Employee(); 
	 		 out.print(empObj.readEmp()); 
		%>
    </div>

   </div> 
  </div>
  </div>
  


</body>
</html> 