package dao.pedido;

import java.sql.SQLException;
import java.util.List;

import bean.Cliente;
import bean.Pedido;

public interface PedidoDAO {
	/**
	 * Inclui um novo pedido.
	 * 
	 * @param p pedido a ser incluído
	 * @return 1 ou mais se inseriu
	 * @throws SQLException
	 */
	public int incluir(Pedido p) throws SQLException;
	/**
	 * Exclui pedido pelo seu id (pedido_id)
	 * @param p
	 * @return
	 * @throws SQLException
	 */
    public boolean excluir(Pedido p) throws SQLException;
    public boolean alterar(Pedido p) throws SQLException;
    public Pedido consultarId(Pedido p) throws SQLException;
    /**
     * Consulta pedidos de um cliente.
     * 
     * @param c cliente com o seu id
     * preenchido
     * @return lista de pedidos feitos pelo
     * cliente
     * @throws SQLException
     */
    public List<Pedido> consultarPorClienteId(Cliente c) throws SQLException;
    public List<Pedido> listar() throws SQLException;
    public void criarTabela() throws SQLException;
}
