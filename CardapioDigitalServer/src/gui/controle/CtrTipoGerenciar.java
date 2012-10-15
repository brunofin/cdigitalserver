package gui.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JDialog;

import bean.Tipo;

import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.tipo.TipoDAO;

import gui.modelo.FrmTipoGerenciar;

public class CtrTipoGerenciar implements Controle {
	private FrmTipoGerenciar form;
	private Controle ctrParent;
	private TipoDAO tipoDao;
	
	public CtrTipoGerenciar(Controle ctrParent) {
		this.ctrParent = ctrParent;
		form = new FrmTipoGerenciar();
		
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		tipoDao = factory.getTipoDAO();
		
		configurar();
		adicionarListeners();
	}
	
	private void configurar() {
		form.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		form.setTitle("Gerenciar Tipos");
	}
	
	private void adicionarListeners() {
		form.getBtnLimpar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				form.getTextFieldNome().setText("");	
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
				Tipo t = new Tipo();
				
				t.setNome(form.getTextFieldNome().getText());
				
				try {
					tipoDao.incluir(t);
				} catch(SQLException ex) {
					System.out.println("<CtrTipoGerenciar> Erro ao inserir novo Tipo: " + ex.getMessage());
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