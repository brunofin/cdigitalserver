package gui.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import util.CozinheiroTableModel;

import dao.cozinheiro.CozinheiroDAO;
import dao.factory.DAOFactory;
import dao.factory.Database;
import gui.modelo.FrmCozinheiroListar;
import bean.Cozinheiro;

public class CtrCozinheiroListar implements Controle {
	private List <Cozinheiro> listaCozinheiros;
	private FrmCozinheiroListar form;
	private Controle ctrParent;
	private CozinheiroDAO cozinheiroDAO;
	
	
	public CtrCozinheiroListar(Controle ctrParent){
		this.ctrParent = ctrParent;
		form = new FrmCozinheiroListar();
		adicionarListeners();
	}
	
	@Override
	public void setVisible(boolean b) {
		atualizarTabela();
		form.setVisible(b);
	}
	
	private void atualizarTabela() {
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		cozinheiroDAO = factory.getCozinheiroDAO();
		try {
			listaCozinheiros = cozinheiroDAO.listar();
		} catch (SQLException e) {
			System.out.println("Erro ao listar cozinheiros");
		}
		//coloca lista na tabela
		if(listaCozinheiros != null && !listaCozinheiros.isEmpty()){
			CozinheiroTableModel model = new CozinheiroTableModel(listaCozinheiros);
			form.getTabelaCozinheiros().setModel(model);
			//só dexa selecionar uma linha por vz
			form.getTabelaCozinheiros().setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		}
	}
	
	private void adicionarListeners(){
		final Controle controle = this;
		//botao cancelar
		form.getCancelButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				form.dispose();
				
			}
			
		});
		//botao incluir
		form.getBtnIncluir().addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e) {
				CtrCozinheiroGerenciar ctr = new CtrCozinheiroGerenciar(controle,false,null);
				ctr.setVisible(true);
			}
		});
		form.getBtnEditar().addActionListener(new ActionListener (){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(form.getTabelaCozinheiros().getSelectedRow()>=0){
					Cozinheiro cozinheiroParaEditar = 
							listaCozinheiros.get(form.getTabelaCozinheiros().getSelectedRow());
					CtrCozinheiroGerenciar ctr = 
							new CtrCozinheiroGerenciar(controle, true ,cozinheiroParaEditar);
					ctr.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Clique sobre a linha do cozinheiro\npara editá-lo");
				}
				
			}
			
		});
		form.getBtnExcluir().addActionListener(new ActionListener (){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(form.getTabelaCozinheiros().getSelectedRow()>=0){
					Cozinheiro cozinheiroParaExcluir = 
							listaCozinheiros.get(form.getTabelaCozinheiros().getSelectedRow());
					DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
					cozinheiroDAO = factory.getCozinheiroDAO();
					boolean excluiu = false;
					try {
						excluiu = cozinheiroDAO.excluir(cozinheiroParaExcluir);
					} catch (SQLException e1) {
						System.out.println("Erro ao exlcuir cozinheiro"+e1);
						JOptionPane.showMessageDialog(null, "Erro ao excluir cozinheiro!");
					}
					if(excluiu){
						atualizarTabela();
						form.getTabelaCozinheiros().repaint();
						JOptionPane.showMessageDialog(null, "Cozinheiro excluído com sucesso!");
					}else{
						JOptionPane.showMessageDialog(null, "Cozinheiro não excluído!");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Clique sobre a linha do cozinheiro\nque deseja excluir");
				}
				
			}
			
		});
	}

}
