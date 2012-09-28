package dao.foto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Foto;
import dao.factory.MySqlDAOFactory;

public class MySqlFotoDAO extends MySqlDAOFactory implements FotoDAO {

	@Override
	public int incluir(Foto f) throws SQLException {
		Connection con = getConnection();
        Statement stmt =  con.createStatement();
        int resultado = stmt.executeUpdate("INSERT INTO foto " +
        				"(local_foto)" +
        				"VALUES ('" +f.getLocal_foto()+ "')");
        stmt.close();
        con.close();
        return resultado;
	}

	@Override
	public boolean excluir(Foto f) throws SQLException {
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		int resultado = stmt.executeUpdate("DELETE FROM foto WHERE id_foto=" 
							+f.getFotoId());
		stmt.close();
		con.close();
		if(resultado==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterar(Foto f) throws SQLException {
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int resultado = stmt.executeUpdate("UPDATE foto SET local_foto= '" 
							+f.getLocal_foto()+"' WHERE id_foto="+f.getFotoId());
		stmt.close();
		con.close();
		if(resultado==1){
			return true;
		}
		return false;
	}

	@Override
	public Foto consultarId(Foto f) throws SQLException {
		Foto resultado = new Foto();           
        Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM foto WHERE id_foto = '" 
        				+ f.getFotoId() +"'");
        while (rs.next()){
        	resultado.setFotoId(rs.getInt("id_foto"));
            resultado.setLocal_foto(rs.getString("local_foto"));
        }
        stmt.close();
        con.close();
        return resultado;
	}

	@Override
	public List<Foto> listar() throws SQLException {
		Foto resultado;
		List <Foto> fotos = new ArrayList<Foto>();
		Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM foto;");
        while (rs.next()){
        	resultado = new Foto();
        	resultado.setFotoId(rs.getInt("id_foto"));
            resultado.setLocal_foto(rs.getString("local_foto"));
            fotos.add(resultado);
        }
        stmt.close();
        con.close();
        return fotos;
	}

	@Override
	public void criarTabela() throws SQLException {
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		//ResultSet rs = null;
		stmt.execute("CREATE TABLE IF NOT EXISTS foto (" +
                " id_foto INTEGER (7) AUTO_INCREMENT NOT NULL," +
                " local_foto VARCHAR (400) NOT NULL," +
                " PRIMARY KEY (id_foto))");
		stmt.close();
		con.close();
	}

	@Override
	public List<Foto> consultarPorItemId(int itemId) throws SQLException {
		//FIXME talvez passar para o DAO ITEM_FOTO
		return null;
	}

}
