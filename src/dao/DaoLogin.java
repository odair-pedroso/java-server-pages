package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DaoLogin {

	private Connection connection;

	public DaoLogin() {

		connection = SingleConnection.getConnection();

	}

	// metodo para validar login e senha de acesso ao banco através da na classe
	// DAO;

	public boolean validarLogin(String login, String senha) throws Exception {

		// criando o objeto java para validar o login e senhas dos usuarios e inserir na
		// tabela de banco usuario;

		String sql = "select * from usuario where login = '" + login + "' and senha = '" + senha + "'";
		PreparedStatement selectUsuario = connection.prepareStatement(sql);
		ResultSet resultado = selectUsuario.executeQuery();

		if (resultado.next()) {
			return true; // possui usuario e senha ;

		} else {
			return false; // não validou usuario e senha;
		}

	}

}
