package dao.cozinheiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.Cozinheiro;
import bean.Endereco;
import bean.Foto;
import dao.factory.MySqlDAOFactory;

public class MySqlCozinheiroDAO extends MySqlDAOFactory implements
		CozinheiroDAO {
	public static final int TAMANHO_NOME = 38;
	public static final int TAMANHO_SOBRENOME = 78;
	public static final int TAMANHO_DATA_NASCIMENTO = 13;
	public static final int TAMANHO_CPF = 14;
	public static final int TAMANHO_RG = 8;
	public static final int TAMANHO_TELEFONE = 12;
	public static final int TAMANHO_CELULAR = 12;
	public static final int TAMANHO_ESPECIALIDADE = 98;
	public static final int TAMANHO_HISTORICO = 298;
	
	@Override
	public int incluir(Cozinheiro c) throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		int idFoto = 0;
		if(c.getFoto()!=null && 
				c.getFoto().getLocal_foto()!=null && 
				!c.getFoto().getLocal_foto().equalsIgnoreCase("")){
			idFoto = getFotoDAO().incluir(c.getFoto());
		}
		int idEndereco = getEnderecoDAO().incluir(c.getEndereco());
		int resultado = stmt.executeUpdate("INSERT INTO cozinheiro " +
				"(nome,sobrenome,data_nascimento,cpf,rg,telefone,celular," +
				"especialidade,historico,id_foto,endereco_id) " +
				"VALUES ('"+c.getNome()+"','"+c.getSobrenome()+
				"',"+c.getDataNascimento().getTimeInMillis()+
				",'"+c.getCpf()+"','"+c.getRg()+"','"+c.getTelefone()+
				"','"+c.getCelular()+"','"+c.getEspecialidade()+
				"','"+c.getHistorico()+"',"+idFoto+","+idEndereco+")");
		stmt.close();
		return resultado;
	}

	@Override
	public boolean excluir(Cozinheiro c) throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		//exclui foto da tabela foto
		getFotoDAO().excluir(c.getFoto());
		//exclui endereco da tabela endereco
		getEnderecoDAO().excluir(c.getEndereco());
		int resultado = stmt.executeUpdate
				("DELETE FROM cozinheiro WHERE id_cozinheiro="+c.getId());
		if(resultado>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterar(Cozinheiro c) throws SQLException {//OK
		Connection con = getConnection();
		getFotoDAO().alterar(c.getFoto());
		getEnderecoDAO().alterar(c.getEndereco());
		PreparedStatement stmt = con.prepareStatement
				("UPDATE cozinheiro SET nome = ?, sobrenome = ?, " +
						"data_nascimento = ?, cpf = ?, rg = ?, " +
						"telefone = ?, celular = ?, especialidade = ?, " +
						"historico = ?, id_foto = ?, endereco_id = ? " +
						"WHERE id_cozinheiro="+c.getId());
		
		stmt.setString(1, c.getNome());  //nome
        stmt.setString(2, c.getSobrenome());  //sobrenome
        stmt.setLong(3, c.getDataNascimento().getTimeInMillis()); //data_nasc
        stmt.setString(4, c.getCpf()); //cpf
        stmt.setString(5, c.getRg()); //rg
        stmt.setString(6, c.getTelefone());
        stmt.setString(7, c.getCelular());
        stmt.setString(8, c.getEspecialidade());
        stmt.setString(9, c.getHistorico());
        stmt.setInt(10, c.getFoto().getFotoId()); //id_foto
        stmt.setInt(11, c.getEndereco().getEnderecoId()); //id_endereco
        
        int modificou=stmt.executeUpdate();
        stmt.close();
        if(modificou > 0){
        	return true;
        }
		return false;
	}

	@Override
	public Cozinheiro consultarId(Cozinheiro c) throws SQLException {//OK
		Cozinheiro resultado = new Cozinheiro();
		Connection con = getConnection();
		Statement stmt = con.createStatement();		
		ResultSet rs = stmt.executeQuery("SELECT * FROM cozinheiro INNER JOIN endereco" +
				" INNER JOIN foto ON cozinheiro.id_foto = foto.id_foto AND" +
				" cozinheiro.endereco_id = endereco.endereco_id WHERE id_cozinheiro="+
				c.getId());
		while(rs.next()){
			resultado.setId(rs.getInt("id_cozinheiro"));
			resultado.setNome(rs.getString("nome"));
			resultado.setSobrenome(rs.getString("sobrenome"));
			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.setTimeInMillis(rs.getLong("data_nascimento"));
        	resultado.setDataNascimento(dataNascimento);
        	resultado.setCpf(rs.getString("cpf"));
        	resultado.setRg(rs.getString("rg"));
        	resultado.setTelefone(rs.getString("telefone"));
        	resultado.setCelular(rs.getString("celular"));
        	resultado.setEspecialidade(rs.getString("especialidade"));
        	resultado.setHistorico(rs.getString("historico"));
        	resultado.setFoto(new Foto());
        	resultado.getFoto().setFotoId(rs.getInt("id_foto"));
        	resultado.getFoto().setLocal_foto(rs.getString("local_foto"));
        	resultado.setEndereco(new Endereco());
        	resultado.getEndereco().setEnderecoId(rs.getInt("endereco_id"));
        	resultado.getEndereco().setCep(rs.getString("cep"));
        	resultado.getEndereco().setNumero(rs.getString("numero"));
        	resultado.getEndereco().setRua(rs.getString("rua"));
        	resultado.getEndereco().setEstado(rs.getString("estado"));
        	resultado.getEndereco().setCidade(rs.getString("cidade"));
        	resultado.getEndereco().setBairro(rs.getString("bairro")); 	
		}
		return resultado;
	}

	@Override
	public List<Cozinheiro> consultarTitulo(Cozinheiro c) throws SQLException {//OK
		List <Cozinheiro> cozinheiros = new ArrayList<Cozinheiro>();
		Cozinheiro resultado;
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM cozinheiro INNER JOIN endereco" +
				" INNER JOIN foto ON cozinheiro.id_foto = foto.id_foto AND" +
				" cozinheiro.endereco_id = endereco.endereco_id WHERE" +
				" cozinheiro.nome LIKE ('%" +c.getNome()+ "%')");
		
		while(rs.next()){
			resultado = new Cozinheiro();
			resultado.setId(rs.getInt("id_cozinheiro"));
			resultado.setNome(rs.getString("nome"));
			resultado.setSobrenome(rs.getString("sobrenome"));
			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.setTimeInMillis(rs.getLong("data_nascimento"));
        	resultado.setDataNascimento(dataNascimento);
        	resultado.setCpf(rs.getString("cpf"));
        	resultado.setRg(rs.getString("rg"));
        	resultado.setTelefone(rs.getString("telefone"));
        	resultado.setCelular(rs.getString("celular"));
        	resultado.setEspecialidade(rs.getString("especialidade"));
        	resultado.setHistorico(rs.getString("historico"));
        	resultado.setFoto(new Foto());
        	resultado.getFoto().setFotoId(rs.getInt("id_foto"));
        	resultado.getFoto().setLocal_foto(rs.getString("local_foto"));
        	resultado.setEndereco(new Endereco());
        	resultado.getEndereco().setEnderecoId(rs.getInt("endereco_id"));
        	resultado.getEndereco().setCep(rs.getString("cep"));
        	resultado.getEndereco().setNumero(rs.getString("numero"));
        	resultado.getEndereco().setRua(rs.getString("rua"));
        	resultado.getEndereco().setEstado(rs.getString("estado"));
        	resultado.getEndereco().setCidade(rs.getString("cidade"));
        	resultado.getEndereco().setBairro(rs.getString("bairro"));
        	cozinheiros.add(resultado);
		}
		return cozinheiros;
	}

	@Override
	public List<Cozinheiro> listar() throws SQLException {//OK
		List <Cozinheiro> cozinheiros = new ArrayList<Cozinheiro>();
		Cozinheiro resultado;
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM cozinheiro INNER JOIN endereco" +
				" INNER JOIN foto ON cozinheiro.id_foto = foto.id_foto AND" +
				" cozinheiro.endereco_id = endereco.endereco_id;");
		
		while(rs.next()){
			resultado = new Cozinheiro();
			resultado.setId(rs.getInt("id_cozinheiro"));
			resultado.setNome(rs.getString("nome"));
			resultado.setSobrenome(rs.getString("sobrenome"));
			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.setTimeInMillis(rs.getLong("data_nascimento"));
        	resultado.setDataNascimento(dataNascimento);
        	resultado.setCpf(rs.getString("cpf"));
        	resultado.setRg(rs.getString("rg"));
        	resultado.setTelefone(rs.getString("telefone"));
        	resultado.setCelular(rs.getString("celular"));
        	resultado.setEspecialidade(rs.getString("especialidade"));
        	resultado.setHistorico(rs.getString("historico"));
        	resultado.setFoto(new Foto());
        	resultado.getFoto().setFotoId(rs.getInt("id_foto"));
        	resultado.getFoto().setLocal_foto(rs.getString("local_foto"));
        	resultado.setEndereco(new Endereco());
        	resultado.getEndereco().setEnderecoId(rs.getInt("endereco_id"));
        	resultado.getEndereco().setCep(rs.getString("cep"));
        	resultado.getEndereco().setNumero(rs.getString("numero"));
        	resultado.getEndereco().setRua(rs.getString("rua"));
        	resultado.getEndereco().setEstado(rs.getString("estado"));
        	resultado.getEndereco().setCidade(rs.getString("cidade"));
        	resultado.getEndereco().setBairro(rs.getString("bairro"));
        	cozinheiros.add(resultado);
		}
		return cozinheiros;
	}

	@Override
	public void criarTabela() throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS cozinheiro (" +
				"id_cozinheiro INTEGER (7) AUTO_INCREMENT NOT NULL, " +
				"nome VARCHAR (40) NOT NULL, " +
				"sobrenome VARCHAR (80) NOT NULL, " +
				"data_nascimento BIGINT (13) NOT NULL, " +
				"cpf VARCHAR (16) NOT NULL, " +
				"rg VARCHAR (10) NOT NULL, " +
				"telefone VARCHAR (14) NOT NULL, " +
				"celular VARCHAR (14) NOT NULL, " +
				"especialidade VARCHAR (100) NOT NULL, " +
				"historico VARCHAR (300) NOT NULL, " +
				"id_foto INTEGER (7) NOT NULL, " +
				"endereco_id INTEGER (7) NOT NULL, " +
				"PRIMARY KEY (id_cozinheiro), " +
				"FOREIGN KEY (id_foto) REFERENCES foto (id_foto), " +
				"FOREIGN KEY (endereco_id) REFERENCES endereco (endereco_id));");
	}

}
