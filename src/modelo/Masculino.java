package modelo;

public class Masculino extends Pessoa {

	private Integer idade;

	public Masculino(Integer id, StringBuilder nomeCompleto, Character saude, Integer idade) {
		super(id, nomeCompleto, saude);
		this.setIdade(idade);
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

}
