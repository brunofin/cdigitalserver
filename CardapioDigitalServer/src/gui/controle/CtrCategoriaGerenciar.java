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
	private boolean editando = false;
	private int idCategoria;
	
	public CtrCategoriaGerenciar(Controle ctrParent, boolean isEditar, Categoria categoriaParaEdicao) {
		this.ctrParent = ctrParent;
		form = new FrmCategoriaGerenciar();
		
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		categoriaDao = factory.getCategoriaDAO();
		tipoDao = factory.getTipoDAO();
		
		editando = isEditar;
		configurar();
		if(editando){
			idCategoria = categoriaParaEdicao.getCategoriaId();
			//idTipo = categoriaParaEdicao.getTipo().getTipoId();
			form.getBtnLimpar().setVisible(false);
			form.getOkButton().setText("Atualizar");
			form.getTextFieldNome().setText(categoriaParaEdicao.getNome());
			form.getTextAreaDescricao().setText(categoriaParaEdicao.getDescricao());
			for (int i = 0; i < form.getComboBoxTipo().getItemCount(); i++) {
				if(form.getComboBoxTipo().getItemAt(i).getNome().equals
						(categoriaParaEdicao.getTipo().getNome())){
					form.getComboBoxTipo().setSelectedIndex(i);
					form.repaint();
					break;
				}
			}
			
		}
		adicionarListeners();
	}
	
	private void configurar() {
		form.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		form.setTitle("Gerenciar Categorias");
		
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
				if(editando){
					Categoria c = new Categoria();
					c.setCategoriaId(idCategoria);
					c.setDescricao(form.getTextAreaDescricao().getText());
					c.setNome(form.getTextFieldNome().getText());
					c.setTipo((Tipo) form.getComboBoxTipo().getSelectedItem());//TODO ver se ta modificando
					
					try{
						categoriaDao.alterar(c);
					}catch(SQLException exe){
						System.out.println("<CtrCategoriaGerenciar> Erro ao alterar nova categoria: " + exe.getMessage());
					}
					ctrParent.setVisible(true);//OK
					
					
					form.dispose();	
					return;
				}else{
					Categoria c = new Categoria();
					c.setDescricao(form.getTextAreaDescricao().getText());
					c.setNome(form.getTextFieldNome().getText());
					c.setTipo((Tipo) form.getComboBoxTipo().getSelectedItem());
					
					try {
						categoriaDao.incluir(c);
					} catch(SQLException ex) {
						System.out.println("<CtrCategoriaGerenciar> Erro ao inserir nova categoria: " + ex.getMessage());
					}
					
					ctrParent.setVisible(true);//OK
					
					
					form.dispose();	
				}	
			}
		});
	}
	
	public void setVisible(boolean b) {
		form.setVisible(b);
	}
}
