package dao.promocao;

import java.sql.SQLException;
import java.util.List;

import bean.Promocao;
import dao.factory.MySqlDAOFactory;

public class MySqlPromocaoDAO extends MySqlDAOFactory implements PromocaoDAO {

	@Override
	public int incluir(Promocao p) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exluir(Promocao p) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Promocao p) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Promocao consultarId(Promocao p) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Promocao> consultarTitulo(Promocao p) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Promocao> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela() throws SQLException {
		// TODO Auto-generated method stub

	}

}
