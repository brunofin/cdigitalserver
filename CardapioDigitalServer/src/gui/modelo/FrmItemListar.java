package gui.modelo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FrmItemListar extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDigiteONome;
	private JButton btnProcurar;
	private JButton btnExcluir;
	private JButton btnVerEditar;
	private JButton btnIncluir;
	private JButton cancelButton;
	private JTable tabelaItens;
	
	public FrmItemListar(){
		setTitle("Lista de Itens");
		setBounds(100, 100, 680, 530);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 77, 570, 341);
		tabelaItens = new JTable();
		scrollPane.setViewportView(tabelaItens);
		contentPanel.add(scrollPane);
		
		JLabel lblListaDeItens = new JLabel("Lista de Itens:");
		lblListaDeItens.setBounds(52, 50, 104, 15);
		contentPanel.add(lblListaDeItens);
		
		JLabel lblBuscar = new JLabel("Procurar:");
		lblBuscar.setBounds(322, 52, 70, 15);
		contentPanel.add(lblBuscar);
		
		txtDigiteONome = new JTextField();
		txtDigiteONome.setText("digite o nome");
		txtDigiteONome.setBounds(395, 45, 114, 26);
		contentPanel.add(txtDigiteONome);
		txtDigiteONome.setColumns(10);
		
		btnProcurar = new JButton("Procurar");
		btnProcurar.setBounds(517, 45, 105, 25);
		contentPanel.add(btnProcurar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(505, 430, 117, 25);
		contentPanel.add(btnExcluir);
		
		btnVerEditar = new JButton("Ver / Editar");
		btnVerEditar.setBounds(376, 430, 117, 25);
		contentPanel.add(btnVerEditar);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(247, 430, 117, 25);
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

	public JButton getBtnProcurar() {
		return btnProcurar;
	}
	public JButton getBtnExcluir() {
		return btnExcluir;
	}
	public JButton getBtnVerEditar() {
		return btnVerEditar;
	}
	public JButton getBtnIncluir() {
		return btnIncluir;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
	public JTable getTabelaItens() {
		return tabelaItens;
	}
}
