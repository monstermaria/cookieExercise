<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="necessaryPackageName.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit your recipe</title>
</head>
<body>

<%

User user = (User) request.getAttribute("user");

if (user == null) {

	user = Model.getUserFromCookie(request.getCookies());
}

if (user == null) {
	
	user = new User();
	
	String info = "User session not found";
	request.setAttribute("loginInfo", info);
	
	RequestDispatcher rd = request.getRequestDispatcher("loginpage.jsp");
	rd.forward(request, response);
} else {
 	out.print("<h3>Change info for " + user.getUserName() + "</h3>");
	out.print("<form action=\"EditServlet\" method=\"post\">");
	out.print("New user name: <input type=\"text\" value=\"" + user.getUserName() + "\" name=\"username\"><br>");
	out.print("<textarea name=\"newcontent\" cols=\"80\" rows=\"10\">" + user.getContent() + "</textarea><br>");
	out.print("<input type=\"submit\" value=\"Update your content\">");
	out.print("</form>");
}

%>
<!--	<h3>Change content for <%= user.getUserName() %></h3>
 	<form id="editform" action="EditServlet" method="post">
		New user name:<input type="text" name="username">
		<input type="submit" value="Update your content">
	</form>
	<textarea name="newcontent" form="editform" cols="80" rows="10"><%= user.getUserName()%></textarea>
 -->

</body>
</html>