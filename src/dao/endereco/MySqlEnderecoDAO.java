package dao.endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Endereco;
import dao.factory.MySqlDAOFactory;

public class MySqlEnderecoDAO extends MySqlDAOFactory implements EnderecoDAO {

	@Override
	public int incluir(Endereco e) throws SQLException {//ok
		Connection con = getConnection();
        Statement stmt =  con.createStatement();
        stmt.executeUpdate("INSERT INTO endereco " +
        				"(cep, numero, rua, estado," +
        				" cidade,bairro) " +
        				"VALUES ('"+e.getCep()+"','"
        				+e.getNumero()+"','"+e.getRua()+"','"
        				+e.getEstado()+"','"+e.getCidade()+"','"
        				+e.getBairro()+"')",Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        int idRecemInserido = 0;
        while (rs.next()){
        	idRecemInserido = rs.getInt(1);
        }
        stmt.close();
        con.close();
        return idRecemInserido;
	}

	@Override
	public boolean excluir(Endereco e) throws SQLException {
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		int resultado = stmt.executeUpdate("DELETE FROM endereco WHERE endereco_id=" 
							+e.getEnderecoId());
		stmt.close();
		con.close();
		if(resultado==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterar(Endereco e) throws SQLException {
		Connection con = getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE endereco SET cep = ?," +
                " numero = ?, rua = ?, estado = ?, cidade = ?, bairro = ? " +
                " WHERE endereco_id = ?");
        stmt.setString(1, e.getCep());  //cep
        stmt.setString(2, e.getNumero());  //numero
        stmt.setString(3, e.getRua()); //rua
        stmt.setString(4, e.getEstado());//estado
        stmt.setString(5, e.getCidade());//cidade
        stmt.setString(6, e.getBairro());//bairro
        stmt.setInt(7, e.getEnderecoId());//endereco_id
        
        int modificou=stmt.executeUpdate();
        stmt.close();
        con.close();
        if(modificou==1){
            return true;
        }
        return false;
	}

	@Override
	public Endereco consultarId(Endereco e) throws SQLException {
		Endereco resultado = new Endereco();           
        Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM endereco WHERE endereco_id='" +e.getEnderecoId()+ "'");
        while (rs.next()){
        	resultado.setEnderecoId(rs.getInt("endereco_id"));
            resultado.setCep(rs.getString("cep"));
            resultado.setNumero(rs.getString("numero"));
            resultado.setRua(rs.getString("rua"));
            resultado.setEstado(rs.getString("estado"));
            resultado.setCidade(rs.getString("cidade"));
            resultado.setBairro(rs.getString("bairro"));
        }
        stmt.close();
        con.close();
        return resultado;
	}

	@Override
	public List<Endereco> listar() throws SQLException {
		Endereco resultado;
		List <Endereco> enderecos = new ArrayList<Endereco>();
		Connection con = getConnection();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM endereco");
        while (rs.next()){
        	resultado = new Endereco();
        	resultado.setEnderecoId(rs.getInt("endereco_id"));
            resultado.setCep(rs.getString("cep"));
            resultado.setNumero(rs.getString("numero"));
            resultado.setRua(rs.getString("rua"));
            resultado.setEstado(rs.getString("estado"));
            resultado.setCidade(rs.getString("cidade"));
            resultado.setBairro(rs.getString("bairro"));
            enderecos.add(resultado);
        }
        stmt.close();
        con.close();
        return enderecos;
	}

	@Override
	public void criarTabela() throws SQLException {
		Connection con = getConnection();
		Statement stmt =  con.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS endereco (" +
                " endereco_id INTEGER (7) AUTO_INCREMENT NOT NULL," +
                " cep VARCHAR (10)," +
                " numero VARCHAR (10)," +
                " rua VARCHAR (60)," +
                " estado VARCHAR (2)," +
                " cidade VARCHAR (60)," +
                " bairro VARCHAR (60)," +
                " PRIMARY KEY (endereco_id))");
		stmt.close();
		con.close();
	}

}
