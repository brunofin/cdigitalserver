package dao.cliente;

import java.sql.SQLException;
import java.util.List;

import bean.Cliente;
import dao.factory.MySqlDAOFactory;

public class MySqlClienteDAO extends MySqlDAOFactory implements ClienteDAO {

	@Override
	public int incluir(Cliente c) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exluir(Cliente c) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Cliente c) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cliente consultarId(Cliente c) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> consultarTitulo(Cliente c) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela() throws SQLException {
		// TODO Auto-generated method stub

	}

}
