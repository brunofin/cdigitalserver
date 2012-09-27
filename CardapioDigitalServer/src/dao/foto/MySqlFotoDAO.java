package dao.foto;

import java.sql.SQLException;
import java.util.List;

import bean.Foto;
import dao.factory.MySqlDAOFactory;

public class MySqlFotoDAO extends MySqlDAOFactory implements FotoDAO {

	@Override
	public int incluir(Foto f) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exluir(Foto f) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Foto f) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Foto consultarId(Foto f) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Foto> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Foto> consultarPorItemId(int itemId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
