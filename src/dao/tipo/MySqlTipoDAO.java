package dao.tipo;

import java.sql.SQLException;
import java.util.List;

import bean.Tipo;
import dao.factory.MySqlDAOFactory;

public class MySqlTipoDAO extends MySqlDAOFactory implements TipoDAO {

	@Override
	public int incluir(Tipo t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exluir(Tipo t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Tipo t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tipo consultarId(Tipo t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tipo> consultarTitulo(Tipo t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tipo> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela() throws SQLException {
		// TODO Auto-generated method stub

	}

}
