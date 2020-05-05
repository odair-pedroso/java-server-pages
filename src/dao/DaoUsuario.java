package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.BeanProjetoJsp;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {

		connection = SingleConnection.getConnection();

	}

	public void salvar(BeanProjetoJsp usuario) { // aqui faremos o insert de novos usuarios e senhas no
													// banco (usuario)

		try {

			String sql = "insert into usuario (login , senha) values (?,?)";
			PreparedStatement insertUsuario = connection.prepareStatement(sql);
			insertUsuario.setString(1, usuario.getLogin());
			insertUsuario.setString(2, usuario.getSenha());
			insertUsuario.execute();
			connection.commit(); // salva no banco de dados

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
