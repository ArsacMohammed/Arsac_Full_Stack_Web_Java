package org.webIntegration;


import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

@WebServlet("/Demo")
public class demo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
		
	@Resource(name="jdbc/project")
	private DataSource dataSource;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// initialize the connection objects 
		PrintWriter out = response.getWriter();
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			connect = dataSource.getConnection();
			
			// create  a sql statement string
			
			String query = "Select  * from users";
			stmt = connect.createStatement();
			// next execute the query
			rs=stmt.executeQuery(query);
			
			// process the result set
			while (rs.next()) {
				out.print("<br>"+  rs.getString("email"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
