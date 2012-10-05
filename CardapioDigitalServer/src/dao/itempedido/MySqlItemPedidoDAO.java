package dao.itempedido;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Item;
import bean.Pedido;
import dao.factory.MySqlDAOFactory;

public class MySqlItemPedidoDAO extends MySqlDAOFactory implements ItemPedidoDAO{

	@Override
	public int incluirItensPedido(Pedido p) throws SQLException {//TODO testar
		StringBuffer query = 
				new StringBuffer("INSERT INTO item_pedido " +
						"(id_pedido, id_item, observacao, quantidade) " +
						"VALUES ");
		for(Item i : p.getItens()){
			query.append("("+p.getPedidoId()+","+i.getItemId()+"," +
					"'"+i.getObservacaoItemPedido()+"',"+i.getQuantidadeItemPedido()+")");
			//caso seja o ultimo elemento da lista concatena ";" no fim da query;
			if(i==p.getItens().get(p.getItens().size()-1)){
				query.append(";");
			}else{
				query.append(",");
			}
		}
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int incluiu = stmt.executeUpdate(query.toString());//TODO testar
		stmt.close();
		return incluiu; 
	}

	@Override
	public boolean excluirItensPedido(Pedido p) throws SQLException {//TODO testar
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int excluiu = stmt.executeUpdate("DELETE * FROM item_pedido WHERE id_pedido="
				+p.getPedidoId());
		if(excluiu > 0){
			return true;
		}
		return false;
	}

	@Override
	public List<Item> consultarItensPedido(Pedido p) throws SQLException {//TODO TESTAR
		List <Item> listaItens = new ArrayList<Item>();
		Item i = null;
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM item_pedido WHERE id_pedido="+p.getPedidoId());
		while(rs.next()){
			i = new Item();
			i.setItemId(rs.getInt("id_item"));
			//Consulta todos os dados do item (categoria,tipo,fotos,ingredientes);
			i = getItemDAO().consultarId(i);
			//seta no objeto item a observacao que foi feita no pedido, q fica na tabela item_pedido
			i.setObservacaoItemPedido(rs.getString("observacao"));
			//seta no item a quantidade que foi pedido dele no pedido
			i.setQuantidadeItemPedido(rs.getInt("quantidade"));
			listaItens.add(i);
		}
		return listaItens;
	}

	@Override
	public void criarTabela() throws SQLException {//TODO testar
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS item_pedido (" +
				"id_pedido INTEGER (7) NOT NULL, " +
				"id_item INTEGER (7) NOT NULL, " +
				"observacao VARCHAR (100), " +
				"quantidade INTEGER (3)," +
				"PRIMARY KEY (id_pedido,id_item))");
	}

}
