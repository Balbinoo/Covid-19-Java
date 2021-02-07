package visao;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controle.Validacao;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaMostraPessoa extends JFrame {

	private JPanel contentPane;
	private JTextField textId, textValidaId;
	private JLabel lblId = new JLabel("ID:");
	private JButton btnProcurar = new JButton("Procurar");
	private JButton btnVoltar = new JButton("Voltar");
	private JLabel lblOrienta = new JLabel("Digite o identificador da pessoa que deseja encontrar:");

	public TelaMostraPessoa() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cruz.png")));
		setTitle("Mostra pessoa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(425, 250, 482, 208);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lblOrienta.setFont(new Font("Arial", Font.PLAIN, 17));
		lblOrienta.setBounds(31, 11, 400, 41);
		
		textId = new JTextField();
		textId.setBounds(114, 63, 197, 31);
		textId.setColumns(10);

		lblId.setFont(new Font("Arial", Font.PLAIN, 15));
		lblId.setBounds(66, 62, 38, 30);
		
		textValidaId = new JTextField();
		textValidaId.setEditable(false);
		textValidaId.setBounds(328, 63, 46, 30);	
		textValidaId.setColumns(10);
		
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Validacao.validaProcuraId(textId.getText())) {
					textValidaId.setText("ERRO!");
					textValidaId.setForeground(Color.red);
					lblId.setForeground(Color.red);
				} else {
					textValidaId.setText("OK");
					textValidaId.setForeground(Color.GREEN);
					lblId.setForeground(Color.BLACK);
					Validacao.procuraPessoa(Integer.parseInt(textId.getText()));
				}
			}
		});
		btnProcurar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnProcurar.setBounds(166, 105, 106, 31);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMostraPessoa.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnVoltar.setBounds(342, 134, 89, 23);

		contentPane.setLayout(null);
		contentPane.add(textId);
		contentPane.add(btnVoltar);contentPane.add(btnProcurar);
		contentPane.add(lblId);
		contentPane.add(lblOrienta);
		contentPane.add(textValidaId);
	}
}
