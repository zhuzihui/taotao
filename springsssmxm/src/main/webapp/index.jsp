<html>
<body>
<h2>Hello World!</h2>
<%
	request.getRequestDispatcher("index").forward(request, response);
	
%>
</body>
</html>
