package dao.item;

import java.sql.SQLException;
import java.util.List;

import bean.Item;
import dao.factory.MySqlDAOFactory;

public class MySqlPratoDAO extends MySqlDAOFactory implements ItemDAO {

	@Override
	public int incluir(Item i) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exluir(Item i) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Item i) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Item consultarId(Item i) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> consultarTitulo(Item i) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela() throws SQLException {
		// TODO Auto-generated method stub

	}
}