<jsp:useBean id="calcula" class="beans.BeanProjetoJsp" type="beans.BeanProjetoJsp" scope="page" ></jsp:useBean>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tela Inicial</title>
</head>


<body>


	<form action="LoginServlet" method="post">
		Login:
		<input type="text" id="login" name="login">		
		<br/>
		Senha:
		<input type="password" id="senha" name="senha">
		<br/>
		<p/>
		<input type="submit" value="Logar">
	
	</form>
	

</body>
</html>