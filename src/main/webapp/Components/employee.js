$(document).ready(function()
{

	$("#alertSuccess").hide();
	$("#alertError").hide();
});


$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------

	
	 $("#alertSuccess").text("");
 	 $("#alertSuccess").hide();
 	 $("#alertError").text("");
 	 $("#alertError").hide();
 	 
 	 
   	// Form validation-------------------
  	
	var status = validateEmpForm();
	if (status != true)
	{
		 $("#alertError").text(status);
 		 $("#alertError").show();
 		 
         return;
    }
 
	// If valid------------------------
	
	
	var type = ($("#hidEmpIdSave").val() == "") ? "POST" : "PUT";

	 $.ajax(
 	 {
 		url : "EmpAPI",
 		type : type,
 		data : $("#formEmp").serialize(),
 		dataType : "text",
	    complete : function(response, status)
        {
   
      			onEmpSaveComplete(response.responseText, status);
	    }
	    
     });
     
});
function onEmpSaveComplete(response, status)
{
	if (status == "success")
	{
		 var resultSet = JSON.parse(response);
		 
	 	 if (resultSet.status.trim() == "success")
		 {
 				$("#alertSuccess").text("Successfully saved.");
		    	$("#alertSuccess").show();
 				$("#divItemsGrid").html(resultSet.data);
 			
 	 	  } else if (resultSet.status.trim() == "error")
 	 	  {
 	 
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
		  }
		  
    } else if (status == "error")
    {
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 			
 	} else
 	{
 			$("#alertError").text("Unknown error while saving..");
 			$("#alertError").show();
    } 

 	$("#hidEmpIdSave").val("");
	 $("#formEmp")[0].reset();
}
$(document).on("click", ".btnUpdate", function(event)
{
		var id = event.target.id;
		$("#hidEmpIdSave").val(id.substring(0, id.length-1));
 		$("#eName").val($(this).closest("tr").find('td:eq(0)').text());
 		$("#position").val($(this).closest("tr").find('td:eq(1)').text());
 		$("#salary").val($(this).closest("tr").find('td:eq(2)').text());
 		$("#empType").val($(this).closest("tr").find('td:eq(3)').text());
});
$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
 	{
 		url : "EmpAPI",
 		type : "DELETE",
	    data : "empId=" + $(this).data("empid"),
 		dataType : "text",
 		complete : function(response, status)
		{
			 onEmpDeleteComplete(response.responseText, status);
 		}
	 });
});



function onEmpDeleteComplete(response, status)
{
	if (status == "success")
    {
 			var resultSet = JSON.parse(response);
 			
		   if (resultSet.status.trim() == "success")
 		   {
 		   
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				
			    $("#divEmpGrid").html(resultSet.data);
			    
			    setTimeout( (function(){alert(43)}, 1000));
 			} else if (resultSet.status.trim() == "error")
 			{
				 $("#alertError").text(resultSet.data);
 				 $("#alertError").show();
		    }
 	} else if (status == "error")
    {
		 $("#alertError").text("Error while deleting.");
 		 $("#alertError").show();
    } else
    {
 		$("#alertError").text("Unknown error while deleting..");
 		$("#alertError").show();
 	}
}
function validateEmpForm()
{
	// Name
	if ($("#eName").val().trim() == "")
 	{
		 return "Insert emp Name.";
    }
    
    
	// Position
	if ($("#position").val().trim() == "")
    {
		 return "Insert position.";
 	} 
 	

	// Salary-------------------------------
	if ($("#salary").val().trim() == "")
    {
 		return "Insert Item Salary.";
 	}
 	
 	
	// is numerical value
	var tmpSalary = $("#empSalary").val().trim();
	if (!$.isNumeric(tmpSalary))
 	{
 		return "Insert a numerical value for Salary.";
 	}
 	
 	
	// convert to decimal price
 	$("#empSalary").val(parseFloat(tmpSalary).toFixed(2));
 	
 	
	// EMP TYPE------------------------
	if ($("#empType").val().trim() == "")
   {
		 return "Insert emp type.";
   }
   return true;
}
