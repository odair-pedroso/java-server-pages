package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProjetoJsp;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(request.getParameter("user"));
		System.out.println(request.getParameter("acao"));

		try {

			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanProjetoJsp consultarObjeto = daoUsuario.consultar(user); // criar metodo consultar login

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", consultarObjeto); // não passo a lista de usuarios, somente um usuario a
																// ser editado
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp"); // Dispatcher é usado
																								// para indicar para
																								// qual tela será
																								// enviada a ação
				request.setAttribute("usuarios", daoUsuario.listar()); // o que será enviado para a tela
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
		System.out.println(request.getParameter("login"));
		System.out.println(request.getParameter("senha"));
		System.out.println(request.getParameter("nome"));

		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			BeanProjetoJsp usuario = new BeanProjetoJsp(); // instancia novo objeto com todos os atributos de usuario;

			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0); // id é long ,
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);

			if (id == null || id.isEmpty()) { // se id for nulo ou ainda se for vazio ( condição para se o id não
												// existir
												// ainda )
				daoUsuario.salvar(usuario);
			} else {
				daoUsuario.atualizar(usuario);
			}

			try {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
