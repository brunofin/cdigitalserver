package dao.itempedido;

import java.sql.SQLException;
import java.util.List;

import bean.Item;
import bean.Pedido;

public interface ItemPedidoDAO {
	public int incluirItensPedido(Pedido p) throws SQLException;
	public boolean excluirItensPedido(Pedido p) throws SQLException;
	/**
	 * Consulta os itens de um pedido.
	 * os itens vem com todos os campos 
	 * preenchidos, inclusive os relativos aos
	 * pedidos (quantidadeItemPedido e 
	 * observacaoItemPedido)
	 * 
	 * @param p pedido apenas com o id
	 * preenchido
	 * @return
	 * @throws SQLException
	 */
	public List<Item> consultarItensPedido(Pedido p)throws SQLException;
	public void criarTabela()throws SQLException;
}
