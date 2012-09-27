package dao.categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Categoria;
import bean.Tipo;
import dao.factory.MySqlDAOFactory;

public class MySqlCategoriaDAO extends MySqlDAOFactory implements CategoriaDAO {

	@Override
	public int incluir(Categoria c) throws SQLException {
		Connection con = getConnection();
        Statement stmt =  con.createStatement();
        int resultado = stmt.executeUpdate("INSERT INTO categoria " +
        				"(id_categoria,nome,descricao,tipo_id) " +
        				"VALUES ("+c.getCategoriaId()+",'"
        				+c.getNome()+"','"+c.getDescricao()+"',"
        				+c.getTipo().getTipoId()+")");
        stmt.close();
        con.close();
        return resultado;
	}

	@Override
	public boolean exluir(Categoria c) throws SQLException {
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		int resultado = stmt.executeUpdate("DELETE FROM categoria WHERE id_categoria=" 
							+c.getCategoriaId());
		stmt.close();
		con.close();
		if(resultado==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterar(Categoria c) throws SQLException {
		Connection con = getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE categoria SET nome = ?," +
                " descricao = ?, tipo_id= ? where id_categoria = ?");
        stmt.setString(1, c.getNome());  //nome
        stmt.setString(2, c.getDescricao());  //desc
        stmt.setInt(3, c.getTipo().getTipoId()); //tipo_id
        stmt.setInt(4, c.getCategoriaId());//categoria_id
        
        int modificou=stmt.executeUpdate();
        stmt.close();
        con.close();
        if(modificou==1){
            return true;
        }
        return false;
	}

	@Override
	public Categoria consultarId(Categoria c) throws SQLException {
		Categoria resultado = new Categoria();           
        Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        //rs = stmt.executeQuery("SELECT * FROM categoria WHERE id_categoria = '" + c.getCategoriaId() +"'"); SEM JOIN
        rs = stmt.executeQuery("SELECT c.id_categoria, c.nome," +
        		" c.descricao, c.tipo_id, t.nome as nome_tipo FROM categoria as c " +
        		" INNER JOIN tipo as t WHERE c.tipo_id=t.tipo_id" +
        		" AND c.id_categoria='" +c.getCategoriaId()+ "'");
        while (rs.next()){
        	resultado.setCategoriaId(rs.getInt("id_categoria"));
            resultado.setNome(rs.getString("nome"));
            resultado.setDescricao(rs.getString("descricao"));
            Tipo t = new Tipo();
            t.setTipoId(rs.getInt("tipo_id"));
            t.setNome(rs.getString("nome_tipo"));//TODO verificar se isso ta certo
            resultado.setTipo(t);
        }
        stmt.close();
        con.close();
        return resultado;
	}

	@Override
	public List<Categoria> consultarTitulo(Categoria c) throws SQLException {//OK
		Categoria resultado;
		List <Categoria> categorias = new ArrayList<Categoria>();
		Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        //rs = stmt.executeQuery("SELECT * FROM categoria WHERE nome = '" + c.getNome()+ "'");//sem JOIN com tipo
        rs = stmt.executeQuery("SELECT c.id_categoria, c.nome," +
        		" c.descricao, c.tipo_id, t.nome as nome_tipo FROM categoria as c " +
        		" INNER JOIN tipo as t WHERE c.tipo_id=t.tipo_id" +
        		" AND c.nome LIKE ('%" +c.getNome()+ "%')");
        while (rs.next()){
        	resultado = new Categoria();
        	resultado.setCategoriaId(rs.getInt("id_categoria"));
            resultado.setNome(rs.getString("nome"));
            resultado.setDescricao(rs.getString("descricao"));
            Tipo t = new Tipo();
            t.setTipoId(rs.getInt("tipo_id"));
            t.setNome(rs.getString("nome_tipo"));
            resultado.setTipo(t);
            categorias.add(resultado);
        }
        stmt.close();
        con.close();
        return categorias;
	}

	@Override
	public List<Categoria> listar() throws SQLException {//OK
		Categoria resultado;
		List <Categoria> categorias = new ArrayList<Categoria>();
		Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        rs = stmt.executeQuery("SELECT c.id_categoria, c.nome," +
        		" c.descricao, c.tipo_id, t.nome as nome_tipo FROM categoria as c " +
        		" INNER JOIN tipo as t WHERE c.tipo_id=t.tipo_id;");
        while (rs.next()){
        	resultado = new Categoria();
        	resultado.setCategoriaId(rs.getInt("id_categoria"));
            resultado.setNome(rs.getString("nome"));
            resultado.setDescricao(rs.getString("descricao"));
            Tipo t = new Tipo();
            t.setTipoId(rs.getInt("tipo_id"));
            t.setNome(rs.getString("nome_tipo"));
            resultado.setTipo(t);
            categorias.add(resultado);
        }
        stmt.close();
        con.close();
        return categorias;
	}

	@Override
	public void criarTabela() throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS categoria (" +
                " id_categoria INTEGER (7) AUTO_INCREMENT NOT NULL," +
                " nome VARCHAR (40) NOT NULL," +
                " descricao VARCHAR (60)," +
                " tipo_id INTEGER (7) NOT NULL," +
                " PRIMARY KEY (id_categoria)," +
                " FOREIGN KEY (tipo_id) REFERENCES tipo (tipo_id))");
		stmt.close();
		con.close();
	}

}
