package dao.itempromocao;

import java.sql.SQLException;
import java.util.List;

import bean.Item;
import bean.Promocao;

public interface ItemPromocaoDAO {
	public int inserirItensPromocao(Promocao p) throws SQLException; 
	public boolean excluirItensPromocao(Promocao p) throws SQLException;
	public boolean alterarItensPromocao(Promocao p) throws SQLException;
	public List <Item> consultarItensPromocao(Promocao p) throws SQLException;
	public void criarTabela() throws SQLException;
}
