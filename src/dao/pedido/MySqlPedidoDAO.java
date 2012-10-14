package dao.pedido;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.Cliente;
import bean.Pedido;
import dao.factory.MySqlDAOFactory;

public class MySqlPedidoDAO extends MySqlDAOFactory implements PedidoDAO {
	public static final int TAMANHO_DATA = 13;
	@Override
	public int incluir(Pedido p) throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		stmt.executeUpdate("INSERT INTO pedido (data,id_cliente) " +
				"VALUES ("+p.getData().getTimeInMillis()+"," +
						""+p.getCliente().getId()+")",
						Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
		int idPedidoInserido = 0;
		while(rs.next()){
			idPedidoInserido = rs.getInt(1);
		}
		if(idPedidoInserido != 0){
			p.setPedidoId(idPedidoInserido);
			int inseriuItens = getItemPedidoDAO().incluirItensPedido(p);
			if(inseriuItens > 0){
				return inseriuItens;
			}
		}
		return 0;
	}

	@Override
	public boolean excluir(Pedido p) throws SQLException {//OK
		//exclui itens do pedido na tebela item_pedido
		getItemPedidoDAO().excluirItensPedido(p);
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int excluiu = stmt.executeUpdate("DELETE FROM pedido WHERE id_pedido="+p.getPedidoId());
		stmt.close();
		if(excluiu > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterar(Pedido p) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pedido consultarId(Pedido p) throws SQLException {//OK
		Pedido pedido = new Pedido();
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM pedido WHERE id_pedido="+p.getPedidoId());
		while (rs.next()){
			pedido.setPedidoId(rs.getInt("id_pedido"));
			Calendar dataPedido = Calendar.getInstance();
        	dataPedido.setTimeInMillis(rs.getLong("data"));
        	pedido.setData(dataPedido);
			Cliente clientePedido = new Cliente();
			clientePedido.setId(rs.getInt("id_cliente"));
			//busca dados do cliente (e do endereço dele)
			pedido.setCliente(getClienteDAO().consultarId(clientePedido));
			//busca lista de itens
			pedido.setItens(getItemPedidoDAO().consultarItensPedido(p));
		}
		return pedido;
	}

	@Override
	public List<Pedido> listar() throws SQLException {//OK
		List <Pedido> listaPedidos = new ArrayList<Pedido>();
		Pedido pedido;
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM pedido");
		while (rs.next()){
			pedido = new Pedido();
			pedido.setPedidoId(rs.getInt("id_pedido"));
			Calendar dataPedido = Calendar.getInstance();
        	dataPedido.setTimeInMillis(rs.getLong("data"));
        	pedido.setData(dataPedido);
			Cliente clientePedido = new Cliente();
			clientePedido.setId(rs.getInt("id_cliente"));
			//busca dados do cliente (e do endereço dele)
			pedido.setCliente(getClienteDAO().consultarId(clientePedido));
			//busca lista de itens
			pedido.setItens(getItemPedidoDAO().consultarItensPedido(pedido));
			listaPedidos.add(pedido);
		}
		return listaPedidos;
	}

	@Override
	public void criarTabela() throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS pedido (" +
				"id_pedido INTEGER (7) AUTO_INCREMENT NOT NULL," +
				"data BIGINT (13) NOT NULL," +
				"id_cliente INTEGER (7) NOT NULL," +
				"PRIMARY KEY (id_pedido)," +
				"FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente))");
	}

	@Override
	public List<Pedido> consultarPorClienteId(Cliente c) throws SQLException {//OK
		List <Pedido> listaPedidos = new ArrayList<Pedido>();
		Pedido pedido;
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM pedido WHERE id_cliente="+c.getId());
		while (rs.next()){
			pedido = new Pedido();
			pedido.setPedidoId(rs.getInt("id_pedido"));
			Calendar dataPedido = Calendar.getInstance();
        	dataPedido.setTimeInMillis(rs.getLong("data"));
        	pedido.setData(dataPedido);
			Cliente clientePedido = new Cliente();
			clientePedido.setId(rs.getInt("id_cliente"));
			//busca dados do cliente (e do endereço dele)
			pedido.setCliente(getClienteDAO().consultarId(clientePedido));
			//busca lista de itens
			pedido.setItens(getItemPedidoDAO().consultarItensPedido(pedido));
			listaPedidos.add(pedido);
		}
		return listaPedidos;
	}

}
