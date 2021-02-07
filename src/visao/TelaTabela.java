package visao;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import controle.Validacao;
import modelo.Populacao;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;

public class TelaTabela extends JFrame {

	private DefaultTableModel model;
	private JPanel contentPane;
	private JTable table;
	private JButton btnVoltar;
	private JLabel lblTitulo = new JLabel("Pessoas Cadastradas");
	private JLabel lblTotalRegistros = new JLabel("");
	private Object[] column = { "Identificador", "Nome", "Situacao de Saude", "Idade", "Gestante" };
	private final Object[] row = new Object[5];
	private JScrollPane scrollPane = new JScrollPane();

	public TelaTabela(Populacao pessoas, char origem) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cruz.png")));
		setTitle("Lista de Cadastros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 593, 445);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setForeground(new Color(175, 238, 238));
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 51, 557, 256);

		model = new DefaultTableModel();
		model.setColumnIdentifiers(column);
		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaTabela.this.dispose();
			}
		});
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnVoltar.setBounds(242, 371, 89, 23);

		lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		lblTitulo.setBounds(198, 11, 173, 45);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		lblTotalRegistros.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTotalRegistros.setBounds(10, 332, 300, 28);

		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(119);
		table.getColumnModel().getColumn(2).setPreferredWidth(190);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);

		if (pessoas.getPessoas().size() > 0) {
			TelaTabela.this.setVisible(true);

			for (int i = 0; i < pessoas.getPessoas().size(); i++) {
				row[0] = pessoas.getPessoas().get(i).getId();
				row[1] = pessoas.getPessoas().get(i).getNomeCompleto().toString().toLowerCase();
				row[2] = Validacao.validaSaude(pessoas.getPessoas().get(i).getSaude());
				row[3] = Validacao.validaMostraIdade(pessoas.getPessoas(), i);
				row[4] = Validacao.validaMostraGestante(pessoas.getPessoas(), i);
				model.addRow(row);
			}
			if (origem == 'P')
				lblTotalRegistros.setText("Foi encontrado no registro " + pessoas.getPessoas().size() + " pessoa(s). ");

		} else if (pessoas.getPessoas().size() == 0 && origem == 'P') {
			Visao.mostraErro("Nenhuma pessoa foi encontrada com o nome digitado.", "Nnehuma pessoa encontrada");
		} else {
			Visao.mostraErro("Nenhuma pessoa foi cadastrada ainda", "Tabela de Registros");
			TelaTabela.this.dispose();
		}

		contentPane.setLayout(null);
		contentPane.add(btnVoltar);
		contentPane.add(scrollPane);
		contentPane.add(lblTotalRegistros);
		contentPane.add(lblTitulo);
	}
}
