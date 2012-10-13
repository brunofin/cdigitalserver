package util;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import bean.Ingrediente;

public class IngredienteTableModel extends AbstractTableModel {

	public enum UNIDADE {
		Mililitros, Miligramas, Unidades
	};

	private List<Ingrediente> ingredientes;
	private List<Integer> quantidades;
	private List<UNIDADE> unidades;

	private String[] colunas = new String[] { "Nome", "Preço", "Quantidade",
			"Unidade" };

	public IngredienteTableModel() {
		ingredientes = new LinkedList<Ingrediente>();
		quantidades = new LinkedList<Integer>();
		unidades = new LinkedList<UNIDADE>();
	}

	public IngredienteTableModel(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
		quantidades = new LinkedList<Integer>();
		unidades = new LinkedList<UNIDADE>();
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
			return i.getNome();
		case 1:
			return i.getPreco();
		case 2:
			return quantidades.get(rowIndex);
		case 3:
			return unidades.get(rowIndex);
		default:
			throw new IndexOutOfBoundsException("columnIndex fora dos limites.");
		}
	}

	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (rowIndex) {
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
		case 2:
			return Integer.class;
		case 3:
			return UNIDADE.class;
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
			quantidades.set(rowIndex, (Integer) aValue);
			break;
		case 3:
			unidades.set(rowIndex, (UNIDADE) aValue);
			break;
			default:
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	public boolean isEmpty() {
		return ingredientes.isEmpty() && quantidades.isEmpty() && unidades.isEmpty();
	}

}
