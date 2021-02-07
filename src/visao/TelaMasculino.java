package visao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import controle.Validacao;
import modelo.Populacao;

public class TelaMasculino extends JFrame {

	private char saude = '0';
	private JPanel contentPane;
	private JTextField textValidaIdade, textValidaSaude, textValidaNome, textIdade, textNome;
	private JLabel lblNome = new JLabel("Nome Completo");
	private JLabel lblSaude = new JLabel("Saude");
	private JLabel lblIdade = new JLabel("Idade");
	private JButton btnVoltar = new JButton("Voltar");
	private JButton btnEnviar = new JButton("Enviar");
	private JRadioButton btnContaminadaTratamento = new JRadioButton("Contaminado em Tratamento");
	private JRadioButton btnContaminadaFalecida = new JRadioButton("Contaminado Falecido");
	private JRadioButton btnContaminadaCurada = new JRadioButton("Contaminado Curado");
	private JRadioButton btnSemContaminacao = new JRadioButton("Sem contaminacao");

	public TelaMasculino(Populacao grupo) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cruz.png")));
		setForeground(Color.green);
		setResizable(false);
		setTitle("Cadastra Homem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 374, 303);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setForeground(new Color(0, 191, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);

		lblNome.setBounds(15, 5, 114, 48);
		lblNome.setFont(new Font("Arial", Font.BOLD, 15));

		lblSaude.setBounds(15, 109, 60, 18);
		lblSaude.setFont(new Font("Arial", Font.BOLD, 15));

		lblIdade.setBounds(15, 59, 73, 32);
		lblIdade.setFont(new Font("Arial", Font.BOLD, 15));

		textNome = new JTextField();
		textNome.setBounds(133, 20, 150, 20);
		textNome.setColumns(10);

		textIdade = new JTextField();
		textIdade.setBounds(133, 66, 150, 20);
		textIdade.setColumns(10);

		textValidaNome = new JTextField();
		textValidaNome.setBounds(293, 20, 43, 20);
		textValidaNome.setEditable(false);
		textValidaNome.setColumns(10);

		textValidaIdade = new JTextField();
		textValidaIdade.setBounds(293, 66, 43, 20);
		textValidaIdade.setEditable(false);
		textValidaIdade.setColumns(10);

		textValidaSaude = new JTextField();
		textValidaSaude.setBounds(293, 109, 43, 20);
		textValidaSaude.setEditable(false);
		textValidaSaude.setColumns(10);

		btnVoltar.setBackground(UIManager.getColor("Button.shadow"));
		btnVoltar.setBounds(185, 238, 85, 25);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMasculino.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 14));

		btnEnviar.setBackground(UIManager.getColor("Button.shadow"));
		btnEnviar.setBounds(90, 238, 85, 25);
		btnEnviar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEnviar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int contador = 0;
				if (!Validacao.validaNome(textNome.getText().trim())) {
					lblNome.setForeground(Color.RED);
					textValidaNome.setForeground(Color.red);
					textValidaNome.setText("ERRO!");
				} else {
					lblNome.setForeground(Color.BLACK);
					textValidaNome.setForeground(Color.green);
					textValidaNome.setText("OK");
					contador++;
				}

				if (!Validacao.validaIdade(textIdade.getText().trim())) {
					lblIdade.setForeground(Color.red);
					textValidaIdade.setForeground(Color.red);
					textValidaIdade.setText("ERRO!");
				} else {
					lblIdade.setForeground(Color.BLACK);
					textValidaIdade.setForeground(Color.green);
					textValidaIdade.setText("OK");
					contador++;
				}
				if (saude == '0') {
					lblSaude.setForeground(Color.RED);
					textValidaSaude.setForeground(Color.red);
					textValidaSaude.setText("ERRO!");
					Visao.mostraErro("Nenhum estado de saude foi selecionado.", "Erro na saude");
				} else {
					lblSaude.setForeground(Color.BLACK);
					textValidaSaude.setForeground(Color.green);
					textValidaSaude.setText("OK");
					contador++;
				}

				if (contador == 3) {
					try {
						Validacao.validaMasculino(textNome.getText().trim(), saude,
								Integer.parseInt(textIdade.getText().trim()), grupo);
						Visao.mostraInfo("Pessoa cadastrada com sucesso!", "Registrado com sucesso");
						TelaMasculino.this.dispose();
					} catch (NullPointerException ex) {
						Visao.mostraErro(
								"Sem conexao\nNao foi possivel utilizar o usuario e senha para acessar o banco de dados",
								"Erro do administrador");
					}
				}
			}
		});

		btnContaminadaTratamento.setBackground(new Color(135, 206, 235));
		btnContaminadaTratamento.setBounds(11, 161, 193, 25);

		btnContaminadaFalecida.setBackground(new Color(135, 206, 235));
		btnContaminadaFalecida.setBounds(11, 136, 155, 25);

		btnContaminadaCurada.setBackground(new Color(135, 206, 235));
		btnContaminadaCurada.setBounds(11, 211, 147, 25);

		btnSemContaminacao.setBackground(new Color(135, 206, 235));
		btnSemContaminacao.setBounds(11, 186, 137, 25);

		btnContaminadaFalecida.setFont(new Font("Arial", Font.PLAIN, 13));
		btnContaminadaFalecida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saude = 'F';
				btnContaminadaFalecida.setSelected(true);
				btnContaminadaCurada.setSelected(false);
				btnSemContaminacao.setSelected(false);
				btnContaminadaTratamento.setSelected(false);
			}
		});

		btnContaminadaTratamento.setFont(new Font("Arial", Font.PLAIN, 13));
		btnContaminadaTratamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saude = 'T';
				btnContaminadaFalecida.setSelected(false);
				btnContaminadaCurada.setSelected(false);
				btnSemContaminacao.setSelected(false);
				btnContaminadaTratamento.setSelected(true);
			}
		});

		btnContaminadaCurada.setFont(new Font("Arial", Font.PLAIN, 13));
		btnContaminadaCurada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saude = 'C';
				btnContaminadaFalecida.setSelected(false);
				btnContaminadaCurada.setSelected(true);
				btnSemContaminacao.setSelected(false);
				btnContaminadaTratamento.setSelected(false);
			}
		});

		btnSemContaminacao.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSemContaminacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saude = 'S';
				btnContaminadaFalecida.setSelected(false);
				btnContaminadaCurada.setSelected(false);
				btnSemContaminacao.setSelected(true);
				btnContaminadaTratamento.setSelected(false);
			}
		});

		contentPane.setLayout(null);
		contentPane.add(lblIdade);
		contentPane.add(lblNome);
		contentPane.add(textNome);
		contentPane.add(textIdade);
		contentPane.add(textValidaIdade);
		contentPane.add(textValidaSaude);
		contentPane.add(textValidaNome);
		contentPane.add(btnEnviar);
		contentPane.add(btnVoltar);
		contentPane.add(lblSaude);
		contentPane.add(btnContaminadaFalecida);
		contentPane.add(btnContaminadaTratamento);
		contentPane.add(btnSemContaminacao);
		contentPane.add(btnContaminadaCurada);
	}
}
