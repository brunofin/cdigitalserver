package gui.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import bean.Categoria;
import bean.Item;
import dao.categoria.CategoriaDAO;
import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.item.ItemDAO;
import gui.modelo.FrmCategoriaListar;

public class CtrCategoriaListar implements Controle {
	private FrmCategoriaListar frm;
	private Controle ctrParent;
	private CategoriaDAO categoriaDAO;
	private List <Categoria> listaCategoria;
	private ItemDAO itemDAO;
	
	
	public CtrCategoriaListar(Controle ctrParent){
		this.ctrParent = ctrParent;
		frm = new FrmCategoriaListar();
		
		adicionarListeners();
	}
	@SuppressWarnings("unchecked")
	private void atualizarTabela(){
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		categoriaDAO = factory.getCategoriaDAO();
		
		try {
			listaCategoria = categoriaDAO.listar();
		} catch (SQLException e) {
			System.out.println("Erro ao listar Categorias");
		}
		DefaultListModel<Categoria> model = new DefaultListModel<Categoria>();
		if(!listaCategoria.isEmpty()){
			for (Categoria c : listaCategoria) {
				model.addElement(c);
			}
		}
		frm.getListCategorias().setModel(model);
		frm.getListCategorias().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	@Override
	public void setVisible(boolean b) {
		atualizarTabela();
		frm.setVisible(b);
	}

	private void adicionarListeners() {
		final Controle aux = this;
		//botao fechar
		frm.getBtnFechar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frm.dispose();
				
			}
			
		});
		//botao cadastrar
		frm.getBtnCadastrarNovo().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frm.setVisible(false);
				CtrCategoriaGerenciar ctr = new CtrCategoriaGerenciar(aux);
				
				ctr.setVisible(true);
				
			}
			
		});
		//botado editar
		frm.getBtnEditarSelecionado().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(frm.getListCategorias().getSelectedIndex()>=0){
					Categoria categoriaEditar = 
							listaCategoria.get(frm.getListCategorias().getSelectedIndex());
					frm.setVisible(false);
					CtrCategoriaGerenciar ctr = new CtrCategoriaGerenciar(aux, categoriaEditar);
					
					ctr.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, 
							"Selecione uma categoria da lista para poder editar"
							,"Selecione uma Categoria",JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
		});
		
		frm.getBtnExcluirSelecionado().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(frm.getListCategorias().getSelectedIndex()>=0){
					Categoria categoriaExcluir = 
							listaCategoria.get(frm.getListCategorias().getSelectedIndex());
					//verifica se nenhum item está na categoria
					DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
					itemDAO = factory.getItemDAO();
					List<Item>listaItens = null;
					try {
						listaItens = itemDAO.listar();
					} catch (SQLException e1) {
						System.out.println("<CtrCategoriaListar> Erro ao listar itens "+e1);
						return;
					}
					if(listaItens != null 
							&& !listaItens.isEmpty()){
						for(Item i : listaItens){
							if(i.getCategoria().equals(categoriaExcluir)){
								JOptionPane.showMessageDialog(null, 
										"Existem itens nessa categoria.\nFavor deleta-los primeiro."
										,"Não foi possivel deletar a categoria selecionada"
										,JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
					}
					//DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
					categoriaDAO = factory.getCategoriaDAO();
					boolean excluiu = false;
					try {
						excluiu = categoriaDAO.excluir(categoriaExcluir);
					} catch (SQLException e1) {
						System.out.println("<CtrCategoriaListar> Erro ao excluir categoria "+e1);
						return;
					}
					if(excluiu){
						JOptionPane.showMessageDialog(null, 
								"Categoria excluída"
								,"Sucesso",JOptionPane.INFORMATION_MESSAGE);
						atualizarTabela();
						return;
					}else{
						JOptionPane.showMessageDialog(null, 
								"Categoria não excluída","Categoria não excluída",JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, 
							"Selecione uma categoria da lista para poder excluir"
							,"Selecione uma Categoria",JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
		});
	}
}
