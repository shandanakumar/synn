<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="com.syntel.isap.provisioning.bean.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SyntBots Logout</title>
</head>
<body>
   <% 
   if(session!=null){
   session.removeAttribute("userValue");
   session.invalidate();
  	response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
  	response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
     response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
     response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
   response.sendRedirect("./index.jsp");
   }
  
        %>
</body>
</html>