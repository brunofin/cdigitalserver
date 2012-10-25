package gui.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import bean.Categoria;
import bean.Tipo;

import dao.categoria.CategoriaDAO;
import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.tipo.TipoDAO;

import gui.modelo.FrmCategoriaListar;
import gui.modelo.FrmTipoListar;


public class CtrTipoListar implements Controle {
	private FrmTipoListar frm;
	private Controle ctrParent;
	private TipoDAO tipoDAO;
	private List <Tipo> listaTipo;
	
	public CtrTipoListar(Controle ctrParent){
		this.ctrParent = ctrParent;
		frm = new FrmTipoListar();
		
		adicionarListeners();
	}
	@Override
	public void setVisible(boolean b) {
		atualizarTabela();
		frm.setVisible(b);
	}
	@SuppressWarnings("unchecked")
	private void atualizarTabela() {
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		tipoDAO = factory.getTipoDAO();
		
		try {
			listaTipo = tipoDAO.listar();
		} catch (SQLException e) {
			System.out.println("<CtrTipoListar> Erro ao listar Tipos");
		}
		DefaultListModel<Tipo> model = new DefaultListModel<Tipo>();
		if(!listaTipo.isEmpty()){
			for (Tipo t : listaTipo) {
				model.addElement(t);
			}
		}
		frm.getListTipos().setModel(model);
		frm.getListTipos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	private void adicionarListeners(){
		final Controle aux = this;
		//botao fechar
		frm.getBtnFechar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//frm.dispose();
				ctrParent.setVisible(true);//TODO testar
				frm.dispose();
			}
			
		});

		//botao cadastrar
		frm.getBtnCadastrarNovo().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frm.setVisible(false);
				CtrTipoGerenciar ctr = new CtrTipoGerenciar(aux);
						
				ctr.setVisible(true);
						
			}
					
		});
		//botao editar
		frm.getBtnEditarSelecionado().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(frm.getListTipos().getSelectedIndex()>=0){
					Tipo tipoEditar = 
							listaTipo.get(frm.getListTipos().getSelectedIndex());
					frm.setVisible(false);
					CtrTipoGerenciar ctr = new CtrTipoGerenciar(aux,tipoEditar);
					
					ctr.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, 
							"Selecione um tipo da lista para poder editar"
							,"Selecione um Tipo",JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		
		frm.getBtnExcluirSelecionado().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(frm.getListTipos().getSelectedIndex()>=0){
					Tipo tipoExcluir = 
							listaTipo.get(frm.getListTipos().getSelectedIndex());
					//verifica se nao tem nenhuma categoria dentro desse tipo
					DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
					CategoriaDAO categoriaDAO = factory.getCategoriaDAO();
					List <Categoria> categorias = null;
					try {
						categorias = categoriaDAO.listar();
					} catch (SQLException e1) {
						System.out.println("<CtrTipoListar> Erro ao listar categoria: " + e1.getMessage());
						return;
					}
					if(categorias !=null
							&& !categorias.isEmpty()){
						for(Categoria c : categorias){
							if(c.getTipo().getTipoId() == tipoExcluir.getTipoId()){
								JOptionPane.showMessageDialog
								(null, "Erro ao excluir tipo, deletar \nas categorias desse tipo primeiro"
										,"Erro ao excluir Tipo",JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}
					try {
						tipoDAO.exluir(tipoExcluir);
					} catch (SQLException e1) {
						System.out.println("<CtrTipoListar> Erro ao excluir tipo: " + e1.getMessage());
					}
					JOptionPane.showMessageDialog(null, "Tipo Excluído com sucesso","Sucesso",JOptionPane.INFORMATION_MESSAGE);
					atualizarTabela();
					
				}else{
					JOptionPane.showMessageDialog(null, 
							"Selecione um tipo da lista para poder excluir"
							,"Selecione um Tipo",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
}
