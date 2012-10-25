package gui.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import bean.Tipo;

import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.tipo.TipoDAO;

import gui.modelo.FrmTipoGerenciar;

public class CtrTipoGerenciar implements Controle {
	private FrmTipoGerenciar form;
	private Controle ctrParent;
	private TipoDAO tipoDao;
	private int tipoId;
	private boolean editando = false;
	
	public CtrTipoGerenciar(Controle ctrParent,Tipo tipoParaEditar) {
		this.ctrParent = ctrParent;
		form = new FrmTipoGerenciar();
		
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		tipoDao = factory.getTipoDAO();
		
		editando = true;
		
		form.getBtnLimpar().setVisible(false);
		form.getOkButton().setText("Atualizar");
		tipoId = tipoParaEditar.getTipoId();
		form.getTextFieldNome().setText(tipoParaEditar.getNome());
		
		
		configurar();
		adicionarListeners();
	}
	
	public CtrTipoGerenciar(Controle ctrParent) {
		this.ctrParent = ctrParent;
		form = new FrmTipoGerenciar();
		editando = false;
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
				if(editando){
					Tipo tipo = new Tipo();
					tipo.setNome(form.getTextFieldNome().getText());
					tipo.setTipoId(tipoId);
					try {
						tipoDao.alterar(tipo);
					} catch (SQLException e1) {
						System.out.println("<CtrTipoGerenciar> Erro ao alterar Tipo: " + e1.getMessage());
						return;
					}
					JOptionPane.showMessageDialog(null, "Tipo Alterado");
					ctrParent.setVisible(true);
					form.dispose();
				}else{
					Tipo t = new Tipo();
					
					t.setNome(form.getTextFieldNome().getText());
					
					try {
						tipoDao.incluir(t);
					} catch(SQLException ex) {
						System.out.println("<CtrTipoGerenciar> Erro ao inserir novo Tipo: " + ex.getMessage());
					}
					JOptionPane.showMessageDialog(null, "Novo tipo incluído com sucesso");
					ctrParent.setVisible(true);
					form.dispose();
				}	
			}
		});
	}
	
	public void setVisible(boolean b) {
		form.setVisible(b);
	}
}