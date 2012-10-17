package gui.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

import util.ItemTableModel;

import gui.modelo.FrmItemSelecionar;
import bean.Item;

public class CtrItemSelecionar implements Controle {
	private FrmItemSelecionar form;
	private Controle ctrParent;
	private List <Item> listaTodosOsItens;
	private List <Item> itensDaPromocao;
	
	public CtrItemSelecionar(Controle ctrParent, List <Item> todosOsItens, List<Item> itensDaPromocao) {
		this.ctrParent = ctrParent;
		this.listaTodosOsItens = todosOsItens;
		form = new FrmItemSelecionar();
		this.itensDaPromocao = itensDaPromocao;
		if(this.itensDaPromocao == null || this.itensDaPromocao.isEmpty()){
			this.itensDaPromocao = new ArrayList <Item>();
		}
		configurar();
		adicionarListeners();
		
		
	}
	private void adicionarListeners() {
		
		form.getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//insere os itens selecionados dentro da lista itensDaPromocao
				int itensSelecionados [] = form.getTabelaItens().getSelectedRows();
				if(itensSelecionados.length > 0){//algum item foi selecionado na lista
					for (int i = 0; i < itensSelecionados.length; i++) {
						itensDaPromocao.add(listaTodosOsItens.get(itensSelecionados[i]));
						//TODO testar
						itensDaPromocao.get(itensDaPromocao.size()-1).setQuantidadeItemPedido(1);//seta qtd em 1
					}
					//OK
					CtrPromocaoCadastrar ctr = (CtrPromocaoCadastrar) ctrParent;
					//seta os itens selecionados dentro da promocao da tela PromocaoCadastrar
					ctr.getPromocao().setItens(itensDaPromocao);
					ctr.setVisible(true);
					form.dispose();
				}else{	
					JOptionPane.showMessageDialog(null, "Nenhum item selecionado, \n" +
						"favor selecionar pelo menos um ou cancelar","Nenhum item selecionado",JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		
		form.getCancelButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				form.dispose();
				
			}
			
		});
	}
	private void configurar() {
		//form.setDefaultCloseOperation(JDialog.);
		form.setTitle("Itens para a Promoção");
		//insere todos os itens na tabela de itens
		ItemTableModel model = new ItemTableModel(listaTodosOsItens,false);
		form.getTabelaItens().setModel(model);
		TableColumn column = null;
	    //fixa largura das colunas
		for(int i = 0; i < 2; i++) {
	      column = form.getTabelaItens().getColumnModel().getColumn(i);
	      if(i == 0){
	        column.setPreferredWidth(180);//nome
	      }else if(i == 1){
	        column.setPreferredWidth(330);//descricao
	      }else {
	    	  column.setPreferredWidth(66);//preco
	      }
	    }
	}
	@Override
	public void setVisible(boolean b) {
		form.setVisible(b);

	}

}
