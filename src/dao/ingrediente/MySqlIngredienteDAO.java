package dao.ingrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Ingrediente;
import dao.factory.MySqlDAOFactory;

public class MySqlIngredienteDAO extends MySqlDAOFactory implements
		IngredienteDAO {

	@Override
	public int incluir(Ingrediente i) throws SQLException {
		Connection con = getConnection();
        Statement stmt =  con.createStatement();
        int resultado = stmt.executeUpdate("INSERT INTO ingrediente " +
        				"(nome,descricao,preco) " +
        				"VALUES ('"+i.getNome()+"','"
        				+i.getDescricao()+"','"+i.getPreco()+"')");
        stmt.close();
        con.close();
        return resultado;
	}

	@Override
	public boolean excluir(Ingrediente i) throws SQLException {
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		int resultado = stmt.executeUpdate("DELETE FROM ingrediente WHERE id_ingrediente=" 
							+i.getIngredienteId());
		stmt.close();
		con.close();
		if(resultado==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterar(Ingrediente i) throws SQLException {
		Connection con = getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE ingrediente SET nome = ?," +
                " descricao = ?, preco= ? WHERE id_ingrediente = ?");
        stmt.setString(1, i.getNome());  //nome
        stmt.setString(2, i.getDescricao());  //desc
        stmt.setFloat(3, i.getPreco()); //preco
        stmt.setInt(4, i.getIngredienteId());//id_ingrediente
        
        int modificou=stmt.executeUpdate();
        stmt.close();
        con.close();
        if(modificou==1){
            return true;
        }
        return false;
	}

	@Override
	public Ingrediente consultarId(Ingrediente i) throws SQLException {
		Ingrediente resultado = new Ingrediente();           
        Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM ingrediente WHERE id_ingrediente="
        								+i.getIngredienteId());
        while (rs.next()){
        	resultado.setIngredienteId(rs.getInt("id_ingrediente"));
        	resultado.setNome(rs.getString("nome"));
        	resultado.setDescricao(rs.getString("descricao"));
        	resultado.setPreco(rs.getFloat("preco"));
        }
        stmt.close();
        con.close();
        return resultado;
	}

	@Override
	public List<Ingrediente> consultarTitulo(Ingrediente i) throws SQLException {
		Ingrediente resultado;
		List <Ingrediente> ingredientes = new ArrayList <Ingrediente>();
		Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM ingrediente WHERE nome LIKE ('%" 
        							+i.getNome()+ "%')");
        while (rs.next()){
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
	public List<Ingrediente> listar() throws SQLException {
		Ingrediente resultado;
		List <Ingrediente> ingredientes = new ArrayList <Ingrediente>();
		Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM ingrediente");
        while (rs.next()){
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
	public void criarTabela() throws SQLException {
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS ingrediente (" +
                " id_ingrediente INTEGER (7) AUTO_INCREMENT NOT NULL," +
                " nome VARCHAR (40) NOT NULL," +
                " descricao VARCHAR (60)," +
                " preco DECIMAL (5,2)," +
                " PRIMARY KEY (id_ingrediente))");
		stmt.close();
		con.close();
	}

	@Override
	public List<Ingrediente> consultarPorItemId(int itemId) throws SQLException {
		//FIXME talvez vá para a item_ingrediente
		return null;
	}

}
