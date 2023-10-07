<%@ include file ="include/header.jsp" %> <!-- used instead of jstl tag  and it is a better way-->
<%@ page import ="java.util.List" %>
<%@ page import ="org.webIntegration.Entity.Users" %>
	<div  data-wow-delay="0.1s">
        <div class="container py-5">
            <div class="row g-5">      
				<strong>List of Users</strong>
				<table border=1>
				<thead>
				<th>User_Id</th>
				<th>User_Name</th>
				<th>User_Email</th>
				<th>Edit</th>
				</thead>
				<%! String deleteUrl; %>
				<% 
				String update_url;
				List<Users> listUsers = (List)request.getAttribute("listusers");
				for (int i=0;i<listUsers.size();i++){
					out.print("<tr>");
					out.print("<td>" + listUsers.get(i).getUser_id()+"</td>");
					out.print("<td>" + listUsers.get(i).getUsername()+"</td>");
					out.print("<td>" + listUsers.get(i).getEmail()+"</td>");
					update_url=request.getContextPath()+"/operation?page=updateUser&user_id="+listUsers.get(i).getUser_id()+
							"&username="+ listUsers.get(i).getUsername()
							+"&email="+listUsers.get(i).getEmail();
					deleteUrl=request.getContextPath()+"/operation?page=deleteUser&user_id="+listUsers.get(i).getUser_id();
					out.print("<td>" +"<a href="+update_url+">Update</a>");
					%>
				<a href="<%= deleteUrl %>"
				 onclick = "if(!confirm('Are you sure to delete the user ?')) return false">delete</a>
				 </td>
				 </tr>
				 <%
				}
				 %>
				</table>
			</div>
		</div>
	</div>
<%@ include file ="include/footer.jsp" %>