<jsp:useBean id="calcula" class="beans.BeanProjetoJsp" type="beans.BeanProjetoJsp" scope="page"></jsp:useBean>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cabeçalho</title>
</head>




<body>
	
	<jsp:setProperty property="*" name="calcula"/>
		
	<center><h1>Seja bem vindo ao sistema</h1></center>
	
	Clique aqui para cadastrar seu usuário:
	<p/>
	<a href="salvarUsuario?acao=listartodos"><img src="resources/img/icone_cadastro.png" alt="Cadastrar Usuário" title="Cadastrar Usuário" width="100px" height="100px"></a>
	<br>
	<br>
	<br>
	<br>
	<br>
	Clique aqui para cadastrar seus produtos:
	<p/>	
	<a href="salvarProduto?acao=listartodos"><img src="resources/img/icone_produtos.png" alt="Cadastrar Produto" title="Cadastrar Produto" width="100px" height="100px"></a>
	
</body>
</html>