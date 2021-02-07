package visao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import modelo.Populacao;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class TelaNovo extends JFrame {

	private JPanel contentPane;
	private Populacao grupo = new Populacao();
	private JLabel lblOrienta = new JLabel("Deseja cadastrar uma pessoa de qual sexo?");
	private JButton btnMasculino = new JButton("Masculino");
	private JButton btnFeminino = new JButton("Feminino");
	private JButton btnVoltar = new JButton("Voltar");
	private JLabel lblImage = new JLabel("");
	private Image image = new ImageIcon(this.getClass().getResource("/FemxMasc.png")).getImage();

	public TelaNovo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cruz.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 450, 263);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lblOrienta.setBackground(Color.LIGHT_GRAY);
		lblOrienta.setBounds(31, 0, 396, 49);
		lblOrienta.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));

		btnMasculino.setBounds(145, 71, 146, 34);
		btnMasculino.setForeground(Color.BLACK);
		btnMasculino.setBackground(Color.WHITE);
		btnMasculino.setFont(new Font("Arial", Font.PLAIN, 15));
		btnMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMasculino frame = new TelaMasculino(grupo);
				frame.setVisible(true);
			}
		});

		btnFeminino.setBounds(145, 116, 146, 38);
		btnFeminino.setForeground(Color.BLACK);
		btnFeminino.setBackground(Color.WHITE);
		btnFeminino.setFont(new Font("Arial", Font.PLAIN, 15));
		btnFeminino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFeminino frame = new TelaFeminino(grupo);
				frame.setVisible(true);
			}
		});

		btnVoltar.setBounds(145, 181, 146, 25);
		btnVoltar.setForeground(Color.BLACK);
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaNovo.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 14));

		lblImage.setIcon(new ImageIcon(image));
		lblImage.setBounds(31, 21, 436, 419);

		contentPane.setLayout(null);
		contentPane.add(lblOrienta);
		contentPane.add(btnVoltar);
		contentPane.add(btnFeminino);
		contentPane.add(btnMasculino);
		contentPane.add(lblImage);
	}

}
