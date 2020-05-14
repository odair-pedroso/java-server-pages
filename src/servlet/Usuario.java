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

		try {

			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);

				System.out.println(request.getParameter("acao"));
				System.out.println(request.getParameter("user"));

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());

				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanProjetoJsp consultarObjeto = daoUsuario.consultar(user); // criar metodo consultar login

				System.out.println(request.getParameter("acao"));
				System.out.println(request.getParameter("user"));

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", consultarObjeto); // n�o passo a lista de usuarios, somente um usuario a
																// ser editado
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp"); // Dispatcher � usado
																								// para indicar para
																								// qual tela ser�
																								// enviada a a��o
				request.setAttribute("usuarios", daoUsuario.listar()); // o que ser� enviado para a tela
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
		System.out.println(request.getParameter("telefone"));

		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
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

			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null); // id � long ,
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);

			try {

				String msg = null;
				boolean podeInserir = true;

				if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) { // para usuario novo.

					msg = "Usu�rio j� existe com o mesmo login";
					podeInserir = false;

				} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {

					msg = "\n A senha informada j� existe para outro usu�rio";
					podeInserir = false;

				}

				if (msg != null) {

					request.setAttribute("msg", msg);
				}

				if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) { // valida se id �
																									// nulo ou vazio e
					// valida o login ( se ja existe )

					daoUsuario.salvar(usuario); // salvar

				} else if (id != null && !id.isEmpty() && podeInserir) { // se o id j� existir sendo diferente de nulo e
																			// diferente de vazio
					daoUsuario.atualizar(usuario);
				}

				if (!podeInserir) {

					request.setAttribute("user", usuario);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
