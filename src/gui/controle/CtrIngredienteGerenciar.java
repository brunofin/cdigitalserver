package gui.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import util.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.ingrediente.IngredienteDAO;

import bean.Ingrediente;

import gui.modelo.FrmIngredienteGerenciar;

public class CtrIngredienteGerenciar implements Controle {
	private FrmIngredienteGerenciar form;
	private Controle ctrParent;
	private Ingrediente ingredienteParaEditar;
	private boolean editando;
	private int idIngrediente;
	
	public CtrIngredienteGerenciar(Controle ctrParent) {
		form = new FrmIngredienteGerenciar();
		this.ctrParent = ctrParent;
		editando = false;
		configurar();
		adicionarListeners();
	}
	
	public CtrIngredienteGerenciar(Controle ctrParent, Ingrediente ingredienteParaEditar) {
		form = new FrmIngredienteGerenciar();
		this.ctrParent = ctrParent;
		editando = true;
		idIngrediente = ingredienteParaEditar.getIngredienteId();
		form.getBtnLimpar().setVisible(false);
		form.getOkButton().setText("Editar");
		this.ingredienteParaEditar = ingredienteParaEditar;
		form.getTextAreaDescricao().setText(ingredienteParaEditar.getDescricao());
		form.getTextFieldNome().setText(ingredienteParaEditar.getNome());
		form.getTextFieldPreco().setText(Float.toString(ingredienteParaEditar.getPreco()));
		configurar();
		adicionarListeners();
	}
	
	private void configurar() {
		form.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		form.setTitle("Gerenciar Ingredientes");
		
		form.getComboBoxCurrency().setModel(new DefaultComboBoxModel<Moeda>(Moeda.values()));
		form.getComboBoxUnidade().setModel(new DefaultComboBoxModel<Unidade>(Unidade.values()));
	}
	
	private void adicionarListeners() {
		form.getBtnLimpar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				form.getTextFieldNome().setText("");
				form.getTextAreaDescricao().setText("");
				form.getTextFieldPreco().setText("");
			}
		});
		
		form.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrParent.setVisible(true);
				form.dispose();
			}
		});
		
		form.getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Ingrediente i = new Ingrediente();
				if(editando){
					i.setIngredienteId(idIngrediente);
				}
				i.setNome(form.getTextFieldNome().getText());
				i.setDescricao(form.getTextAreaDescricao().getText());
				try {
					i.setPreco(Float.parseFloat(form.getTextFieldPreco().getText()));
					
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(form, "O campo texto não possui um formato numérico, favor verificar.\n\n" + ex.getMessage());
					return;
				}
				
				DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
				IngredienteDAO dao = factory.getIngredienteDAO();
				if(editando){//TODO testar
					boolean alterou = false;
					try {
						alterou = dao.alterar(i);
					} catch (SQLException e1) {
						System.out.println("<CtrIngredienteGerenciar> Erro ao alterar Ingrediente: " + e1.getMessage());
						e1.printStackTrace();
					}
					if(alterou){
						JOptionPane.showMessageDialog
						(null, "Ingrediente editado com sucesso","Sucesso",JOptionPane.INFORMATION_MESSAGE);
						ctrParent.setVisible(true);
						form.dispose();
						return;
					}else{
						JOptionPane.showMessageDialog
						(null, "Ingrediente não foi editado","Erro",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				try {
					dao.incluir(i);
				} catch(SQLException ex) {
					System.out.println("<CtrIngredienteGerenciar> Erro ao incluir Ingrediente: " + ex.getMessage());
				}
				
				ctrParent.setVisible(true);
				form.dispose();
			}
		});
	}
	
	public void setVisible(boolean b) {
		form.setVisible(b);
	}
}
