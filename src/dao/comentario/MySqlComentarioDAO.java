package dao.comentario;

import java.sql.SQLException;
import java.util.List;

import bean.Comentario;
import dao.factory.MySqlDAOFactory;

public class MySqlComentarioDAO extends MySqlDAOFactory implements
		ComentarioDAO {

	@Override
	public int incluir(Comentario c) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exluir(Comentario c) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Comentario c) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Comentario consultarId(Comentario c) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comentario> consultarTitulo(Comentario c) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comentario> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela() throws SQLException {
		// TODO Auto-generated method stub

	}

}
