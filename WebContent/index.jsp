<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
</head>
<body>
	<h1> Bem vindo a pagina jsp</h1>
	<%= "bem vindo ao jsp" %>
	<br/>
	<br/>
	<form action="receber-nome.jsp" method="get">
		Insira seu nome : <input type="text" id="nome" name="nome">
		<input type="submit" value="salvar">	
	</form>
	
	<%session.setAttribute("projeto", "projeto jsp");//setando um atributo na sessao de (index.jsp) e resgatando em (receber-nome.jsp) %> 
	
	
	
	
	
	<br/>
	
	<%! int number = 2; %>
	<%= number %>
	<br/>
	
	<%! 
		public int result(int n){	//não é indicado colocar metodos e regras de negocio dentro do jsp, mas é possivel		
			return n*4;
		} 
	%>
	<%= result(10) %>
	<br/>	
	
	<%= application.getInitParameter("estado") %>
	

</body>
</html>