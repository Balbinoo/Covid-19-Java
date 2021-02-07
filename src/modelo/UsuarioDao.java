package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controle.Validacao;
import visao.Visao;

public class UsuarioDao {

	private static Connection con = Conexao.getConnection();

	public void cadastrarPessoa(Masculino masculino) {
		con = Conexao.getConnection();
		String sql = "INSERT INTO pessoa(nome,idade,saude)VALUES(?,?,?)";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, masculino.getNomeCompleto().toString());
			preparador.setInt(2, masculino.getIdade());
			preparador.setString(3, Character.toString(masculino.getSaude()));
			preparador.execute();
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexão.");
					System.out.println("Causa:" + e.getMessage());
				}
		}
	}

	public void cadastrarPessoa(Feminino feminino) {
		con = Conexao.getConnection();
		String sql = "INSERT INTO pessoa(nome,saude,gestante)VALUES(?,?,?)";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, feminino.getNomeCompleto().toString());
			preparador.setString(2, Character.toString(feminino.getSaude()));
			preparador.setString(3, Character.toString(feminino.getGestante()));
			preparador.execute();
			preparador.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					System.out.print("Falha ao fechar a conexão.");
					System.out.println("Causa:" + e.getMessage());
				}
		}
	}

	public static Populacao getListaCompleta() {
		Populacao pessoas = new Populacao();
		con = Conexao.getConnection();
		String sql = "SELECT * FROM PESSOA";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet lista = preparador.executeQuery();
			while (lista.next()) {
				pessoas.setPessoas(Validacao.criaPessoa(lista));
			}
			lista.close();
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException ex) {
			Visao.mostraErro("Sem conexao\nNao foi possivel utilizar o usuario e senha para acessar o banco de dados",
					"Erro do administrador");
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.print("Falha ao fechar a conexão.");
				System.out.println("Causa:" + e.getMessage());
			}
		}
		return pessoas;
	}

	
}
