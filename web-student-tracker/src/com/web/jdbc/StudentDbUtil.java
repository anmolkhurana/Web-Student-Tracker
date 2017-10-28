package com.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	public DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource)
	{
		dataSource = theDataSource;
	} 
	
	public List<Student> getStudents() throws Exception 
	{
		List<Student> students = new ArrayList<>();
		
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try
		{
			
			//get a connection
			myConn = dataSource.getConnection();
			
			//write or create sql statement 
			String sql = "Select * from student order by last_name";
			myStmt = myConn.createStatement();
			
			//execute the result set
			myRs = myStmt.executeQuery(sql);
			
			//process the results
			while(myRs.next())
			{
				
				//retreive the data from the result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				//create a new student object based on that
				Student tempStudent = new Student(id,firstName,lastName,email);
				
				//add into the list of students
				students.add(tempStudent);
				
			}
			
			return students;
			
		}
		
		finally
		{
			//close the objects
			close(myConn, myStmt, myRs);
		}
		
		
}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try
		{
			if(myRs != null)
			{
				myRs.close();
			}
			
			if(myStmt != null)
			{
				myStmt.close();
			}
			
			if(myConn != null) // actually doesn't closes the connection but make it available for other person to use it
			{
				myConn.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void addStudent(Student theStudent) throws Exception
	{
		Connection myConn=null;
		PreparedStatement myStmt = null;
		
		try
		{

			//get db connection
			myConn = dataSource.getConnection();
			//create sql for insert
			String sql = "Insert into student" + "(first_name,last_name,email)" + "values (?,?,?)";
			
			myStmt =myConn.prepareStatement(sql);
			//set the param values for the students
			myStmt.setString(1, theStudent.getFirstname());
			myStmt.setString(2, theStudent.getLastname());
			myStmt.setString(3, theStudent.getEmail());
			
			//execute sql insert
			myStmt.execute();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			//clean up jdbc
			close(myConn,myStmt,null);
		}
		
		
	}

	
	public Student getStudents(String theStudentId) throws Exception {
		
		Student	theStudent=null;
		
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs=null;
		int studentId;
		
		
		try
		{
			//convert student id to int
			studentId = Integer.parseInt(theStudentId);
			
			//get conncetions
			myConn = dataSource.getConnection();
			
			//create sql statement
			String sql="select * from student where id=?";
			
			//create prepared stmt
			myStmt = myConn.prepareStatement(sql);
			
			//set param
			myStmt.setInt(1, studentId);
			
			//execute 
			myRs=myStmt.executeQuery();
			
			//retreive data
			if(myRs.next())
			{
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
			theStudent = new Student(studentId,firstName,lastName,email);
				
			}
			else
			{
				throw new Exception("Could not find any results for" + studentId);
			}
			
			return theStudent;
		}
		finally
		{
			close(myConn,myStmt,myRs);
		}
		
		

	}

	public void updateStudent (Student theStudent) throws Exception
	{
		
		Connection myConn=null;
		PreparedStatement myStmt=null;
		
	try
	 {
			
		//get db connection
		myConn = dataSource.getConnection();
		
		//creating sql update statement
		String sql="update student " + "set first_name=?, last_name=?, email=?" + "where id=?";
		
		//prepared statement
		myStmt = myConn.prepareStatement(sql);
		
		//param setting
		myStmt.setString(1, theStudent.getFirstname());
		myStmt.setString(2, theStudent.getLastname());
		myStmt.setString(3, theStudent.getEmail());
		myStmt.setInt (4, theStudent.getId());
		
		//execute sql 
		myStmt.execute();
		
	 }
		
	finally
		{
			close(myConn,myStmt,null);
		}
	}

	public void deleteStudent(String theStudentId) throws Exception {
		
		Connection myConn=null;
		PreparedStatement myStmt=null;
		
	try
	 {
		//conert student id to int
		int studentId=Integer.parseInt(theStudentId);
		
		//get db connection
		myConn = dataSource.getConnection();
		
		//creating sql update statement
		String sql="delete from student where id=?";
		
		//prepared statement
		myStmt = myConn.prepareStatement(sql);
		
		//param setting
		myStmt.setInt(1, studentId);
		
		
		//execute sql 
		myStmt.execute();
		
	 }
	finally
		{
			close(myConn,myStmt,null);
		}
		
	}
	
}
