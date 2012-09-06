package dao.pedido;

import java.sql.SQLException;
import java.util.List;

import bean.Pedido;

public interface PedidoDAO {
	public int incluir(Pedido p) throws SQLException;
    public boolean exluir(Pedido p) throws SQLException;
    public boolean alterar(Pedido p) throws SQLException;
    public Pedido consultarId(Pedido p) throws SQLException;
    public List<Pedido> consultarTitulo(Pedido p) throws SQLException;
    public List<Pedido> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
