package gui.modelo;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;

public class FrmPromocaoCadastrar extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	public FrmPromocaoCadastrar(){
		setTitle("Cadastro de Promoção");
		setBounds(100, 100, 680, 524);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblFoto = new JLabel("Foto:");
		lblFoto.setBounds(138, 108, 47, 15);
		contentPanel.add(lblFoto);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(258, 98, 148, 26);
		contentPanel.add(textField);
		
		JButton button = new JButton("Adicionar");
		button.setBounds(418, 98, 100, 25);
		contentPanel.add(button);
		
		JLabel lblNome = new JLabel("Nome:*");
		lblNome.setBounds(138, 165, 61, 15);
		contentPanel.add(lblNome);
		
		JLabel lblDataDeIncio = new JLabel("Data de Início:*");
		lblDataDeIncio.setBounds(138, 215, 114, 15);
		contentPanel.add(lblDataDeIncio);
		
		JLabel lblValidade = new JLabel("Validade:");
		lblValidade.setBounds(138, 266, 114, 15);
		contentPanel.add(lblValidade);
		
		JLabel lblDescrio = new JLabel("Descrição:*");
		lblDescrio.setBounds(138, 315, 114, 15);
		contentPanel.add(lblDescrio);
		
		textField_1 = new JTextField();
		textField_1.setBounds(258, 155, 148, 26);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(258, 205, 148, 26);
		contentPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(258, 255, 148, 26);
		contentPanel.add(textField_3);
		
		JTextArea txtrDigiteAquiA = new JTextArea();
		txtrDigiteAquiA.setText("Digite aqui a descrição da promoção");
		txtrDigiteAquiA.setBounds(138, 342, 380, 69);
		contentPanel.add(txtrDigiteAquiA);
		
		JLabel lblcamposObrigatrios = new JLabel("*Campos Obrigatórios");
		lblcamposObrigatrios.setFont(new Font("Dialog", Font.BOLD, 8));
		lblcamposObrigatrios.setBounds(405, 37, 114, 15);
		contentPanel.add(lblcamposObrigatrios);
	}
}
