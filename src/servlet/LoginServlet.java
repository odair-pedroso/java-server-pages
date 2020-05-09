package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProjetoJsp;
import dao.DaoLogin;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoLogin daoLogin = new DaoLogin(); // criar uma nova instancia na servlet do DaoLogin para usar este objeto
												// DaoLogin

	public LoginServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			BeanProjetoJsp beanValidar = new BeanProjetoJsp();

			System.out.println(request.getParameter("id"));
			System.out.println(request.getParameter("login"));
			System.out.println(request.getParameter("senha"));
			System.out.println(request.getParameter("nome"));
			System.out.println(request.getParameter("telefone"));
			System.out.println("--------------------------------------------------------------------------");

			String login = request.getParameter("login");
			String senha = request.getParameter("senha");

			if (daoLogin.validarLogin(login, senha)) { // se for acesso ok
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessoliberado.jsp");
				dispatcher.forward(request, response);

			} else { // se for acesso negado
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessonegado.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
