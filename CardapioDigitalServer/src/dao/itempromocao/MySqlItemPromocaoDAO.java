package dao.itempromocao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Item;
import bean.Promocao;
import dao.factory.MySqlDAOFactory;

public class MySqlItemPromocaoDAO extends MySqlDAOFactory implements
		ItemPromocaoDAO {
	public static final int TAMANHO_QUANTIDADE = 3;
	@Override
	public int inserirItensPromocao(Promocao p) throws SQLException {//OK
		StringBuffer query = 
				new StringBuffer("INSERT INTO item_promocao " +
						"(id_promocao, id_item, quantidade) " +
						"VALUES ");
		for(Item i : p.getItens()){
			query.append("("+p.getPromocaoId()+","+i.getItemId()+","
					+i.getQuantidadeItemPedido()+")");
			if(i==p.getItens().get(p.getItens().size()-1)){
				query.append(";");
			}else{
				query.append(",");
			}
		}
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int incluiu = stmt.executeUpdate(query.toString());
		stmt.close();
		return incluiu;
	}

	@Override
	public boolean excluirItensPromocao(Promocao p) throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int excluiu = stmt.executeUpdate
				("DELETE FROM item_promocao WHERE id_promocao="+p.getPromocaoId());
		stmt.close();
		if(excluiu > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterarItensPromocao(Promocao p) throws SQLException {//OK
		//exclui itens antigos da promocao
		excluirItensPromocao(p);
		//inclui novos itens da promocao
		if(p.getItens()!=null && !p.getItens().isEmpty()){
			inserirItensPromocao(p);
		}
		
		return true;
		
	}

	@Override
	public List<Item> consultarItensPromocao(Promocao p) throws SQLException {//OK
		List <Item> itens = new ArrayList <Item>();
		Item item;
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery
				("SELECT * FROM item_promocao WHERE id_promocao="+p.getPromocaoId());
		while(rs.next()){
			item = new Item();
			item.setItemId(rs.getInt("id_item"));
			//Consulta todos os dados do item (categoria,tipo,fotos,ingredientes);
			item = getItemDAO().consultarId(item);
			//seta no item a quantidade que foi pedido dele no pedido
			item.setQuantidadeItemPedido(rs.getInt("quantidade"));
			itens.add(item);
		}
		return itens;
	}

	@Override
	public void criarTabela() throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS item_promocao (" +
				"id_promocao INTEGER (7) NOT NULL, " +
				"id_item INTEGER (7) NOT NULL, " +
				"quantidade INTEGER (3), " +
				"PRIMARY KEY (id_promocao, id_item))");
	}

}
