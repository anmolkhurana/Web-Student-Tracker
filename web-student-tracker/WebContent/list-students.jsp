<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--In order to replace JSP scriplets we have to use JSTL --%>
<%-- <%@ page import = "java.util.*, com.web.jdbc.*" %> --%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List-Students/Student Tracker App</title>
<link type="text/css" rel="stylesheet" href="css/style.css">

</head>

<%-- 

<%
	
	//Replacing Scriplets with JSTL , so we don't need this anymore
	//get the students from the request object (sent by the servelet)
	List<Student> theStudents = (List<Student>) request.getAttribute("STUDENT_LIST");
	
%>


--%>

<body>

	<div id="wrapper">
		<div id="header">
		
		<h2>California State University, East Bay</h2>	
		
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			
			<input type="button" value="Add Student" onclick="window.location.href='add-student-form.jsp'; return false;" class="add-student-button"></input>
			
			<table border="1">
			
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				
				</tr>
				
				<%--
				
					<% for (Student tempStudent : theStudents) { %>
					<tr>
						<td><%= tempStudent.getFirstname() %></td>
						<td><%= tempStudent.getLastname() %></td>
						<td><%= tempStudent.getEmail() %></td>
					</tr>
						
				<% } %>
				
				
				--%>
				
				
				<c:forEach var="tempStudent" items="${STUDENT_LIST}">
				
				
				<!-- Set up a link for each student so that when you click on each update It will prepopulate the data from the database -->
				
				<c:url var="tempLink" value="StudentControllerServlet">
				
				<c:param name="command" value="LOAD" />
				<c:param name="studentId" value="${tempStudent.id}" />
				
				</c:url>
				
				<!-- Set up a link for each student so that when you click on each delete It will delete the data from the database -->
				
				<c:url var="deleteLink" value="StudentControllerServlet">
				
				<c:param name="command" value="DELETE" />
				<c:param name="studentId" value="${tempStudent.id}" />
				
				</c:url>
				
				
				
				<tr>
						<td> ${tempStudent.firstname} </td>
						<td> ${tempStudent.lastname} </td>
						<td> ${tempStudent.email} </td>
						<td> <a href="${tempLink}">Update </a>
							| 
							<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete the record'))) return false">Delete </a>
						
						 </td>
				</tr>
					
			</c:forEach>
				
			</table>
		</div>
	</div>

 </body>
</html>