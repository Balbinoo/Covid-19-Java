package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import controle.Validacao;
import modelo.UsuarioDao;
import javax.swing.JTextField;

public class TelaPesquisa extends JFrame {

	private JPanel contentPane;
	private JButton btnVoltar;
	private JTextField textParteNome,textValida;
	private JButton btnPesquisar = new JButton("Pesquisar");
	private JLabel lblOrienta = new JLabel("Digite parte do nome: ");
	private JLabel lblTitulo = new JLabel("Pesquisa pessoa pelo nome");
	
	public TelaPesquisa() {
		
		TelaPesquisa.this.setVisible(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cruz.png")));
		setTitle("Pesquisa pessoa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 569, 201);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setForeground(new Color(175, 238, 238));
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPesquisa.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnVoltar.setBounds(453, 128, 89, 23);
	
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitulo.setBounds(178, 0, 215, 45);
		
		lblOrienta.setFont(new Font("Arial", Font.PLAIN, 15));
		lblOrienta.setBounds(28, 62, 148, 28);
			
		textParteNome = new JTextField();
		textParteNome.setBounds(186, 67, 193, 20);
		textParteNome.setColumns(10);

		textValida = new JTextField();
		textValida.setEditable(false);
		textValida.setBounds(402, 67, 51, 20);
		textValida.setColumns(10);
	
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Validacao.validaParteNome(textParteNome.getText())) {
					Visao.mostraErro("Nao deixe em branco e nao utilize numeros.", "Erro ao procurar nome");
					textValida.setText("ERRO");
					textValida.setForeground(Color.RED);
				} else {
					textValida.setText("OK");
					textValida.setForeground(Color.green);

					new TelaTabela(Validacao.validaProcuraNome(UsuarioDao.getListaCompleta().getPessoas(),
							textParteNome.getText().toLowerCase()), 'P');
				}
			}
		});
		btnPesquisar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnPesquisar.setBounds(229, 98, 107, 23);
		
		contentPane.setLayout(null);
		contentPane.add(lblOrienta);
		contentPane.add(lblTitulo);
		contentPane.add(btnVoltar);
		contentPane.add(textParteNome);
		contentPane.add(btnPesquisar);
		contentPane.add(textValida);
	}
}
