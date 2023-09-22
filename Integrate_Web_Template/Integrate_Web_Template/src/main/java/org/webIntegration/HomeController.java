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
@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/project")
	private DataSource dataSource;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		page = page.toLowerCase();
		switch(page) {
		case "home" :
			request.getRequestDispatcher("index.jsp").forward(request,response);
			break;
		case "listusers":
			
			List<Users> listUsers =new ArrayList<>();
			listUsers = new UserModel().listusers(dataSource);
			request.setAttribute("listusers", listUsers);
			// this "listusers" refers to the listusers in the get attribute in the listusers.jsp
			request.getRequestDispatcher("listusers.jsp").forward(request,response);
			break;
		default:
			request.getRequestDispatcher("error.jsp").forward(request,response);
			break;
		}
	}



}
