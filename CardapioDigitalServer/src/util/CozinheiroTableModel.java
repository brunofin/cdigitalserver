package util;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import bean.Cozinheiro;

@SuppressWarnings("serial")
public class CozinheiroTableModel extends AbstractTableModel {
	private String colunas [] = {"Nome", "Sobrenome", "Telefone", "Celular"};
	private List <Cozinheiro> cozinheiros;
	//construtor
	public CozinheiroTableModel() {
		cozinheiros = new LinkedList <Cozinheiro>();
	}
	//construtor
	public CozinheiroTableModel(List<Cozinheiro> listaCozinheiros) {  
		this();  
        cozinheiros = listaCozinheiros;  
    }
	@Override
	public int getRowCount() {
		return cozinheiros.size();
	}
	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cozinheiro c = cozinheiros.get(rowIndex);
		
		switch (columnIndex) {
			case 0:
				return (String) c.getNome();
			case 1:
				return (String) c.getSobrenome();
			case 2:
				return (String) c.getTelefone();
			case 3:
				return (String) c.getCelular();
			default:
				throw new IndexOutOfBoundsException("columnIndex fora dos limites.");
		}
	}
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {//TODO testar
		switch (columnIndex) {
		case 0:
			return false;
		case 1:
			return false;
		case 2:
			return false;
		case 3:
			return false;
		default:
			return false;
		}
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		default:
			return Object.class;
		}
	}
	
	
	
	
    /** 
     * Similar ao {@link #adiciona(Cliente)}, porém para remover. Recebe o 
     * índice do cliente a ser removido. Se não souber o índice, use o método 
     * {@link #getIndice(Cliente)} antes. 
     */  
    public void remove(int indice) {  
        cozinheiros.remove(indice);  
        fireTableRowsDeleted(indice, indice);  
    }  
  
    /** 
     * Retorna o índice de determinado cliente. 
     */  
    public int getIndice(Cozinheiro c) {  
        return cozinheiros.indexOf(c);  
    }  
  
    /** 
     * Adiciona todos os clientes na lista à este modelo. 
     */  
    public void adicionaLista(List<Cozinheiro> lista) {  
        int i = cozinheiros.size();  
        cozinheiros.addAll(lista);  
        fireTableRowsInserted(i, i + lista.size());  
    }  
  
//    /** 
//     * Esvazia a lista. 
//     */  
//    public void limpaLista() {  
//        int i = cozinheiros.size();  
//        cozinheiros.clear();  
//        fireTableRowsDeleted(0, i - 1);  
//    }  
  


}
