package dao.item;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import bean.Item;
import dao.factory.MySqlDAOFactory;

public class MySqlItemDAO extends MySqlDAOFactory implements ItemDAO {

	@Override
	public int incluir(Item i) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exluir(Item i) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Item i) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Item consultarId(Item i) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> consultarTitulo(Item i) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela() throws SQLException {//TODO testar
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS item (" +
                " id_item INTEGER (7) AUTO_INCREMENT NOT NULL," +
                " nome VARCHAR (40) NOT NULL," +
                " descricao VARCHAR (60) NOT NULL," +
                " preco DECIMAL (8,2) NOT NULL," +
                " id_categoria INTEGER (7) NOT NULL," +
                " PRIMARY KEY (id_item)," +
                " FOREIGN KEY (id_categoria) REFERENCES categoria (id_categoria))");
		stmt.close();
		con.close();
	}
}