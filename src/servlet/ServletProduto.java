package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProduto;

@WebServlet("/salvarProduto")
public class ServletProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public ServletProduto() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(request.getParameter("acao")); // Servlet captura acao na tabela Produtos cadastrados da
															// telaCadastroProduto

		try {

			String acao = request.getParameter("acao"); // recupero os parametros de acao desta tabela de Produtos
														// Cadastrados para utilizar
			String prod = request.getParameter("prod");

			if (acao.equalsIgnoreCase("delete")) { // metodo para deletar / excluir um item ja cadastrado , se acao =
													// delete ; processo o dao.delete , disparo acao para a tela e
													// retorno a lista de itens que ainda existe
				daoProduto.delete(prod);

				System.out.println(request.getParameter("acao"));
				System.out.println(request.getParameter("prod"));

				RequestDispatcher view = request.getRequestDispatcher("/telaCadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());

				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanProduto consultarObjeto = daoProduto.consultar(prod); // criar metodo consultar produto

				System.out.println(request.getParameter("acao"));
				System.out.println(request.getParameter("user"));

				RequestDispatcher view = request.getRequestDispatcher("/telaCadastroProduto.jsp");
				request.setAttribute("prod", consultarObjeto); // não passo a lista de usuarios, somente um usuario a
																// ser editado
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/telaCadastroProduto.jsp");
				try {
					request.setAttribute("produtos", daoProduto.listar());
				} catch (Exception e) {

					e.printStackTrace();
				}
				view.forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(request.getParameter("acao"));
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("quantidade"));
		System.out.println(request.getParameter("valor"));

		String acao = request.getParameter("acao");
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String quantidade = request.getParameter("quantidade");
		String valor = request.getParameter("valor");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {

				RequestDispatcher view = request.getRequestDispatcher("/telaCadastroProduto.jsp");
				request.setAttribute("usuarios", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			BeanProduto beanProduto = new BeanProduto();

			beanProduto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			beanProduto.setNome(nome);

			if (quantidade != null && !quantidade.isEmpty()) {
				beanProduto.setQuantidade(Double.parseDouble(quantidade));
			}

			if (valor != null && !valor.isEmpty()) {
				beanProduto.setValor(Double.parseDouble(valor));
			}

			try {

				String msg = null;
				boolean podeInserir = true;

				// validação para o caso de clicar em salvar usuario sem ter inserido nenhuma
				// informação, não deixar o codigo fazer mais nenhuma validação neste cenario

				if (nome == null || nome.isEmpty()) {
					msg = "Nome do produto deve ser informado";
					podeInserir = false;

				} else if (quantidade == null || quantidade.isEmpty()) {
					msg = "A quantidade do produto deve ser informada";
					podeInserir = false;

				} else if (valor == null || valor.isEmpty()) {
					msg = "Valor do produto deve ser informado";
					podeInserir = false;

				} else if (id == null || id.isEmpty() && !daoProduto.validarNome(nome)) {

					msg = "Produto já existe na base de dados, se desejar, realize alteração usando a opção editar";
					podeInserir = false;

				}

				if (msg != null) {

					request.setAttribute("msg", msg);

				} else if (id == null || id.isEmpty() && daoProduto.validarNome(nome) && podeInserir) {

					daoProduto.salvar(beanProduto); // salvar

				}

				if (id != null && !id.isEmpty() && podeInserir) { // se o id já existir sendo diferente de nulo e

					if (!daoProduto.validarNomeUpdate(nome, id)) {
						request.setAttribute("msg", "Este nome de produto ja existe já existe");

					} else {

						daoProduto.atualizar(beanProduto);

					}
				}

				if (!podeInserir) {

					request.setAttribute("prod", beanProduto);
				}

				RequestDispatcher view = request.getRequestDispatcher("/telaCadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
