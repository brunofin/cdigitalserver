package gui.modelo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import util.Estado;

public class FrmClienteGerenciar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNome;
	private JTextField textFieldSobrenome;
	private JTextField textFieldEmail;
	private JTextField textFieldRG;
	private JTextField textFieldCPF;
	private JTextField txtDia;
	private JTextField txtMs;
	private JTextField txtAno;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JComboBox<Estado> comboBox;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmClienteGerenciar dialog = new FrmClienteGerenciar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmClienteGerenciar() {
		setBounds(100, 100, 612, 301);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nome:");
			lblNewLabel.setBounds(12, 12, 136, 15);
			contentPanel.add(lblNewLabel);
		}
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(148, 10, 114, 19);
		contentPanel.add(textFieldNome);
		textFieldNome.setColumns(10);
		{
			JLabel lblNewLabel_1 = new JLabel("Sobrenome:");
			lblNewLabel_1.setBounds(12, 39, 118, 15);
			contentPanel.add(lblNewLabel_1);
		}
		
		textFieldSobrenome = new JTextField();
		textFieldSobrenome.setBounds(148, 37, 114, 19);
		contentPanel.add(textFieldSobrenome);
		textFieldSobrenome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("E-mail:");
		lblNewLabel_2.setBounds(12, 66, 118, 15);
		contentPanel.add(lblNewLabel_2);
		{
			textFieldEmail = new JTextField();
			textFieldEmail.setBounds(148, 68, 114, 19);
			contentPanel.add(textFieldEmail);
			textFieldEmail.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("RG:");
			lblNewLabel_3.setBounds(12, 93, 118, 15);
			contentPanel.add(lblNewLabel_3);
		}
		{
			textFieldRG = new JTextField();
			textFieldRG.setBounds(148, 91, 114, 19);
			contentPanel.add(textFieldRG);
			textFieldRG.setColumns(10);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("CPF:");
			lblNewLabel_4.setBounds(12, 120, 118, 15);
			contentPanel.add(lblNewLabel_4);
		}
		{
			textFieldCPF = new JTextField();
			textFieldCPF.setBounds(148, 118, 114, 19);
			contentPanel.add(textFieldCPF);
			textFieldCPF.setColumns(10);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Data de nascimento");
			lblNewLabel_5.setBounds(12, 147, 213, 15);
			contentPanel.add(lblNewLabel_5);
		}
		{
			txtDia = new JTextField();
			txtDia.setText("dia");
			txtDia.setBounds(16, 174, 49, 19);
			contentPanel.add(txtDia);
			txtDia.setColumns(10);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("/");
			lblNewLabel_6.setBounds(71, 174, 16, 15);
			contentPanel.add(lblNewLabel_6);
		}
		{
			txtMs = new JTextField();
			txtMs.setText("mês");
			txtMs.setBounds(88, 174, 49, 19);
			contentPanel.add(txtMs);
			txtMs.setColumns(10);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("/");
			lblNewLabel_7.setBounds(148, 174, 16, 15);
			contentPanel.add(lblNewLabel_7);
		}
		{
			txtAno = new JTextField();
			txtAno.setText("ano");
			txtAno.setBounds(158, 174, 74, 19);
			contentPanel.add(txtAno);
			txtAno.setColumns(10);
		}
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(277, 12, 8, 187);
		contentPanel.add(separator);
		{
			JLabel lblNewLabel_8 = new JLabel("Endereço");
			lblNewLabel_8.setBounds(303, 12, 70, 15);
			contentPanel.add(lblNewLabel_8);
		}
		{
			JLabel lblNewLabel_9 = new JLabel("Rua:");
			lblNewLabel_9.setBounds(303, 39, 104, 15);
			contentPanel.add(lblNewLabel_9);
		}
		
		textField_1 = new JTextField();
		textField_1.setBounds(385, 37, 213, 19);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Número:");
		lblNewLabel_10.setBounds(303, 66, 92, 15);
		contentPanel.add(lblNewLabel_10);
		{
			textField_2 = new JTextField();
			textField_2.setBounds(413, 64, 114, 19);
			contentPanel.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			JLabel lblNewLabel_11 = new JLabel("CEP:");
			lblNewLabel_11.setBounds(303, 93, 70, 15);
			contentPanel.add(lblNewLabel_11);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(413, 91, 114, 19);
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}
		{
			JLabel lblNewLabel_12 = new JLabel("Bairro:");
			lblNewLabel_12.setBounds(303, 120, 70, 15);
			contentPanel.add(lblNewLabel_12);
		}
		{
			textField_4 = new JTextField();
			textField_4.setBounds(413, 118, 114, 19);
			contentPanel.add(textField_4);
			textField_4.setColumns(10);
		}
		{
			JLabel lblNewLabel_13 = new JLabel("Cidade:");
			lblNewLabel_13.setBounds(303, 147, 70, 15);
			contentPanel.add(lblNewLabel_13);
		}
		{
			textField_5 = new JTextField();
			textField_5.setBounds(413, 145, 114, 19);
			contentPanel.add(textField_5);
			textField_5.setColumns(10);
		}
		{
			JLabel lblNewLabel_14 = new JLabel("Estado:");
			lblNewLabel_14.setBounds(303, 176, 70, 15);
			contentPanel.add(lblNewLabel_14);
		}
		
		comboBox = new JComboBox<Estado>();
		comboBox.setBounds(413, 171, 70, 19);
		contentPanel.add(comboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
	public JTextField getTextFieldSobrenome() {
		return textFieldSobrenome;
	}
	public JTextField getTextFieldEmail() {
		return textFieldEmail;
	}
	public JTextField getTextFieldRG() {
		return textFieldRG;
	}
	public JTextField getTextFieldNome() {
		return textFieldNome;
	}
	public JTextField getTextFieldCPF() {
		return textFieldCPF;
	}
	public JTextField getTxtDia() {
		return txtDia;
	}
	public JTextField getTxtMes() {
		return txtMs;
	}
	public JTextField getTxtAno() {
		return txtAno;
	}
	public JTextField getTextFieldRua() {
		return textField_1;
	}
	public JTextField getTextFieldNumero() {
		return textField_2;
	}
	public JTextField getTextFieldCEP() {
		return textField_3;
	}
	public JTextField getTextFieldBairro() {
		return textField_4;
	}
	public JTextField getTextFieldCidade() {
		return textField_5;
	}
	public JComboBox<Estado> getComboBoxEstado() {
		return comboBox;
	}
	public JButton getOkButton() {
		return okButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
}
