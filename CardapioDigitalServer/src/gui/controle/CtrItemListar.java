package gui.controle;

import gui.modelo.FrmItemListar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import util.ItemTableModel;

import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.item.ItemDAO;

import bean.Cozinheiro;
import bean.Item;

public class CtrItemListar implements Controle {
	private List <Item> listaItens;
	private FrmItemListar form;
	private Controle ctrParent;
	private ItemDAO itemDAO;
	
	public CtrItemListar (Controle ctrParent) {
		this.ctrParent = ctrParent;
		form = new FrmItemListar();
		adicionarListeners();
	}
	
	@Override
	public void setVisible(boolean b) {
		atualizarTabela();
		form.setVisible(b);
	}
	
	private void atualizarTabela() {
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		itemDAO = factory.getItemDAO();
		try {
			listaItens = itemDAO.listar();
		} catch (SQLException e) {
			System.out.println("Erro ao listar cozinheiros");
		}
		//coloca lista na tabela
		if(listaItens != null && !listaItens.isEmpty()){
			ItemTableModel model = new ItemTableModel(listaItens,false);
			form.getTabelaItens().setModel(model);
			//só dexa selecionar uma linha por vz
			form.getTabelaItens().setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		}
	}
	
	private void adicionarListeners() {
		final Controle controle = this;
		form.getCancelButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				form.dispose();
			}
		});
		
		form.getBtnIncluir().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				form.setVisible(false);//TODO testar
				CtrItemGerenciar ctr = new CtrItemGerenciar(controle);
				ctr.setVisible(true);
				
			}
		});
		
		form.getBtnVerEditar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(form.getTabelaItens().getSelectedRow()>=0){
					Item itemParaEditar = 
							listaItens.get(form.getTabelaItens().getSelectedRow());
					form.setVisible(false);
					CtrItemGerenciar ctr = new CtrItemGerenciar(controle, itemParaEditar);
					ctr.setVisible(true);
					
				}else{
					JOptionPane.showMessageDialog(null, "Clique sobre a linha do item\nque deseja ver / editar");
				}
			}
			
		});
		
		form.getBtnExcluir().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(form.getTabelaItens().getSelectedRow()>=0){
					Item itemParaExcluir = 
							listaItens.get(form.getTabelaItens().getSelectedRow());
					DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
					itemDAO = factory.getItemDAO();
					boolean excluiu = false;
					try {
						excluiu = itemDAO.exluir(itemParaExcluir);
					} catch (SQLException e1) {
						System.out.println("Erro ao exlcuir item"+e1);
						JOptionPane.showMessageDialog(null, "Erro ao excluir Item!");
					}
					if(excluiu){
						atualizarTabela();
						form.getTabelaItens().repaint();
						JOptionPane.showMessageDialog(null, "Item excluído com sucesso!");
					}else{
						JOptionPane.showMessageDialog(null, "Item não excluído!");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Clique sobre a linha do item\nque deseja excluir");
				}
			}
			
		});
	}

}
