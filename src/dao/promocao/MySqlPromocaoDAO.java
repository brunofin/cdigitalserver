package dao.promocao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.Foto;
import bean.Promocao;
import dao.factory.MySqlDAOFactory;

public class MySqlPromocaoDAO extends MySqlDAOFactory implements PromocaoDAO {

	@Override
	public int incluir(Promocao p) throws SQLException {//OK
		int idFoto = 0;
		if(p.getFoto()!=null 
				&& !p.getFoto().getLocal_foto().equalsIgnoreCase("")){
			idFoto = getFotoDAO().incluir(p.getFoto());
		}
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		stmt.executeUpdate("INSERT INTO promocao " +
				"(nome, data_inicio, validade, descricao, id_foto) " +
				"VALUES ('"+p.getNome()+
				"',"+p.getDataInicio().getTimeInMillis()+
				","+p.getValidade().getTimeInMillis()+
				",'"+p.getDescricao()+
				"',"+idFoto+")", Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
		while(rs.next()){
			p.setPromocaoId(rs.getInt(1));
		}
		if(p.getPromocaoId()==0){
			return 0;
		}
		int inseriu = getItemPromocaoDAO().inserirItensPromocao(p);
		return inseriu;
	}

	@Override
	public boolean excluir(Promocao p) throws SQLException {//OK
		//exlui itens promocao
		getItemPromocaoDAO().excluirItensPromocao(p);
		//exclui foto promocao
		getFotoDAO().excluir(p.getFoto());
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		//exclui restante dos dados da promocao
		int excluiu = stmt.executeUpdate
				("DELETE FROM promocao WHERE id_promocao="+p.getPromocaoId());
		if(excluiu > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean alterar(Promocao p) throws SQLException {//OK
		//altera itens da promocao
		getItemPromocaoDAO().alterarItensPromocao(p);
		//altera foto
		getFotoDAO().alterar(p.getFoto());
		//altera restante dos dados da promocao
		Connection con = getConnection();
		PreparedStatement stmt = con.prepareStatement("UPDATE promocao SET nome = ?," +
                " data_inicio = ?, validade = ?, descricao = ?" +
                " WHERE id_promocao = ?");
		stmt.setString(1, p.getNome());  //nome
        stmt.setLong(2, p.getDataInicio().getTimeInMillis());  //data_inicio
        stmt.setLong(3, p.getValidade().getTimeInMillis()); //validade
        stmt.setString(4, p.getDescricao()); //descricao
        stmt.setInt(5, p.getPromocaoId());//id promoção
        
        int alterou = stmt.executeUpdate();
        stmt.close();
        if(alterou > 0){
        	return true;
        }
		return false;
	}

	@Override
	public Promocao consultarId(Promocao p) throws SQLException {//OK
		Promocao resultado = new Promocao();
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery
		("SELECT * FROM promocao WHERE id_promocao="+p.getPromocaoId());
		while (rs.next()){
			resultado.setPromocaoId(rs.getInt("id_promocao"));
			resultado.setNome(rs.getString("nome"));
			Calendar dataInicio = Calendar.getInstance();
			dataInicio.setTimeInMillis(rs.getLong("data_inicio"));
			resultado.setDataInicio(dataInicio);
			Calendar validade = Calendar.getInstance();
			validade.setTimeInMillis(rs.getLong("validade"));
			resultado.setValidade(validade);
			resultado.setDescricao(rs.getString("descricao"));
			resultado.setFoto(new Foto());
			resultado.getFoto().setFotoId(rs.getInt("id_foto"));
			resultado.setFoto(getFotoDAO().consultarId(resultado.getFoto()));
			resultado.setItens(getItemPromocaoDAO().consultarItensPromocao(p));
		}
		rs.close();
		stmt.close();
		return resultado;
	}

	@Override
	public List<Promocao> consultarTitulo(Promocao p) throws SQLException {//OK
		List <Promocao> promocoes = new ArrayList<Promocao>();
		Promocao promocao;
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery
				("SELECT * FROM promocao WHERE nome LIKE ('%"+p.getNome()+"%')");
		while (rs.next()){
			promocao = new Promocao();
			promocao.setPromocaoId(rs.getInt("id_promocao"));
			promocao.setNome(rs.getString("nome"));
			Calendar dataInicio = Calendar.getInstance();
			dataInicio.setTimeInMillis(rs.getLong("data_inicio"));
			promocao.setDataInicio(dataInicio);
			Calendar validade = Calendar.getInstance();
			validade.setTimeInMillis(rs.getLong("validade"));
			promocao.setValidade(validade);
			promocao.setDescricao(rs.getString("descricao"));
			promocao.setFoto(new Foto());
			promocao.getFoto().setFotoId(rs.getInt("id_foto"));
			promocao.setFoto(getFotoDAO().consultarId(promocao.getFoto()));
			promocao.setItens(getItemPromocaoDAO().consultarItensPromocao(promocao));
			promocoes.add(promocao);
		}
		return promocoes;
	}

	@Override
	public List<Promocao> listar() throws SQLException {//OK
		List <Promocao> promocoes = new ArrayList<Promocao>();
		Promocao promocao;
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery
				("SELECT * FROM promocao");
		while (rs.next()){
			promocao = new Promocao();
			promocao.setPromocaoId(rs.getInt("id_promocao"));
			promocao.setNome(rs.getString("nome"));
			Calendar dataInicio = Calendar.getInstance();
			dataInicio.setTimeInMillis(rs.getLong("data_inicio"));
			promocao.setDataInicio(dataInicio);
			Calendar validade = Calendar.getInstance();
			validade.setTimeInMillis(rs.getLong("validade"));
			promocao.setValidade(validade);
			promocao.setDescricao(rs.getString("descricao"));
			promocao.setFoto(new Foto());
			promocao.getFoto().setFotoId(rs.getInt("id_foto"));
			promocao.setFoto(getFotoDAO().consultarId(promocao.getFoto()));
			promocao.setItens(getItemPromocaoDAO().consultarItensPromocao(promocao));
			promocoes.add(promocao);
		}
		rs.close();
		stmt.close();
		return promocoes;
	}

	@Override
	public void criarTabela() throws SQLException {//OK
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS promocao (" +
				"id_promocao INTEGER (7) AUTO_INCREMENT NOT NULL, " +
				"nome VARCHAR (60), " +
				"data_inicio BIGINT (13) NOT NULL," +
				"validade BIGINT (13), " +
				"descricao VARCHAR (300) NOT NULL, " +
				"id_foto INTEGER (7), " +
				"PRIMARY KEY (id_promocao), " +
				"FOREIGN KEY (id_foto) REFERENCES foto (id_foto))");
	}

}
