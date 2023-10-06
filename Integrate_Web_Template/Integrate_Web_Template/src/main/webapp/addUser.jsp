<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/include/header.jsp"><c:param name="title" value="Home"/></c:import>
	<div  data-wow-delay="0.1s">
        <div class="container py-5">
            <div class="row g-5">      
				<h1>Add User Page ......Thank You For Registering</h1>
				<form action="${pageContext.request.contextPath}/operation" method ="post" >
				UserName: <input type="text" name="username" required =required></br>
				UserEmail: <input type="email" name="email" required =required></br>
				<input type="hidden" name ="form" value ="addUserOperation"> 
			    <input type="submit" value ="AddUsers"></br></form>
	 		</div>
	</div>
<c:import url="/include/footer.jsp"></c:import>