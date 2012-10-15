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
	
	public CtrIngredienteGerenciar(Controle ctrParent) {
		form = new FrmIngredienteGerenciar();
		this.ctrParent = ctrParent;
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
