package dao.tipo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Tipo;
import dao.factory.MySqlDAOFactory;

public class MySqlTipoDAO extends MySqlDAOFactory implements TipoDAO {

	@Override
	public int incluir(Tipo t) throws SQLException {//ok     
        Connection con = getConnection();
        Statement stmt =  con.createStatement();
        int resultado = stmt.executeUpdate("INSERT INTO tipo (tipo_id,nome) " +
        					"VALUES ("+t.getTipoId()+",'"+t.getNome()+"')");
        //1 inseriu, 2 - nao inseriu
        stmt.close();
        con.close();
        return resultado;
	}

	@Override
	public boolean exluir(Tipo t) throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		int resultado = stmt.executeUpdate("DELETE FROM tipo WHERE tipo_id=" 
							+t.getTipoId());
		stmt.close();
		con.close();
		if(resultado==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterar(Tipo t) throws SQLException {//TODO testar
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int resultado = stmt.executeUpdate("UPDATE tipo SET nome= '" 
							+t.getNome()+"' WHERE tipo_id="+t.getTipoId());
		stmt.close();
		con.close();
		if(resultado==1){
			return true;
		}
		return false;
	}

	@Override
	public Tipo consultarId(Tipo t) throws SQLException {//OK
		Tipo resultado = new Tipo();           
        Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM tipo WHERE tipo_id = '" 
        				+ t.getTipoId() +"'");
        while (rs.next()){
        	resultado.setTipoId(rs.getInt("tipo_id"));
            resultado.setNome(rs.getString("nome"));
        }
        stmt.close();
        con.close();
        return resultado;
	}

	@Override
	public List<Tipo> consultarTitulo(Tipo t) throws SQLException {//OK
		Tipo resultado;
		List <Tipo> tipos = new ArrayList<Tipo>();
		Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM tipo WHERE nome = '" + t.getNome()+ "'");
        while (rs.next()){
        	resultado = new Tipo();
        	resultado.setTipoId(rs.getInt("tipo_id"));
            resultado.setNome(rs.getString("nome"));
            tipos.add(resultado);
        }
        stmt.close();
        con.close();
        return tipos;
	}

	@Override
	public List<Tipo> listar() throws SQLException {//OK
		Tipo resultado;
		List <Tipo> tipos = new ArrayList<Tipo>();
		Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM tipo;");
        while (rs.next()){
        	resultado = new Tipo();
        	resultado.setTipoId(rs.getInt("tipo_id"));
            resultado.setNome(rs.getString("nome"));
            tipos.add(resultado);
        }
        stmt.close();
        con.close();
        return tipos;
	}
	
	@Override
	public void criarTabela() throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		//ResultSet rs = null;
		stmt.execute("CREATE TABLE IF NOT EXISTS tipo (" +
                " tipo_id INTEGER (7) NOT NULL," +
                " nome VARCHAR (40)," +
                " PRIMARY KEY (tipo_id))");
		stmt.close();
		con.close();
	}
}
