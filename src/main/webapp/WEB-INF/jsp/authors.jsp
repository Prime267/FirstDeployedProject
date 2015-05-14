
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Authors page</title>
</head>
<body bgcolor="#E6E6FA">
	<c:url var="removeAuthor" value="/authors/remove" />
	<c:url var="addAuthor" value="/authors/add" />
	<c:url var="editAuthor" value="/authors/edit" />
	<c:url var="refreshAuthor" value="/authors/refresh" />
	
	
	
	
	<div id="main_table" align="center" width="250" height="350" border="3" style="
    margin-top: 70px">
	
	
	<td>
	<form:form action="${addAuthor}" commandName="author" >
		
		<tr>
		<form:input path="authorName"/> 
		<form:label path="authorName"/> 
		
		<form:input path="country"/>
		<form:label path="country"/>
		</tr>
		
		
		<br/>
		<tr> <input type="submit" value="Add Author"> </tr>
	</form:form>
	</td>
	
	
	<td>
	<form:form action="${removeAuthor}" commandName="author">
		
			<form:label path="authorId"></form:label>
			<form:input path="authorId" ></form:input>
			<br/>
			<tr>
				<input type="submit" value="Remove Author">
			</tr>
		
	</form:form>
	</td>
	<td>
	<form:form action="${editAuthor}" commandName="author">
		
			<tr> 
			<td> <form:label path="authorId"></form:label></td>
			<td><form:input path="authorId" /></td> 
			</tr>
			
			<br/>
			<tr>
			<td> <form:label path="authorName"></form:label> </td> 
			<td> <form:input path="authorName" /> </td>
			
			<td> <form:label path="country"></form:label> </td> 
			<td> <form:input path="country" /> </td>
			</tr>
			<br/>
			<tr>
				<input type="submit" value="Edit Author">
			</tr>
		
	</form:form>
	</td>
	
	<form:form action="${refreshAuthor}" commandName="author">
		<tr>
				<input type="submit" value="Refresh Author">
			</tr>
		</form:form>
	
	
	<table border="0.5" width="200" >
	<h3>Author list</h3>
	<c:if test="${!empty authors}">
		<table>
			<tr>
				<th width="100">Author id</th>
				<th width="100">Name</th>
				<th width="100">Country</th>
				
			</tr>
			<c:forEach items="${authors}" var="a">
				<tr>
					<td>${a.authorId}</td>
					<td>${a.authorName}</td>
					<td>${a.country}</td>
				</tr>
			</c:forEach>
			
		</table>
		</table>
	</c:if>
	</table>
	</div>
	
</body>
</html>