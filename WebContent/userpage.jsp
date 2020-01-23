<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="necessaryPackageName.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cookie Community</title>
</head>
<body>

<%

User user = Model.getUserFromRequest(request);

if (user == null) {
	
	user = new User();
	
	String info = "User session not found";
	request.setAttribute("loginInfo", info);
	
	RequestDispatcher rd = request.getRequestDispatcher("loginpage.jsp");
	rd.forward(request, response);
}

%>

	<h3>Welcome to Cookie Community, <%= user.getUserName()%>!</h3>
	<p><%= user.getContent()%></p>
	<form action="editpage.jsp">
		<input type="submit" value="Edit your info">
	</form>
<!-- 	<button onclick="window.location.href = 'editpage.jsp';">Edit your info</button> -->

</body>
</html>