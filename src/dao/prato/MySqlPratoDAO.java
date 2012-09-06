package dao.prato;

import java.sql.SQLException;
import java.util.List;

import bean.Prato;
import dao.factory.MySqlDAOFactory;

public class MySqlPratoDAO extends MySqlDAOFactory implements PratoDAO {

	@Override
	public int incluir(Prato p) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exluir(Prato p) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Prato p) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Prato consultarId(Prato p) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Prato> consultarTitulo(Prato p) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Prato> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela() throws SQLException {
		// TODO Auto-generated method stub

	}

}
