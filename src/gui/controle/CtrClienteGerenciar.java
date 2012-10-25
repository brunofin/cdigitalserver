package gui.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.Normalizer.Form;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import dao.cliente.ClienteDAO;
import dao.factory.DAOFactory;

import servidor.Configuracao;
import util.Estado;

import gui.modelo.FrmClienteGerenciar;
import bean.Cliente;
import bean.Endereco;

public class CtrClienteGerenciar implements Controle {
	
	private FrmClienteGerenciar form;
	private Controle parent;
	private boolean editando;
	private Cliente cliente;
	
	public CtrClienteGerenciar(Controle parent) {
		this.parent = parent;
		cliente = new Cliente();
		form = new FrmClienteGerenciar();
		editando = false;
		
		configurar();
		adicionarListeners();
	}
	
	public CtrClienteGerenciar(Controle parent, Cliente cliente) {
		this.parent = parent;
		this.cliente = cliente;
		form = new FrmClienteGerenciar();
		editando = true;
		
		configurar();
		adicionarListeners();
	}
	
	private void configurar() {
		form.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		form.getComboBoxEstado().setModel(new DefaultComboBoxModel<Estado>(Estado.values()));
		if(editando) {
			form.setTitle("Editar cliente :: " + cliente.getNome());
			
			form.getTextFieldNome().setText(cliente.getNome());
			form.getTextFieldSobrenome().setText(cliente.getSobrenome());
			form.getTextFieldEmail().setText(cliente.getEmail());
			form.getTextFieldRG().setText(cliente.getRg());
			form.getTextFieldCPF().setText(cliente.getCpf());
			
			form.getTxtDia().setText(cliente.getDataNascimento().get(Calendar.DAY_OF_MONTH) + "");
			form.getTxtMes().setText(cliente.getDataNascimento().get(Calendar.MONTH) + 1 + "");
			form.getTxtAno().setText(cliente.getDataNascimento().get(Calendar.YEAR) + "");
			
			form.getTextFieldRua().setText(cliente.getEndereco().getRua());
			form.getTextFieldNumero().setText(cliente.getEndereco().getNumero());
			form.getTextFieldCEP().setText(cliente.getEndereco().getCep());
			form.getTextFieldBairro().setText(cliente.getEndereco().getBairro());
			form.getTextFieldCidade().setText(cliente.getEndereco().getCidade());
			form.getComboBoxEstado().setSelectedItem(cliente.getEndereco().getEstado());
		} else {
			form.setTitle("Cadastro de novo cliente");
		}
		
		
		
	}
	
	private void adicionarListeners() {
		form.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				form.dispose();
			}
		});
		
		form.getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente c = (editando? cliente : new Cliente());
				
				c.setNome(form.getTextFieldNome().getText());
				c.setSobrenome(form.getTextFieldSobrenome().getText());
				c.setEmail(form.getTextFieldEmail().getText());
				c.setRg(form.getTextFieldRG().getText());
				c.setCpf(form.getTextFieldCPF().getText());
				
				Calendar ca = Calendar.getInstance();
				try {
					ca.set(Calendar.DAY_OF_MONTH, Integer.parseInt(form.getTxtDia().getText()));
					ca.set(Calendar.MONTH, Integer.parseInt(form.getTxtMes().getText()) - 1);
					ca.set(Calendar.YEAR, Integer.parseInt(form.getTxtAno().getText()));
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(form, "Um dos itens na data de nascimento não é um número." +
							"Por favor verifique antes de continuar.");
					return;
				}
				c.setDataNascimento(ca);
				
				Endereco en = (editando? c.getEndereco() : new Endereco());
				en.setRua(form.getTextFieldRua().getText());
				en.setNumero(form.getTextFieldNumero().getText());
				en.setCep(form.getTextFieldCEP().getText());
				en.setBairro(form.getTextFieldBairro().getText());
				en.setCidade(form.getTextFieldCidade().getText());
				en.setEstado((Estado) form.getComboBoxEstado().getSelectedItem());
				c.setEndereco(en);
				
				ClienteDAO dao = DAOFactory.getDaoFactory(Configuracao.DB_SELECIONADO).getClienteDAO();
				
				try {
					if(editando)
						dao.alterar(c);
					else
						dao.incluir(c);
				} catch(SQLException ex) {
					System.out.println("<CtrClienteGerenciar> Erro ao incluir ou alterar cliente: " + ex.getMessage());
				}
				
				parent.setVisible(true);
				form.dispose();
			}
		});
	}

	@Override
	public void setVisible(boolean b) {
		form.setVisible(true);
	}

}
