package visao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controle.Validacao;
import modelo.UsuarioDao;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class TelaInicial extends JFrame {

	private JPanel contentPane;
	private JButton btnLista = new JButton("Lista");
	private JButton btnMostra = new JButton("Mostra");
	private JButton btnPesquisar = new JButton("Pesquisar");
	private JButton btnSair = new JButton("Sair");
	private JLabel lblImage = new JLabel("");
	private JButton btnCadastro = new JButton("Novo ");
	private Image image = new ImageIcon(this.getClass().getResource("/covid.jpg")).getImage();
	
	public TelaInicial() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cruz.png")));
		setTitle("Pandemia do Coronavirus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 452, 301);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		btnCadastro.setBounds(136, 16, 155, 29);
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaNovo novo = new TelaNovo();
				novo.setVisible(true);
			}
		});

		btnCadastro.setForeground(Color.BLACK);
		btnCadastro.setBackground(Color.WHITE);
		btnCadastro.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
		
		btnLista.setBounds(136, 59, 155, 32);
		btnLista.setForeground(Color.BLACK);
		btnLista.setBackground(Color.WHITE);
		btnLista.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaTabela(UsuarioDao.getListaCompleta(), 'L');
			}
		});
	
		btnMostra.setBounds(136, 102, 155, 29);
		btnMostra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMostraPessoa mostra = new TelaMostraPessoa();
				mostra.setVisible(true);
			}
		});
		btnMostra.setForeground(Color.BLACK);
		btnMostra.setBackground(Color.WHITE);
		btnMostra.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));

		
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPesquisa pesquisa = new TelaPesquisa();
				pesquisa.setVisible(true);
			}
		});
		btnPesquisar.setForeground(Color.BLACK);
		btnPesquisar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
		btnPesquisar.setBackground(Color.WHITE);
		btnPesquisar.setBounds(136, 142, 155, 29);
		
		btnSair.setBounds(136, 210, 155, 29);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Validacao.calculaRelatorioFinal();
				TelaInicial.this.dispose();
			}
		});
		btnSair.setForeground(Color.BLACK);
		btnSair.setBackground(Color.WHITE);
		btnSair.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));

		lblImage.setIcon(new ImageIcon(image));
		lblImage.setBounds(0, 0, 436, 263);

		contentPane.setLayout(null);
		contentPane.add(btnPesquisar);
		contentPane.add(btnMostra);
		contentPane.add(btnLista);
		contentPane.add(btnCadastro);
		contentPane.add(btnSair);
		contentPane.add(lblImage);
	}
}
