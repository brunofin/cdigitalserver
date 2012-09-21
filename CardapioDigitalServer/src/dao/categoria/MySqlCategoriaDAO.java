package dao.categoria;

import java.sql.SQLException;
import java.util.List;

import bean.Categoria;
import dao.factory.MySqlDAOFactory;

public class MySqlCategoriaDAO extends MySqlDAOFactory implements CategoriaDAO {

	@Override
	public int incluir(Categoria c) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exluir(Categoria c) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Categoria c) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Categoria consultarId(Categoria c) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> consultarTitulo(Categoria c) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela() throws SQLException {
		// TODO Auto-generated method stub

	}

}
