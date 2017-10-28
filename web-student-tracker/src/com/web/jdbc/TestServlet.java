package com.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;




@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Define Connection Pool using Resourse Injection
			@Resource(name="jdbc/web_student_tracker")
			private DataSource dataSource;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			// Step 1: Setting up the print writer 
		
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			
			//Step	2: Get a connection to the database
			
			Connection myConn = null;
			Statement myStmt =null;
			ResultSet myRs= null;
			
			try
			{
				myConn = dataSource.getConnection();
				
				//Step	3: Create a SQL statement
				String sql = "Select * from student";
				myStmt = myConn.createStatement();
			
				
				//Step	4: Execute SQL query
				myRs = myStmt.executeQuery(sql);
				
				//Step 	5: Process the result set
				while(myRs.next())
				{
					String email = myRs.getString("email");
					out.println(email);
				}
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
	}

}
