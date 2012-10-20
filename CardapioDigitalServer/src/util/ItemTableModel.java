package util;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import bean.Ingrediente;
import bean.Item;

public class ItemTableModel extends AbstractTableModel {
	private List <Item> itens;
	private String[] colunas = new String[] { "Nome", "Descrição", "Preço"};
	private String[] colunasComQuantidade = new String[] {"Nome", "Descrição", "Preço", "Quantidade"};
	private boolean mostrarColunaQuantitidade;
	
	public ItemTableModel(){
		itens = new LinkedList <Item>();
	}
	public ItemTableModel(List<Item> itens, boolean mostrarColunaQuantitidade) {
		this.itens = itens;
		this.mostrarColunaQuantitidade = mostrarColunaQuantitidade;
	}
	@Override
	public int getRowCount() {
		return itens.size();
	}

	@Override
	public int getColumnCount() {
		if(mostrarColunaQuantitidade){
			return colunasComQuantidade.length;
		}
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Item i = itens.get(rowIndex);
		if(mostrarColunaQuantitidade){
			switch (columnIndex) {
			case 0:
				return (String) i.getNome();
			case 1:
				return (String) i.getDescricao();
			case 2:
				return (Float) i.getPreco();
			case 3:
				return (Integer) i.getQuantidadeItemPedido();
			default:
				throw new IndexOutOfBoundsException("columnIndex fora dos limites.");
			}
		}else{
			switch (columnIndex) {
			case 0:
				return (String) i.getNome();
			case 1:
				return (String) i.getDescricao();
			case 2:
				return (Float) i.getPreco();
			default:
				throw new IndexOutOfBoundsException("columnIndex fora dos limites.");
			}
		}
	}
	
	public String getColumnName(int columnIndex) {
		if(mostrarColunaQuantitidade){
			return colunasComQuantidade[columnIndex];
		}
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
			return true;
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
			return Float.class;
		case 3:
			return Integer.class;//coluna qtd
		default:
			return Object.class;
		}
	}
	
	public boolean isEmpty() {
		return itens.isEmpty();
	}
	
	public void setNumRows(int i) {
		itens.clear();
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
		case 1:
		case 2:
			break;
		case 3:
			itens.get(rowIndex).setQuantidadeItemPedido((Integer) aValue);

			break;
			default:
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}
}
