package modelo;

public class Feminino extends Pessoa {

	private Character gestante;

	public Feminino(Integer id, StringBuilder nomeCompleto, Character saude, Character gestante) {
		super(id, nomeCompleto, saude);
		this.setGestante(gestante);
	}

	public Character getGestante() {
		return gestante;
	}

	public void setGestante(Character gestante) {
		this.gestante = gestante;
	}

}
