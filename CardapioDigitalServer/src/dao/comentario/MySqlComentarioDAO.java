package dao.comentario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.Cliente;
import bean.Comentario;
import bean.Item;
import dao.factory.MySqlDAOFactory;

public class MySqlComentarioDAO extends MySqlDAOFactory implements
		ComentarioDAO {

	@Override
	public int incluir(Comentario c) throws SQLException {//OK
		Connection con = getConnection();
        Statement stmt =  con.createStatement();
        int resultado = stmt.executeUpdate("INSERT INTO comentario " +
        				"(id_comentario,data,comentario,id_item,id_cliente) " +
        				"VALUES ("+c.getComentarioId()+","
        				+c.getData().getTimeInMillis()+
        				",'"+c.getComentario()+
        				"',"+c.getItem().getItemId()+
        				","+c.getCliente().getId()+")");
        stmt.close();
        con.close();
        return resultado;
	}

	@Override
	public boolean excluir(Comentario c) throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		int resultado = stmt.executeUpdate("DELETE FROM comentario WHERE id_comentario=" 
							+c.getComentarioId());
		stmt.close();
		con.close();
		if(resultado==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterar(Comentario c) throws SQLException {//OK
		Connection con = getConnection();
        PreparedStatement stmt = con.prepareStatement
        		("UPDATE comentario SET comentario = ?" +
                " WHERE id_comentario = ?");
        stmt.setString(1, c.getComentario());
        stmt.setInt(2, c.getComentarioId()); 
        
        int modificou=stmt.executeUpdate();
        stmt.close();
        //con.close();
        if(modificou==1){
            return true;
        }
        return false;
	}

	@Override
	public List<Comentario> listar() throws SQLException {//OK
		Comentario resultado = null;
		List <Comentario> comentarios = new ArrayList<Comentario>();
		Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        rs = stmt.executeQuery("SELECT id_comentario, data, comentario FROM comentario;");
        while (rs.next()){
        	resultado = new Comentario();
        	resultado.setComentarioId(rs.getInt("id_comentario"));
        	Calendar dataComentario = Calendar.getInstance();
        	dataComentario.setTimeInMillis(rs.getLong("data"));
        	resultado.setData(dataComentario);
        	resultado.setComentario(rs.getString("comentario"));
        	
            comentarios.add(resultado);
        }
        stmt.close();
        //con.close();
        return comentarios;
	}

	@Override
	public void criarTabela() throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS comentario (" +
                " id_comentario INTEGER (7) AUTO_INCREMENT NOT NULL," +
                " data BIGINT (13) NOT NULL," +
                " comentario VARCHAR (160) NOT NULL," +
                " id_item INTEGER (7) NOT NULL," +
                " id_cliente INTEGER (7) NOT NULL," +
                " PRIMARY KEY (id_comentario)," +
                " FOREIGN KEY (id_item) REFERENCES item (id_item)," +
                " FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente))");
		stmt.close();
		//con.close();
	}

	@Override
	public List<Comentario> consultarComentariosItem(Comentario c) throws SQLException {//ok
		Comentario resultado = null;
		List <Comentario> comentarios = new ArrayList<Comentario>();
		Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        rs = stmt.executeQuery("SELECT co.id_comentario, " +
        		"co.data, co.comentario, co.id_item, co.id_cliente, c.nome" +
        		" FROM comentario AS co INNER JOIN cliente AS c WHERE " +
        		"co.id_cliente=c.id_cliente AND" +
        		" co.id_item = "+c.getItem().getItemId());
        while (rs.next()){
        	resultado = new Comentario();
        	resultado.setComentarioId(rs.getInt("id_comentario"));
        	Calendar dataComentario = Calendar.getInstance();
        	dataComentario.setTimeInMillis(rs.getLong("data"));
        	resultado.setData(dataComentario);
        	resultado.setComentario(rs.getString("comentario"));
        	resultado.setItem(new Item());
        	resultado.getItem().setItemId(rs.getInt("id_item"));
        	resultado.setCliente(new Cliente());
        	resultado.getCliente().setId(rs.getInt("id_cliente"));
        	resultado.getCliente().setNome(rs.getString("nome"));
        	
            comentarios.add(resultado);
        }
        stmt.close();
        con.close();
        return comentarios;
	}

	@Override
	public List<Comentario> consultarComentariosCliente(Comentario c)
			throws SQLException {//OK
		Comentario resultado = null;
		List <Comentario> comentarios = new ArrayList<Comentario>();
		Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        //rs = stmt.executeQuery("SELECT * FROM comentario WHERE id_cliente="+c.getCliente().getId());
        rs = stmt.executeQuery("SELECT co.id_comentario, co.data, co.comentario, i.nome AS nome_do_item FROM" +
        		" comentario AS co INNER JOIN item AS i ON co.id_item=i.id_item WHERE co.id_cliente = "+c.getCliente().getId());
        		
        while (rs.next()){
        	resultado = new Comentario();
        	resultado.setComentarioId(rs.getInt("id_comentario"));
        	Calendar dataComentario = Calendar.getInstance();
        	dataComentario.setTimeInMillis(rs.getLong("data"));
        	resultado.setData(dataComentario);
        	resultado.setComentario(rs.getString("comentario"));
        	resultado.setItem(new Item());
        	resultado.getItem().setNome(rs.getString("nome_do_item"));
        	
            comentarios.add(resultado);
        }
        
        return comentarios;
	}

	@Override
	public boolean excluirComentariosClienteExcluido(Comentario c)
			throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		int resultado = stmt.executeUpdate("DELETE FROM comentario WHERE id_cliente=" 
							+c.getCliente().getId());
		stmt.close();
		con.close();
		if(resultado>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean excluirTodosComentariosDeItemExcluido(Item i)
			throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		int resultado = stmt.executeUpdate("DELETE FROM comentario WHERE id_item=" 
							+i.getItemId());
		stmt.close();
		if(resultado>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterarComentariosItemEditado(int idItem, List <Comentario> comentariosAtualizados) throws SQLException {//OK
		Item i = new Item();
		i.setItemId(idItem);
		//deleta comentarios antigos da tabela de comentarios
		excluirTodosComentariosDeItemExcluido(i);
		//insere lista de comentarios alteranovamente
		if(comentariosAtualizados.size() > 0){
			return inserirComentariosAtualizadosItemAtualizado(idItem, comentariosAtualizados);
		}
		return false;
	}
	/**
	 * Insere comentarios atualizados quando
	 * um item é editado
	 * 
	 * @param comentariosAtualizados, lista dos 
	 * comentarios para serem inseridos
	 * @return true se inseriu
	 * @throws SQLException 
	 */
	private boolean inserirComentariosAtualizadosItemAtualizado
	(int idItem, List <Comentario> comentariosAtualizados) throws SQLException {//OK
		StringBuffer query = 
				new StringBuffer("INSERT INTO comentario (data, comentario, id_item, id_cliente) VALUES ");
		for(Comentario c : comentariosAtualizados){
			query.append("("+c.getData().getTimeInMillis()+",'"+c.getComentario()+
					"',"+c.getItem().getItemId()+","+c.getCliente().getId()+")");
			//caso seja o ultimo elemento da lista concatena ";" no fim da query;
			if(c==comentariosAtualizados.get(comentariosAtualizados.size()-1)){
				query.append(";");
			}else{
				query.append(",");
			}
		}
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int inseriu = stmt.executeUpdate(query.toString());
		stmt.close();
		if(inseriu > 0){
			return true;
		}
		return false;
	}
	

}
