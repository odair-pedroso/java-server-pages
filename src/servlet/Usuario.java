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

			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null); // id é long ,
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);

			try {

				String msg = null;
				boolean podeInserir = true;

				// validação para o caso de clicar em salvar usuario sem ter inserido nenhuma
				// informação, não deixar o codigo fazer mais nenhuma validação neste cenario

				if (login == null || login.isEmpty()) {
					msg = "Login deve ser informado";
					podeInserir = false;

				} else if (senha == null || senha.isEmpty()) {
					msg = "Senha deve ser informada";
					podeInserir = false;

				} else if (nome == null || nome.isEmpty()) {
					msg = "Nome deve ser informado";
					podeInserir = false;

				} else if (telefone == null || telefone.isEmpty()) {
					msg = "Telefone deve ser informado";
					podeInserir = false;

				}

				else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) { // para usuario novo.

					msg = "Usuário já existe com o mesmo login";
					podeInserir = false;

				} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {

					msg = "\n A senha informada já existe para outro usuário";
					podeInserir = false;

				}

				if (msg != null) {
					request.setAttribute("msg", msg);
				} else if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) {
					daoUsuario.salvar(usuario);

				}

				if (id != null && !id.isEmpty() && podeInserir) { // se o id já existir sendo diferente de nulo e

					if (!daoUsuario.validarLoginUpdate(login, id)) {
						request.setAttribute("msg", "Este login já existe para outro usuario");

					} else {

						daoUsuario.atualizar(usuario);

					}
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
