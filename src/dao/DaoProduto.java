package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {

		connection = SingleConnection.getConnection();

	}

	// Método Dao para criar o objeto de lista dos produtos inseridos no cadastro de
	// produtos

	public List<BeanProduto> listar() throws Exception {

		List<BeanProduto> list = new ArrayList<BeanProduto>(); // iniciando um objeto de lista de BeanProduto

		String sql = "select * from produto"; // criar o SQL select

		PreparedStatement listaProduto = connection.prepareStatement(sql);
		ResultSet resultado = listaProduto.executeQuery();

		while (resultado.next()) { // percorrer a lista enquanto houver novos

			BeanProduto beanProduto = new BeanProduto();// instanciar o objeto , para cada novo produto,
														// são criados novos beanProduto

			beanProduto.setId(resultado.getLong("id"));
			beanProduto.setNome(resultado.getString("nome")); // setar e recuperar atributos
			beanProduto.setQuantidade(resultado.getDouble("quantidade"));
			beanProduto.setValor(resultado.getDouble("valor"));

			list.add(beanProduto); // adicionar cada novo objeto produto na lista

		}

		return list; // retornar a lista

	}

	public void salvar(BeanProduto produto) { // objeto Java de insert de cadastro de produtos do formulario jsp

		try {

			String sql = "insert into produto (nome, quantidade, valor) values (?,?,?)";
			PreparedStatement insertProduto = connection.prepareStatement(sql);
			insertProduto.setString(1, produto.getNome());
			insertProduto.setDouble(2, produto.getQuantidade());
			insertProduto.setDouble(3, produto.getValor());
			insertProduto.execute();
			connection.commit(); // salva no banco de dados

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		}

	}

	public void delete(String id) {

		try {
			String sql = "delete from produto where id = '" + id + "'";
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

	public BeanProduto consultar(String id) throws Exception { // metodo dao para consultar

		String sql = "select * from produto where id='" + id + "'";

		PreparedStatement selectProduto = connection.prepareStatement(sql);
		ResultSet resultado = selectProduto.executeQuery();
		connection.commit();

		if (resultado.next()) {

			BeanProduto retornoConsulta = new BeanProduto();

			retornoConsulta.setId(resultado.getLong("id"));
			retornoConsulta.setNome(resultado.getString("nome"));
			retornoConsulta.setQuantidade(resultado.getDouble("quantidade"));
			retornoConsulta.setValor(resultado.getDouble("valor"));

			return retornoConsulta;
		}

		return null;
	}

	public void atualizar(BeanProduto produto) { // metodo para atualizar um usuario ja existente

		try {

			String sql = "update produto set nome = ?, quantidade = ?, valor = ? where id = " + produto.getId();

			PreparedStatement atualizarObjeto = connection.prepareStatement(sql);
			atualizarObjeto.setString(1, produto.getNome());
			atualizarObjeto.setDouble(2, produto.getQuantidade());
			atualizarObjeto.setDouble(3, produto.getValor());

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

	// método dao que valida o nome do produto

	public Boolean validarNome(String nome) throws Exception {

		String sql = "select count(1) as qtd from produto where nome ='" + nome + "'";

		PreparedStatement validaNome = connection.prepareStatement(sql);
		ResultSet resultado = validaNome.executeQuery();
		connection.commit();

		if (resultado.next()) {

			return resultado.getInt("qtd") <= 0; // retorna true
		}

		return false;
	}

	public Boolean validarNomeUpdate(String nome, String id) throws Exception {

		String sql = "select count(1) as qtd from usuario where login='" + nome + "' and id <> " + id;

		PreparedStatement validaLogin = connection.prepareStatement(sql);
		ResultSet resultado = validaLogin.executeQuery();
		connection.commit();

		if (resultado.next()) {

			return resultado.getInt("qtd") <= 0; // retorna true
		}

		return false;
	}

}
