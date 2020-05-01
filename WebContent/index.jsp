<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>


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
	<br/>
	<br/>
	
	<%@ page import="java.util.Date" %> 
	
	<%="data de hoje é : " + new Date() %>
	
	<br/>
	<br/>
	
	<%@ page errorPage="receber-nome.jsp"%> <!-- direcionar para uma pagina de erro quando ocorrer algum erro na pagina, no caso forçamos erro 100/0 -->
	<%=100/2 %>
	
	<br/>
	<br/>
	
	<%@ include file="pagina-include.jsp" %>
	
	<br/>
	
	<myprefix:minhatag/>
	
	<br/>
	<br/>
	
	<h1> Index </h1>
	
	
	
	<jsp:include page="cabecalho.jsp"></jsp:include>
	
		<h3> conteudo da pagina</h3>
	
	<jsp:include page="rodape.jsp"></jsp:include>
	

</body>
</html>