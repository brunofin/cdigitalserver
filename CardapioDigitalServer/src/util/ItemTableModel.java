package util;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import bean.Ingrediente;
import bean.Item;

public class ItemTableModel extends AbstractTableModel {
	private List <Item> itens;
	private String[] colunas = new String[] { "Nome", "Descrição", "Preço"};
	
	public ItemTableModel(){
		itens = new LinkedList <Item>();
	}
	public ItemTableModel(List<Item> itens) {
		this.itens = itens;
	}
	@Override
	public int getRowCount() {
		return itens.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Item i = itens.get(rowIndex);

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
	
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return false;
		case 1:
			return false;
		case 2:
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
			return Float.class;
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
}
