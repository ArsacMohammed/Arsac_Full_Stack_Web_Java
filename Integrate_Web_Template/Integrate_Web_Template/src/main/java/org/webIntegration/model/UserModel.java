package org.webIntegration.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.webIntegration.Entity.Users;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserModel {
	//cant connect to the datasource directly from here . so getting from homecontroller
	@Resource(name="jdbc/project")
	private DataSource dataSource;
	public List<Users> listusers(DataSource dataSource){
		List<Users> listusers = new ArrayList<>();
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
				listusers.add(new Users(rs.getInt("User_id"),rs.getString("Username"),rs.getString("Email")));
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listusers;
		}
	public boolean addUser(DataSource dataSource,Users newUser) {
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection ();
			String username= newUser.getUsername();
			String email= newUser.getEmail();
			String query= "insert into users (username,email) values (?,?)";
			statement= connect.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, email);
			return statement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				connect.close();
				statement.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
	}
	public void updateUser(DataSource dataSource, Users updatedUser) {
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection ();
			String username= updatedUser.getUsername();
			String email= updatedUser.getEmail();
			int user_id=updatedUser.getUser_id();
			String query= "update users set username=? ,email=? where user_id=?";
			statement= connect.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, email);
			statement.setInt(3,user_id);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void deleteUser(DataSource dataSource, int user_id) {
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect=dataSource.getConnection();
			String query= "delete from users  where user_id=?";
			statement= connect.prepareStatement(query);
			statement.setInt(1,user_id);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
}
