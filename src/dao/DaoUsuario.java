package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

			String sql = "insert into usuario (login, senha, nome, telefone) values (?,?,?,?)";
			PreparedStatement insertUsuario = connection.prepareStatement(sql);
			insertUsuario.setString(1, usuario.getLogin());
			insertUsuario.setString(2, usuario.getSenha());
			insertUsuario.setString(3, usuario.getNome());
			insertUsuario.setString(4, usuario.getTelefone());
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

	// metodo para carregar e retornar uma lista de usuarios que logaram (listar
	// todos
	// os usuarios com acesso ao sistema);

	public List<BeanProjetoJsp> listar() throws Exception {

		List<BeanProjetoJsp> list = new ArrayList<BeanProjetoJsp>(); // iniciando um objeto de lista de BeanProjetoJsp

		String sql = "select * from usuario"; // criar o SQL select

		PreparedStatement listaUsuario = connection.prepareStatement(sql);
		ResultSet resultado = listaUsuario.executeQuery();

		while (resultado.next()) { // percorrer a lista enquanto houver novos

			BeanProjetoJsp beanProjetoJsp = new BeanProjetoJsp();// instanciar o objeto , para cada novo login e senha ,
																	// são criados novos beanProjetoJsp

			beanProjetoJsp.setId(resultado.getLong("id"));
			beanProjetoJsp.setLogin(resultado.getString("login")); // setar e recuperar atributos
			beanProjetoJsp.setSenha(resultado.getString("senha"));
			beanProjetoJsp.setNome(resultado.getString("nome"));
			beanProjetoJsp.setTelefone(resultado.getString("telefone"));

			list.add(beanProjetoJsp); // adicionar cada novo objeto na lista

		}

		return list; // retornar a lista

	}

	// método para deletar os registros de cadastro de usuarios via front

	public void delete(String id) {

		try {
			String sql = "delete from usuario where id = '" + id + "'";
			PreparedStatement deletar = connection.prepareStatement(sql);
			deletar.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		}

	}

	public BeanProjetoJsp consultar(String id) throws Exception {

		String sql = "select * from usuario where id='" + id + "'";

		PreparedStatement selectLogin = connection.prepareStatement(sql);
		ResultSet resultado = selectLogin.executeQuery();
		connection.commit();

		if (resultado.next()) {

			BeanProjetoJsp retornoConsulta = new BeanProjetoJsp();

			retornoConsulta.setId(resultado.getLong("id"));
			retornoConsulta.setLogin(resultado.getString("login"));
			retornoConsulta.setSenha(resultado.getString("senha"));
			retornoConsulta.setNome(resultado.getString("nome"));
			retornoConsulta.setTelefone(resultado.getString("telefone"));

			return retornoConsulta;
		}

		return null;
	}

	// metodo para validar login no momento da primeira edição do usuário .

	public Boolean validarLogin(String login) throws Exception {

		String sql = "select count(1) as qtd from usuario where login='" + login + "'";

		PreparedStatement validaLogin = connection.prepareStatement(sql);
		ResultSet resultado = validaLogin.executeQuery();
		connection.commit();

		if (resultado.next()) {

			return resultado.getInt("qtd") <= 0; // retorna true
		}

		return false;
	}

	// metodo para validar a senha não deicando ser igual a uma existente

	public boolean validarSenha(String senha) throws Exception {
		String sql = "select count(1) as qtd from usuario where senha='" + senha + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;/* Return true */
		}
		return false;
	}

	// metodo para validar se o login ja existe , para o cenario do usuario clicar
	// em editar um login ja existente e atualizar para um login já existente,
	// evitando que o banco e o sistema fiquem com logins duplicados.

	public Boolean validarLoginUpdate(String login, String id) throws Exception {

		String sql = "select count(1) as qtd from usuario where login='" + login + "' and id <> " + id;

		PreparedStatement validaLogin = connection.prepareStatement(sql);
		ResultSet resultado = validaLogin.executeQuery();
		connection.commit();

		if (resultado.next()) {

			return resultado.getInt("qtd") <= 0; // retorna true
		}

		return false;
	}

	public void atualizar(BeanProjetoJsp usuario) { // metodo para atualizar um usuario ja existente

		try {

			String sql = "update usuario set login = ?, senha = ?, nome = ?, telefone = ? where id = "
					+ usuario.getId();

			PreparedStatement atualizarObjeto = connection.prepareStatement(sql);
			atualizarObjeto.setString(1, usuario.getLogin());
			atualizarObjeto.setString(2, usuario.getSenha());
			atualizarObjeto.setString(3, usuario.getNome());
			atualizarObjeto.setString(4, usuario.getTelefone());

			atualizarObjeto.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

}
