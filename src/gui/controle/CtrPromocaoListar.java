package gui.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import util.CozinheiroTableModel;
import util.PromocaoTableModel;
import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.promocao.PromocaoDAO;

import bean.Cozinheiro;
import bean.Promocao;

import gui.modelo.FrmPromocaoListar;

public class CtrPromocaoListar implements Controle {
	private FrmPromocaoListar form;
	private List <Promocao> listaPromocoes;
	private Controle ctrParent;
	private PromocaoDAO promocaoDAO;
	
	public CtrPromocaoListar(Controle ctrParent){
		this.ctrParent = ctrParent;
		form = new FrmPromocaoListar();
		adicionarListeners();
	}
	@Override
	public void setVisible(boolean b) {
		atualizarTabela();
		form.setVisible(b);
	}
	/**
	 * Busca promoções e joga na tabela
	 * de promoções
	 */
	private void atualizarTabela() {
		DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
		promocaoDAO = factory.getPromocaoDAO();
		try {
			listaPromocoes = promocaoDAO.listar();
		} catch (SQLException e) {
			System.out.println("Erro ao listar cozinheiros");
		}
		//coloca lista na tabela
		if(listaPromocoes != null && !listaPromocoes.isEmpty()){
			PromocaoTableModel model = new PromocaoTableModel(listaPromocoes);
			form.getTabelaPromocao().setModel(model);
			//só dexa selecionar uma linha por vz
			form.getTabelaPromocao().setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		}
	}
	private void adicionarListeners() {
		final Controle controle = this;
		//botao cancelar
		form.getCancelButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				form.dispose();
				
			}
			
		});
		//botao incluir
		form.getBtnIncluir().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CtrPromocaoCadastrar ctr = new CtrPromocaoCadastrar(controle,false,null);
				ctr.setVisible(true);
			}
			
		});
		//botao editar/ver
		form.getBtnEditarVer().addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(form.getTabelaPromocao().getSelectedRow()>=0){
					Promocao promocaoParaEditar = 
							listaPromocoes.get(form.getTabelaPromocao().getSelectedRow());
					CtrPromocaoCadastrar ctr = //new CtrPromocaoCadastrar(controle,true,)
							new CtrPromocaoCadastrar(controle,true,promocaoParaEditar);
					ctr.setVisible(true);
				}else{
					JOptionPane.showMessageDialog
					(null, "Clique sobre a linha da promoção\npara editar");
				}
				
			}
			
		});
		
		form.getBtnExcluir().addActionListener(new ActionListener(){//TODO testar

			@Override
			public void actionPerformed(ActionEvent e) {
				if(form.getTabelaPromocao().getSelectedRow()>=0){
					Promocao promocaoParaExcluir = 
							listaPromocoes.get(form.getTabelaPromocao().getSelectedRow());
					DAOFactory factory = DAOFactory.getDaoFactory(Database.MYSQL);
					promocaoDAO = factory.getPromocaoDAO();
					boolean excluiu = false;
					try {
						excluiu = promocaoDAO.excluir(promocaoParaExcluir);
					} catch (SQLException e1) {
						System.out.println("Erro ao exlcuir Promocao"+e1);
						JOptionPane.showMessageDialog(null, "Erro ao excluir promoção!"
								,"Erro na Exclusão",JOptionPane.ERROR_MESSAGE);
					}
					if(excluiu){
						atualizarTabela();
						form.getTabelaPromocao().repaint();
						JOptionPane.showMessageDialog(null, "Promoção excluída com sucesso!");
					}else{
						JOptionPane.showMessageDialog(null, "Promoção não excluída!"
								,"Erro na Exclusão",JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Clique sobre a linha da promoção\nque deseja excluir"
							,"Selecione a Promoção",JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
		});
	}
}
