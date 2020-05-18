<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Produtos</title>

<link rel="stylesheet" href="resources/css/cadastro.css">
</head>


<body>

	<center>
			<h1>Cadastro de produtos</h1>
			
			<h3 style="color : red ;">${msg}</h3>
						
	</center>	
	
	<form action ="salvarProduto" method="post" id="formProd">		
		<ul class="form-style-1">
			<li>
				<table>		
					<tr>				
						<td>Codigo:</td>				
						<td><input type="text" readonly="readonly" id="id" name="id" value="${prod.id}" class="field-long"></td>		
					</tr>					
					<tr>				
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome" value="${prod.nome}"></td>		
					</tr>
					<tr>				
						<td>Quantidade:</td>
						<td><input type="text" id="quantidade" name="quantidade" value="${prod.quantidade}"></td>		
					</tr>
					<tr>				
						<td>Valor R$:</td>
						<td><input type="text" id="valor" name="valor" value="${prod.valor}"></td>		
					</tr>
					
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input type="submit" value="Cancelar" onclick="document.getElementById('formProd').action = 'salvarProduto?acao=reset'"></td>				
					</tr>		
				</table>			
			</li>	
		</ul>		
	</form>	
	
	<div class="container">
			<table class="responsive-table">
				<caption>Produtos Cadastrados</caption>
				<tr>				
					<th>Codigo</th>					
					<th>Nome Produto</th>
					<th>Quantidade</th>
					<th>Valor R$</th>
					<th>Excluir</th>
					<th>Editar</th>				
				</tr>
				<c:forEach items="${produtos}" var="prod">
						<tr>
							<td style="width: 150px"><c:out value="${prod.id}"></c:out></td>		
							<td style="width: 150px"><c:out value="${prod.nome}"></c:out></td>							
							<td><c:out value="${prod.quantidade}"></c:out></td>
							<td><c:out value="${prod.valor}"></c:out></td>
																			
							<td><a href="salvarProduto?acao=delete&prod=${prod.id}"><img src="resources/img/botaoExcluir.png" alt="Excluir" title="Excluir" width="40px" height="40px"></a></td>
							<td><a href="salvarProduto?acao=editar&prod=${prod.id}"><img src="resources/img/botaoEditar.png" alt="Editar" title="Editar" width="40px" height="40px"></a></td>								
						</tr>		
				</c:forEach>	
			</table>
	</div>	
















</body>
</html>