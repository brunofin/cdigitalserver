package dao.bebida;

import java.sql.SQLException;
import java.util.List;

import bean.Bebida;

import dao.factory.MySqlDAOFactory;

public class MySqlBebidaDAO extends MySqlDAOFactory implements BebidaDAO {

	@Override
	public int incluir(Bebida b) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exluir(Bebida b) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Bebida b) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bebida consultarId(Bebida b) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bebida> consultarTitulo(Bebida b) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bebida> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela() throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
}