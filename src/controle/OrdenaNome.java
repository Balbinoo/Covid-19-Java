package controle;

import java.util.Comparator;
import modelo.Pessoa;

public class OrdenaNome implements Comparator<Pessoa> {
	public int compare(Pessoa pessoa1, Pessoa pessoa2) {
		return (pessoa1.getNomeCompleto().toString().compareTo(pessoa2.getNomeCompleto().toString()));
	}
}