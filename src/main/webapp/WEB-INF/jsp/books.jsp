
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Books page</title>
</head>
<body bgcolor="#E6E6FA">
	<c:url var="removeBook" value="/books/remove" />
	<c:url var="addBook" value="/books/add" />
	<c:url var="editBook" value="/books/edit" />
	
	
	
	<div id="main_table" align="center" width="250" height="350" border="3" style="
    margin-top: 70px">
	
	
	<td>
	<form:form action="${addBook}" commandName="book" id="bookForm">
	
		<form:input path="theName" /> 
		<form:label path="theName"/> 
		
		
		<form:input path="count"/>
		<form:label path="count"/>
		
		<form:input path="price"/>
		<form:label path="price"/>
		
		
		
		
		<br/>
		<tr> <input type="submit" value="Add Book"> </tr>
	</form:form>
	</td>
	
	
	<td>
	<form:form action="${removeBook}" commandName="book">
		
			<form:label path="bookId"></form:label>
			<form:input path="bookId" />
			<br/>
			<tr>
				<input type="submit" value="Remove Book">
			</tr>
		
	</form:form>
	</td>
	<td>
	<form:form action="${editBook}" commandName="book">
		
			<form:label path="bookId"></form:label> 
			
			<tr> 
			
			<td> <form:label path="bookId"></form:label></td>
			<td><form:input path="bookId" /></td> 
			</tr>
			<br/>
			<tr>
			<td> <form:label path="theName"></form:label> </td> <td> <form:input path="theName" /> </td>
			<td> <form:label path="price"></form:label> </td> <td> <form:input path="price" /> </td>
			</tr>
			<br/>
			<tr>
				<input type="submit" value="Edit Book">
			</tr>
		
	</form:form>
	</td>
	
	
	<table border="0.5" width="200" >
	<h3>Books list</h3>
	<c:if test="${!empty books}">
		<table>
			<tr>
				<th width="100">Book id</th>
				<th width="100">Book name</th>
				<th width="100">the Author</th>
				<th width="100">Book country</th>
				<th width="100">Book genre</th>
				<th width="100">Book price</th>
				<th width="100">Count</th>
				
			</tr>
			<c:forEach items="${books}" var="b">
				<tr>
					<td>${b.bookId}</td>
					<td>${b.theName}</td>
					<td>${b.author}</td>
					<td>${b.author.country}</td>
					<td>${b.genre}</td>
					<td>${b.price}</td>
					<td>${b.count}</td>
					
				</tr>
			</c:forEach>
		</table>
		</table>
	</c:if>
	</table>
	</div>
	
</body>
</html>