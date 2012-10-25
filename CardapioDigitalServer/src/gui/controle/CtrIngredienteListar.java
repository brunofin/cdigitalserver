package gui.controle;

import gui.modelo.FrmCategoriaListar;
import gui.modelo.FrmIngredienteListar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.ingrediente.IngredienteDAO;
import dao.itemingrediente.ItemIngredienteDAO;

import bean.Categoria;
import bean.Ingrediente;

public class CtrIngredienteListar implements Controle {
	private FrmIngredienteListar frm;
	private Controle ctrParent;
	private IngredienteDAO ingredienteDAO;
	private List <Ingrediente> listaIngrediente;
	private ItemIngredienteDAO itemIngredienteDAO;
	private DAOFactory factory;
	
	public CtrIngredienteListar(Controle ctrParent) {
		this.ctrParent = ctrParent;
		frm = new FrmIngredienteListar();
		
		configurar();
		adicionarListeners();
	}
	private void configurar(){
		factory = DAOFactory.getDaoFactory(Database.MYSQL);
		ingredienteDAO = factory.getIngredienteDAO();
	}
	@SuppressWarnings("unchecked")
	private void atualizarTabela(){
		
		try {
			listaIngrediente = ingredienteDAO.listar();
		} catch (SQLException e) {
			System.out.println("Erro ao listar Categorias");
		}
		DefaultListModel<Ingrediente> model = new DefaultListModel<Ingrediente>();
		if(!listaIngrediente.isEmpty()){
			for (Ingrediente i : listaIngrediente) {
				model.addElement(i);
			}
		}
		frm.getListIngredientes().setModel(model);
		frm.getListIngredientes().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@Override
	public void setVisible(boolean b) {
		atualizarTabela();
		frm.setVisible(b);
	}
	
	private void adicionarListeners() {
		final Controle aux = this;
		//botao fechar
		frm.getBtnFechar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frm.dispose();
			}
		});
		//btao cadastrar novo
		frm.getBtnCadastrarNovo().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frm.setVisible(false);
				CtrIngredienteGerenciar ctr = new CtrIngredienteGerenciar(aux);
				ctr.setVisible(true);
			}
		});
		frm.getBtnExcluirSelecionado().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(frm.getListIngredientes().getSelectedIndex()>=0){
					Ingrediente ingredienteExcluir = 
							listaIngrediente.get(frm.getListIngredientes().getSelectedIndex());
					//verifica se nenhum item usa esse ingrediente;
					itemIngredienteDAO = factory.getItemIngredienteDAO();
					boolean ingredienteEhUsado = false;
					try {
						ingredienteEhUsado = 
								itemIngredienteDAO.ingredienteEhUsado(ingredienteExcluir.getIngredienteId());
					} catch (SQLException e1) {
						System.out.println
						("<CtrIngredienteListar> Erro ao verificar se ingrediente " +
								"é usado para poder excluí-lo: "+e1.getMessage());
						return;
					}
					if(ingredienteEhUsado){
						JOptionPane.showMessageDialog(null, "Não foi possível deletar o ingrediente selecionado.\n" +
								"o mesmo é utilizado por um item, favor deletar o item primeiro","Ingrediente não foi deletado"
								,JOptionPane.WARNING_MESSAGE);
						return;
					}else{
						boolean excluiu = false;
						try {
							excluiu = ingredienteDAO.excluir(ingredienteExcluir);
						} catch (SQLException e1) {
							System.out.println
							("<CtrIngredienteListar> Erro ao excluir ingrediente "+e1.getMessage());
						}
						if(excluiu){
							JOptionPane.showMessageDialog(null, "Ingrediente excluído com sucesso","Sucesso"
									,JOptionPane.INFORMATION_MESSAGE);
							atualizarTabela();
							return;
						}else{
							JOptionPane.showMessageDialog(null, "Não foi possível deletar o ingrediente selecionado.\n" +
								"o mesmo é utilizado por um item, favor deletar o item primeiro","Ingrediente não foi deletado"
								,JOptionPane.WARNING_MESSAGE);
						}	
					}
				}	
				
			}
		});
		
		frm.getBtnEditarSelecionado().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(frm.getListIngredientes().getSelectedIndex()>=0){
					Ingrediente ingredienteEditar = 
							listaIngrediente.get(frm.getListIngredientes().getSelectedIndex());
					frm.setVisible(false);
					CtrIngredienteGerenciar ctr = new CtrIngredienteGerenciar(aux, ingredienteEditar);
					
					ctr.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, 
							"Selecione um ingrediente da lista para poder editar"
							,"Selecione um Ingrediente",JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
		});
	}

}
