package gui.modelo;

import util.Moeda;
import util.Unidade;

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
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class FrmIngredienteGerenciar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNome;
	private JTextField textFieldPreco;
	private JTextArea textAreaDescricao;
	private JComboBox<Moeda> comboBoxCurrency;
	private JComboBox<Unidade> comboBoxUnidade;
	private JButton btnLimpar;
	private JButton okButton;
	private JButton cancelButton;
	private JScrollPane scrollPane;

	/**
	 * Create the dialog.
	 */
	public FrmIngredienteGerenciar() {
		setBounds(100, 100, 373, 271);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setResizable(false);//Não deixa maximizar a tla
		setLocationRelativeTo(null);// abre janela no centro da tela
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 12, 105, 15);
		contentPanel.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(135, 10, 198, 22);
		contentPanel.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setBounds(12, 39, 128, 15);
		contentPanel.add(lblDescrio);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 66, 321, 49);
		contentPanel.add(scrollPane);
		
		textAreaDescricao = new JTextArea();
		textAreaDescricao.setLineWrap(true);
		scrollPane.setViewportView(textAreaDescricao);
		
		JLabel lblPreoDeCompra = new JLabel("Preço de compra:");
		lblPreoDeCompra.setBounds(12, 139, 145, 15);
		contentPanel.add(lblPreoDeCompra);
		
		textFieldPreco = new JTextField();
		textFieldPreco.setBounds(95, 164, 85, 22);
		contentPanel.add(textFieldPreco);
		textFieldPreco.setColumns(10);
		
		comboBoxCurrency = new JComboBox<Moeda>();
		comboBoxCurrency.setBounds(12, 163, 71, 22);
		contentPanel.add(comboBoxCurrency);
		
		comboBoxUnidade = new JComboBox<Unidade>();
		comboBoxUnidade.setBounds(192, 163, 114, 22);
		contentPanel.add(comboBoxUnidade);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnLimpar = new JButton("Limpar");
			buttonPane.add(btnLimpar);
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
	public JTextField getTextFieldNome() {
		return textFieldNome;
	}
	public JTextArea getTextAreaDescricao() {
		return textAreaDescricao;
	}
	public JComboBox<Moeda> getComboBoxCurrency() {
		return comboBoxCurrency;
	}
	public JTextField getTextFieldPreco() {
		return textFieldPreco;
	}
	public JComboBox<Unidade> getComboBoxUnidade() {
		return comboBoxUnidade;
	}
	public JButton getBtnLimpar() {
		return btnLimpar;
	}
	public JButton getOkButton() {
		return okButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
}
