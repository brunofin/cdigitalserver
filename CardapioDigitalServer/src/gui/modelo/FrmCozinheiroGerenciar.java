package gui.modelo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class FrmCozinheiroGerenciar extends JDialog{
	private final JPanel contentPanel = new JPanel();
	private Component parent;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtDataNascimento;
	private JFormattedTextField txtCpf;
	private JFormattedTextField txtRg;
	private JTextField txtTelefone;
	private JTextField txtCelular;
	private JTextField txtFoto;
	private JTextField txtCep;
	private JTextField txtNumero;
	private JTextField txtRua;
	private JTextField txtCidade;
	private JTextField txtBairro;
	private JTextArea txtAreaHistorico;
	private JTextArea txtAreaEspecialidade;
	private JButton limparButton;
	private JButton okButton;
	private JButton cancelButton;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxEstados;
	private JButton btnAdicionarFoto;
	
	@SuppressWarnings("rawtypes")
	public FrmCozinheiroGerenciar() {
		setTitle("Gerenciar Cozinheiros");
		setBounds(100, 100, 680, 524);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setResizable(false);//Não deixa maximizar a tla
		setLocationRelativeTo(null);// abre janela no centro da tela
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 12, 70, 18);
		contentPanel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(166, 12, 162, 22);
		//txtNome.setText("Nome");
		txtNome.setToolTipText("Nome");
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(12, 44, 87, 18);
		contentPanel.add(lblSobrenome);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setToolTipText("Sobrenome");
		//txtSobrenome.setText("Sobrenome");
		txtSobrenome.setColumns(10);
		txtSobrenome.setBounds(166, 44, 162, 22);
		contentPanel.add(txtSobrenome);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(12, 77, 148, 18);
		contentPanel.add(lblDataDeNascimento);
		
		txtDataNascimento = new JFormattedTextField(Mascara("##/##/####"));
		txtDataNascimento.setBounds(166, 77, 132, 22);
		contentPanel.add(txtDataNascimento);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(12, 113, 148, 18);
		contentPanel.add(lblCpf);
		
		
		txtCpf = new JFormattedTextField(Mascara("###.###.###-##"));  
		txtCpf.setBounds(166, 113, 132, 22);
		contentPanel.add(txtCpf);
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setBounds(12, 145, 148, 18);
		contentPanel.add(lblRg);
		
		txtRg = new JFormattedTextField(Mascara("#.###.###-#"));
		txtRg.setBounds(166, 145, 132, 22);
		contentPanel.add(txtRg);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(12, 175, 148, 18);
		contentPanel.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(166, 175, 132, 22);
		contentPanel.add(txtTelefone);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setBounds(12, 209, 148, 18);
		contentPanel.add(lblCelular);
		
		txtCelular = new JTextField();
		txtCelular.setColumns(10);
		txtCelular.setBounds(166, 209, 132, 22);
		contentPanel.add(txtCelular);
		
		JLabel lblHistrico = new JLabel("Histórico:");
		lblHistrico.setBounds(356, 14, 70, 15);
		contentPanel.add(lblHistrico);
		
		JScrollPane scrollAreaHistorico = new JScrollPane();
		scrollAreaHistorico.setBounds(356, 36, 306, 59);
		txtAreaHistorico = new JTextArea();
		txtAreaHistorico.setToolTipText("Escreva um breve histórico do cozinheiro");
		//txtAreaHistorico.setBounds(356, 36, 306, 59);
		txtAreaHistorico.setLineWrap(true);//quebra linha
		scrollAreaHistorico.getViewport().setView(txtAreaHistorico);
		//contentPanel.add(txtAreaHistorico);
		contentPanel.add(scrollAreaHistorico);
		
		JLabel lblNewLabel = new JLabel("Especialidade:");
		lblNewLabel.setBounds(356, 115, 107, 15);
		contentPanel.add(lblNewLabel);
		
		JScrollPane scrollAreaEspecialidade = new JScrollPane();
		scrollAreaEspecialidade.setBounds(356, 134, 306, 59);
		txtAreaEspecialidade = new JTextArea();
		txtAreaEspecialidade.setToolTipText("Escreva a(s) especialidade(s) do cozinheiro");
		//txtAreaEspecialidade.setBounds(356, 134, 306, 59);
		txtAreaEspecialidade.setLineWrap(true);
		scrollAreaEspecialidade.getViewport().setView(txtAreaEspecialidade);
		//contentPanel.add(txtAreaEspecialidade);
		contentPanel.add(scrollAreaEspecialidade);
		
		JLabel lblFoto = new JLabel("Foto:");
		lblFoto.setBounds(356, 359, 47, 15);
		contentPanel.add(lblFoto);
		
		txtFoto = new JTextField();
		txtFoto.setEditable(false);
		txtFoto.setColumns(10);
		txtFoto.setBounds(406, 357, 148, 24);
		contentPanel.add(txtFoto);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 255, 326, 15);
		contentPanel.add(separator);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(12, 282, 70, 15);
		contentPanel.add(lblCep);
		
		txtCep = new JTextField();
		//txtCep.setText("CEP");
		txtCep.setBounds(100, 280, 132, 22);
		contentPanel.add(txtCep);
		txtCep.setColumns(10);
		
		JLabel lblNmero = new JLabel("Número:");
		lblNmero.setBounds(12, 313, 70, 15);
		contentPanel.add(lblNmero);
		
		txtNumero = new JTextField();
		//txtNumero.setText("Número");
		txtNumero.setColumns(10);
		txtNumero.setBounds(100, 311, 132, 22);
		contentPanel.add(txtNumero);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setBounds(12, 347, 70, 15);
		contentPanel.add(lblRua);
		
		txtRua = new JTextField();
		//txtRua.setText("Rua");
		txtRua.setColumns(10);
		txtRua.setBounds(100, 345, 198, 24);
		contentPanel.add(txtRua);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(12, 380, 70, 15);
		contentPanel.add(lblEstado);
		
		comboBoxEstados = new JComboBox();
		comboBoxEstados.setBounds(100, 375, 60, 24);
		contentPanel.add(comboBoxEstados);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(12, 411, 70, 15);
		contentPanel.add(lblCidade);
		
		txtCidade = new JTextField();
		//txtCidade.setText("Cidade");
		txtCidade.setBounds(100, 409, 198, 24);
		contentPanel.add(txtCidade);
		txtCidade.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(12, 442, 70, 15);
		contentPanel.add(lblBairro);
		
		txtBairro = new JTextField();
		//txtBairro.setText("Bairro");
		txtBairro.setColumns(10);
		txtBairro.setBounds(100, 440, 198, 22);
		contentPanel.add(txtBairro);
		
		btnAdicionarFoto = new JButton("Adicionar");
		btnAdicionarFoto.setBounds(562, 354, 100, 25);
		contentPanel.add(btnAdicionarFoto);
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
	public MaskFormatter Mascara(String Mascara){  
        
	       MaskFormatter F_Mascara = new MaskFormatter();  
	       try{  
	           F_Mascara.setMask(Mascara); //Atribui a mascara  
	           F_Mascara.setPlaceholderCharacter(' '); //Caracter para preencimento   
	       }  
	       catch (Exception excecao) {  
	       excecao.printStackTrace();  
	       }   
	       return F_Mascara;  
	}

	public JTextField getTxtNome(){
		return txtNome;
	}
	public JTextField getTxtSobrenome(){
		return txtSobrenome;
	}
	public JTextField getTxtDataNascimento(){
		return txtDataNascimento;
	}
	public JFormattedTextField getTxtCpf(){
		return txtCpf;
	}
	public JFormattedTextField getTxtRg(){
		return txtRg;
	}
	public JTextField getTxtTelefone(){
		return txtTelefone;
	}
	public JTextField getTxtCelular(){
		return txtCelular;
	}
	public JTextField getTxtFoto(){
		return txtFoto;
	}
	public JTextField getTxtCep(){
		return txtCep;
	}
	public JTextField getTxtNumero(){
		return txtNumero;
	}
	public JTextField getTxtRua(){
		return txtRua;
	}
	public JTextField getTxtCidade(){
		return txtCidade;
	}
	public JTextField getTxtBairro(){
		return txtBairro;
	}
	public JTextArea getTxtAreaHistorico(){
		return txtAreaHistorico;
	}
	public JTextArea getTxtAreaEspecialidade(){
		return txtAreaEspecialidade;
	}
	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxEstados(){
		return comboBoxEstados;
	}
	public JButton getBtnAdicionarFoto(){
		return btnAdicionarFoto;
	}
	public JButton getLimparButton(){
		return limparButton;
	}
	public JButton getOkButton(){
		return okButton;
	}
	public JButton getCancelButton(){
		return cancelButton;
	}
}
