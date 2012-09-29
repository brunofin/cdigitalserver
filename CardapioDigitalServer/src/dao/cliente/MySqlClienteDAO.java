package dao.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.Cliente;
import bean.Endereco;
import dao.endereco.EnderecoDAO;
import dao.factory.ConexaoSingleton;
import dao.factory.DAOFactory;
import dao.factory.Database;
import dao.factory.MySqlDAOFactory;

public class MySqlClienteDAO extends MySqlDAOFactory implements ClienteDAO {
	private DAOFactory factory;
	private EnderecoDAO enderecoDAO;

	@Override
	public int incluir(Cliente c) throws SQLException {//TODO testar
//		factory = DAOFactory.getDaoFactory(Database.MYSQL);
//		enderecoDAO = factory.getEnderecoDAO();
		//insere novo endereço e seta id no atributo endereço do Cliente
//		c.getEndereco().setEnderecoId(enderecoDAO.incluir(c.getEndereco()));
		
		Connection con = ConexaoSingleton.getConexao();
		con.setAutoCommit(false);
		factory = DAOFactory.getDaoFactory(Database.MYSQL);
		enderecoDAO = factory.getEnderecoDAO();
		//seta o novo id
		c.getEndereco().setEnderecoId(enderecoDAO.incluir(c.getEndereco()));
		
		
        Statement stmt =  con.createStatement();
        int resultado = stmt.executeUpdate("INSERT INTO cliente " +
        				"(nome,sobrenome,data_nascimento,email,cpf," +
        				"rg,endereco_id) " +
        				"VALUES ('"+c.getNome()+
        				"','"+c.getSobrenome()+
        				"','"+c.getDataNascimento().getTimeInMillis()+
        				"','"+c.getEmail()+
        				"','"+c.getCpf()+
        				"','"+c.getRg()+
        				"','"+c.getEndereco().getEnderecoId()+"')");
        con.commit();
        con.setAutoCommit(true);
        stmt.close();
        //con.close();
        return resultado;
	}

	@Override
	public boolean excluir(Cliente c) throws SQLException {
		//Connection con = getConnection();
		factory = DAOFactory.getDaoFactory(Database.MYSQL);
		enderecoDAO = factory.getEnderecoDAO();
		//exclui endereço do cliente que sera deletado
		Connection con = ConexaoSingleton.getConexao();
		//TODO talvez possa dexa o getConexao() dentro do MySqlDAOFactory
		//Connection con = MySqlDAOFactory.getConexao();
		con.setAutoCommit(false);
		enderecoDAO.excluir(c.getEndereco());
		
		Statement stmt =  con.createStatement();
		int resultado = stmt.executeUpdate("DELETE FROM cliente WHERE id_cliente=" 
							+c.getId());
		stmt.close();
		con.commit();
        con.setAutoCommit(true);
		//con.close();
		if(resultado==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterar(Cliente c) throws SQLException {
		factory = DAOFactory.getDaoFactory(Database.MYSQL);
		enderecoDAO = factory.getEnderecoDAO();
		Connection con = ConexaoSingleton.getConexao();
		con.setAutoCommit(false);
		//altera dados do endereço de um cliente editado;
		enderecoDAO.alterar(c.getEndereco());
		//Connection con = getConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE cliente SET nome = ?," +
                " sobrenome = ?, data_nascimento = ?, email = ?," +
                " cpf = ?, rg = ?, endereco_id = ? WHERE id_cliente = ?");
        stmt.setString(1, c.getNome());  //nome
        stmt.setString(2, c.getSobrenome());  //sobrenome
        stmt.setLong(3, c.getDataNascimento().getTimeInMillis()); //data_nasc//FIXME ver se é assim mesmo
        stmt.setString(4, c.getEmail()); //email
        stmt.setString(5, c.getCpf()); //cpf
        stmt.setString(6, c.getRg()); //rg
        stmt.setInt(7, c.getEndereco().getEnderecoId()); //endereco_id
        stmt.setInt(8, c.getId()); //id_cliente
        
        int modificou=stmt.executeUpdate();
        stmt.close();
        con.commit();
        con.setAutoCommit(true);
        //con.close();
        if(modificou==1){
            return true;
        }
        return false;
	}
	
	@Override
	public Cliente consultarId(Cliente c) throws SQLException {
		Cliente resultado = new Cliente();           
        //Connection con = getConnection();
		Connection con = ConexaoSingleton.getConexao();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        //rs = stmt.executeQuery("SELECT * FROM cliente WHERE id_cliente="+c.getId());//query q só busca cliente;
        rs = stmt.executeQuery("SELECT * FROM cliente INNER JOIN endereco ON" +
        		" cliente.endereco_id=endereco.endereco_id WHERE " +
        		"cliente.id_cliente = "+c.getId());
        while (rs.next()){
        	resultado.setId(rs.getInt("id_cliente"));
        	resultado.setNome(rs.getString("nome"));
        	resultado.setSobrenome(rs.getString("sobrenome"));
        	Calendar dataNascimento = Calendar.getInstance();
        	dataNascimento.setTimeInMillis(rs.getLong("data_nascimento"));
        	resultado.setDataNascimento(dataNascimento);
        	resultado.setEmail(rs.getString("email"));
        	resultado.setCpf(rs.getString("cpf"));
        	resultado.setRg(rs.getString("rg"));
        	resultado.setEndereco(new Endereco());
        	resultado.getEndereco().setEnderecoId(rs.getInt("endereco_id"));
        	resultado.getEndereco().setCep(rs.getString("cep"));
        	resultado.getEndereco().setNumero(rs.getString("numero"));
        	resultado.getEndereco().setRua(rs.getString("rua"));
        	resultado.getEndereco().setEstado(rs.getString("estado"));
        	resultado.getEndereco().setCidade(rs.getString("cidade"));
        	resultado.getEndereco().setBairro(rs.getString("bairro"));
        }
        stmt.close();
        //con.close();
        return resultado;
	}

	@Override
	public List<Cliente> consultarTitulo(Cliente c) throws SQLException {
		Cliente resultado;
		List <Cliente> clientes = new ArrayList<Cliente>();
		//Connection con = getConnection();
		Connection con = ConexaoSingleton.getConexao();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        rs = stmt.executeQuery("SELECT * FROM cliente INNER JOIN endereco ON" +
        		" cliente.endereco_id=endereco.endereco_id WHERE" +
        		" cliente.nome LIKE ('%" +c.getNome()+ "%')");
        while (rs.next()){
        	resultado = new Cliente();
        	resultado.setId(rs.getInt("id_cliente"));
        	resultado.setNome(rs.getString("nome"));
        	resultado.setSobrenome(rs.getString("sobrenome"));
        	Calendar dataNascimento = Calendar.getInstance();//TODO testar isso
        	dataNascimento.setTimeInMillis(rs.getLong("data_nascimento"));
        	resultado.setDataNascimento(dataNascimento);
        	resultado.setEmail(rs.getString("email"));
        	resultado.setCpf(rs.getString("cpf"));
        	resultado.setRg(rs.getString("rg"));
        	resultado.setEndereco(new Endereco());
        	resultado.getEndereco().setEnderecoId(rs.getInt("endereco_id"));
        	resultado.getEndereco().setCep(rs.getString("cep"));
        	resultado.getEndereco().setNumero(rs.getString("numero"));
        	resultado.getEndereco().setRua(rs.getString("rua"));
        	resultado.getEndereco().setEstado(rs.getString("estado"));
        	resultado.getEndereco().setCidade(rs.getString("cidade"));
        	resultado.getEndereco().setBairro(rs.getString("bairro"));
            clientes.add(resultado);
        }
        stmt.close();
        //con.close();
        return clientes;
	}

	@Override
	public List<Cliente> listar() throws SQLException {
		Cliente resultado;
		List <Cliente> clientes = new ArrayList<Cliente>();
		//Connection con = getConnection();
		Connection con = ConexaoSingleton.getConexao();
        ResultSet rs = null;
        Statement stmt =  con.createStatement();
        //select * from cliente inner join endereco on cliente.endereco_id = endereco.endereco_id;
        rs = stmt.executeQuery("SELECT * FROM cliente INNER JOIN endereco ON cliente.endereco_id=endereco.endereco_id;");
        while (rs.next()){
        	resultado = new Cliente();
        	resultado.setId(rs.getInt("id_cliente"));
        	resultado.setNome(rs.getString("nome"));
        	resultado.setSobrenome(rs.getString("sobrenome"));
        	Calendar dataNascimento = Calendar.getInstance();//TODO testar isso
        	dataNascimento.setTimeInMillis(rs.getLong("data_nascimento"));
        	resultado.setDataNascimento(dataNascimento);
        	resultado.setEmail(rs.getString("email"));
        	resultado.setCpf(rs.getString("cpf"));
        	resultado.setRg(rs.getString("rg"));
        	resultado.setEndereco(new Endereco());
        	resultado.getEndereco().setEnderecoId(rs.getInt("endereco_id"));
        	resultado.getEndereco().setCep(rs.getString("cep"));
        	resultado.getEndereco().setNumero(rs.getString("numero"));
        	resultado.getEndereco().setRua(rs.getString("rua"));
        	resultado.getEndereco().setEstado(rs.getString("estado"));
        	resultado.getEndereco().setCidade(rs.getString("cidade"));
        	resultado.getEndereco().setBairro(rs.getString("bairro"));
            clientes.add(resultado);
        }
        stmt.close();
        //con.close();
        return clientes;
	}

	@Override
	public void criarTabela() throws SQLException {
		Connection con = ConexaoSingleton.getConexao();
		//Connection con = getConnection();
		Statement stmt = con.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS cliente (" +
				"id_cliente INTEGER (7) AUTO_INCREMENT NOT NULL," +
				"nome VARCHAR (50) NOT NULL," +
				"sobrenome VARCHAR (80)," +
				"data_nascimento BIGINT (13)," +
				"email VARCHAR (40) NOT NULL," +
				"cpf VARCHAR (16)," +
				"rg VARCHAR (10)," +
				"endereco_id INTEGER (7)," +
				"PRIMARY KEY (id_cliente)," +
				"FOREIGN KEY (endereco_id) REFERENCES endereco (endereco_id))");
		stmt.close();
		//con.close();
	}

}
