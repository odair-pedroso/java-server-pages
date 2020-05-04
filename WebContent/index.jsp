<jsp:useBean id="calcula" class="beans.BeanProjetoJsp" type="beans.BeanProjetoJsp" scope="page" ></jsp:useBean>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

<c:out value="${ 'bem vindo ao JSTL' }"></c:out>

<p/>

<c:set var="data" scope="page" value="${500*6 }" ></c:set>
<c:out value="${data}"></c:out>

<p/>

<c:set var="data1" scope="page" value="${500*10 }" ></c:set>

<c:remove var="data1"/>

<c:out value="${data1}"></c:out>

<p/>

<c:catch var="erro">
	<% int var = 100/0; %>

</c:catch>

<c:if test="${erro != null }">
	${erro.message}

</c:if>

<p/>

<c:set var ="numero" value="${100/2}"></c:set>

<c:choose>
	
	<c:when test="${numero > 50 }">
		
		<c:out value="${'O valor é maior que 50'}"></c:out>	
	
	</c:when>
	
	<c:when test="${numero < 50 }">
		
		<c:out value="${'O valor é menor que 50'}"></c:out>	
	
	</c:when>
	
	<c:otherwise>
	
		<c:out value="${'Número não satisfaz nenhuma das condições'}"></c:out>
	
	</c:otherwise>


</c:choose>

<p/>


<c:set var ="numero" value="${100/3}"></c:set>

<c:forEach var="n" begin="1" end="${numero}">

	Item : ${n}
	<br/>

</c:forEach>

<br/>

<c:forTokens items="Odair-Pedroso-Junior" delims="-" var="nome">

	Nome : <c:out value="${nome}"></c:out>
	<br/>


</c:forTokens>

<br/>

<c:url value="/acessoliberado.jsp" var="acesso">
	<c:param name="param 1" value="111"></c:param>
	<c:param name="param 2" value="222"></c:param>

</c:url>
${acesso}

<br/>




<p/>
<p/>
<p/>
<p/>



	
	<form action="LoginServlet" method="post">
		Login:
		<input type="text" id="login" name="login">		
		<br/>
		Senha:
		<input type="text" id="senha" name="senha">
		<br/>
		<input type="submit" value="Logar">
	
	</form>
	

</body>
</html>