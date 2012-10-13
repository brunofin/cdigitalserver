package gui.modelo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import bean.Categoria;
import bean.Foto;
import bean.Ingrediente;
import bean.Tipo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class FrmItemGerenciar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField textFieldPrecoVenda;
	private JList<Foto> listFotos;
	private JButton okButton;
	private JTextArea txtDescricao;
	private JButton btnCategoriaGerenciar;
	private JComboBox<Categoria> comboBoxCategoria;
	private JTextArea txtIngredientes;
	private JLabel lblPrecoCompra;
	private JButton btnFotoVer;
	private JButton btnFotoAdicionar;
	private JButton btnFotoRemover;
	private JButton btnIngredienteEditar;
	private JButton limparButton;
	private JButton cancelButton;
	private JComboBox comboBoxCurrency;
	private JTable tableIngredientes;

	/**
	 * Create the dialog.
	 */
	public FrmItemGerenciar() {
		setTitle("Gerenciar Item");
		setBounds(100, 100, 807, 458);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 12, 70, 15);
		contentPanel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(100, 10, 187, 19);
		txtNome.setText("Nome");
		contentPanel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblDescrio = new JLabel("Descrição");
		lblDescrio.setBounds(12, 39, 70, 15);
		contentPanel.add(lblDescrio);

		txtDescricao = new JTextArea();
		txtDescricao.setBounds(12, 66, 275, 45);
		txtDescricao.setText("Breve descrição");
		contentPanel.add(txtDescricao);

		JLabel lblPreo = new JLabel("Preço de venda:");
		lblPreo.setBounds(12, 128, 138, 15);
		contentPanel.add(lblPreo);

		comboBoxCurrency = new JComboBox();
		comboBoxCurrency.setBounds(151, 123, 62, 24);
		contentPanel.add(comboBoxCurrency);

		textFieldPrecoVenda = new JTextField();
		textFieldPrecoVenda.setBounds(225, 126, 62, 19);
		contentPanel.add(textFieldPrecoVenda);
		textFieldPrecoVenda.setColumns(10);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(12, 186, 123, 15);
		contentPanel.add(lblCategoria);

		comboBoxCategoria = new JComboBox<Categoria>();
		comboBoxCategoria.setBounds(12, 213, 275, 24);
		contentPanel.add(comboBoxCategoria);

		btnCategoriaGerenciar = new JButton("Gerenciar...");
		btnCategoriaGerenciar.setBounds(130, 181, 157, 25);
		contentPanel.add(btnCategoriaGerenciar);

		JLabel lblFotos = new JLabel("Fotos:");
		lblFotos.setBounds(325, 12, 70, 15);
		contentPanel.add(lblFotos);

		listFotos = new JList<Foto>();
		listFotos.setBounds(325, 202, 113, -172);
		listFotos.setSelectedIndex(0);
		contentPanel.add(listFotos);

		btnFotoVer = new JButton("Ver");
		btnFotoVer.setBounds(325, 213, 113, 25);
		contentPanel.add(btnFotoVer);

		btnFotoAdicionar = new JButton("Adicionar...");
		btnFotoAdicionar.setBounds(325, 238, 113, 25);
		contentPanel.add(btnFotoAdicionar);

		btnFotoRemover = new JButton("Remover");
		btnFotoRemover.setBounds(325, 261, 113, 25);
		contentPanel.add(btnFotoRemover);

		JLabel lblIngredientes = new JLabel("Ingredientes:");
		lblIngredientes.setBounds(505, 12, 113, 15);
		contentPanel.add(lblIngredientes);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(312, 12, -11, 300);
		contentPanel.add(separator);

		btnIngredienteEditar = new JButton("Editar...");
		btnIngredienteEditar.setBounds(629, 7, 138, 25);
		contentPanel.add(btnIngredienteEditar);

		JLabel lblIngredientesSelecionados = new JLabel(
				"Ingredientes selecionados:");
		lblIngredientesSelecionados.setBounds(12, 271, 275, 15);
		contentPanel.add(lblIngredientesSelecionados);

		txtIngredientes = new JTextArea();
		txtIngredientes.setBounds(12, 298, 631, 45);
		contentPanel.add(txtIngredientes);

		JLabel lblPrecoDeCompra = new JLabel("Preço de compra:");
		lblPrecoDeCompra.setBounds(12, 355, 157, 15);
		contentPanel.add(lblPrecoDeCompra);

		lblPrecoCompra = new JLabel("R$10,00");
		lblPrecoCompra.setBounds(168, 355, 119, 15);
		contentPanel.add(lblPrecoCompra);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(765, 34, -251, 164);
		contentPanel.add(scrollPane);
		
		tableIngredientes = new JTable();
		tableIngredientes.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Ingrediente", "Quantidade"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tableIngredientes);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			limparButton = new JButton("Limpar");
			buttonPane.add(limparButton);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JList<Foto> getListFotos() {
		return listFotos;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public JTextArea getTxtDescricao() {
		return txtDescricao;
	}

	public JTextField getTxtPreco() {
		return textFieldPrecoVenda;
	}

	public JButton getBtnCategoriaGerenciar() {
		return btnCategoriaGerenciar;
	}

	public JComboBox<Categoria> getComboBoxCategoria() {
		return comboBoxCategoria;
	}

	public JTextArea getTxtIngredientes() {
		return txtIngredientes;
	}

	public JLabel getLblPrecoCompra() {
		return lblPrecoCompra;
	}

	public JButton getBtnFotoVer() {
		return btnFotoVer;
	}

	public JButton getBtnFotoAdicionar() {
		return btnFotoAdicionar;
	}

	public JButton getBtnFotoRemover() {
		return btnFotoRemover;
	}

	public JButton getBtnIngredienteEditar() {
		return btnIngredienteEditar;
	}
	public JButton getLimparButton() {
		return limparButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
	public JComboBox getComboBoxCurrency() {
		return comboBoxCurrency;
	}
	public JTable getTableIngredientes() {
		return tableIngredientes;
	}
}
