package modelo;

public abstract class Pessoa {

	private Integer id;
	private StringBuilder nomeCompleto;
	private Character saude;

	public Pessoa(Integer id, StringBuilder nomeCompleto, Character saude) {
		this.setNomeCompleto(nomeCompleto);
		this.setSaude(saude);
		this.setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StringBuilder getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(StringBuilder nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Character getSaude() {
		return saude;
	}

	public void setSaude(Character saude) {
		this.saude = saude;
	}

}
