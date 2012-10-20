package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


import bean.Cozinheiro;
import bean.Promocao;

public class PromocaoTableModel extends AbstractTableModel {
	private String colunas [] = {"Nome", "Início", "Validade", "Descrição"};
	private List <Promocao> promocoes;
	//construtor
	public PromocaoTableModel(List <Promocao> listaPromocoes) {  
		promocoes = listaPromocoes;  
	}
	@Override
	public int getRowCount() {
		return promocoes.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Promocao p = promocoes.get(rowIndex);
		
		switch (columnIndex) {
			case 0:
				return (String) p.getNome();
			case 1:
				return (String) formatarData(p.getDataInicio());
			case 2:
				return (String) formatarData(p.getValidade());
			case 3:
				return (String) p.getDescricao();
			default:
				throw new IndexOutOfBoundsException("columnIndex fora dos limites.");
		}
	}
	
	private String formatarData(Calendar data){
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		return formatoData.format(data.getTime());
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

}
