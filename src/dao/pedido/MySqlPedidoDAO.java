package dao.pedido;

import java.sql.SQLException;
import java.util.List;

import bean.Pedido;
import dao.factory.MySqlDAOFactory;

public class MySqlPedidoDAO extends MySqlDAOFactory implements PedidoDAO {

	@Override
	public int incluir(Pedido p) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exluir(Pedido p) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Pedido p) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pedido consultarId(Pedido p) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> consultarTitulo(Pedido p) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela() throws SQLException {
		// TODO Auto-generated method stub

	}

}
