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
	public List<Ingrediente> consultarPorItemId(int idItem) throws SQLException {//TODO testar
		Ingrediente resultado;
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		Connection con = getConnection();
		ResultSet rs = null;
		Statement stmt = con.createStatement();
		rs = stmt
				.executeQuery("SELECT i.id_ingrediente, i.nome, i.descricao, " +
						"i.preco from ingrediente as i inner join " +
						"item_ingrediente WHERE i.id_ingrediente=" +
						"item_ingrediente.id_ingrediente " +
						"AND item_ingrediente.id_item="+ idItem);
		while (rs.next()) {
			resultado = new Ingrediente();
			resultado.setIngredienteId(rs.getInt("id_ingrediente"));
			resultado.setNome(rs.getString("nome"));
			resultado.setDescricao(rs.getString("descricao"));
			resultado.setPreco(rs.getFloat("preco"));
			ingredientes.add(resultado);
		}
		stmt.close();
		con.close();
		return ingredientes;
	}

	@Override
	public int inserir(List<Ingrediente> listaIngredientes, int idItem)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean excluir(List<Ingrediente> listaIngredientes, int idItem)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterarIngredientes(Item i) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
