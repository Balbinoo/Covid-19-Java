package visao;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class Visao {

	public static void mostraInfo(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void mostraErro(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}

	public static void mostraIdPessoa(String message) {
		JOptionPane.showMessageDialog(null, message, "ID encontrado", JOptionPane.DEFAULT_OPTION);

	}

	public static void mostraSairDados(int curado, int tratamento, int falecido, int homensSemContaminacao,
			int mulheresSemContaminacao, int total) {
		DecimalFormat mascara = new DecimalFormat("00");

		System.out.format("%22s %1s\n%22s %1s\n%22s %1s\n%22s %1s\n%22s %1s\n", mascara.format(curado),
				"= CONTAMINADOS CURADOS", mascara.format(tratamento), "= CONTAMINADOS EM TRATAMENTO",
				mascara.format(falecido), "= CONTAMINADOS FALECIDOS", mascara.format(homensSemContaminacao),
				"= HOMENS SEM CONTAMINAÇÃO", mascara.format(mulheresSemContaminacao), "= MULHERES SEM CONTAMINAÇÃO");

		System.err.format("%22s %1s\n", mascara.format(total), "= TOTAL DE REGISTRO DE PESSOAS");
	}

}
