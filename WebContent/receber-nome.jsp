<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>receber-nome</title>
</head>
<body>
	
<%= "Objetos implicitos : são do tipo HttpServletRequest"%>
	<br/>
	<br/>
	<% out.print(request.getParameter("nome"));%>
	<br/>
	<br/>
	<%= "Receber nome : " + request.getParameter("nome")%>
	<br/>
	<br/>
	<%= request.getContextPath()%>
	<br/>
	<br/>
	<%= request.getLocalPort()%>
	<br/>
	<br/>
	<%= request.getProtocol()%>
	<br/>
	<br/>
	<%= request.getRequestedSessionId()%>
	<br/>
	<% //response.sendRedirect("http://www.uol.com.br"); //comentando o codigo que redireciona esta pagina para a pagina uol.%>
	
	<%=session.getAttribute("projeto") %>
	
	<br/>
	
	<%@ page isErrorPage="true" %> <!-- recebendo o erro da pagina (index.jsp) e lançando o erro como exceção na pagina indicada (receber-nome.jsp) -->
	<%= exception %>
	
	
	

</body>
</html>