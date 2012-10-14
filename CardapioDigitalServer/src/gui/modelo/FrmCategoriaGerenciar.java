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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import bean.Tipo;

public class FrmCategoriaGerenciar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNome;
	private JButton btnTipoGerenciar;
	private JComboBox<Tipo> comboBoxTipo;
	private JTextArea textAreaDescricao;
	private JButton okButton;
	private JButton cancelButton;
	private JButton btnLimpar;
	
	/**
	 * Create the dialog.
	 */
	public FrmCategoriaGerenciar() {
		setBounds(100, 100, 572, 209);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(12, 12, 70, 15);
		contentPanel.add(lblNewLabel);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(100, 10, 134, 19);
		contentPanel.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setBounds(278, 12, 202, 15);
		contentPanel.add(lblDescrio);
		
		textAreaDescricao = new JTextArea();
		textAreaDescricao.setBounds(278, 39, 256, 55);
		contentPanel.add(textAreaDescricao);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(12, 53, 70, 15);
		contentPanel.add(lblTipo);
		
		comboBoxTipo = new JComboBox<Tipo>();
		comboBoxTipo.setBounds(12, 80, 228, 19);
		contentPanel.add(comboBoxTipo);
		
		btnTipoGerenciar = new JButton("Gerenciar...");
		btnTipoGerenciar.setBounds(100, 48, 134, 25);
		contentPanel.add(btnTipoGerenciar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 39, 222, 2);
		contentPanel.add(separator);
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
	public JButton getBtnTipoGerenciar() {
		return btnTipoGerenciar;
	}
	public JComboBox<Tipo> getComboBoxTipo() {
		return comboBoxTipo;
	}
	public JTextArea getTextAreaDescricao() {
		return textAreaDescricao;
	}
	public JButton getOkButton() {
		return okButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
	public JButton getBtnLimpar() {
		return btnLimpar;
	}
}
