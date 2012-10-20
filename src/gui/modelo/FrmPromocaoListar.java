package gui.modelo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class FrmPromocaoListar extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JButton btnExcluir;
	private JButton btnEditarVer;
	private JButton btnIncluir;
	private JTable tabelaPromocao;
	private JButton cancelButton;
	
	public FrmPromocaoListar(){
		setTitle("Lista de Promocões");
		setBounds(100, 100, 680, 530);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 89, 562, 279);
		contentPanel.add(scrollPane);
		tabelaPromocao = new JTable();
		scrollPane.setViewportView(tabelaPromocao);
		
		JLabel lblTabelaDePromoes = new JLabel("Tabela de Promoções");
		lblTabelaDePromoes.setBounds(54, 62, 197, 15);
		contentPanel.add(lblTabelaDePromoes);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(499, 380, 117, 25);
		contentPanel.add(btnExcluir);
		
		btnEditarVer = new JButton("Editar / Ver");
		btnEditarVer.setBounds(370, 380, 117, 25);
		contentPanel.add(btnEditarVer);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(241, 380, 117, 25);
		contentPanel.add(btnIncluir);
		this.setResizable(false);//Não deixa maximizar a tla
		setLocationRelativeTo(null);// abre janela no centro da tela
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public JButton getBtnExcluir(){
		return btnExcluir;
	}
	public JButton getBtnEditarVer(){
		return btnEditarVer;
	}
	public JButton getBtnIncluir(){
		return btnIncluir;
	}
	public JTable getTabelaPromocao(){
		return tabelaPromocao;
	}
	public JButton getCancelButton(){
		return cancelButton;
	}
}
