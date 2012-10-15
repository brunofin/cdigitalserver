package gui.modelo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

import bean.Cliente;

public class FrmClienteListar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList<Cliente> listClientes;
	private JButton btnCadastrarNovo;
	private JButton btnEditarSelecionado;
	private JButton btnExcluirSelecionado;
	private JLabel labelTotal;
	private JButton btnFechar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmClienteListar dialog = new FrmClienteListar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmClienteListar() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblListaDeClientes = new JLabel("Lista de clientes cadastrados:");
			lblListaDeClientes.setBounds(12, 12, 276, 15);
			contentPanel.add(lblListaDeClientes);
		}
		{
			listClientes = new JList<Cliente>();
			listClientes.setBounds(12, 26, 218, 197);
			contentPanel.add(listClientes);
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
		
		JLabel lblTotalDeClientes = new JLabel("Total:");
		lblTotalDeClientes.setBounds(248, 208, 76, 15);
		contentPanel.add(lblTotalDeClientes);
		
		labelTotal = new JLabel("999");
		labelTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTotal.setBounds(358, 208, 70, 15);
		contentPanel.add(labelTotal);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnFechar = new JButton("Fechar");
			buttonPane.add(btnFechar);
		}
	}
	public JList<Cliente> getListClientes() {
		return listClientes;
	}
	public JButton getBtnCadastrar() {
		return btnCadastrarNovo;
	}
	public JButton getBtnEditar() {
		return btnEditarSelecionado;
	}
	public JButton getBtnExcluir() {
		return btnExcluirSelecionado;
	}
	public JLabel getLabelTotal() {
		return labelTotal;
	}
	public JButton getBtnFechar() {
		return btnFechar;
	}
}
