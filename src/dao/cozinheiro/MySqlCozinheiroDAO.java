package dao.cozinheiro;

import java.sql.SQLException;
import java.util.List;

import bean.Cozinheiro;
import dao.factory.MySqlDAOFactory;

public class MySqlCozinheiroDAO extends MySqlDAOFactory implements
		CozinheiroDAO {

	@Override
	public int incluir(Cozinheiro c) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exluir(Cozinheiro c) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Cozinheiro c) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cozinheiro consultarId(Cozinheiro c) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cozinheiro> consultarTitulo(Cozinheiro c) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cozinheiro> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela() throws SQLException {
		// TODO Auto-generated method stub

	}

}
