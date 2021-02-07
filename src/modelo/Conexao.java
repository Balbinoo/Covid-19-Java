package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import visao.Visao;

public class Conexao {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/pandemia?useTimezone=true&serverTimezone=UTC", "Rodrigo",
					"!AdministraBancos2");
		} catch (SQLException e) {

			Visao.mostraErro("Sem conectar!\n" + e.getMessage(), "Erro");

		} catch (ClassNotFoundException e) {
			System.out.println("Falha na Conexão!\n" + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
}
