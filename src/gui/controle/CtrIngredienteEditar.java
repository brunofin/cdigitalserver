package gui.controle;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

import dao.factory.DAOFactory;
import dao.factory.Database;

import gui.modelo.FrmIngredienteEditar;
import bean.Ingrediente;

public class CtrIngredienteEditar {
	private Controle ctrParent;
	private FrmIngredienteEditar form;
	private List<Ingrediente> lista;
	
	public CtrIngredienteEditar(Controle ctrParent, List<Ingrediente> lista) {
		form = new FrmIngredienteEditar();
		this.ctrParent = ctrParent;
		this.lista = lista;
		
		configurar();
		adicionarListeners();
	}
	
	private void configurar() {
		DefaultListModel<Ingrediente> model = new DefaultListModel<Ingrediente>();
		List<Ingrediente> aux = null;
		try {
			aux = (DAOFactory.getDaoFactory(Database.MYSQL)).getIngredienteDAO().listar();
		} catch(SQLException e) {
			System.out.println("<CtrIngredienteEditar> Erro ao recuperar ingedientes: " + e.getMessage());
		}
		for(Ingrediente i : aux) {
			model.addElement(i);
		}
		form.getListIngredientes().setModel(model);
		form.getListIngredientes().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}
	
	private void adicionarListeners() {
		form.getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * preencher a lista com os Ingredientes selecionados e fechar a janela
				 * A janela anterior vai ter acesso à lista, já que ela é enviada por referencia e não por cópia
				 */
				lista.clear();
				List<Ingrediente> aux = form.getListIngredientes().getSelectedValuesList();
				for(Ingrediente i : aux) {
					lista.add(i);
				}
				ctrParent.setVisible(true);
				form.dispose();
			}
		});
		
		form.getCancelButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrParent.setVisible(true);
				form.dispose();
			}
		});
	}
	
	public void setVisible(boolean b) {
		form.setVisible(b);
	}
}
