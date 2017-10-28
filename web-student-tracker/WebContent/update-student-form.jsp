<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Student</title>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
<link type="text/css" rel="stylesheet" href="css/add-student-style.css"/>
</head>

<body>
<div id="wrapper">
		<div id="header">
		
		<h2>California State University, East Bay</h2>	
		
		</div>
	</div>
	
	<div id="container">
	<h3>Add Student</h3>
	<form action="StudentControllerServlet" method="GET">
	
	<%-- Hidden field --%>
	<input type="hidden" name="command" value="UPDATE"/>
	<input type="hidden" name="studentId" value="${THE_STUDENT.id}"/>
	<table>
	
		<tbody>
		
			<tr>
			<td><label>First name:</label></td>
			<td><input type="text" name="firstname" value="${THE_STUDENT.firstname}"></input></td>
			</tr>
			
			<tr>
			<td><label>Last name:</label></td>
			<td><input type="text" name="lastname" value="${THE_STUDENT.lastname}"></input></td>
			</tr>
			
			<tr>
			<td><label>email:</label></td>
			<td><input type="text" name="email" value="${THE_STUDENT.email}"></input></td>
			</tr>
			
			<tr>
			<td><input type="submit" value="Save" class="save"></input></td>
			</tr>
			
		</tbody>
	</table>
	
	</form>
	<p>
	<a href="StudentControllerServlet">Back to List</a>
	</p>
	</div>
	
</body>
</html>