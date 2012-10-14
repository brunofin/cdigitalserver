package gui.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JDialog;

import bean.Categoria;
import bean.Tipo;

import dao.categoria.CategoriaDAO;
import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.tipo.TipoDAO;

import gui.modelo.FrmCategoriaGerenciar;

public class CtrCategoriaGerenciar implements Controle {
	private FrmCategoriaGerenciar form;
	private Controle ctrParent;
	private CategoriaDAO categoriaDao;
	private TipoDAO tipoDao;
	
	public CtrCategoriaGerenciar(Controle ctrParent) {
		this.ctrParent = ctrParent;
		form = new FrmCategoriaGerenciar();
		
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		categoriaDao = factory.getCategoriaDAO();
		tipoDao = factory.getTipoDAO();
		
		configurar();
		adicionarListeners();
	}
	
	private void configurar() {
		form.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		List<Tipo> listaTipo = null;
		try {
			listaTipo = tipoDao.listar();
		} catch(SQLException e) {
			System.out.println("<CtrCategoriaGerenciar> Erro ao listar Tipos: " + e.getMessage());
		}
		for(Tipo tipo : listaTipo) {
			form.getComboBoxTipo().addItem(tipo);
		}
		
	}
	
	private void adicionarListeners() {
		form.getBtnTipoGerenciar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrTipoGerenciar ctr = new CtrTipoGerenciar(ctrParent);
				ctr.setVisible(true);
				form.setVisible(false);
			}
		});
		
		form.getBtnLimpar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				form.getTextFieldNome().setText("");
				form.getTextAreaDescricao().setText("");
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
				Categoria c = new Categoria();
				c.setDescricao(form.getTextAreaDescricao().getText());
				c.setNome(form.getTextFieldNome().getText());
				c.setTipo((Tipo) form.getComboBoxTipo().getSelectedItem());
				
				try {
					categoriaDao.incluir(c);
				} catch(SQLException ex) {
					System.out.println("<CtrCategoriaGerenciar> Erro ao inserir nova categoria: " + ex.getMessage());
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
