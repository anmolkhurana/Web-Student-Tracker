package com.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	//This method is called as soon as your servlet is first loaded. Best place to put in stuff. //overridden method 
	@Override
	public void init() throws ServletException {
		super.init();
		
		try
		{
			studentDbUtil = new StudentDbUtil(dataSource);
		}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try 
		{	
			//read the "command (hidden field)" parameter
			
			String theCommand = request.getParameter("command");
			
			//if the command is missing, then default to the listing of students
			if (theCommand==null)
			{
				theCommand="LIST";
			}
			//route to the appropriate method
			switch(theCommand)
			{
				case "LIST":
					// list the students in MVC fashion
					listStudents(request,response);
					break;
					
				case "ADD":
					//adding a new student to the list
					addStudent(request,response);
					break;
				
				case "LOAD":
					//Preppulating the form
					loadStudent(request,response);
				
				case "UPDATE":
					//updating the record
					updateStudent(request,response);
					break;
				
				case "DELETE":
					//deleting the record
					deleteStudent(request,response);
					break;
				
				default:
					listStudents(request,response);
				
			}
				
			
			
			
		} 
		
		catch (Exception e) 
		{
			
			throw new ServletException(e);
		}
		
		
		
		
		
		
	}



	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read student id from the form data
		String theStudentId=request.getParameter("studentId");
			
		//delete record from the database
		studentDbUtil.deleteStudent(theStudentId);		
		
		//send the data back to "List student form.jsp"
		listStudents(request,response);
		
	}



	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read student info from the form data
		int id=Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		//create a new student object
		Student theStudent = new Student(id,firstName,lastName,email);
		
		//perform update on the database
		studentDbUtil.updateStudent(theStudent);
		
		//send the data back to list student form 
		listStudents(request,response);
	}



	private void loadStudent(HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		//read the student id from the data
		String theStudentId = request.getParameter("studentId");
		
		//get student from the database (db util class)
		Student theStudent = studentDbUtil.getStudents(theStudentId);
		
		//place the student attributes in the request attributes
		request.setAttribute("THE_STUDENT", theStudent);
		
		//send it to new jsp page 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
	}



	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		//read student info from the form data
		String firstName =request.getParameter("firstname");
		String lastName =request.getParameter("lastname");
		String email =request.getParameter("email");
		
		//create a new student object
		Student theStudent = new Student(firstName,lastName,email);
		
		//add the student to the database
		studentDbUtil.addStudent(theStudent);
		
		//send back to main page(list-student.jsp)
		listStudents(request,response);
	}



	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//get students from db util
		List<Student> students = studentDbUtil.getStudents();
		
		//add students to the request
		request.setAttribute("STUDENT_LIST", students);
		
		//send to JSP page (view)
		javax.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
		
	}
	
	

}
