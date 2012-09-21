package dao.item;

import java.sql.SQLException;
import java.util.List;

import bean.Item;

public interface ItemDAO {
	public int incluir(Item i) throws SQLException;
    public boolean exluir(Item i) throws SQLException;
    public boolean alterar(Item i) throws SQLException;
    public Item consultarId(Item i) throws SQLException;
    public List<Item> consultarTitulo(Item i) throws SQLException;
    public List<Item> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
