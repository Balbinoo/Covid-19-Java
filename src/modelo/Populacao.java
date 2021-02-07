package modelo;

import java.util.ArrayList;

public class Populacao {

	private ArrayList<Pessoa> pessoas;

	public Populacao() {
		pessoas = new ArrayList<Pessoa>();
	}

	public ArrayList<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoa pessoa) {
		pessoas.add(pessoa);
	}

}
