<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuario</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>

	<center>
			<h1>Cadastro de usuario</h1>			
	</center>
	
	
	
	<form action ="salvarUsuario" method="post">
		
		<ul class="form-style-1">
			<li>
				<table>		
					<tr>				
						<td>C�digo:</td>				
						<td><input type="text" readonly="readonly" id="id" name="id" value="${user.id}" class="field-long"></td>		
					</tr>				
					<tr>				
						<td>Login:</td>
						<td><input type="text" id="login" name="login" value="${user.login}"></td>		
					</tr>			
					<tr>				
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha" value="${user.senha}"></td>		
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"></td>				
					</tr>		
				</table>			
			</li>	
		</ul>		
	</form>	
	
	<div class="container">
			<table class="responsive-table">
				<caption>Usu�rios cadastrados</caption>
				<tr>				
					<th>Id</th>
					<th>Login</th>
					<th>Senha</th>
					<th>Excluir</th>
					<th>Editar</th>				
				</tr>
				<c:forEach items="${usuarios}" var="user">
						<tr>
							<td style="width: 150px"><c:out value="${user.id}"></c:out></td>		
							<td style="width: 150px"><c:out value="${user.login}"></c:out></td>
							<td><c:out value="${user.senha}"></c:out></td>
																			
							<td><a href="salvarUsuario?acao=delete&user=${user.login}"><img src="resources/img/botaoExcluir.png" alt="Excluir" title="Excluir" width="40px" height="40px"></a></td>
							<td><a href="salvarUsuario?acao=editar&user=${user.login}"><img src="resources/img/botaoEditar.png" alt="Editar" title="Editar" width="40px" height="40px"></a></td>								
						</tr>		
				</c:forEach>	
			</table>
	</div>	
	

</body>
</html>