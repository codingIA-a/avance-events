<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>
  
  <h1>Hola <c:out value="${user.firstName}" /> </h1>
  <a href="/logout">Cerrar Sesión</a>
  <h4>Here are some of the events in your state:</h4>
		<table class="table">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">Name</th>
		      <th scope="col">Date</th>
		      <th scope="col">Location</th>
		      <th scope="col">Host</th>
		       <th scope="col">Action/Status</th>
		    </tr>
		  </thead>


      <!--Crear eventos-->
</body>
</html>