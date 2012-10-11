package dao.itemingrediente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.factory.MySqlDAOFactory;

import bean.Ingrediente;
import bean.Item;

public class MySqlItemIngredienteDAO extends MySqlDAOFactory implements
		ItemIngredienteDAO {

	@Override
	public List<Ingrediente> consultarPorItemId(int idItem) throws SQLException {//OK
		Ingrediente resultado;
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		Connection con = getConnection();
		ResultSet rs = null;
		Statement stmt = con.createStatement();
		rs = stmt
				.executeQuery("SELECT i.id_ingrediente, i.nome, i.descricao, " +
						"i.preco, item_ingrediente.quantidade from ingrediente as i inner join " +
						"item_ingrediente WHERE i.id_ingrediente=" +
						"item_ingrediente.id_ingrediente " +
						"AND item_ingrediente.id_item="+ idItem);
		while (rs.next()) {
			resultado = new Ingrediente();
			resultado.setIngredienteId(rs.getInt("id_ingrediente"));
			resultado.setNome(rs.getString("nome"));
			resultado.setDescricao(rs.getString("descricao"));
			resultado.setPreco(rs.getFloat("preco"));
			resultado.setQuantidade(rs.getInt("quantidade"));
			ingredientes.add(resultado);
		}
		stmt.close();
		return ingredientes;
	}

	@Override
	public boolean excluir(Item i) throws SQLException {//TODO testar
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int excluiu = stmt.executeUpdate
				("DELETE FROM item_ingrediente WHERE id_item="+i.getItemId());
		stmt.close();
		if(excluiu>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterarIngredientes(Item i) throws SQLException {//OK
		//exclui todos os registros
		excluir(i);
		int alterou = 0;
		//registra tudo de novo
		if(i.getIngredientes()!=null && i.getIngredientes().size() > 0){
			alterou = inserir(i);
		}
		if(alterou > 0){
			return true;
		}
		return false;
	}

	@Override
	public void criarTabela() throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS item_ingrediente (" +
                " id_item INTEGER (7) NOT NULL," +
                " id_ingrediente INTEGER (7) NOT NULL," +
                " quantidade INTEGER (3)," +
                " PRIMARY KEY (id_item,id_ingrediente))");
		stmt.close();
	}

	@Override
	public int inserir(Item i) throws SQLException {//OK
		StringBuffer query = new StringBuffer
				("INSERT INTO item_ingrediente (id_ingrediente, id_item, quantidade) VALUES ");
		for(Ingrediente ing : i.getIngredientes()){
			query.append("("+ing.getIngredienteId()+","+i.getItemId()+","+ing.getQuantidade()+")");
			//caso seja o ultimo elemento da lista concatena ";" no fim da query;
			if(ing==i.getIngredientes().get(i.getIngredientes().size()-1)){
				query.append(";");
			}else{
				query.append(",");
			}
		}
		System.out.println("Teste query insert na tab itens: \n"+query);
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int inseriu = stmt.executeUpdate(query.toString());
		stmt.close();
		return inseriu;
	}

}
