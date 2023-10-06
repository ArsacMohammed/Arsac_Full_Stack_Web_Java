package org.webIntegration;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.webIntegration.Entity.Users;
import org.webIntegration.model.UserModel;
@WebServlet("/operation")
public class operationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/project")
	private DataSource dataSource;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		page = page.toLowerCase();
		switch(page) {
		case "listusers":
			listUsers(response,request);
			break;
		case "addusers" :
			request.setAttribute("title", "Add User");
			request.getRequestDispatcher("addUser.jsp").forward(request,response);
			break;
		default:
			request.getRequestDispatcher("error.jsp").forward(request,response);
			break;
		}
	}
	
	private void listUsers(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		List<Users> listUsers =new ArrayList<>();
		listUsers = new UserModel().listusers(dataSource);
		request.setAttribute("listusers", listUsers);
		request.setAttribute("title", "List Of Users");
		request.getRequestDispatcher("listusers.jsp").forward(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("form");
		operation =operation.toLowerCase();
		switch(operation) {
			case "adduseroperation":
				Users newUser= new Users(request.getParameter("username"),request.getParameter("email"));
				addUserOperation(newUser);
				listUsers(response,request);
				break;
			default:
				request.getRequestDispatcher("error.jsp").forward(request,response);
				break;
			
	
		}
	}

	private void addUserOperation(Users newUser) {
		new UserModel().addUser(dataSource,newUser);
		return ;
	}



}
