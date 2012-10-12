package dao.foto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Foto;
import bean.Item;
import dao.factory.MySqlDAOFactory;

public class MySqlFotoDAO extends MySqlDAOFactory implements FotoDAO {

	@Override
	public int incluir(Foto f) throws SQLException {//OK
		Connection con = getConnection();
        Statement stmt =  con.createStatement();
        stmt.executeUpdate("INSERT INTO foto " +
        				"(local_foto)" +
        				"VALUES ('" +f.getLocal_foto()+ "')",
        				Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        int idRecemInserido = 0;
        while (rs.next()){
        	idRecemInserido = rs.getInt(1);
        }
        stmt.close();
        return idRecemInserido;
	}

	@Override
	public boolean excluir(Foto f) throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		int resultado = stmt.executeUpdate("DELETE FROM foto WHERE id_foto=" 
							+f.getFotoId());
		stmt.close();
		//con.close();
		if(resultado==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterar(Foto f) throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int resultado = stmt.executeUpdate("UPDATE foto SET local_foto= '" 
							+f.getLocal_foto()+"' WHERE id_foto="+f.getFotoId());
		stmt.close();
		//con.close();
		if(resultado==1){
			return true;
		}
		return false;
	}

	@Override
	public Foto consultarId(Foto f) throws SQLException {//OK
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
        return resultado;
	}

	@Override
	public List<Foto> listar() throws SQLException {//OK
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
        return fotos;
	}

	@Override
	public void criarTabela() throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		//ResultSet rs = null;
		stmt.execute("CREATE TABLE IF NOT EXISTS foto (" +
                " id_foto INTEGER (7) AUTO_INCREMENT NOT NULL," +
                " local_foto VARCHAR (400) NOT NULL," +
                " id_item INTEGER (7)," +
                " PRIMARY KEY (id_foto))");
		stmt.close();
	}

	@Override
	public List<Foto> consultarPorItemId(int itemId) throws SQLException {//OK
		List <Foto> fotos = new ArrayList <Foto>();
		Foto f = null;
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT * FROM foto WHERE id_item="+itemId);
		while(rs.next()){
			f = new Foto();
			f.setFotoId(rs.getInt("id_foto"));
			f.setLocal_foto(rs.getString("local_foto"));
			f.setItemId(rs.getInt("id_item"));
			fotos.add(f);
		}
		stmt.close();
		return fotos;
	}

	@Override
	public int incluirFotosItem(Item i) throws SQLException {//OK
		StringBuffer query = 
				new StringBuffer("INSERT INTO foto (local_foto, id_item) VALUES ");
		for(Foto f : i.getFoto()){
			query.append("('"+f.getLocal_foto()+"',"+i.getItemId()+")");
			//caso seja o ultimo elemento da lista concatena ";" no fim da query;
			if(f==i.getFoto().get(i.getFoto().size()-1)){
				query.append(";");
			}else{
				query.append(",");
			}
		}
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int inseriu = stmt.executeUpdate(query.toString());
		stmt.close();
		return inseriu;
	}

	@Override
	public boolean excluirFotosItem(Item i) throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int excluiu = stmt.executeUpdate("DELETE FROM foto WHERE id_item="+i.getItemId());
		stmt.close();
		if(excluiu>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterarFotosItem(Item i) throws SQLException {//ok
		//exclui fotos antigas
		excluirFotosItem(i);
		int alterou = 0;
		//inclui fotos fotos novas
		if(i.getFoto().size() > 0){
			alterou = incluirFotosItem(i);
		}
		if(alterou > 0){
			return true;
		}
		return false;
	}
	
}
