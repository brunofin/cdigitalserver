package gui.modelo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Categoria;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class FrmCategoriaListar extends JFrame {
	private final JPanel contentPanel = new JPanel();
	private JList listCategorias;
	private JButton btnCadastrarNovo;
	private JButton btnEditarSelecionado;
	private JButton btnExcluirSelecionado;
	private JButton btnFechar;
	private JScrollPane scrollPane;
	
	public FrmCategoriaListar(){
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblListaDeClientes = new JLabel("Lista de Categorias:");
			lblListaDeClientes.setBounds(12, 12, 276, 15);
			contentPanel.add(lblListaDeClientes);
		}
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 26, 218, 197);
		contentPanel.add(scrollPane);
		{
			listCategorias = new JList<Categoria>();
			scrollPane.setViewportView(listCategorias);
		}
		btnCadastrarNovo = new JButton("Cadastrar novo...");
		btnCadastrarNovo.setBounds(242, 22, 194, 25);
		contentPanel.add(btnCadastrarNovo);
		
		btnEditarSelecionado = new JButton("Editar selecionado...");
		btnEditarSelecionado.setBounds(242, 59, 194, 25);
		contentPanel.add(btnEditarSelecionado);
		
		btnExcluirSelecionado = new JButton("Excluir selecionado");
		btnExcluirSelecionado.setBounds(242, 96, 194, 25);
		contentPanel.add(btnExcluirSelecionado);
		this.setResizable(false);//Não deixa maximizar a tla
		setLocationRelativeTo(null);// abre janela no centro da tela
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnFechar = new JButton("Fechar");
			buttonPane.add(btnFechar);
		}
	}
	public JList getListCategorias(){
		return listCategorias;
	}
	public JButton getBtnCadastrarNovo(){
		return btnCadastrarNovo;
	}
	public JButton getBtnEditarSelecionado(){
		return btnEditarSelecionado;
	}
	public JButton getBtnExcluirSelecionado(){
		return btnExcluirSelecionado;
	}
	public JButton getBtnFechar(){
		return btnFechar;
	}
}
