<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link="stylesheet"href="Views/bootstrap.min.css">
<scriptsrc="Components/jquery-3.2.1.min.js"></script>
<scriptsrc="Components/main.js"></script>
<title>Hospital Management</title>
</head>
<body>
<divclass="container">
  <divclass="row">
     <divclass="col-8">
              <h1 class="m-3">Hospital Management</h1>   
              
              
      <form id="formHospital" name="fromHospital"  method="post" action="Index.jsp">
            HosRegNo:
         <input id="HosRegNo" name="HosRegNo" type="text" class="form-control form-control-sm">
         <br> 
            HosName:
         <input id="hosName" name="HosName" type="text" class="form-control form-control-sm">
         <br>
            hosAddress:
         <input id="hosAddress""name="hosAddress" type="text"class="form-control form-control-sm">
            <br> 
            hosPhone: 
         <input id="hosPhone" name="hosPhone" type="text" class="form-control form-control-sm">
            <br>
             hosEmail: 
         <input id="hosEmail" name="hosEmail" type="text" class="form-control form-control-sm">
            <br>
             departments: 
         <input id="Departments" name="Departments" type="text" class="form-control form-control-sm">
            <br>
           
           
            <input id="btnSave"name="btnSave"type="button"value="Save"class="btn btn-primary">
            
            <input type="hidden"id="hidItemIDSave"name="hidItemIDSave"value="">" 
</body>
</html>