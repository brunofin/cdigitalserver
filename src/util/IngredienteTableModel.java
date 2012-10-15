package util;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import bean.Ingrediente;

public class IngredienteTableModel extends AbstractTableModel {

	private List<Ingrediente> ingredientes;
	private List<Unidade> unidades;

	private String[] colunas = new String[] { "Nome", "Preço", "Quantidade",
			"Unidade" };

	public IngredienteTableModel() {
		ingredientes = new LinkedList<Ingrediente>();
		unidades = new LinkedList<Unidade>();
	}

	public IngredienteTableModel(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
		unidades = new LinkedList<Unidade>();
	}

	@Override
	public int getRowCount() {
		return ingredientes.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Ingrediente i = ingredientes.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return (String) i.getNome();
		case 1:
			return (Float) i.getPreco();
		case 2:
			return (Integer) i.getQuantidade();
		case 3:
			return (Unidade) Unidade.Miligramas;
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
		case 1:
			return false;
		default:
			return true;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return Float.class;
		case 2:
			return Integer.class;
		case 3:
			return Unidade.class;
		default:
			return Object.class;
		}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
		case 1:
			break;
		case 2:
			ingredientes.get(rowIndex).setQuantidade((Integer) aValue);
			break;
		case 3:
			unidades.add((Unidade) aValue);
			break;
			default:
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	public boolean isEmpty() {
		return ingredientes.isEmpty() && unidades.isEmpty();
	}

}
