package visao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controle.Validacao;
import modelo.Populacao;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.Color;

public class TelaFeminino extends JFrame {

	private char saude = '0';
	private char gestante = '0';
	private JPanel contentPane;
	private JTextField textValidaNome, textValidaGestante, textValidaSaude, textNome;
	private JButton btnEnviar;
	private JLabel lblNome = new JLabel("Nome Completo");
	private JLabel lblSaude = new JLabel("Saude");
	private JLabel lblGestante = new JLabel("Gestante");
	private JButton btnVoltar = new JButton("Voltar");
	private JRadioButton btnSim = new JRadioButton("Sim");
	private JRadioButton btnNao = new JRadioButton("Nao");
	private JRadioButton btnNaoTemCerteza = new JRadioButton("Nao tem certeza");
	private JRadioButton btnContaminadaTratamento = new JRadioButton("Contaminada em Tratamento");
	private JRadioButton btnContaminadaFalecida = new JRadioButton("Contaminada Falecida");
	private JRadioButton btnContaminadaCurada = new JRadioButton("Contaminada Curada");
	private JRadioButton btnSemContaminacao = new JRadioButton("Sem contaminacao");

	public TelaFeminino(Populacao grupo) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cruz.png")));
		setResizable(false);
		setTitle("Cadastra Mulher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 391, 323);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(233, 150, 122));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lblNome.setBounds(19, 26, 127, 18);
		lblNome.setFont(new Font("Arial", Font.BOLD, 15));

		lblSaude.setBounds(15, 123, 74, 18);
		lblSaude.setFont(new Font("Arial", Font.BOLD, 15));

		lblGestante.setBounds(15, 64, 74, 18);
		lblGestante.setFont(new Font("Arial", Font.BOLD, 15));

		textNome = new JTextField();
		textNome.setBounds(139, 26, 166, 20);
		textNome.setColumns(10);

		textValidaSaude = new JTextField();
		textValidaSaude.setBounds(315, 123, 43, 20);
		textValidaSaude.setEditable(false);
		textValidaSaude.setColumns(10);

		textValidaNome = new JTextField();
		textValidaNome.setBounds(315, 26, 43, 20);
		textValidaNome.setEditable(false);
		textValidaNome.setColumns(10);

		textValidaGestante = new JTextField();
		textValidaGestante.setBounds(315, 64, 43, 20);
		textValidaGestante.setEditable(false);
		textValidaGestante.setColumns(10);

		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(111, 254, 85, 25);
		btnEnviar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int contador = 0;

				if (!Validacao.validaNome(textNome.getText().trim())) {
					lblNome.setForeground(Color.red);
					textValidaNome.setForeground(Color.red);
					textValidaNome.setText("ERRO!");
				} else {
					lblNome.setForeground(Color.black);
					textValidaNome.setForeground(Color.green);
					textValidaNome.setText("OK");
					contador++;
				}

				if (gestante == '0') {
					lblGestante.setForeground(Color.red);
					textValidaGestante.setText("ERRO!");
					textValidaGestante.setForeground(Color.red);
					Visao.mostraErro("Escolha uma opcao de gestante", "Erro em gestante");
				} else {
					lblGestante.setForeground(Color.black);
					textValidaGestante.setForeground(Color.green);
					textValidaGestante.setText("OK");
					contador++;
				}

				if (saude == '0') {
					lblSaude.setForeground(Color.red);
					textValidaSaude.setForeground(Color.red);
					textValidaSaude.setText("ERRO!");
					Visao.mostraErro("Escolha uma opcao de saude", "Erro em saude");
				} else {
					lblSaude.setForeground(Color.black);
					textValidaSaude.setForeground(Color.green);
					textValidaSaude.setText("OK");
					contador++;
				}

				if (contador == 3) {
					try {
						Validacao.validaFeminino(textNome.getText().trim(), saude, gestante, grupo);
						Visao.mostraInfo("Mulher cadastrada com sucesso", "Registrado com sucesso");
						TelaFeminino.this.dispose();
					} catch (NullPointerException ex) {
						Visao.mostraErro(
								"Sem conexao\nNao foi possivel utilizar o usuario e senha para acessar o banco de dados",
								"Erro do administrador");
					}

				}
			}
		});

		btnVoltar.setBounds(202, 254, 85, 25);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFeminino.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 14));

		btnSim.setBackground(new Color(233, 150, 122));
		btnSim.setBounds(15, 91, 49, 25);
		btnSim.setFont(new Font("Arial", Font.PLAIN, 14));

		btnNao.setBackground(new Color(233, 150, 122));
		btnNao.setBounds(66, 91, 51, 25);
		btnNao.setFont(new Font("Arial", Font.PLAIN, 14));

		btnNaoTemCerteza.setBackground(new Color(233, 150, 122));
		btnNaoTemCerteza.setBounds(123, 91, 127, 25);
		btnNaoTemCerteza.setFont(new Font("Arial", Font.PLAIN, 14));

		btnNaoTemCerteza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestante = 'T';
				btnNao.setSelected(false);
				btnSim.setSelected(false);
				btnNaoTemCerteza.setSelected(true);
			}
		});

		btnSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestante = 'S';
				btnNao.setSelected(false);
				btnSim.setSelected(true);
				btnNaoTemCerteza.setSelected(false);
			}
		});

		btnNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestante = 'N';
				btnNao.setSelected(true);
				btnSim.setSelected(false);
				btnNaoTemCerteza.setSelected(false);
			}
		});

		btnContaminadaTratamento.setBackground(new Color(233, 150, 122));
		btnContaminadaTratamento.setBounds(19, 175, 193, 25);

		btnContaminadaFalecida.setBackground(new Color(233, 150, 122));
		btnContaminadaFalecida.setBounds(19, 150, 171, 25);

		btnContaminadaCurada.setBackground(new Color(233, 150, 122));
		btnContaminadaCurada.setBounds(19, 225, 156, 25);

		btnSemContaminacao.setBackground(new Color(233, 150, 122));
		btnSemContaminacao.setBounds(19, 200, 137, 25);

		btnContaminadaFalecida.setFont(new Font("Arial", Font.PLAIN, 13));
		btnContaminadaFalecida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saude = 'F';
				btnContaminadaFalecida.setSelected(true);
				btnContaminadaTratamento.setSelected(false);
				btnContaminadaCurada.setSelected(false);
				btnSemContaminacao.setSelected(false);
			}
		});

		btnContaminadaTratamento.setFont(new Font("Arial", Font.PLAIN, 13));
		btnContaminadaTratamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saude = 'T';
				btnContaminadaFalecida.setSelected(false);
				btnContaminadaTratamento.setSelected(true);
				btnContaminadaCurada.setSelected(false);
				btnSemContaminacao.setSelected(false);
			}
		});

		btnContaminadaCurada.setFont(new Font("Arial", Font.PLAIN, 13));
		btnContaminadaCurada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saude = 'C';
				btnContaminadaFalecida.setSelected(false);
				btnContaminadaTratamento.setSelected(false);
				btnContaminadaCurada.setSelected(true);
				btnSemContaminacao.setSelected(false);
			}
		});

		btnSemContaminacao.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSemContaminacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saude = 'S';
				btnContaminadaFalecida.setSelected(false);
				btnContaminadaTratamento.setSelected(false);
				btnContaminadaCurada.setSelected(false);
				btnSemContaminacao.setSelected(true);
			}
		});

		contentPane.setLayout(null);
		contentPane.add(lblNome);
		contentPane.add(textNome);
		contentPane.add(btnContaminadaCurada);
		contentPane.add(btnSemContaminacao);
		contentPane.add(btnContaminadaTratamento);
		contentPane.add(btnContaminadaFalecida);
		contentPane.add(btnSim);
		contentPane.add(btnNao);
		contentPane.add(btnEnviar);
		contentPane.add(btnVoltar);
		contentPane.add(btnNaoTemCerteza);
		contentPane.add(lblSaude);
		contentPane.add(lblGestante);
		contentPane.add(textValidaGestante);
		contentPane.add(textValidaSaude);
		contentPane.add(textValidaNome);
	}

}
