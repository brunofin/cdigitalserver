package dao.endereco;

import java.sql.SQLException;
import java.util.List;

import bean.Endereco;
import dao.factory.MySqlDAOFactory;

public class MySqlEnderecoDAO extends MySqlDAOFactory implements EnderecoDAO {

	@Override
	public int incluir(Endereco e) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exluir(Endereco e) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Endereco e) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Endereco consultarId(Endereco e) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Endereco> consultarTitulo(Endereco e) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Endereco> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela() throws SQLException {
		// TODO Auto-generated method stub

	}

}
