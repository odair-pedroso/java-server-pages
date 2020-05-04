package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	/* criar conex�o com o banco */

	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true"; /* url do banco */
	private static String password = "admin"; /* senha */
	private static String user = "postgres"; /* usu�rio */
	private static Connection connection = null; /* instanciar o objeto do banco sql */

	static {

		conectar();
	}

	public SingleConnection() { /* construtor */

		conectar();

	}

	private static void conectar() {

		try {

			if (connection == null) {

				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false); /*
													 * as opera��es de banco n�o ser�o salvas automaticamente, insert,
													 * delete, update
													 */

				System.out.println("Conectou com sucesso");

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("erro ao conectar com o banco de dados");
		}

	}

	public static Connection getConnection() { // metodo que retorna a conex�o

		return connection;
	}

}
