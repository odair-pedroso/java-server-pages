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
			
			<h3 style="color : red ;">${msg}</h3>
						
	</center>	
	
	<form action ="salvarUsuario" method="post" id="formUser" onsubmit="validarCampos()">		
		<ul class="form-style-1">
			<li>
				<table>		
					<tr>				
						<td>Codigo:</td>				
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
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome" value="${user.nome}"></td>		
					</tr>
					<tr>				
						<td>Telefone:</td>
						<td><input type="text" id="telefone" name="telefone" value="${user.telefone}"></td>		
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>				
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
					<th>Nome</th>
					<th>Telefone</th>
					<th>Excluir</th>
					<th>Editar</th>				
				</tr>
				<c:forEach items="${usuarios}" var="user">
						<tr>
							<td style="width: 150px"><c:out value="${user.id}"></c:out></td>		
							<td style="width: 150px"><c:out value="${user.login}"></c:out></td>							
							<td><c:out value="${user.nome}"></c:out></td>
							<td><c:out value="${user.telefone}"></c:out></td>
																			
							<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img src="resources/img/botaoExcluir.png" alt="Excluir" title="Excluir" width="40px" height="40px"></a></td>
							<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img src="resources/img/botaoEditar.png" alt="Editar" title="Editar" width="40px" height="40px"></a></td>								
						</tr>		
				</c:forEach>	
			</table>
	</div>	
<script type="text/javascript"> 

	function validarCampos() {
		
		if(document.getElementById("login").value ==''){
			alert('Informe o login');
			return false;
		}
		
		if(document.getElementById("senha").value ==''){
			alert('Informe a senha');
			return false;
		}
		
		if(document.getElementById("nome").value ==''){
			alert('Informe o seu nome');
			return false;
		}
		
		if(document.getElementById("telefone").value ==''){
			alert('Informe o telefone');
			return false;
		}
		
		
	}

</script>
	
		

</body>
</html>