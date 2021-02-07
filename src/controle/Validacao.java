package controle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import modelo.Feminino;
import modelo.Populacao;
import modelo.Masculino;
import modelo.Pessoa;
import modelo.UsuarioDao;
import visao.Visao;

public class Validacao {

	public static void validaMasculino(String nome, char saude, int idade, Populacao grupo) {
		Masculino masculino = new Masculino(validaId(), new StringBuilder(nome), saude, idade);
		UsuarioDao usuDao = new UsuarioDao();
		grupo.setPessoas(masculino);
		usuDao.cadastrarPessoa(masculino);
	}

	public static void validaFeminino(String nome, char saude, char gestante, Populacao grupo) {
		Feminino feminino = new Feminino(validaId(), new StringBuilder(nome), saude, gestante);
		UsuarioDao usuDao = new UsuarioDao();
		grupo.setPessoas(feminino);
		usuDao.cadastrarPessoa(feminino);
	}

	public static String validaSaude(char saude) {
		switch (saude) {
		case 'T':
			return "Contaminado(a) em Tratamento";
		case 'F':
			return "Contaminado(a) Falecido(a)";
		case 'C':
			return "Contaminado(a) Curado(a)";
		}
		return "Sem contaminacao";
	}

	public static String validaMostraGestante(ArrayList<Pessoa> pessoa, int contador) {
		try {
			Feminino feminino = (Feminino) pessoa.get(contador);
			switch (feminino.getGestante()) {
			case 'T':
				return "Nao tem certeza";
			case 'S':
				return "Sim";
			}
			return "Nao";
		} catch (ClassCastException e) {
			return "   ";
		}
	}

	public static boolean validaProcuraId(String procura) {
		final int MIN = 100;
		int id = 0;
		try {
			id = Integer.parseInt(procura);
			if (id <= MIN) {
				Visao.mostraErro("O ID deve ser maior do que 100", "Erro no identificador");
				return false;
			}
		} catch (NumberFormatException Exception) {
			Visao.mostraErro("Escreva um numeral no campo do ID!", "Erro no identificador");
			return false;
		}
		return true;
	}

	public static boolean validaNome(String nome) {
		final int MIN = 3;
		if (nome.isBlank() || nome.isEmpty()) {
			Visao.mostraErro("Nao deixe o nome em branco!", " Erro no nome");
			return false;
		} else if (nome.split(" ")[0].length() < MIN || validaNumeroNome(nome)) {
			Visao.mostraErro("O nome deve ter um minimo de 3 caracteres e nao ter numerais", "Erro no nome");
			return false;
		}
		return true;
	}

	public static boolean validaNumeroNome(String nome) {
		for (char palavra : nome.toCharArray()) {
			if (Character.isDigit(palavra)) {
				return true;
			}
		}
		return false;
	}

	public static int validaId() {
		final int MIN = 100;
		int ultimoId = 0;

		ArrayList<Pessoa> pessoas;
		pessoas = UsuarioDao.getListaCompleta().getPessoas();

		if (pessoas.size() == 0) {
			ultimoId = MIN + 1;
		} else {
			ultimoId += pessoas.get((pessoas.size() - 1)).getId() + 1;
		}
		return ultimoId;
	}

	public static boolean validaIdade(String valor) {
		final int MIN = 0;
		final int MAXIMO = 150;
		int idade = 0;
		try {
			idade = Integer.parseInt(valor);
			if (idade < MIN || idade > MAXIMO) {
				Visao.mostraErro("A idade deve ser entre 0 a 150", "Erro na idade");
				return false;
			}
		} catch (NumberFormatException Exception) {
			Visao.mostraErro("Escreva um numeral na idade!", "Erro na idade");
			return false;
		}
		return true;
	}

	public static String validaMostraIdade(ArrayList<Pessoa> pessoa, int contador) {
		try {
			Masculino masculino = (Masculino) pessoa.get(contador);
			return masculino.getIdade().toString();
		} catch (ClassCastException e) {
			return "  ";
		}
	}

	public static boolean validaSexo(ArrayList<Pessoa> pessoa, int contador) {
		try {
			Masculino masculino = (Masculino) pessoa.get(contador);
			return true;
		} catch (ClassCastException e) {
			return false;
		}
	}

	public static void calculaRelatorioFinal() {
		int tratamento = 0;
		int falecido = 0;
		int curado = 0;
		int homensSemContaminacao = 0;
		int mulheresSemContaminacao = 0;
		int total = 0;

		ArrayList<Pessoa> pessoas;
		pessoas = UsuarioDao.getListaCompleta().getPessoas();

		if (pessoas.size() > 0) {
			for (int i = 0; i < pessoas.size(); i++) {
				total++;
				if (pessoas.get(i).getSaude() == 'C') {
					curado++;
				} else if (pessoas.get(i).getSaude() == 'T') {
					tratamento++;
				} else if (pessoas.get(i).getSaude() == 'F') {
					falecido++;
				} else if (pessoas.get(i).getSaude() == 'S') {
					if (Validacao.validaSexo(pessoas, i)) {
						homensSemContaminacao++;
					} else {
						mulheresSemContaminacao++;
					}
				}
			}
		} else {
			Visao.mostraErro("Nenhum registro foi realizado ainda", "Nenhum registro");
		}
		Visao.mostraSairDados(curado, tratamento, falecido, homensSemContaminacao, mulheresSemContaminacao, total);
	}

	public static void procuraPessoa(int id) {
		ArrayList<Pessoa> pessoas;
		pessoas = UsuarioDao.getListaCompleta().getPessoas();

		if (pessoas.size() > 0) {
			int contador = pessoas.size();
			do {
				contador--;
				if (id == pessoas.get(contador).getId()) {
					validaMostraPessoa(pessoas, contador);
				} else if (contador == 0) {
					Visao.mostraErro("Nenhum registro realizado com o id " + id, "Procura id");
				}
			} while (id != pessoas.get(contador).getId() && contador != 0);
		} else {
			Visao.mostraErro("Nenhum registro foi realizado ainda", "Nenhum registro");
		}
	}

	public static void validaMostraPessoa(ArrayList<Pessoa> pessoas, int contador) {

		if (validaSexo(pessoas, contador)) {
			Visao.mostraIdPessoa("ID: " + pessoas.get(contador).getId() + "\nNome completo: "
					+ pessoas.get(contador).getNomeCompleto().toString().toLowerCase() + "\nSaude: "
					+ validaSaude(pessoas.get(contador).getSaude()) + "\nIdade: "
					+ validaMostraIdade(pessoas, contador));
		} else {
			Visao.mostraIdPessoa("ID: " + pessoas.get(contador).getId() + "\nNome completo: "
					+ pessoas.get(contador).getNomeCompleto().toString().toLowerCase() + "\nSaude: "
					+ validaSaude(pessoas.get(contador).getSaude()) + "\nGestante: "
					+ validaMostraGestante(pessoas, contador));
		}

	}

	public static Pessoa criaPessoa(ResultSet lista) throws SQLException {
		if (lista.getInt("idade") == 0) {
			return criaFeminino(lista);
		}
		return criaMasculino(lista);
	}

	public static Masculino criaMasculino(ResultSet lista) throws SQLException {
		Masculino masculino = new Masculino(lista.getInt("idPessoa"),
				new StringBuilder(lista.getString("nome").toLowerCase()), lista.getString("saude").charAt(0),
				lista.getInt("idade"));
		return masculino;
	}

	public static Feminino criaFeminino(ResultSet lista) throws SQLException {
		Feminino feminino = new Feminino(lista.getInt("idPessoa"),
				new StringBuilder(lista.getString("nome").toLowerCase()), lista.getString("saude").charAt(0),
				lista.getString("Gestante").charAt(0));
		return feminino;
	}

	public static boolean validaParteNome(String parteNome) {
		return (parteNome.isBlank() || parteNome.isEmpty() || validaNumeroNome(parteNome)) ? true : false;
	}

	public static Populacao validaProcuraNome(ArrayList<Pessoa> pessoas, String parteNome) {
		Populacao recebe = new Populacao();
		if (pessoas.size() > 0) {
			for (int i = 0; i < pessoas.size(); i++) {
				if (pessoas.get(i).getNomeCompleto().toString().contains(parteNome)) {
					recebe.setPessoas(validaPessoa(pessoas.get(i)));
				}
			}
		}
		OrdenaNome ordemNome = new OrdenaNome();
		Collections.sort(recebe.getPessoas(), ordemNome);
		return recebe;
	}

	public static Pessoa validaPessoa(Pessoa pessoa) {
		try {
			Masculino masculino = (Masculino) pessoa;
			return masculino;
		} catch (ClassCastException e) {
			Feminino feminino = (Feminino) pessoa;
			return feminino;
		}
	}

}
