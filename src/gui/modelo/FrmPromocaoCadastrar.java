package gui.modelo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class FrmPromocaoCadastrar extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField txtFoto;
	private JTextField txtNome;
	private JTextField txtDataInicio;
	private JTextField txtValidade;
	private JTextArea txtAreaDescricao;
	private JButton limparButton;
	private JButton okButton;
	private JButton cancelButton;
	private JButton adicionarFoto;
	private JTable tabelaItens;
	private JButton btnAdicionarItem;
	
	public FrmPromocaoCadastrar(){
		setTitle("Cadastro de Promoção");
		setBounds(100, 100, 680, 524);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setResizable(false);//Não deixa maximizar a tla
		setLocationRelativeTo(null);// abre janela no centro da tela
		
		JLabel lblFoto = new JLabel("Foto:");
		lblFoto.setBounds(12, 226, 47, 15);
		contentPanel.add(lblFoto);
		
		txtFoto = new JTextField();
		//txtFoto.setText("foto");
		txtFoto.setEditable(false);
		txtFoto.setColumns(10);
		txtFoto.setBounds(132, 221, 148, 26);
		contentPanel.add(txtFoto);
		
		adicionarFoto = new JButton("Adicionar");
		adicionarFoto.setBounds(292, 221, 100, 25);
		contentPanel.add(adicionarFoto);
		
		JLabel lblNome = new JLabel("Nome:*");
		lblNome.setBounds(12, 72, 61, 15);
		contentPanel.add(lblNome);
		
		JLabel lblDataDeIncio = new JLabel("Data de Início:*");
		lblDataDeIncio.setBounds(12, 122, 114, 15);
		contentPanel.add(lblDataDeIncio);
		
		JLabel lblValidade = new JLabel("Validade:");
		lblValidade.setBounds(12, 173, 114, 15);
		contentPanel.add(lblValidade);
		
		JLabel lblDescrio = new JLabel("Descrição:*");
		lblDescrio.setBounds(348, 72, 314, 15);
		contentPanel.add(lblDescrio);
		
		txtNome = new JTextField();
		//txtNome.setText("nome");
		txtNome.setBounds(132, 67, 148, 26);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		
		//txtDataInicio = new JTextField();
		txtDataInicio = new JFormattedTextField(Mascara("##/##/####"));
		txtDataInicio.setColumns(10);
		txtDataInicio.setBounds(132, 117, 148, 26);
		contentPanel.add(txtDataInicio);
		
		txtValidade = new JFormattedTextField(Mascara("##/##/####"));
		//txtValidade = new JTextField();
		txtValidade.setColumns(10);
		txtValidade.setBounds(132, 168, 148, 26);
		contentPanel.add(txtValidade);
		
		JScrollPane scrollAreaDescricao = new JScrollPane();
		scrollAreaDescricao.setBounds(348, 99, 314, 89);
		txtAreaDescricao = new JTextArea();
		//txtAreaDescricao.setText("Digite aqui a descrição da promoção");
		txtAreaDescricao.setToolTipText("Digite aqui a descrição da promoção");
		//txtAreaDescricao.setBounds(138, 342, 380, 69);
		txtAreaDescricao.setLineWrap(true);
		scrollAreaDescricao.getViewport().setView(txtAreaDescricao);
		//contentPanel.add(txtAreaDescricao);
		contentPanel.add(scrollAreaDescricao);
		
		JLabel lblcamposObrigatrios = new JLabel("*Campos Obrigatórios");
		lblcamposObrigatrios.setFont(new Font("Dialog", Font.BOLD, 8));
		lblcamposObrigatrios.setBounds(548, 24, 114, 15);
		contentPanel.add(lblcamposObrigatrios);
		
		JButton btnVer = new JButton("Ver Foto");
		
		btnVer.setBounds(407, 221, 100, 25);
		contentPanel.add(btnVer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(67, 303, 551, 89);
		contentPanel.add(scrollPane);
		
		tabelaItens = new JTable();
		scrollPane.getViewport().setView(tabelaItens);
		
		JLabel lblNewLabel = new JLabel("Itens da promoção:");
		lblNewLabel.setBounds(67, 276, 187, 15);
		contentPanel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 259, 650, 7);
		contentPanel.add(separator);
		
		btnAdicionarItem = new JButton("Adicionar Itens");
		btnAdicionarItem.setBounds(284, 404, 144, 25);
		contentPanel.add(btnAdicionarItem);
		
		JButton btnExcluirSelecionados = new JButton("Excluir Selecionados");
		btnExcluirSelecionados.setBounds(440, 404, 178, 25);
		contentPanel.add(btnExcluirSelecionados);
		
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
	private MaskFormatter Mascara(String Mascara){  
        
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
	public JTextField getTxtFoto(){
		return txtFoto;
	}
	public JTextField getTxtNome(){
		return txtNome;
	}
	public JTextField getTxtDataInico(){
		return txtDataInicio;
	}
	public JTextField getTxtValidade(){//Chamar metodo para tirar mascara
		return txtValidade;
	}
	public JTextArea getTxtAreaDescricao(){
		return txtAreaDescricao;
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
	public JButton getAdicionarFoto(){
		return adicionarFoto;
	}
	public JButton getBtnAdicionarItem(){
		return btnAdicionarItem;
	}
	public JTable getTabelaItens(){
		return tabelaItens;
	}
}
