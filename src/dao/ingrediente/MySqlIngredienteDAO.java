package dao.ingrediente;

import java.sql.SQLException;
import java.util.List;

import bean.Ingrediente;
import dao.factory.MySqlDAOFactory;

public class MySqlIngredienteDAO extends MySqlDAOFactory implements
		IngredienteDAO {

	@Override
	public int incluir(Ingrediente i) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exluir(Ingrediente i) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Ingrediente i) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Ingrediente consultarId(Ingrediente i) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingrediente> consultarTitulo(Ingrediente i) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ingrediente> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Ingrediente> consultarPorItemId(int itemId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
